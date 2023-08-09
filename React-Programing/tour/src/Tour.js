import React, { useState } from 'react';

const Tour = ({id, image, info,name,price, removeTourData}) => {

  // console.log("tour id:", id);
  const [readMore,setreadMore] = useState(false)

  function readMoreOrShowLess(){
    if(readMore)
    return ("Show Less")
    else 
    return ("Read More")
  }

  return <div className='tour-container'>
    <img src={image} alt={name} className='tour-image'/>
    <div className='tour-detail'>
      <div className='tour-detail-header'>
        <h3>{name}</h3>
        <div><i className="fa fa-inr">{price}</i></div>
      </div>
      <div className='mt-1'>{readMore ? info : info.substring(0,200)+"..."}
      </div>
      <div className='tour-detail-button mt-2'>        
      <button onClick={()=>setreadMore(!readMore)} className="btn btn-outline-danger tour-button">{readMore ? "Show Less" : "Read More"}</button>
      <button type="button" onClick={()=>removeTourData(id)} className="btn btn-outline-danger tour-button">Not Interested</button>
      </div>
    </div>
  </div>;
};

export default Tour;
