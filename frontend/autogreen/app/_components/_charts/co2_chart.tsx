'use client';
import { useState, useEffect, useRef } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';
interface Co2Data {
  time_stamp: string;
  co2_val: number;
}
export default function Co2Chart() {
  const [co2, setCo2] = useState<Co2Data[]>([]);
  const audioRef = useRef<HTMLAudioElement | null>(null);
  const [deviceId, setDeviceId] = useState<number>(1);
  const [size, setSize] = useState<number>(10);
  const page = 0;
  const CO2_URL = `http://lilithvoid.local/backend/api/co2/${deviceId}/co2s?page=${page}&size=${size}&sort=timeStamp,desc`;



  useEffect(() => {
    async function loadData() {
      try {
        const response = await fetch(CO2_URL);
        if (!response.ok) throw new Error(`HTTP error ${response.status}`);
        const json = await response.json();
        setCo2(Array.isArray(json) ? json : json.content);
      } catch (err: unknown) {
        console.error('Failed to fetch co2 data:', err);
      }
    }
    loadData();
    const interval = setInterval(loadData, 1000);
    return () => clearInterval(interval);
  }, [CO2_URL]);



  useEffect(() => {
    if (!audioRef.current) return;
    if (deviceId == 40000 || size == 40000) {
      audioRef.current.currentTime = 0;
      audioRef.current.play().catch((err: unknown) =>
        console.error('Audio play error:', err)
      );
    }
  }, [deviceId, size]);



  return (
    <div className="w-full flex flex-col items-center gap-4">
      <audio ref={audioRef} src="/audio/Prayer to the Machine God  Warhammer 40k.mp3" preload="auto" />
      <div className="flex flex-wrap gap-4 mb-4 justify-center">
        <label className="flex flex-col text-sm font-semibold w-full">
          Device ID
          <input
            type="number"
            value={deviceId}
            onChange={(e) => setDeviceId(Number(e.target.value))}
            className="border rounded p-1 text-black"
          />
        </label>
        <label className="flex flex-col text-sm font-semibold w-full">
          Size
          <input
            type="number"
            value={size}
            onChange={(e) => setSize(Number(e.target.value))}
            className="border rounded p-1 text-black"
          />
        </label>
      </div>


      
      {(!co2 || co2.length === 0) ? (
        <p>Loading or no data yet...</p>
      ) : (
        <LineChart
          style={{
            width: '100%',
            maxWidth: '700px',
            height: '100%',
            maxHeight: '70vh',
            aspectRatio: 1.618,
          }}
          data={co2}
          margin={{ top: 5, right: 0, left: 0, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="time_stamp" reversed={true} />
          <YAxis width="auto" dataKey="co2_val" />
          <Tooltip />
          <Legend />
          <Line
            type="monotone"
            dataKey="co2_val"
            stroke="#979c49ff"
            isAnimationActive={false}
            activeDot={{ r: 8 }}
          />
        </LineChart>
      )}
    </div>
  );
}
