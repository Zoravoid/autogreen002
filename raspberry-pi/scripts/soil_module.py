from machine import ADC, Pin, SPI
import time
import struct
from lora import LoRa

LoRa_PSW = "REQ2"

led = Pin("LED", Pin.OUT)   # Onboard LED for Pico W

soil_pin = ADC(Pin(26))

# SPI
spi = SPI(0, baudrate=5000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(19), miso=Pin(16))
lora_node2 = LoRa(spi, cs=17, rst=20)

# Calibration points
raw_min = 47799.21
perc_min = 32
raw_max = 23795.13
perc_max = 100

# Compute slope and intercept
m = (perc_max - perc_min) / (raw_max - raw_min)
b = perc_min - m * raw_min

def raw_to_percent(raw_value):
    perc = m * raw_value + b

    if perc < 0:
        perc = 0
    elif perc > 100:
        perc = 100
    return perc

while True:
    led.toggle()
    # Read raw 16-bit value (0–65535)
    soil_value = soil_pin.read_u16()
    moist = raw_to_percent(soil_value)
    
    msg_node2 = lora_node2.receive()
    text_node2 = msg_node2.decode()
    if text_node2 == LoRa_PSW:
        print("Received request from main (node 2)")
        lora_node2.send(str(round(moist,2)))
    
    time.sleep(1)