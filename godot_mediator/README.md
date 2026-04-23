./idea.sh

Copy and paste this command to interact with the docker Broker in the Terminal:
- docker exec -it kafka sh

To read your message run: 
- /usr/bin/kafka-console-consumer --bootstrap-server kafka:9092 --topic "topic_name" --from-beginning