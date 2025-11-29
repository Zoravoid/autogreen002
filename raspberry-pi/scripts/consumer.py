import json
from kafka import KafkaConsumer

consumer = KafkaConsumer{
    "s_moisture",
    bootstrap_server = "localhost:9092",
    auto_offset_reset = "earliest",
    group_id = "groupId",
    value_deserializer = lambda m: json.loads(m.decode("utf:8")),
}