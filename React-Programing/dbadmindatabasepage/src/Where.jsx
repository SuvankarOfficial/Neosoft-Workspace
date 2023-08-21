import React from "react";
import WhereColumn from "./WhereColumn";

function Where({ optionsForWhere,whereConditionValue,setWhereConditionValue }) {

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
    "NCS",
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
    "NOT CONTAINS",
  ];

  return (
    <div>
      <button
        type="button"
        className="btn btn-info"
        onClick={() => setWhereConditionValue(undefined)}
      >
        +
      </button>
      {whereConditionValue.map((whereColumnsList, index) => {
        return (
          <div>
            <WhereColumn
              conditionConstant={conditionConstant}
              conditionValue={conditionValue}
              optionsForWhere={optionsForWhere}
              dataWhereCondition={setWhereConditionValue}
              setDataWhereConditionValue={setDataWhereConditionValue}
              id={index}
            />
          </div>
        );
      })}
    </div>
  );
}

export default Where;
