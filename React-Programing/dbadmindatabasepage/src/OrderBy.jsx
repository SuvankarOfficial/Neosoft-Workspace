import React from "react";
import OrderByColumn from "./OrderByColumn";

function OrderBy({ optionsForOrderBy, dataOrderByCondition, orderByConditionValue }) {
  return (
    <div>
      <button
        type="button"
        className="btn btn-info"
        onClick={() =>dataOrderByCondition(undefined)}
      >
        +
      </button>
      {orderByConditionValue.map((orderBy,index)=>{
        return <div>
        <OrderByColumn
          optionsForOrderBy={optionsForOrderBy}
          dataOrderByCondition={dataOrderByCondition}
          setDataOrderByConditionValue={orderByConditionValue}
          id={index}
        />
      </div>
})
      }
    </div>
  );
}

export default OrderBy;
