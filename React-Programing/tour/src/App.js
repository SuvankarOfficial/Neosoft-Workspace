import React, { useState, useEffect } from "react";
import Loading from "./Loading";
import Tours from "./Tours";
// ATTENTION!!!!!!!!!!
// I SWITCHED TO PERMANENT DOMAIN
const url = "https://course-api.com/react-tours-project";

function App() {
  // fetch.get(url)

  const [tourdata, setTour] = useState([]);
  const [loading, setLoading] = useState(true);

  function removeTourData(id) {
    const tour = tourdata.filter((data) => data.id !== id);
    setTour(tour);
  }

  const getTourData = async () => {
    setLoading(true);
    try {
      const response = await fetch(url);
      const tours = await response.json();
      tours.map(tour=>{
        console.log(tour);
      })
      setTour(tours);
      setLoading(false);
    } catch (error) {
      setLoading(false);
      console.log(error);
    }
  };

  useEffect(() => {
    getTourData();
  }, []);

  if (loading) {
    return (
      <div>
        <Loading />
      </div>
    );
  } 

  if (tourdata.length == 0) {
    return <p>No Data</p>;
  } else {
    console.log("Sending Data to Tours");
    return (
      <div className="app-container">
        <Tours tours={tourdata} removeTourData={removeTourData}/>
      </div>
    );
  }
}

export default App;
