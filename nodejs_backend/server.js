import { Kafka } from 'kafkajs';
import WebSocket, { WebSocketServer } from 'ws';

const wss = new WebSocketServer({ port: 8081 });
console.log('WebSocket server started on ws://localhost:8081');

const kafka = new Kafka({
  clientId: 'nodejs-backend',
  brokers: ['kafka:9092'],
});

const consumer = kafka.consumer({ groupId: 'yolo-backend-group' });

async function run() {
    await consumer.connect();
    await consumer.subscribe({ topic: 'c_image', fromBeginning: false });

    await consumer.run({
        eachMessage: async ({ topic, message}) => {
            const detection = message.value.toString();
            console.log(`Received detection: ${detection}`);

            wws.clients.forEach((client) => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(detection);
            }
      });
    }
  });
}

        
        

run().catch(console.error);