import React, { useState } from 'react';
import Menu from './Menu';
import Categories from './Categories';
import items from './data';

function App() {
  const [menu, setmenu] = useState(items);
  const [category, setcategory] = useState(["All",...new Set(items.map(item=> item.category))])

  function setMenuAccordingToCategory(category) {
    console.log(category);
    if(category === "All"){
      setmenu(items)
    }    
    else{
      setmenu(items.filter(item => item.category === category))
    }
  }

  return <div>
    <div className='title'>
    <h1 style={{color : "Red"}}>Our Menu</h1>
  </div>
  <div className='underline'></div>
  <Categories categories={category} setMenuAccordingToCategory={setMenuAccordingToCategory}/>
  <Menu menu={menu}/>
  </div>;
}

export default App;
