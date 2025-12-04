import json
import time
import requests
import random
from kafka import KafkaProducer


print("Starting producer...")
try:
    json_producer = KafkaProducer(
        bootstrap_servers="lilithvoid.local:29092",
        value_serializer=lambda v: json.dumps(v).encode("utf-8")
    )
    print("JSON Producer created successfully.")
except Exception as e:
    print("JSON Producer FAILED to create:", e)
    raise e

try:
    binary_producer = KafkaProducer(
        bootstrap_servers="lilithvoid.local:29092",
        value_serializer=lambda v: v 
    )
    print("Binary Producer created successfully.")
except Exception as e:
    print("Binary Producer FAILED to create:", e)


def send_sensor_data(sensor_type, data):
    print("Sending: ", sensor_type, data)

    future = json_producer.send(sensor_type, data)

    try:
        metadata = future.get(timeout=2)
        print("Message delivered:", metadata)
    except Exception as e:
        print("Delivery failed:", e)

def send_frame(topic, frame_bytes):

    future = binary_producer.send(topic, frame_bytes)

    try:
        metadata = future.get(timeout=2)
        print(f"Sent frame to topic: {topic}")
    except Exception as e:
        print(f"Failed to send frame to Tpoic: {topic}", e)
    