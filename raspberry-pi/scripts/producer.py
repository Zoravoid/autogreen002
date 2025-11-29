import json
import time
import requests
import random
from kafka import KafkaProducer

print("Starting producer...")

try:
    producer = KafkaProducer(
        bootstrap_servers="192.168.1.72:29092",
        value_serializer=lambda v: json.dumps(v).encode("utf-8")
    )
    print("Producer created successfully.")
except Exception as e:
    print("Producer FAILED to create:", e)
    raise e

print("Entering loop...")

while True:
    data = {
        "device_id": 1,
        "moisture_val": 69.69,
        "time_stamp": "2025-11-29 14:00:00.000"
    }

    print("Sending:", data)
    future = producer.send("s_moisture", data)

    try:
        metadata = future.get(timeout=2)
        print("Message delivered:", metadata)
    except Exception as e:
        print("Delivery failed:", e)

    time.sleep(1)