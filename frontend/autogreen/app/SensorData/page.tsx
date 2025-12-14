import Co2Chart from "../_components/_charts/co2_chart";
import HeatChart from "../_components/_charts/heat_chart";
import HumidityChart from "../_components/_charts/humidity_chart";
import MoistureChart from "../_components/_charts/moisture_chart";
import LightChart from "../_components/_charts/light_chart";

export default function SensorData() {
  return (
    <main>
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
        Hello From Sensor Data Page!

      <div className="grid xl:grid-cols-2 lg:grid-cols-2 w-full gap-10 max-w-[1400px]">
          
        <GridItem title="Moisture in %">
          <MoistureChart />
        </GridItem>
          
        <GridItem title="Humidity in %">
          <HumidityChart />
        </GridItem>

        <GridItem title="Temperature in °C">
          <HeatChart />
        </GridItem>

        <GridItem title="CO₂ in ppm">
          <Co2Chart />
        </GridItem>

        <GridItem title="Luminecense in Lux">
          <LightChart />
        </GridItem>

      </div>

    </div>
    </main>
    
  );
}

function GridItem({ title, children}) {
  return (
    <div className="flex flex-col items-center justify-center p-4 border border-slate-900 
    rounded-xl h-[500px]">
      <h3 className="text-2xl font-semibold  mb-4" >{title}</h3>
      {children} 

    </div>
  );
}