import network
import ntptime
from machine import I2C, Pin, SPI
import time
import struct
from lora import LoRa

LoRa_PSW = "REQ1"

sensor_data_send = []

led = Pin("LED", Pin.OUT)   # Onboard LED for Pico W

SCD30_ADDRESS = 0x61
SHT85_ADDRESS = 0x44   # Default I2C address

# SPI
spi = SPI(0, baudrate=5000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(19), miso=Pin(16))

lora_node1 = LoRa(spi, cs=17, rst=20)

i2c = I2C(0, scl=Pin(5), sda=Pin(4), freq=50000)

class SCD30:
    def __init__(self, i2c):
        self.i2c = i2c

    def write_command(self, cmd, args=None):
        buf = bytearray(2)
        buf[0] = cmd >> 8
        buf[1] = cmd & 0xFF
        self.i2c.writeto(SCD30_ADDRESS, buf)

        if args:
            self.i2c.writeto(SCD30_ADDRESS, args)

    def read_data(self):
        self.write_command(0x0300)
        time.sleep_ms(3)
        data = self.i2c.readfrom(SCD30_ADDRESS, 18)

        # Unpack sensor readings (CO2, temperature, humidity)
        co2 = struct.unpack(">f", data[0:4])[0]
        temp = struct.unpack(">f", data[6:10])[0]
        hum = struct.unpack(">f", data[12:16])[0]
        return co2, temp, hum

    def data_ready(self):
        self.write_command(0x0202)
        time.sleep_ms(3)
        data = self.i2c.readfrom(SCD30_ADDRESS, 3)
        return data[1] == 1

    def start(self):
        # Start continuous measurement, 0 = ambient pressure
        self.write_command(0x0010, b'\x00\x00')
        time.sleep(1)
        


class SHT85:
    def __init__(self, i2c):
        self.i2c = i2c

    def _crc8(self, data):
        # CRC-8 formula from Sensirion datasheet
        crc = 0xFF
        for byte in data:
            crc ^= byte
            for _ in range(8):
                if crc & 0x80:
                    crc = (crc << 1) ^ 0x31
                else:
                    crc <<= 1
                crc &= 0xFF
        return crc

    def read_temp_hum(self):
        # High repeatability measurement, clock stretching disabled
        self.i2c.writeto(SHT85_ADDRESS, b'\x24\x00')
        time.sleep_ms(15)

        data = self.i2c.readfrom(SHT85_ADDRESS, 6)

        temp_raw = data[0:2]
        temp_crc = data[2]
        hum_raw = data[3:5]
        hum_crc = data[5]

        # CRC check
        if self._crc8(temp_raw) != temp_crc or self._crc8(hum_raw) != hum_crc:
            raise ValueError("CRC error - check wiring or I2C speed")

        raw_t = temp_raw[0] << 8 | temp_raw[1]
        raw_h = hum_raw[0] << 8 | hum_raw[1]

        # Convert per Sensirion formula
        temp_c = -45 + (175 * raw_t / 65535)
        humidity = 100 * raw_h / 65535
        return temp_c, humidity
    
       
SCD30_sensor = SCD30(i2c)
SCD30_sensor.start()

SHT85_sensor = SHT85(i2c)

while 1:
    led.toggle()
    
    if SCD30_sensor.data_ready():
        co2, temp_b, hum_b = SCD30_sensor.read_data()
        print("CO2: {:.2f} ppm  |  Temp: {:.2f} °C  |  Humidity: {:.2f} %".format(co2, temp_b, hum_b))
    try:
        temp, hum = SHT85_sensor.read_temp_hum()
        print("Temp: {:.2f} °C  |  Humidity: {:.2f} %RH".format(temp, hum))
    except Exception as e:
        print("Error:", e)
    
    sensor_data_send = [round(co2,2),round(temp,2),round(hum,2)]
    
    msg_node1 = lora_node1.receive()
    if msg_node1:
        text_node1 = msg_node1.decode()
        if text_node1 == LoRa_PSW:
            print("Received request from main (node 1)")
            lora_node1.send(str(sensor_data_send).encode())
    
    time.sleep(1)