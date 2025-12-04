import { Kafka } from 'kafkajs';
import WebSocket, { WebSocketServer } from 'ws';

const wss = new WebSocketServer({ port: 8081, host: '0.0.0.0' });
console.log('WebSocket server started on ws://localhost:8081');

const kafka = new Kafka({
  clientId: 'nodejs-backend',
  brokers: ['kafka:9092'],
});

const consumer = kafka.consumer({ groupId: 'node_backend' });

async function run() {
    await consumer.connect();
    await consumer.subscribe({ topic: 'c_stream', fromBeginning: false });
    await consumer.subscribe({ topic: 'c_stream_2', fromBeginning: false });

    await consumer.run({
        eachMessage: async ({ topic, message}) => {
            const detection = message.value.toString();
            console.log(`Received detection: ${detection}`);
            
            try {
                const base64Image = message.value.toString('base64');
                const payload = JSON.stringify({
                camera: topic,
                frame: base64Image
              });

              wss.clients.forEach((client) => {
              if (client.readyState === WebSocket.OPEN) {
                  client.send(payload);
              }
            });
            } 
            catch (err) {
              console.error('Error processing message:', err);
            }
            console.log(`Received message from topic ${topic}: ${message.value.length} bytes`);
        },
  });

  console.log('Kafka consumer running...');
}

run().catch(console.error);