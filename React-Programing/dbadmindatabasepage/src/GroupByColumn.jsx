import React, { useState } from "react";

export default function GroupByColumn({
  optionsForGroupBy,
  dataGroupByCondition,
  setDataGroupByConditionValue,
  id,
}) {
  // const [finalValue, setFinalValue] = useState("");
  function setName(e) {
    const [value] = e.target;
    console.log(value);
    // setFinalValue(value);
    setDataGroupByConditionValue(id, value);
  }
  return (
    <div className="group-by-column">
      <div>Select Column</div>
      <select name="" id="" style={{ width: "200px" }}>
        <option value="null">Select Column</option>
        {optionsForGroupBy.map((column,index) => {
          return (
            <option key={index} value={column} onChange={()=>setName}>
              {column.split(".")[1]}
            </option>
          );
        })}
      </select>
      <button
        type="button"
        className="btn btn-danger"
        onClick={()=>dataGroupByCondition(id)}
      >
        -
      </button>
    </div>
  );
}
