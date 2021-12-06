import { useEffect, useState } from 'react';
import './App.css';
import Submit from './components/Submit';



function App() {
  //Create States called Input String and Output String with setterts
  const[inputString ,setInputString] = useState("");
  const[outputString, setOutputString] = useState("");
  
  // Using useEffect to call API request from localHost
  useEffect(()=> { if(inputString !== ""){ // if the input is not empty, call a post request to the api with a json object containning "Expression". Return solution
      fetch('/calculate/', {
          method: 'POST',
          body: JSON.stringify({
            "expression":inputString
          }),
          headers: {
              'Content-type': 'application/json',
              'Accept': 'application/json'
          }
      }).then(response => response.json()).then(json => setOutputString(json))
      .catch(err => {
        // Run error when we can't compute expression
        setOutputString("Cannot compute");
      }
    )
    } else{ // Otherwise set both input and output to an empty string
      setOutputString("");
      setInputString("")
    }  
  }, [inputString]);
  
  return (
    <div className="App">
      <Submit expression={inputString} setExpresssion={setInputString}/>
      <h2>This is your Expression: {inputString}</h2>
      <h3>This is the output of your expression: {outputString}</h3>
   </div>
  );
}

export default App;
