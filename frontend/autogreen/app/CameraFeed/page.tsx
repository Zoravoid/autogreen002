"use client";

import { useEffect, useState } from "react";

export default function CameraFeed() {
  const [camera0Frame, setCamera0Frame] = useState<string | null>(null);
  const [camera1Frame, setCamera1Frame] = useState<string | null>(null);

  useEffect(() => {
    const ws = new WebSocket("ws://localhost:8081");

    ws.onopen = () => {
      console.log("Connected to WebSocket server");
    };

    ws.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data);
        if (data.camera === "c_stream") {
          setCamera0Frame(`data:image/jpeg;base64,${data.frame}`);
        } else if (data.camera === "c_detection") {
          setCamera1Frame(`data:image/jpeg;base64,${data.frame}`);
        }
      } catch (err) {
        console.error("Error parsing WebSocket message:", err);
      }
    };

    ws.onclose = () => {
      console.log("WebSocket connection closed");
    };

    ws.onerror = (err) => {
      console.error("WebSocket error:", err);
    };

    return () => {
      ws.close();
    };
  }, []);

  return (
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
      <h1 className="text-xl font-bold">Live Camera Feed</h1>
      <div className="grid grid-cols-2 gap-4 w-full justify-items-center">
        <div>
          <h2 className="text-center mb-2">Camera 0</h2>
          {camera0Frame ? (
            <img
              src={camera0Frame}
              alt="Camera 0"
              className="max-w-full rounded shadow"
            />
          ) : (
            <p>Waiting for Camera 0...</p>
          )}
        </div>

        <div>
          <h2 className="text-center mb-2">Camera 1</h2>
          {camera1Frame ? (
            <img
              src={camera1Frame}
              alt="Camera 1"
              className="max-w-full rounded shadow"
            />
          ) : (
            <p>Waiting for Camera 1...</p>
          )}
        </div>
      </div>
    </div>
  );
}