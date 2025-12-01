import json
import time
import requests
import random
from kafka import KafkaProducer


print("Starting producer...")
try:
    producer = KafkaProducer(
        bootstrap_servers="lilithvoid.local:29092",
        value_serializer=lambda v: json.dumps(v).encode("utf-8")
    )
    print("Producer created successfully.")
except Exception as e:
    print("Producer FAILED to create:", e)
    raise e


def send_sensor_data(sensor_type, data):
    print("Sending: ", sensor_type, data)

    future = producer.send(sensor_type, data)

    try:
        metadata = future.get(timeout=2)
        print("Message delivered:", metadata)
    except Exception as e:
        print("Delivery failed:", e)
    