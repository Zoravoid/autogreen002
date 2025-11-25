'use client';
import { useState, useEffect } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

export default function ApiChart() {

  const [moisture, setMoisture] = useState([]);

  const deviceId = 1;
  const page = 0;
  const size = 10;
  const MOISTURE_URL = `http://localhost:8080/api/moisture/${deviceId}/moistures?page=${page}&size=${size}`;

  useEffect(() => {
    async function loadData() {
      const response = await fetch(MOISTURE_URL);
      const json = await response.json();

      setMoisture(Array.isArray(json) ? json : json.content);
    }
    loadData();

    const interval = setInterval(() => {loadData();}, 1000);

    return () => clearInterval(interval);
  }, []);
  
  return (
    <LineChart
      style={{ width: '100%', maxWidth: '700px', height: '100%', maxHeight: '70vh', aspectRatio: 1.618 }}
      responsive
      data={moisture}
      margin={{
        top: 5,
        right: 0,
        left: 0,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="time_stamp" />
      <YAxis width="auto" dataKey="moisture_val"/>
      <Tooltip />
      <Legend />
      <Line type="monotone" dataKey="moisture_val" stroke="#8884d8" activeDot={{ r: 8 }} />
    </LineChart>
  );
}