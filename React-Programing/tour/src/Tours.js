import React from "react";
import Tour from "./Tour";
const Tours = ({ tours, removeTourData }) => {
  console.log("sending Data to tour");
  return (
    <div className="tours-container">
      {tours.map((tour) => {
        return <div className="mt-4"><Tour {...tour} removeTourData={removeTourData} /></div>;
      })}
    </div>
  );
};

export default Tours;
