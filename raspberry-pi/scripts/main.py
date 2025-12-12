from pi_producer import send_sensor_data
from pi_consumer import get_sensor_data
from cam_stream import start_camera_streaming
from lora_linux_rpi5 import LoRa

import threading
from datetime import datetime
import json
import time

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
        "moisture": pico2[0]
    }


def send_all():
    raw = fetch_all_lora_data()
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S.%f")

    for sensor, value in raw.items():
        payload = {
            "device_id": 1,
            f"{sensor}_val": value,
            "time_stamp": timestamp
        }

        topic = f"s_{sensor}"
        print(f"Sending to topic '{topic}': {payload}")
        send_sensor_data(topic, payload)

def receive_all():
    for sensor in ["co2", "heat", "humidity", "moisture"]:
        vals = get_sensor_data(sensor)
        print(f"Received from Kafka ({sensor}): {vals}")


def main():
    cam_thread = threading.Thread(target=start_camera_streaming, daemon=True)
    cam_thread.start()

    print("System initialized.")

    while True:
        send_all()
        time.sleep(5)


if __name__ == "__main__":
    main()