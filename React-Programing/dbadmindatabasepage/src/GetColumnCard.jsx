import React, { useEffect, useState } from "react";
import Loading from "./Loading";

const GetColumnCard = ({ tableName, getSelectList }) => {
  const url =
    "http://localhost:9003/query/get-all-table-fields?userId=1&connectionName=mysqllocal&tableName=" +
    tableName;

  const [loading, setLoading] = useState(false);
  const [columnDetails, setColumnDetails] = useState([]);

  const getColumn = async () => {
    setLoading(true);
    try {
      const response = await fetch(url);
      const columns = await response.json();
      setColumnDetails(columns.data);
      setLoading(false);
    } catch (error) {
      setLoading(false);
      console.log(error);
    }
  };

  useEffect(() => {
    getColumn();
  }, []);

  if (loading) {
    return <Loading />;
  }

  function passToSelectList(e){
    const {name,checked} = e.target;
    getSelectList(name,checked);
  }

  return (
    <div className="get-column-card">
      <h3>{tableName}</h3>
      {columnDetails.map((column) => (
        <div key={column.column_name}>
          <input type="checkbox" name={column.table_name+"."+column.column_name} id="" onClick={passToSelectList}/>
          {column.column_name}
        </div>
      ))}
    </div>
  );
};

export default GetColumnCard;
