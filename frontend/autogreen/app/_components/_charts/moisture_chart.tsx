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
  const MOISTURE_URL = `http://lilithvoid:8080/api/level/${deviceId}/levels?page=${page}&size=${size}&sort=playerId,desc`;

  

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
          Level ID
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
          <XAxis dataKey="time_stamp" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="number_sense" stroke="rgb(184, 34, 34)" isAnimationActive={false}/>
          <Line type="monotone" dataKey="counting" stroke="#ec8805" isAnimationActive={false}/>
          <Line type="monotone" dataKey="arithmetic" stroke="#342fa5" isAnimationActive={false}/>
          <Line type="monotone" dataKey="visual_patterns" stroke="#8a7c05" isAnimationActive={false}/>
          <Line type="monotone" dataKey="memory" stroke="#009e6f" isAnimationActive={false}/>
        </LineChart>
      )}
    </div>
  );
}