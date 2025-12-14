'use client';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

// #region Sample data
const data = [
  {
    name: '2025-11-25 14:00:00.000',
    energyUsed: 5287,
    energyGained: 781,
    amt: 2400,
  },
  {
    name: '2025-12-21 21:00:00.000',
    energyUsed: 5817,
    energyGained: 3335,
    amt: 2210,
  },
  {
    name: '2026-1-17 16:00:00.000',
    energyUsed: 5662,
    energyGained: 4272,
    amt: 2290,
  },
  {
    name: '2026-2-19 17:00:00.000',
    energyUsed: 3251,
    energyGained: 2948,
    amt: 2000,
  },
  {
    name: '2026-3-28 18:00:00.000',
    energyUsed: 7621,
    energyGained: 4528,
    amt: 2181,
  },
  {
    name: '2026-4-22 19:00:00.000',
    energyUsed: 8104,
    energyGained: 5117,
    amt: 2500,
  },
  {
    name: '2026-5-6 20:00:00.000',
    energyUsed: 7425,
    energyGained: 3855,
    amt: 2100,
  },
];
// #endregion

export default function TestChart2() {
  return (
    <LineChart
      style={{ width: '100%', maxWidth: '700px', height: '100%', maxHeight: '70vh', aspectRatio: 1.618 }}
      responsive
      data={data}
      margin={{
        top: 5,
        right: 0,
        left: 0,
        bottom: 5,
      }}
    >
      <CartesianGrid strokeDasharray="3 3" />
      <XAxis dataKey="name" />
      <YAxis width="auto" />
      <Tooltip />
      <Legend />
      <Line type="monotone" dataKey="energyUsed" stroke="#8884d8" activeDot={{ r: 8 }} />
      <Line type="monotone" dataKey="energyGained" stroke="#82ca9d" />
    </LineChart>
  );
}