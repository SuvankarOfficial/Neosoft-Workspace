import React from "react";
import GroupByColumn from "./GroupByColumn";

function GroupBy({
  optionsForGroupBy,
  dataGroupByCondition,
  groupByConditionValue,
}) {
  return (
    <div>
      <button
        type="button"
        className="btn btn-info"
        onClick={() => dataGroupByCondition(undefined)}
      >
        +
      </button>
      {groupByConditionValue.map((groupBy, index) => {
        return (
          <div>
            <GroupByColumn
              optionsForGroupBy={optionsForGroupBy}
              dataGroupByCondition={dataGroupByCondition}
              setDataGroupByConditionValue={groupByConditionValue}
              id={index}
            />
          </div>
        );
      })}
    </div>
  );
}

export default GroupBy;
