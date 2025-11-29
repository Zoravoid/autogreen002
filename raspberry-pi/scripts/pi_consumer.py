import json
"""from kafka import KafkaConsumer

consumer = KafkaConsumer{
    "s_moisture",
    bootstrap_server = "localhost:9092",
    auto_offset_reset = "earliest",
    group_id = "groupId",
    value_deserializer = lambda m: json.loads(m.decode("utf:8")),
}
"""
#  NOT WORKING CODE ABOVE
#  THIS SHIT IS ASS <<<

def get_sensor_data(sensor_type):
    #dummy data in the format that we use
    value_type = sensor_type+"_val"
    data = {
        "device_id": 1,
        value_type: 69.69,
        "time_stamp": "2025-11-29 14:00:00.000" 
    }
    return data