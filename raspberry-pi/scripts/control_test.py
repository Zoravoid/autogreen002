from lora_linux_rpi5 import LoRa
from datetime import datetime
import json
import time
import serial

ser = serial.Serial(port="/dev/ttyAMA0", baudrate=9600, timeout=1)

CS_PIN = 8
RST_PIN = 22

lora = LoRa(cs_pin=CS_PIN, rst_pin=RST_PIN)

sensor_map = {
    "co2": "REQ1",
    "heat": "REQ1",
    "humidity": "REQ1",
    "moisture": "REQ2"
}

def request_from_pico(request_code):
    lora.send(request_code.encode())
    time.sleep(2)

    msg = lora.receive()
    if not msg:
        return None
    
    try:
        return json.loads(msg.decode().replace("'", '"'))
    except:
        return None
    
def fetch_all_lora_data():
    pico1 = request_from_pico("REQ1")
    pico2 = request_from_pico("REQ2")

    if pico1 is None:
        pico1 = [None, None, None]

    if pico2 is None:
        pico2 = [None]

    if isinstance(pico2, list):
        moisture = pico2[0]
    else:
        moisture = pico2

    return {
        "co2": pico1[0],
        "heat": pico1[1],
        "humidity": pico1[2],
        "moisture": moisture
    }

def send_all():
    raw = fetch_all_lora_data()
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S.%f")
    #print(raw)
    payload = {"device_id": 1}
    for sensor, value in raw.items():
        payload[f"{sensor}_val"] = value
    payload["time_stamp"] = timestamp
    json_bytes = json.dumps(payload).encode("utf-8")
    ser.write(json_bytes)
    print("sent uart", payload)


def receive_uart():
    line = ser.readline().decode("utf-8", errors="replace").strip()
    if line:
        print("Recived:", line)

def main():

    print("System initialized.")

    while True:
        receive_uart()
        send_all()
        time.sleep(1)


if __name__ == "__main__":
    main()