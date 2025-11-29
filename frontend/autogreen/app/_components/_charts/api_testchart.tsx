'use client';

import { useState, useEffect } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

export default function ApiChart() {
const [moisture, setMoisture] = useState([]);

const deviceId = 1;
const page = 0;
const size = 10;

// Dynamically choose base URL: localhost for dev, backend service for Docker, or LAN IP for other devices
const baseUrl = typeof window !== 'undefined'
? `http://${window.location.hostname}:8080`
: '[http://backend:8080](http://backend:8080)';

const MOISTURE_URL = `${baseUrl}/api/moisture/${deviceId}/moistures?page=${page}&size=${size}`;

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


loadData(); // initial load
const interval = setInterval(loadData, 1000); // refresh every 1s

return () => clearInterval(interval); // cleanup


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
<Line type="monotone" dataKey="moisture_val" stroke="#8884d8" isAnimationActive={false} activeDot={{ r: 8 }} /> </LineChart>
);
}
