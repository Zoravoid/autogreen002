import TestChart2 from "../_components/_charts/testchart2";
import "./energyChart.css";


export default function EnergyInfo() {
  return (
    <main>
    <div className="font-sans grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20">
        This page is meant to show the amount of energy used and gained from the greenhouse, although currently the graph is simply a placeholder.

      <div className="grid xl:grid-cols-1 lg:grid-cols-1 w-auto gap-10 max-w-[1400px]">
          
        <GridItem title="Energy Info">
          <TestChart2 />
        </GridItem>
        
      </div>

    </div>
    </main>
    
  );
}

function GridItem({ title, children}) {
  return (
    <div className="chartsgrid">
      <div className="flex flex-col items-center justify-center p-4 h-[400px]">
        <h3 className="text-2xl font-semibold  mb-4" >{title}</h3>
        {children} 

      </div>
    </div>
  );
}
