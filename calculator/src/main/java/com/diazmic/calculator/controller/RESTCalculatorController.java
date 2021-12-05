package com.diazmic.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RESTCalculatorController {
    @Autowired
    private CalculatorController calculatorController;

    @GetMapping(value = "/calculate")
    @ResponseBody
    public double calculateExpression(@RequestBody Map<String,String> expression){
        double answer = calculatorController.calculate(expression.get("expression"));
        System.out.println(answer);
        return answer;
    }
}
