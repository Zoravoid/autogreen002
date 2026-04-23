import React from "react";
import Image from "next/image";
import "./logo.css";

const Logo = () => {
    return <div className="logo">
        <Image
                  src="/calculia.png"
                  alt="Autogreen logo"
                  width={50}
                  height={50}
                />
    </div>;
};

export default Logo;