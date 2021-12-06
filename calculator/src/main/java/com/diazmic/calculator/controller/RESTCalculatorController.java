package com.diazmic.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RESTCalculatorController {
    @Autowired
    private CalculatorController calculatorController;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/calculate")
    @ResponseBody
    public String calculateExpression(@RequestBody Map<String,String> expression){
        String answer;
        try {
            answer =String.valueOf(calculatorController.calculate(expression.get("expression")));
            System.out.println(answer);
            return answer;
        }
        catch (Exception e){
            answer = "Cannot Compute Expression";
        }
        return answer;
    }
}
