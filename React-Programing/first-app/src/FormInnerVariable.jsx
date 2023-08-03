import React, { useState } from "react";

export default function FormInnerVariable() {
  const data = [
    {table_name: "user", column_name: "user_id", column_type: "bigint"},
    {table_name: "user", column_name: "email", column_type: "varchar"},
    {table_name: "user", column_name: "first_name", column_type: "varchar"},
    {table_name: "user", column_name: "last_name", column_type: "varchar"},
    {table_name: "user", column_name: "password", column_type: "varchar"},
    {table_name: "user", column_name: "user_number", column_type: "varchar"},
    {table_name: "user", column_name: "status", column_type: "tinyint"},
    {table_name: "user", column_name: "user_name", column_type: "varchar"},
    {table_name: "user", column_name: "userAge", column_type: "varchar"},
    {table_name: "user", column_name: "userName", column_type: "varchar"},
    {table_name: "user", column_name: "userPhoneNumber", column_type: "bigint"}
];

  var output =[];
  var columnList = [];
  var table_name_for_select = "";
  var selectList =[];

  const handleChangeList = (e) =>{
    const {name , checked} = e.target;
    console.log(name);
    console.log(checked);
    if(checked){
      output.push(name)
    }
    else{
      output = output.filter(o=> o !== name);
      console.log(name);
      console.log(output);
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    for (let index = 0; index < output.length; index++) {
      var split = output[index].split(".");
      table_name_for_select = split[0]
        let columnDetail = {
          table_name : split[0],
          column_name : split[1],
        }
        columnList.push(columnDetail);
    }
    console.log(columnList);
    selectList.table_name_for_select = table_name_for_select;
    selectList.columnList = columnList;
    console.log(selectList);
    alert(output)
  }

  return (
    <div>
      <div>
        {data.map((row) => (
          <div>
            <span>
              <input type="checkbox" name={row.table_name+"."+row.column_name} id="" onClick={handleChangeList}/>
            </span>
            <span> </span>
            <span>{row.column_name}</span>
          </div>
        ))}
      </div>
      <button type="button" className="btn btn-primary" onClick={handleSubmit}>
        Submit
      </button>
    </div>
  );
}
