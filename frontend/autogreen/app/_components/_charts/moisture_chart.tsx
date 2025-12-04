'use client';

import { useState, useEffect } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

export default function MoistureChart() {
const [moisture, setMoisture] = useState([]);

const deviceId = 1;
const page = 0;
const size = 10;

const MOISTURE_URL = `http://0.0.0.0:8080/api/moisture/${deviceId}/moistures?page=${page}&size=${size}`;

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

if (!moisture || moisture.length === 0) {
return <p>Loading or no data yet...</p>;
}

return (
<LineChart
style={{ width: '100%', maxWidth: '700px', height: '100%', maxHeight: '70vh', aspectRatio: 1.618 }}
data={moisture}
margin={{ top: 5, right: 0, left: 0, bottom: 5 }}
> <CartesianGrid strokeDasharray="3 3" /> <XAxis dataKey="time_stamp" /> <YAxis width="auto" dataKey="moisture_val"/> <Tooltip /> <Legend />
<Line type="monotone" dataKey="moisture_val" stroke="#323da1ff" isAnimationActive={false} activeDot={{ r: 8 }} /> </LineChart>
);
}
