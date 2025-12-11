from kafka import KafkaProducer

print("Starting producer...")

try:
    binary_producer = KafkaProducer(
        bootstrap_servers="localhost:29092",
        value_serializer=lambda v: v 
    )
    print("Binary Producer created successfully.")
except Exception as e:
    print("Binary Producer FAILED to create:", e)


def send_frame(topic, frame_bytes):

    future = binary_producer.send(topic, frame_bytes)

    try:
        metadata = future.get(timeout=2)
        print(f"Sent frame to topic: {topic}")
    except Exception as e:
        print(f"Failed to send frame to Tpoic: {topic}", e)