import React, { useState } from "react";
import Where from "./Where";
import OrderBy from "./OrderBy";
import GroupBy from "./GroupBy";

function ConditionQuery({ queryCondition, setValueQueryCondition, selectedColumnName }) {
  const [whereActive, setWhereActive] = useState(true);
  const [orderByActive, setOrderByActive] = useState(false);
  const [groupByActive, setGroupByActive] = useState(false);

  function setWhereTabActive() {
    setValueQueryCondition(true);
    setWhereActive(true);
    setOrderByActive(false);
    setGroupByActive(false);
  }

  function setOrderByTabActive() {
    setValueQueryCondition(true);
    setWhereActive(false);
    setOrderByActive(true);
    setGroupByActive(false);
  }

  function setGroupByTabActive() {
    setValueQueryCondition(true);
    setWhereActive(false);
    setOrderByActive(false);
    setGroupByActive(true);
  }

  function getBody() {
    if (queryCondition) {
      return (
        <div style={{ height: "200px" }}>
          {whereActive && <Where optionsForWhere={selectedColumnName}/>}
          {orderByActive && <OrderBy />}
          {groupByActive && <GroupBy />}
        </div>
      );
    } else {
      return <div></div>;
    }
  }

  return (
    <div>
      <div className="condition-container">
        <button
          type="button"
          className="btn btn-info"
          onClick={() => setWhereTabActive()}
        >
          Where
        </button>
        <button
          type="button"
          className="btn btn-info"
          onClick={() => setOrderByTabActive()}
        >
          Order By
        </button>
        <button
          type="button"
          className="btn btn-info"
          onClick={() => setGroupByTabActive()}
        >
          Group By
        </button>
        {queryCondition ? (
          <button
            type="button"
            className="btn btn-info"
            onClick={() => setValueQueryCondition(false)}
          >
            decrease
          </button>
        ) : (
          <button
            type="button"
            className="btn btn-info"
            onClick={() => setValueQueryCondition(true)}
          >
            increase
          </button>
        )}
      </div>
      <div>{getBody()}</div>
    </div>
  );
}

export default ConditionQuery;
