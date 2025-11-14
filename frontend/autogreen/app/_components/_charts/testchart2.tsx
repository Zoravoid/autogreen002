'use client';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

// #region Sample data
const data = [
  {
    name: 'Page 1',
    uv: 4000,
    pv: 2400,
    amt: 2400,
  },
  {
    name: 'Page 2',
    uv: 3000,
    pv: 1398,
    amt: 2210,
  },
  {
    name: 'Page 3',
    uv: 2000,
    pv: 9800,
    amt: 2290,
  },
  {
    name: 'Page 4',
    uv: 2780,
    pv: 3908,
    amt: 2000,
  },
  {
    name: 'Page 5',
    uv: 1890,
    pv: 4800,
    amt: 2181,
  },
  {
    name: 'Page 6',
    uv: 2390,
    pv: 3800,
    amt: 2500,
  },
  {
    name: 'Page 7',
    uv: 3490,
    pv: 4300,
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
      <Line type="monotone" dataKey="pv" stroke="#8884d8" activeDot={{ r: 8 }} />
      <Line type="monotone" dataKey="uv" stroke="#82ca9d" />
    </LineChart>
  );
}