package com.diazmic.calculator.engine;

import com.diazmic.calculator.calculation.interfaces.Calculable;
import com.diazmic.calculator.conversion.interfaces.Convertible;
import com.diazmic.calculator.engine.interfaces.CalculatorEngine;
import com.diazmic.calculator.engine.operation.OperatorComparator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Stack;

public class BinaryCalculationEngine implements CalculatorEngine {
    @Autowired
    private Convertible doubleConvertible;

    @Autowired
    private Calculable<Double> doubleCalculable;

    private final Comparator<Character> operatorComparator = new OperatorComparator();

    @Override
    public String convertForCalculation(String inputString) {
        Stack<Character> operationStack = new Stack<>();
        StringBuilder postfixBuilder = new StringBuilder();
        StringBuilder numberHolderBuilder = new StringBuilder();
        boolean decimalFlag = false;
        for(String token : inputString.split("^[0-9]$")){
            if(token.matches("^[0-9]$")){
                numberHolderBuilder.insert(0, token);
            }
            else if(token.matches("^.$") && !decimalFlag ){
                numberHolderBuilder.append(token);
                decimalFlag = true;
            }
            else if (Calculable.OPERATION_SET.contains(token)){
                operationStack.push(token.charAt(0));
            }

        }
        return null;
    }

    @Override
    public double calculate(String inputString) {
        return 0;
    }
}
