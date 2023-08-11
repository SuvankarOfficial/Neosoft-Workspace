import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import About from './About';


function App() {
  const [count, setCount] = useState(0);

  function handleClick(){
    setCount(count+1);
  }

  return (
    <>
    <MyButton count={count} onClick={handleClick}/>
    <MyButton count={count} onClick={handleClick}/>
    </>
  );
  
}

export function MyButton({count,onClick}) {
  return (
   <>
   <button onClick={onClick}>
    you have {count} count
  </button>
  <About />
  </>
  );  
}

export default App;
