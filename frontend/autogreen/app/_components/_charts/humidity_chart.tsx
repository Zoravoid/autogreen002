'use client';

import { useState, useEffect } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

export default function HumidityChart() {
const [humidity, setHumidity] = useState([]);

const deviceId = 1;
const page = 0;
const size = 10;

const HUMIDITY_URL = `http://0.0.0.0:8080/api/humidity/${deviceId}/humiditys?page=${page}&size=${size}`;

useEffect(() => {
async function loadData() {
try {
const response = await fetch(HUMIDITY_URL);
if (!response.ok) throw new Error(`HTTP error ${response.status}`);
const json = await response.json();
setHumidity(Array.isArray(json) ? json : json.content);
} catch (err) {
console.error('Failed to fetch humidity data:', err);
}
}


loadData(); 
const interval = setInterval(loadData, 1000); 

return () => clearInterval(interval); 


}, [HUMIDITY_URL]);

if (!humidity || humidity.length === 0) {
return <p>Loading or no data yet...</p>;
}

return (
<LineChart
style={{ width: '100%', maxWidth: '700px', height: '100%', maxHeight: '70vh', aspectRatio: 1.618 }}
data={humidity}
margin={{ top: 5, right: 0, left: 0, bottom: 5 }}
> <CartesianGrid strokeDasharray="3 3" /> <XAxis dataKey="time_stamp" /> <YAxis width="auto" dataKey="humidity_val"/> <Tooltip /> <Legend />
<Line type="monotone" dataKey="humidity_val" stroke="#4e9760ff" isAnimationActive={false} activeDot={{ r: 8 }} /> </LineChart>
);
}
