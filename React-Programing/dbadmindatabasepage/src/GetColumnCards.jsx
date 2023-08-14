import React from 'react'
import GetColumnCard from './GetColumnCard';

 const GetColumnCards = ({getTable, getSelectList}) => {
    const tables = getTable;

    function getTables() {
        return (
            <div className='get-column-cards'>
                {tables.map((table)=>{
            return <GetColumnCard key={table} tableName={table} getSelectList={getSelectList}/>
          })}
            </div>
        )
    }
  return (
      getTables()
  )
}

export default GetColumnCards;