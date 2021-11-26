package com.diazmic.calculator.calculation;

import com.diazmic.calculator.calculation.interfaces.Calculable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class DoubleCalculator implements Calculable<Double> {

    public static final DoubleCalculator doubleCalculator = new DoubleCalculator();

    public Double calculate(Double inputOne, Double inputTwo, String operation) throws ArithmeticException, IllegalArgumentException{
        if(inputOne == null || inputTwo == null){
            String exceptionMsg = (inputOne == null && inputTwo == null) ? "Both numbers are null. Please evaluate input." : inputOne == null ? "First Number is null. Please evaluate input." : "Second Number is null. Please evaluate input.";
            throw new IllegalArgumentException(exceptionMsg);
        }
        if(!Calculable.OPERATION_SET.contains(operation)){
            throw new IllegalArgumentException("Operation is not valid. Please evaluate operator.");
        }
        else{
            return calculateExpression(inputOne,inputTwo,operation.charAt(0));
        }
    }

    /**
     * Calculates convertedInputOne and convertedInputTwo operators from the operation character, based on order:
     * inputOne (o) inputTwo, where o is the operator and is in OPERATION_SET = { '+' , '-' , '*' , '/' }.
     * Throws {@code ArithmeticException} if the operation is not apart of OPERATION_SET.
     * Returns output of operation if operation is in OPERATION_SET.
     *
     * @param inputOne First input of operation.
     * @param inputTwo Second input of operation.
     * @param operationCharacter Operation character, where the character is in OPERATION_SET.
     * @return {@code double} the output of the operation. Otherwise, throws an {@code ArithmeticException}
     */
    private double calculateExpression(double inputOne, double inputTwo, char operationCharacter){
        switch (operationCharacter){
            // If the operation character is '+', add the two inputs
            case '+': return addition(inputOne,inputTwo);
            // If the operation character is '-', subtract the two inputs
            case '-': return subtraction(inputOne,inputTwo);
            // If the operation character is '*', multiply the two inputs
            case '*': return multiplication(inputOne,inputTwo);
            // If the operation character is '/', divide the two inputs
            case '/': return division(inputOne,inputTwo);
            // Otherwise, throw ArithmeticException
            default: throw new ArithmeticException("Cannot evaluate the two inputs with operator.");
        }
    }

    /**
     * Checks if the two inputs of the operation is null or blank.
     * @param inputOne First Input of Operation
     * @param inputTwo Second Input of Operation
     * @return {@code true} if one of the inputs are null or blank. Otherwise, return {@code false}.
     */
    private boolean inputsAreNullOrEmpty(String inputOne, String inputTwo) {
        return  inputOne == null || inputTwo == null || inputOne.isEmpty() || inputTwo.isEmpty() || inputOne.isBlank() || inputTwo.isBlank();
    }

    /**
     * Subtracts two {@code double} inputs:
     *          inputOne - inputTwo.
     * Returns result of operation.
     * @param inputOne First input of expression
     * @param inputTwo Second input of expression
     * @return {@code double} output of the expression.
     */
    private double subtraction(double inputOne, double inputTwo){
        return inputOne - inputTwo;
    }

    /**
     * Adds two {@code double} inputs:
     *          inputOne + inputTwo.
     * Returns result of operation.
     * @param inputOne First input of expression
     * @param inputTwo Second input of expression
     * @return {@code double} output of the expression.
     */
    private double addition(double inputOne, double inputTwo){
        return inputOne + inputTwo;
    }

    /**
     * Multiples two {@code double} inputs:
     *          inputOne * inputTwo.
     * Returns result of operation.
     * @param inputOne First input of expression
     * @param inputTwo Second input of expression
     * @return {@code double} output of the expression.
     */
    private double multiplication(double inputOne, double inputTwo){
        return inputOne * inputTwo;
    }

    /**
     * Divides two {@code double} inputs:
     *          inputOne / inputTwo.
     * If inputTwo is 0. System will throw {@code ArithmeticException}.
     * Otherwise, returns result of operation.
     * @param inputOne First input of expression
     * @param inputTwo Second input of expression
     * @return {@code double} output of the expression.
     */
    private double division(double inputOne, double inputTwo) throws ArithmeticException{
        if(inputTwo == 0.0){
            throw new ArithmeticException("Division of inputOne over 0. Output is undefined.");
        }
        return inputOne / inputTwo;
    }
}
