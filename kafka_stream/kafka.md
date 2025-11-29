# Kafka

## First step to setup the Broker: 
- run the docker setup form the compose.yaml in /database

## Create Topics

Copy and paste this command to interact with the docker Broker in the Terminal:
- docker exec --workdir /opt/kafka/bin/ -it autogreen_kafka_broker sh

To create the topics run the following commands:
- ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic s_heat
- ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic s_co2
- ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic s_humidity
- ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic s_moisture
- ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic c_stream
- ./kafka-topics.sh --bootstrap-server localhost:9092 --create --topic c_image

You can test the topics with the following:
- ./kafka-console-producer.sh --bootstrap-server localhost:9092 --topic "topic_name"

Write a message and when ur done do ctrl + c 

To read your message run: 
- ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic "topic_name" --from-beginning

To check if topics exist run:
- ./kafka-topics.sh --bootstrap-server localhost:9092 --list


# Kafka dummy data

{"device_id": 1, "moisture_val": 49.309, "time_stamp": "2025-11-25 22:00:00.000"}
{"device_id": 1, "moisture_val": 2.101, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 1, "moisture_val": 70.101, "time_stamp": "2025-11-25 23:00:00.000"}
{"device_id": 1, "moisture_val": 4.101, "time_stamp": "2025-11-25 24:00:00.000"}
{"device_id": 1, "moisture_val": 60.101, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 1, "moisture_val": 7.101, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 1, "moisture_val": 80.101, "time_stamp": "2025-11-25 20:00:00.000"}


