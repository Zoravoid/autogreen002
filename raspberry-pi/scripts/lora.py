# lora.py  -- fixed minimal SX127x driver for MicroPython (RFM9x/RFM96W)
from machine import Pin, SPI
import time

# LoRa registers (subset used)
REG_FIFO = 0x00
REG_OP_MODE = 0x01
REG_FRF_MSB = 0x06
REG_FRF_MID = 0x07
REG_FRF_LSB = 0x08
REG_PA_CONFIG = 0x09
REG_LNA = 0x0C
REG_FIFO_ADDR_PTR = 0x0D
REG_FIFO_TX_BASE_ADDR = 0x0E
REG_FIFO_RX_BASE_ADDR = 0x0F
REG_FIFO_RX_CURRENT_ADDR = 0x10
REG_IRQ_FLAGS = 0x12
REG_RX_NB_BYTES = 0x13
REG_PKT_RSSI = 0x1A
REG_MODEM_CONFIG_1 = 0x1D
REG_MODEM_CONFIG_2 = 0x1E
REG_PAYLOAD_LENGTH = 0x22

# Modes
MODE_LONG_RANGE_MODE = 0x80
MODE_SLEEP = 0x00
MODE_STDBY = 0x01
MODE_TX = 0x83
MODE_RXCONTINUOUS = 0x85

class LoRa:
    def __init__(self, spi, cs, rst, frequency=433000000):
        self.spi = spi
        self.cs = Pin(cs, Pin.OUT, value=1)
        self.rst = Pin(rst, Pin.OUT, value=1)

        # reset module
        self.reset()
        time.sleep(0.05)

        # Put into sleep + LoRa mode
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_SLEEP)
        time.sleep(0.05)

        # Set frequency (implemented here)
        self.set_freq(frequency)

        # Basic modem config (these are reasonable defaults)
        self.write_reg(REG_MODEM_CONFIG_1, 0x72)
        self.write_reg(REG_MODEM_CONFIG_2, 0x74)

        # PA + LNA
        self.write_reg(REG_PA_CONFIG, 0x8F)   # max power
        self.write_reg(REG_LNA, 0x23)

        # FIFO base addresses
        self.write_reg(REG_FIFO_TX_BASE_ADDR, 0x00)
        self.write_reg(REG_FIFO_RX_BASE_ADDR, 0x00)

        # Start in continuous RX
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_RXCONTINUOUS)
        time.sleep(0.05)

    def reset(self):
        self.rst.value(0)
        time.sleep(0.01)
        self.rst.value(1)
        time.sleep(0.05)

    def write_reg(self, address, value):
        # write a single register
        self.cs.value(0)
        self.spi.write(bytearray([address | 0x80, value & 0xFF]))
        self.cs.value(1)

    def read_reg(self, address):
        # read a single register
        self.cs.value(0)
        self.spi.write(bytearray([address & 0x7F]))
        res = self.spi.read(1)[0]
        self.cs.value(1)
        return res

    def set_freq(self, frequency):
        # frequency in Hz, convert to FRF register (SX127x formula)
        # FRF = frequency * 2^19 / 32e6
        frf = int((frequency << 19) // 32000000)
        self.write_reg(REG_FRF_MSB, (frf >> 16) & 0xFF)
        self.write_reg(REG_FRF_MID, (frf >> 8) & 0xFF)
        self.write_reg(REG_FRF_LSB, frf & 0xFF)
        time.sleep(0.01)

    def send(self, data):
        # tx: put in standby, write payload, set tx, wait for done, back to rx
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_STDBY)
        time.sleep(0.01)

        # reset FIFO ptr
        self.write_reg(REG_FIFO_ADDR_PTR, 0x00)
        self.write_reg(REG_PAYLOAD_LENGTH, len(data) & 0xFF)

        # write payload to FIFO
        self.cs.value(0)
        self.spi.write(bytearray([REG_FIFO | 0x80]) + data)
        self.cs.value(1)

        # start TX
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_TX)

        # wait TX done (IRQ flags bit 3)
        for _ in range(1000):
            irq = self.read_reg(REG_IRQ_FLAGS)
            if irq & 0x08:
                break
            time.sleep_ms(5)

        # clear IRQs
        self.write_reg(REG_IRQ_FLAGS, 0xFF)
        # back to RX
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_RXCONTINUOUS)
        time.sleep(0.01)

    def receive(self):
        # check IRQ
        irq = self.read_reg(REG_IRQ_FLAGS)
        if irq & 0x40:  # RxDone
            # clear interrupts
            self.write_reg(REG_IRQ_FLAGS, 0xFF)

            fifo_addr = self.read_reg(REG_FIFO_RX_CURRENT_ADDR)
            self.write_reg(REG_FIFO_ADDR_PTR, fifo_addr)

            length = self.read_reg(REG_RX_NB_BYTES)
            if length == 0:
                length = self.read_reg(REG_PAYLOAD_LENGTH)

            # read payload from FIFO
            self.cs.value(0)
            self.spi.write(bytearray([REG_FIFO & 0x7F]))
            payload = self.spi.read(length)
            self.cs.value(1)
            return payload
        return None