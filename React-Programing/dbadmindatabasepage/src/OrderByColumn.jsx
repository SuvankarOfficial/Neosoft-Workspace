import React, { useState } from "react";

export default function OrderByColumn({
  optionsForOrderBy,
  dataOrderByCondition,
  setDataOrderByConditionValue,
  id,
}) {
  const [finalValue, setFinalValue] = useState("");
  function setName(e) {
    const [value] = e.target;
    setFinalValue(value);
    // setDataOrderByConditionValue(id, value);
  }
  return (
    <div className="order-by-column">
      <div>Select Column</div>
      <select name="" id="" style={{ width: "200px" }}>
        <option value="null">Select Column</option>
        {optionsForOrderBy.map((column) => {
          return (
            <option value="column" onClick={setName}>
              {column.split(".")[1]}
            </option>
          );
        })}
      </select>
      <button
        type="button"
        className="btn btn-danger"
        onClick={()=>dataOrderByCondition(id)}
      >
        -
      </button>
    </div>
  );
}
