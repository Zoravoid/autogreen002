import React from "react";
import "./navbar.css";

const Navbar = () => {
    return <div className="sidenav">
        <a href="/CameraFeed">CameraFeed</a>
        <a href="/EnergyInfo">EnergyInfo</a>
        <a href="/PlantState">PlantState</a>
        <a href="/SensorData">SensorData</a>
    </div>;
};

export default Navbar;