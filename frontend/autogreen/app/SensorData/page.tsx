import TestChart from "../_components/_charts/testchart";
import TestChart2 from "../_components/_charts/testchart2";

export default function SensorData() {
  return (
    <main>
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
        Hello From Sensor Data Page!

      <div className="grid xl:grid-cols-2 lg:grid-cols-2 w-full gap-10 max-w-[1400px]">
          
        <GridItem title="Test Chart">
          <TestChart />
        </GridItem>
          
        <GridItem title="Test2 Chart">
          <TestChart2 />
        </GridItem>
      </div>

    </div>
    </main>
    
  );
}

function GridItem({ title, children}) {
  return (
    <div className="flex flex-col items-center justify-center p-4 border border-slate-900 
    rounded-xl h-[400px]">
      <h3 className="text-2xl font-semibold text-white mb-4">{title}</h3>
      {children} 

    </div>
  );
}
