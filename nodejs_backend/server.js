import { Kafka } from 'kafkajs';
import WebSocket, { WebSocketServer } from 'ws';

const WS_PORT = 8081;
const WS_HOST = "0.0.0.0";

let wss;

async function startWebsocketServer(retry = 0) {
  return new Promise((resolve) => {
    try {
      wss = new WebSocketServer({ port: WS_PORT, host: WS_HOST });

      wss.on("listening", () => {
        console.log(`WebSocket server ready on ws://${WS_HOST}:${WS_PORT}`);
        resolve();
      });

      wss.on("error", (err) => {
        console.error("WebSocket server error:", err);

        if (err.code === "EADDRINUSE" || err.code === "EACCES") {
          const delay = Math.min(2000 + retry * 500, 10000);
          console.log(`Retrying WebSocket bind in ${delay}ms...`);
          setTimeout(() => startWebsocketServer(retry + 1).then(resolve), delay);
        } else {
          console.error("Fatal WebSocket error. Exiting.");
          process.exit(1);
        }
      });
    } catch (e) {
      console.error("Unexpected WebSocket startup error:", e);
      setTimeout(() => startWebsocketServer(retry + 1).then(resolve), 1000);
    }
  });
}

const kafka = new Kafka({
  clientId: 'nodejs-backend',
  brokers: ['kafka:9092'],
});

const consumer = kafka.consumer({ groupId: 'node_backend' });

async function startKafka() {
  try {
    await consumer.connect();
    await consumer.subscribe({ topic: 'c_stream', fromBeginning: false });
    await consumer.subscribe({ topic: 'c_detection', fromBeginning: false });

    await consumer.run({
      eachMessage: async ({ topic, message }) => {
        const payload = JSON.stringify({
          camera: topic,
          frame: message.value.toString("base64"),
        });

        wss.clients.forEach((client) => {
          if (client.readyState === WebSocket.OPEN) {
            client.send(payload);
          }
        });

        console.log(`Message from ${topic}, ${message.value.length} bytes`);
      },
    });

    console.log("Kafka consumer running...");
  } catch (err) {
    console.error("Kafka startup error. Retrying in 2s...", err);
    setTimeout(startKafka, 2000);
  }
}

(async () => {
  await startWebsocketServer();
  await startKafka();
})();