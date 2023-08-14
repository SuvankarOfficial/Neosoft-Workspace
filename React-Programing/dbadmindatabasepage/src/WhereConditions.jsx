import React from "react";

export default function WhereConditions({optionsForWhere,conditionValue,whereConditionCount}){


    return (
        <>
        <div>Select Column</div>
        <select name="" id="" style={{ width: "200px" }}>
          {optionsForWhere.map((column) => {
            return <option value="column">{column.split(".")[1]}</option>;
          })}
        </select>
        <div>Select Condition</div>
        <select
          className="form-select"
          aria-label="Default select example"
          placeholder="Select Condition"
        >
          {conditionValue.map((data, count) => (
            <option
              name="condition"
              value={conditionConstant[count]}
              key={data}
            >
              {data}
            </option>
          ))}
        </select>
        <div>Enter Value</div>
        <input type="text" placeholder="Enter Value" />
        <button type="button" className="btn btn-info">
          +
        </button>

        </>
    )

}