import React, { useState } from "react";
import { AiOutlineMinus, AiOutlinePlus } from "react-icons/ai";
const Question = ({ id, title, info }) => {
  const [showInfo, setShowInfo] = useState(false);
  function setInverseValueOfShowInfo() {
    console.log(showInfo);
    setShowInfo(!showInfo);
  }
  return (
    <div>
      <div className="question-card">
        <div className="question-card-title">
          <div>{title}</div>
          <button
            type="button"
            className="btn btn-outline-danger"
            onClick={() => setInverseValueOfShowInfo()}
          >
            {showInfo ? <AiOutlineMinus /> : <AiOutlinePlus />}
          </button>
        </div>
        {showInfo && <p>{info}</p>}
      </div>
    </div>
  );
};

export default Question;
