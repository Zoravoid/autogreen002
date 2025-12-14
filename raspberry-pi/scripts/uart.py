import serial
import time

ser = serial.Serial(port="/dev/ttyAMA0", baudrate=9600, timeout=1)

def receive_uart():
    line = ser.readline().decode("utf-8", errors="replace").strip()
    if line:
        print("Recived:", line)

while True:
    ser.write(b"Hello")
    print("sending hello")
    receive_uart()
    time.sleep(1)