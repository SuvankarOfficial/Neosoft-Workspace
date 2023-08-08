import React from 'react'

export default function List(props) {
  return (
    <div>
        {props.data.map((person)=>
        <div className="card">
          <div>
            <img src={person.image} alt={person.name} className="profileImage"/>
          </div>
          <div className="personDetail" >
            <div style={{fontWeight : "bold"}}>{person.name}</div>
            <div>{person.age}</div>
          </div>
        </div>
        )}
    </div>
  )
}
