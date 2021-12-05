package com.diazmic.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTCalculatorController {
    @Autowired
    private CalculatorController calculatorController;

    @GetMapping("/calculate")
    public double calculateExpression(String expression){
        return calculatorController.calculate(expression);
    }
}
