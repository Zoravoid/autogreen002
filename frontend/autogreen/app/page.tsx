import Image from "next/image";
import "./home.css";

export default function Home() {
  return (
    <div className="homePage">
    <div className="items-center justify-items-center min-h-screen">
       <div className="image">
       <Image
          src="/home.png"
          alt="Tomato Picture"
          width={550}
          height={550}
        />
        </div>
        <div className="text">
          This system was originally designed for our Experte in Teams project called Automated Greenhouse. 
          This service used to handle sensory analytics for greenhouse components but was repurposed to serve as an analysis tool for our current Bachelor Thesis.
          Here we observe the results of our 2 Parameter Logistics function, a model based on Item Response Theory, to estimate player Skill and adjust difficulty based on performance.
        </div>
    </div>
    </div>
  );
}