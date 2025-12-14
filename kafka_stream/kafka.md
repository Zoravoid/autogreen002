# Kafka

## First step to setup the Broker: 
- run the docker setup form the compose.yaml in /database

## Create Topics

Copy and paste this command to interact with the docker Broker in the Terminal:
- docker exec -it kafka sh

You can test the topics with the following:
- /usr/bin/kafka-console-producer --bootstrap-server kafka:9092 --topic "topic_name"

Write a message and when ur done do ctrl + c 

To read your message run: 
- /usr/bin/kafka-console-consumer --bootstrap-server kafka:9092 --topic "topic_name" --from-beginning

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

{"device_id": 2, "moisture_val": 57.602, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 2, "moisture_val": 3.522, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 2, "moisture_val": 75.211, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 2, "moisture_val": 7.976, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 2, "moisture_val": 63.455, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 2, "moisture_val": 9.323, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 2, "moisture_val": 81.343, "time_stamp": "2025-11-25 20:00:00.000"}

## Humidity 

{"device_id": 1, "humidity_val": 226.820, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 1, "humidity_val": 231.862, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 1, "humidity_val": 609.749, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 1, "humidity_val": 90.845, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 1, "humidity_val": 883.449, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 1, "humidity_val": 40.452, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 1, "humidity_val": 337.225, "time_stamp": "2025-11-25 20:00:00.000"}

{"device_id": 2, "humidity_val": 28.246, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 2, "humidity_val": 450.556, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 2, "humidity_val": 874.448, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 2, "humidity_val": 502.921, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 2, "humidity_val": 954.662, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 2, "humidity_val": 86.538, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 2, "humidity_val": 12.682, "time_stamp": "2025-11-25 20:00:00.000"}

## Heat

{"device_id": 1, "heat_val": 846.548, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 1, "heat_val": 412.498, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 1, "heat_val": 37.449, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 1, "heat_val": 375.775, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 1, "heat_val": 326.994, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 1, "heat_val": 309.700, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 1, "heat_val": 323.701, "time_stamp": "2025-11-25 20:00:00.000"}

{"device_id": 2, "heat_val": 882.840, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 2, "heat_val": 679.775, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 2, "heat_val": 726.843, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 2, "heat_val": 792.638, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 2, "heat_val": 668.545, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 2, "heat_val": 97.858, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 2, "heat_val": 871.814, "time_stamp": "2025-11-25 20:00:00.000"}

## CO2

{"device_id": 1, "co2_val": 450.373, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 1, "co2_val": 299.749, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 1, "co2_val": 248.804, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 1, "co2_val": 378.511, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 1, "co2_val": 51.863, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 1, "co2_val": 568.439, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 1, "co2_val": 261.440, "time_stamp": "2025-11-25 20:00:00.000"}

{"device_id": 2, "co2_val": 930.799, "time_stamp": "2025-11-25 14:00:00.000"}
{"device_id": 2, "co2_val": 848.772, "time_stamp": "2025-11-25 21:00:00.000"}
{"device_id": 2, "co2_val": 737.634, "time_stamp": "2025-11-25 16:00:00.000"}
{"device_id": 2, "co2_val": 881.109, "time_stamp": "2025-11-25 17:00:00.000"}
{"device_id": 2, "co2_val": 179.877, "time_stamp": "2025-11-25 18:00:00.000"}
{"device_id": 2, "co2_val": 358.678, "time_stamp": "2025-11-25 19:00:00.000"}
{"device_id": 2, "co2_val": 46.350, "time_stamp": "2025-11-25 20:00:00.000"}

## Light

{"device_id": 1, "light_val": 56.322, "time_stamp": "2025-11-25 22:00:00.000"}
{"device_id": 1, "light_val": 264.190, "time_stamp": "2025-11-25 21:00:00.000"} 
{"device_id": 1, "light_val": 301.111, "time_stamp": "2025-11-25 16:00:00.000"} 
{"device_id": 1, "light_val": 960.254, "time_stamp": "2025-11-25 17:00:00.000"} 
{"device_id": 1, "light_val": 322.673, "time_stamp": "2025-11-25 18:00:00.000"} 
{"device_id": 1, "light_val": 477.576, "time_stamp": "2025-11-25 19:00:00.000"} 
{"device_id": 1, "light_val": 821.221, "time_stamp": "2025-11-25 20:00:00.000"}

{"device_id": 2, "light_val": 562.611, "time_stamp": "2025-11-25 14:00:00.000"} 
{"device_id": 2, "light_val": 368.666, "time_stamp": "2025-11-25 21:00:00.000"} 
{"device_id": 2, "light_val": 875.697, "time_stamp": "2025-11-25 16:00:00.000"} 
{"device_id": 2, "light_val": 315.188, "time_stamp": "2025-11-25 17:00:00.000"} 
{"device_id": 2, "light_val": 125.276, "time_stamp": "2025-11-25 18:00:00.000"} 
{"device_id": 2, "light_val": 310.921, "time_stamp": "2025-11-25 19:00:00.000"} 
{"device_id": 2, "light_val": 855.590, "time_stamp": "2025-11-25 20:00:00.000"}
