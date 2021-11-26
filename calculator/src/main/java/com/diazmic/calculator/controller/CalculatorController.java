package com.diazmic.calculator.controller;

import com.diazmic.calculator.engine.BinaryCalculationEngine;
import com.diazmic.calculator.parser.ExpressionParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorController {
    @Autowired
    private BinaryCalculationEngine engine;

    public double calculate(String expression) {
        double answer = 0;
        try {
            answer = engine.calculateExpression(ExpressionParser.createExpressionArray(expression));
        } catch (Exception e) {
            throw new ArithmeticException("Cannot Calculate Expression");
        }
        return answer;
    }
}
