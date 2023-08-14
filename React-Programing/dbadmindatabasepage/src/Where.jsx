import React, { useState } from "react";
import WhereColumn from "./WhereColumn";

function Where({ optionsForWhere }) {
  const [whereConditionValue, setWhereConditionValue] = useState([""]);

  function addnewDataWhereCondition(id) {
    if (id === undefined)
      setWhereConditionValue((data) => [...data,""]);
    else {
      setWhereConditionValue(whereConditionValue.filter((data,index) => index !== id))
    }
  }

  function setDataWhereConditionValue(id, value) {
    whereConditionValue[id] = value;
  }

  const conditionConstant = [
    "EQ",
    "NEQ",
    "LT",
    "LTEQ",
    "GT",
    "GTEQ",
    "BWT",
    "CS",
    "NCS"
];
  const conditionValue = [
    "EQUALS",
    "NOT EQUALS",
    "LESS THAN",
    "LESS THAN OR EQUAL TO",
    "GREATER THAN",
    "GREATER THAN OR EQUAL TO",
    "BETWEEN",
    "CONTAINS",
    "NOT CONTAINS"
  ];

  return (
    <div>
      {whereConditionValue.map((whereColumnsList, index) => {
        return (
          <WhereColumn
            conditionConstant={conditionConstant}
            conditionValue={conditionValue}
            optionsForWhere={optionsForWhere}
            addnewDataWhereCondition={addnewDataWhereCondition}
            setDataWhereConditionValue={setDataWhereConditionValue}
            id={index}
          />
        );
      })}
    </div>
  );
}

export default Where;
