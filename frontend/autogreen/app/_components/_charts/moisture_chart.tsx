'use client';

import { useState, useEffect, useRef } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, } from 'recharts';
import "./chart.css";

interface MoistureData {
  time_stamp: string;
  moisture_val: number;
}

export default function MoistureChart() {
  const [moisture, setMoisture] = useState<MoistureData[]>([]);
  const [deviceId, setDeviceId] = useState<number>(1);
  const [size, setSize] = useState<number>(10);
  const page = 0;
  const MOISTURE_URL = `http://lilithvoid.local/backend/api/moisture/${deviceId}/moistures?page=${page}&size=${size}&sort=timeStamp,desc`;

  

  useEffect(() => {
    async function loadData() {
      try {
        const response = await fetch(MOISTURE_URL);
        if (!response.ok) throw new Error(`HTTP error ${response.status}`);
        const json = await response.json();
        setMoisture(Array.isArray(json) ? json : json.content);
      } catch (err) {
        console.error('Failed to fetch moisture data:', err);
      }
    }

    loadData();
    const interval = setInterval(loadData, 1000);

    return () => clearInterval(interval);
  }, [MOISTURE_URL]);

  return (
    <div className="w-full flex flex-col items-center gap-4">
      <div className="flex flex-wrap gap-4 mb-4 justify-center">
        <div className='inputall'>
        <label className="flex flex-col text-sm font-semibold w-full">
          Device ID
          <input
            type="number"
            value={deviceId}
            onChange={(e) => setDeviceId(Number(e.target.value))}
            className="inputfield"
          />
        </label>
        </div>
        <div className='inputall'>
        <label className="flex flex-col text-sm font-semibold w-full">
          Size
          <input
            type="number"
            value={size}
            onChange={(e) => setSize(Number(e.target.value))}
            className="inputfield"
          />
        </label>
        </div>
      </div>



      {!moisture || moisture.length === 0 ? (
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
          data={moisture}
          margin={{ top: 5, right: 0, left: 0, bottom: 5 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="time_stamp" reversed />
          <YAxis dataKey="moisture_val" />
          <Tooltip />
          <Legend />
          <Line
            type="monotone"
            dataKey="moisture_val"
            stroke="#323da1ff"
            isAnimationActive={false}
            activeDot={{ r: 8 }}
          />
        </LineChart>
      )}
    </div>
  );
}
