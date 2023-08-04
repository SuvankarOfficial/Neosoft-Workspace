// import axios from "axios";
import axios from "axios";
import React, { useEffect, useState } from "react";

export default function FormInnerVariable() {
  // var columns = [];
  // function addColumns(column) {
  //   columns.push(column);
  // }
  // axios
  // .get(
  //   "http://localhost:9003/query/get-table-fields?userId=1&tableName=contact&connectionName=mysqllocal"
  // )
  // .then((response)=>{
  //   response.data.data.map((column) => {
  //     addColumns(column);
  //   })
  // })

  var [columns, setColumn] = useState([]);
  const [ whereData, setWhereData ] = useState({table_name:"",column_name:"",column_type:"",valueOne:"",valueTwo:"",condition:"EQ"})

  const fetchColumn = () => {
    // fetch("http://localhost:9003/query/get-table-fields?userId=1&tableName=contact&connectionName=mysqllocal")
    // .then(response => {
    //   console.log(response.json());
    //   return response
    // })
    // .then(column =>{
    //   setColumn(column)
    // })

    // fetch("https://jsonplaceholder.typicode.com/users")
    //   .then(response => {
    //     return response.json()
    //   })
    //   .then(data => {
    //     setColumn(data)
    //   })
    return axios
      .get(
        "http://localhost:9003/query/get-table-fields?userId=1&tableName=contact&connectionName=mysqllocal"
      )
      .then((response) => setColumn(response.data.data));
  };

  useEffect(() => {
    fetchColumn();
  }, []);

  var select = [];
  var where = [];
  var columnList = [];
  var table_name_for_select = "";
  var selectList = [];
  var whereList = [];
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
    console.log(columns);
  };

  const handleWhereCondition = (e)=>{
    const { name, value } = e.target;
    console.log(name);
    console.log(value);
    setWhereData((data) => ({...data, condition:value}));
  }

  const handleWhereColumn = (e)=>{
    console.log("column");
    const value = e.target.value;
    const row = columns.filter(column => column.column_name === value)
    console.log(row);
    setWhereData((data) => ({[data.table_name] :  row[0].table_name}));
    console.log("wheredata");
    console.log(whereData);

  }

  const handleWhereValue = (e)=>{
    const value = e.target.value;
    setWhereData((data) => ({...data, valueOne : value}));
  }

  const handleChangeList = (e) => {
    const { name, checked } = e.target;
    if (checked) {
      select.push(name);
    } else {
      select = select.filter((o) => o !== name);
      console.log(name);
      console.log(select);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    for (let index = 0; index < select.length; index++) {
      var split = select[index].split(".");
      table_name_for_select = split[0];
      let columnDetail = {
        table_name: split[0],
        column_name: split[1],
      };
      columnList.push(columnDetail);
    }
    console.log(columnList);
    selectList.table_name_for_select = table_name_for_select;
    selectList.columnList = columnList;
    // select.push(selectList)
    console.log("select");
    console.log(selectList);

    //Done with select
    console.log("wheredata");
    console.log(whereData);

    let columnDetail = {
      table_name: whereData.table_name,
      column_name: whereData.column_name,
      column_type: whereData.column_type,
      valueOne: whereData.valueOne,
      valueTwo: whereData.valueTwo,
      condition: whereData.condition
    };
    where.table_name = table_name_for_select;
    where.columnDetail = columnDetail;
    console.log(columnDetail);
    console.log(where);
    alert(selectList);
  };

  // function stringData(){
  //   setTimeout(() => {
  //       <div>
  //        {columns.map((row) => (
  //          <div>
  //            <span>
  //              <input
  //                type="checkbox"
  //                name={row.table_name + "." + row.column_name}
  //                id=""
  //                onClick={handleChangeList}
  //              />
  //            </span>
  //            <span> </span>
  //            <span>{row.column_name}</span>
  //          </div>
  //        ))}
  //      </div>
  //   }, 1000);
  // }

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
      <span>
        <select className="form-select" aria-label="Default select example" placeholder="Select Column"  onChange={handleWhereColumn}>
          <option value="null">Please Select Column</option>
          {columns.length > 0 &&
            columns.map((row) => (
              <option value={row.column_name} key={row.column_name}>
                {row.column_name}
              </option>
            ))}
          ;
        </select>
      </span>
      <span>
        <select className="form-select" aria-label="Default select example" placeholder="Select Condition" onChange={handleWhereCondition}>
          {conditionValue.map((data, count) => (
            <option name="condition" value={conditionConstant[count]} key={data}>{data}</option>
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
      <div>
      <button type="button" className="btn btn-primary" onClick={handleSubmit}>
        Submit
      </button>
      <button
        type="button"
        className="btn btn-primary"
        onClick={handlePrintData}>
        PrintData
      </button>
      </div>
    </div>
  );
}
