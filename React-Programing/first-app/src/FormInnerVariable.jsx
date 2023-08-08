// import axios from "axios";
import axios from "axios";
import React, { useEffect, useState } from "react";

export default function FormInnerVariable() {
 
  const [selectedColumns,setSelectedColumns] = useState([]);
  var [columns, setColumn] = useState([]);
  const [whereData, setWhereData] = useState({
    table_name: "",
    column_name: "",
    column_type: "",
    valueOne: "",
    valueTwo: "",
    condition: "EQ",
  });

  const fetchColumn = () => {
     return axios
      .get(
        "http://localhost:9003/query/get-table-fields?userId=1&tableName=contact&connectionName=mysqllocal"
      )
      .then((response) => setColumn(response.data.data));
  };

  useEffect(() => {
    fetchColumn();
  }, []);

  const [outputData,setOutputData] = useState({
    select_list: [],
    where_list: []
  });
  var select=[];

  var columnList = [];
  var table_name_for_select = "";
  var where = [];
  var whereList = []
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
  const handlePrintData = (e) => {
    console.log(outputData);
  };

  const handleWhereCondition = (e) => {
    const { name, value } = e.target;
    setWhereData((data) => ({ ...data, condition: value }));
  };

  const handleWhereColumn = (e) => {
    const value = e.target.value;
    let row = value.split(".");
    setWhereData((data) => ({
      ...data,
      table_name: row[0],
      column_name: row[1],
      column_type: row[2],
    }));
  };

  const handleWhereValue = (e) => {
    const value = e.target.value;
    setWhereData((data) => ({ ...data, valueOne: value }));
  };

  const handleChangeList = (e) => {
    const { name, checked } = e.target;
    if (checked) {
      setSelectedColumns((data)=>{
          if(data.indexOf(name) === -1)
            data.push(name)
        return data;
      });
    } else {
      setSelectedColumns((data)=>{
        return data.filter((column)=> column !== name);
      });
    }
    console.log(selectedColumns);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    selectedColumns.map((column) => {
      let split = column.split(".");
      table_name_for_select = split[0];
      let selectData = {
        table_name : split[0],
        column_name : split[1]
      }
      columnList.push(selectData);
    });
    let selectColumn = {
      table_name: table_name_for_select,
      column_list: columnList,
    };
    // setSelect((data)=>{
      select.push(selectColumn);
      // return data;
    // });
  

    //Done with select
    let columnDetail_for_where = {
      table_name: whereData.table_name,
      column_name: whereData.column_name,
      column_type: whereData.column_type,
      valueOne: whereData.valueOne,
      valueTwo: whereData.valueTwo,
      condition: whereData.condition,
    };
    whereList.push(columnDetail_for_where);
    let whereColumn = {
      table_name: whereData.table_name,
      column_list: whereList,
    };
    where.push(whereColumn);
    outputData.select_list = select;
    outputData.where_list = where;
    setOutputDataF(e)
    console.log(outputData);
  };

  const setOutputDataF = async (e) => {
    e.preventDefault();
    console.log(outputData);
    const responseData = await axios.post(
      "http://localhost:9003/query/create?userId=1&connectionName=mysqllocal",
      outputData,
      {headers: {"Content-Type": "application/json"}}
    )
    console.log(outputData);
    console.log(responseData);
  }

  return (
    <div>
      <div>
        {columns.length > 0 && (
          <ul>
            {columns.map((row) => (
              <li key={row.column_name}>
                <span>
                  <input
                    type="checkbox"
                    name={row.table_name + "." + row.column_name}
                    onClick={handleChangeList}
                  />
                </span>
                <span> </span>
                <span>{row.column_name}</span>
              </li>
            ))}
          </ul>
        )}
      </div>
      <div>
        <span>
          <select
            className="form-select"
            aria-label="Default select example"
            placeholder="Select Column"
            onChange={handleWhereColumn}
          >
            <option value="null">Please Select Column</option>
            {columns.length > 0 &&
              columns.map((row) => (
                <option
                  value={
                    row.table_name +
                    "." +
                    row.column_name +
                    "." +
                    row.column_type
                  }
                  key={row.column_name}
                >
                  {row.column_name}
                </option>
              ))}
            ;
          </select>
        </span>
        <span>
          <select
            className="form-select"
            aria-label="Default select example"
            placeholder="Select Condition"
            onChange={handleWhereCondition}
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
        </span>
        <span className="form-group">
          <input
            type="text"
            className="whereValue"
            placeholder="Enter Value"
            onChange={handleWhereValue}
          />
        </span>
      </div>
      <div>
        <button
          type="button"
          className="btn btn-primary"
          onClick={handleSubmit}
        >
          Submit
        </button>
        <button
          type="button"
          className="btn btn-primary"
          onClick={setOutputDataF}
        >
          GetOutPut
        </button>
        <button
          type="button"
          className="btn btn-primary"
          onClick={handlePrintData}
        >
          PrintData
        </button>
      </div>
    </div>
  );
}
