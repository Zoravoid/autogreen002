'use client';
import { useState, useEffect } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

export default function ApiChart() {

  const [moisture, setMoisture] = useState([]);

  useEffect(() => {
    async function loadData() {
      const response = await fetch('http://localhost:8080/api/moisture?page=0&size=10');
      const json = await response.json();

      setMoisture(Array.isArray(json) ? json : json.content);
    }
    loadData();
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