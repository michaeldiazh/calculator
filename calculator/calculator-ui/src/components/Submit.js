import React, { useState } from "react";

/**
 * Creates a Submit component to be rendered in app.js. 
 * Creates a form where one can input an expression.
 * @param {*} props parameter that allows us to get parameters from app.js and map it to this component
 * @returns JMX of a submit form
 */
function Submit(props){
    const[inputString , setInputString] = useState("");
    
    function changeExpression(e){
        e.preventDefault();
        const newInput = inputString
        props.setExpresssion(newInput);
    }

    return(
     <form onSubmit = {changeExpression}>
        <label>
            Input your expression:
            <input type="text" id="expression_input" value ={inputString} onChange={(e)=>setInputString(e.target.value)}/>
            <button type="submit">Submit Expression</button>
        </label>
     </form>
    );
}

export default Submit;