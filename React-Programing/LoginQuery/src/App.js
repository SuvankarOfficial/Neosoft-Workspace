import React, { useLayoutEffect, useRef, useState } from "react";
import data from "./data";
import SingleQuestion from "./Question";
function App() {
  const getHeight = useRef(0);
  const [height, setHeight] = useState(0);

  useLayoutEffect(() => {
    setHeight(getHeight.current.offsetHeight);
  }, []);
  return (
    <div className="main-container">
      <div className="inner-container mt-5">
        <div style={{ height: height + "px" }} className="left-title">
          <h1>Welcome to Login Q&A</h1>
        </div>
        <div style={{ width: "600px" }} ref={getHeight}>
          {data.map((question) => {
            return <SingleQuestion key={question.id} {...question} />;
          })}
        </div>
      </div>
    </div>
  );
}

export default App;
