import React from "react";
import Review from "./Review";
function App() {
  return (
    <div className="main">
      <div className="header mb-5">
        <h1>Our Reviews</h1>
        <hr style={{width: "100px", color: "pink", innerHeight : "100px"}} class="dashed"/>
      </div>
      <div className="app">
        <Review />
      </div>
    </div>
  );
}

export default App;
