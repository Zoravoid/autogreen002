import React from "react";
import "./navbar.css";

const Navbar = () => {
    return <div className="sidenav">
        <a href="/">Home</a>
        <a href="/CameraFeed">Camera Feed</a>
        <a href="/EnergyInfo">Energy Info</a>
        <a href="/PlantState">Plant State</a>
        <a href="/SensorData">Sensor Data</a>
    </div>;
};

export default Navbar;