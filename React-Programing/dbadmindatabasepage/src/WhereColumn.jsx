import React, { useState } from "react";

export default function WhereColumn({
  optionsForWhere,
  conditionConstant,
  conditionValue,
  addnewDataWhereCondition,
  setDataWhereConditionValue,
  id,
}) {
  const [finalValue, setFinalValue] = useState("/EQ/");
  console.log(id);
  function setName(e) {
    const [value] = e.target;
    if (value !== "null") {
      let values = finalValue.split("/");
      setFinalValue(value + "/" + values[1] + "/" + values[2]);
      setDataWhereConditionValue(id, value);
    }
  }
  function setCondition(e) {
    const [value] = e.target;
    let values = finalValue.split("/");
    setFinalValue(values[0] + "/" + value + "/" + values[2]);
    setDataWhereConditionValue(id, value);
  }
  function setValue(e) {
    const [value] = e.target;
    let values = finalValue.split("/");
    setFinalValue(values[0] + "/" + values[1] + "/" + value);
    setDataWhereConditionValue(id, value);
  }

  return (
    <div className="where-column">
      <div>Select Column</div>
      <select name="" id="" style={{ width: "200px" }}>
        <option value="null">Select Column</option>
        {optionsForWhere.map((column) => {
          return <option value="column" onClick={setName}>{column.split(".")[1]}</option>;
        })}
      </select>
      <div>Select Condition</div>
      <select
        className="form-select"
        aria-label="Default select example"
        placeholder="Select Condition"
      >
        {conditionValue.map((data, count) => (
          <option name="condition" value={conditionConstant[count]} key={data}>
            {data}
          </option>
        ))}
      </select>
      <div>Enter Value</div>
      <input type="text" placeholder="Enter Value" />
      <button
        type="button"
        className="btn btn-info"
        onClick={()=>addnewDataWhereCondition(undefined)}
      >
        +
      </button>
    </div>
  );
}
