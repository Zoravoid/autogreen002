import Image from "next/image";
import "./home.css";

export default function Home() {
  return (
    <div className="homePage">
    <div className="items-center justify-items-center min-h-screen">
       <div className="image">
       <Image
          src="/tomatoes.jpg"
          alt="Tomato Picture"
          width={550}
          height={550}
        />
        </div>
        <div className="text">
          This systems was made for a student project called Automated 
          Greenhouse. The project aims to design and implement a full-scale
          automated greenhouse that integrates mechanical, mechatronic, 
          electronic and software systems into a cohesive, intelligent 
          agricultural environment. The project is being made together with
          Szelgaard ApS, which is a company that focuses on developing future
          technologies within the green sector. The system will employ an 
          array of sensors, robotic mechanisms, and AI functionalities. The 
          project should be approached mostly as a ptototype or proof of 
          concept for what could become a fully automated greenhouse with 
          minimal human supervision.
        </div>
    </div>
    </div>
  );
}