from kafka import KafkaConsumer
import json

def get_sensor_data(sensor_type: str):
    print("starting consumer...")
    topic_name= f"b_{sensor_type}"
    print(f"Listening at topic: b_{sensor_type}")

    try:
        consumer = KafkaConsumer(
            topic_name,
            bootstrap_servers="lilithvoid.local:29092",
            value_deserializer=lambda m: json.loads(m.decode("utf-8")),
            auto_offset_reset="latest",
            enable_auto_commit=True,
            group_id="green_pi_01"
        )
        print("Consumer has connected!")
    except Exception as e:
        print("Connection failed:", e)
        raise e

    for msg in consumer:
        print(f" Value: {msg.value}")
        
    consumer.close()
    return msg.value

    