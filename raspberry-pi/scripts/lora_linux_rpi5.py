import spidev
import lgpio
import time

# Registers
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
    def __init__(self, cs_pin, rst_pin, frequency=433000000):
        self.cs = cs_pin
        self.rst = rst_pin

        self.chip = lgpio.gpiochip_open(4)

        #lgpio.gpio_claim_output(self.chip, self.cs, 1)
        lgpio.gpio_claim_output(self.chip, self.rst, 1)

        # Open SPI
        self.spi = spidev.SpiDev()
        self.spi.open(0, 0)
        self.spi.max_speed_hz = 5000000
        self.spi.mode = 0b00

        self.reset()
        time.sleep(0.1)

        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_SLEEP)
        time.sleep(0.05)

        self.set_freq(frequency)

        self.write_reg(REG_MODEM_CONFIG_1, 0x72)
        self.write_reg(REG_MODEM_CONFIG_2, 0x74)

        self.write_reg(REG_PA_CONFIG, 0x8F)
        self.write_reg(REG_LNA, 0x23)

        self.write_reg(REG_FIFO_TX_BASE_ADDR, 0)
        self.write_reg(REG_FIFO_RX_BASE_ADDR, 0)

        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_RXCONTINUOUS)
        time.sleep(0.1)

    def reset(self):
        lgpio.gpio_write(self.chip, self.rst, 0)
        time.sleep(0.01)
        lgpio.gpio_write(self.chip, self.rst, 1)
        time.sleep(0.05)

    def write_reg(self, address, value):
        #lgpio.gpio_write(self.chip, self.cs, 0)
        self.spi.xfer2([address | 0x80, value])
        #lgpio.gpio_write(self.chip, self.cs, 1)

    def read_reg(self, address):
        #lgpio.gpio_write(self.chip, self.cs, 0)
        result = self.spi.xfer2([address & 0x7F, 0x00])[1]
        #lgpio.gpio_write(self.chip, self.cs, 1)
        return result

    def set_freq(self, frequency):
        frf = int((frequency << 19) // 32000000)
        self.write_reg(REG_FRF_MSB, (frf >> 16) & 0xFF)
        self.write_reg(REG_FRF_MID, (frf >> 8) & 0xFF)
        self.write_reg(REG_FRF_LSB, frf & 0xFF)
        time.sleep(0.01)

    def send(self, data: bytes):
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_STDBY)
        time.sleep(0.01)

        self.write_reg(REG_FIFO_ADDR_PTR, 0)
        self.write_reg(REG_PAYLOAD_LENGTH, len(data))

        #lgpio.gpio_write(self.chip, self.cs, 0)
        self.spi.xfer2([REG_FIFO | 0x80] + list(data))
        #lgpio.gpio_write(self.chip, self.cs, 1)

        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_TX)

        for _ in range(1000):
            irq = self.read_reg(REG_IRQ_FLAGS)
            if irq & 0x08:
                break
            time.sleep(0.005)

        self.write_reg(REG_IRQ_FLAGS, 0xFF)
        self.write_reg(REG_OP_MODE, MODE_LONG_RANGE_MODE | MODE_RXCONTINUOUS)

    def receive(self):
        irq = self.read_reg(REG_IRQ_FLAGS)
        if irq & 0x40:
            self.write_reg(REG_IRQ_FLAGS, 0xFF)

            addr = self.read_reg(REG_FIFO_RX_CURRENT_ADDR)
            self.write_reg(REG_FIFO_ADDR_PTR, addr)

            length = self.read_reg(REG_RX_NB_BYTES)
            if length == 0:
                length = self.read_reg(REG_PAYLOAD_LENGTH)

            #lgpio.gpio_write(self.chip, self.cs, 0)
            raw = self.spi.xfer2([REG_FIFO & 0x7F] + [0x00]*length)[1:]
            #lgpio.gpio_write(self.chip, self.cs, 1)

            return bytes(raw)
        return None