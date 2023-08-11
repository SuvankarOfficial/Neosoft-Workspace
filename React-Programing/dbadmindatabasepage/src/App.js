import "./App.css";
import Loading from "./Loading";
import React, { useEffect, useState } from "react";
import GetColumnCards from "./GetColumnCards";

function App() {
  const [table, setTable] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedTable, setSelectedTable] = useState([]);
  const [selectedColumnList, setSelectedColumnList] = useState([]);

  var selectList = [];

  const url =
    "http://localhost:9003/query/get-all-tables?userId=1&connectionName=mysqlLocal";

  const getTableData = async () => {
    setLoading(true);
    try {
      const response = await fetch(url);
      const tables = await response.json();
      setTable(tables.data.tableNames);
      setLoading(false);
    } catch (error) {
      setLoading(false);
      console.log(error);
    }
  };

  useEffect(() => {
    getTableData();
  }, []);

  function getData() {
    console.log(selectList);
  }

  const getSelectedColumnList = (name, checked) => {
    if (checked) {
      setSelectedColumnList((data) => [...data, name]);
    } else {
      setSelectedColumnList((data) => {
        data = data.filter((tableName) => tableName !== name);
        return data;
      });
    }
  };

  const setTableNameForSelectedTable = (e) => {
    const { name, checked } = e.target;
    if (checked) {
      setSelectedTable((data) => [...data, name]);
    } else {
      setSelectedTable((selectedTable) => {
        selectedTable = selectedTable.filter((tableName) => tableName !== name);
        return selectedTable;
      });
    }
  };

  function submitData() {
    let columnListData = [];
    let table_name_for_select = "";
    selectedColumnList.map((data) => {
      console.log(data);
      let splitData = data.split(".");
      if (table_name_for_select.length > 0 && table_name_for_select != splitData[0]) {
        let selectData = {
          table_name: table_name_for_select,
          columnList: columnListData,
        };
        selectList.push(selectData);
        columnListData = [];
      }
      table_name_for_select = splitData[0];
      let columnData = {
        table_name: splitData[0],
        column_name: splitData[1],
      };
      columnListData.push(columnData);
    });
    let selectData = {
      table_name: table_name_for_select,
      columnList: columnListData,
    };
    selectList.push(selectData);
    console.log(selectList);
  }

  const getOutputData = async () => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:9003/query/create-query?userId=1&connectionName=mysqllocal",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(selectList),
        }
      );
      const tables = await response.json();
      console.log(tables);
      setLoading(false);
    } catch (error) {
      setLoading(false);
      console.log(error);
    }
  };

  if (loading) {
    return <Loading />;
  }

  if (table.length === 0) {
    return <div>No Data</div>;
  } else {
    return (
      <div className="page">
        <div className="left-column">
          <div className="database-name-as-header">MysqlLocal</div>
          <div className="App">
            {table.map((tableName) => (
              <div key={tableName} className="column-name">
                <input
                  type="checkbox"
                  name={tableName}
                  onClick={setTableNameForSelectedTable}
                />
                <div>{tableName}</div>
              </div>
            ))}
          </div>
          <button onClick={getData}>PrintData</button>
        </div>
        <div>
          <GetColumnCards
            getTable={selectedTable}
            getSelectList={getSelectedColumnList}
          />
        </div>
        <div>
          <button
            type="button"
            className="btn btn-success"
            onClick={submitData}
          >
            Submit
          </button>
          <button
            type="button"
            className="btn btn-danger"
            onClick={getOutputData}
          >
            getOutput
          </button>
        </div>
      </div>
    );
  }
}

export default App;
