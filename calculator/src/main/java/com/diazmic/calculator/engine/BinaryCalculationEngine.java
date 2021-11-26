package com.diazmic.calculator.engine;

import com.diazmic.calculator.calculation.DoubleCalculator;
import com.diazmic.calculator.calculation.interfaces.Calculable;
import com.diazmic.calculator.conversion.StringToNumberConverter;
import com.diazmic.calculator.conversion.interfaces.Convertible;
import com.diazmic.calculator.engine.interfaces.CalculatorEngine;
import com.diazmic.calculator.engine.operation.OperatorComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Stack;

@Service
public class BinaryCalculationEngine implements CalculatorEngine {
    private static final String LEFT_PARENTHESES= "(";
    private static final String RIGHT_PARENTHESES= ")";

    @Autowired
    private StringToNumberConverter doubleConvertible;

    @Autowired
    private DoubleCalculator doubleCalculable;

    private final Comparator<Character> operatorComparator = new OperatorComparator();

    @Override
    public String[] convertForCalculation(String[] expression) {
        return createPostFixArray(expression);
    }

    public double calculateExpression(String[] expression){
        String[] posfix = convertForCalculation(expression);
        return calculate(posfix);
    }
    @Override
    public double calculate(String[] postFixExpression) {
        if(postFixExpression == null||postFixExpression.length == 0){
            throw new ArithmeticException("Post Fix Expression Stack is empty or null. Please look at your input");
        }
        Stack<Double> calculatedStack = new Stack<>();
        for(String token: postFixExpression){
            if(isANumber(token)){
                //Check if integer, make it double
                if(token.matches("^-?[0-9]+$")){
                    token = token + ".0";
                }
                calculatedStack.push(doubleConvertible.convertToDouble(token));
            }
            else if(isOperation(token)){
                double rightHandSide  = calculatedStack.pop(), leftHandSide = calculatedStack.pop();
                calculatedStack.push(doubleCalculable.calculate(leftHandSide,rightHandSide,token));
            }
        }
        return calculatedStack.pop();
    }

    private static String[] createPostFixArray(String[] expression) {
        if(expression == null || expression.length == 0)
            throw new ArithmeticException("Expression array is empty or null. Please look at your input");
        Stack<String> postFixStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        boolean numberIsNegative = false;
        boolean pushedNumber = false;
        for(String token : expression){

            // If it is Parentheses
            if(isParentheses(token)){
                if(token.equals(LEFT_PARENTHESES)){
                    if(pushedNumber) {
                        throw new IllegalArgumentException("Faux multiplication i.e \"number(\". Please check expression");
                    }
                    operationStack.push(token.charAt(0));
                }

                else{
                   while(!operationStack.isEmpty()){
                       char operator = operationStack.pop();
                       if(operator != '(') {
                           postFixStack.push(String.valueOf(operator));
                       }
                       else{
                           break;
                       }
                   }
                }

            }

            // If it is a number, push to postFix Stack
            else if(isANumber(token)){
                if(numberIsNegative){
                    token = operationStack.pop().toString() + token;
                    numberIsNegative = false;
                }
                postFixStack.push(token);
                pushedNumber = true;
            }
            // If it is a Operation, do the following
            else if(isOperation(token)) {
                // If we pushed Number before
                if (pushedNumber) {
                    // If Operation Stack is not empty and the top operator has more priority then the token
                    if(!operationStack.isEmpty() && currOperationBigger(operationStack.peek(), token.charAt(0))) {
                            while (!operationStack.isEmpty()) {
                                postFixStack.push(operationStack.pop().toString());
                        }
                    }
                }
                //If we did not push number before
                else{
                    // And token is "-" then the next number is negative
                    if(token.equals("-") && !numberIsNegative){
                        numberIsNegative = true;

                    }
                    else if(!(isParentheses(token) && isParentheses(operationStack.pop().toString()))){
                        throw new IllegalArgumentException("Expression has 2 operators at the same time. Please check");
                    }
                }
                operationStack.push(token.charAt(0));
                pushedNumber = false;
            }
        }
        while(!operationStack.isEmpty()){
            postFixStack.push(operationStack.pop().toString());
        }
        return createArrayFromStack(postFixStack);
    }

    private static boolean isParentheses(String token) {
        return token.equals(LEFT_PARENTHESES)||token.equals(RIGHT_PARENTHESES);
    }

    private static boolean topOfOperationStackIsNotAParentheses(Character peek) {
        return Calculable.OPERATION_SET.contains(peek.toString());

    }

    private static String[] createArrayFromStack(Stack<String> postFixStack) {
        String[] postFixArray = new String[postFixStack.size()];
        for(int i = postFixArray.length-1 ; i > -1 ; i--){
            postFixArray[i] = postFixStack.pop();
        }
        return postFixArray;
    }

    private static boolean currOperationBigger(Character peek, char token) {
        return OperatorComparator.operatorComparator.compare(peek,token) >= 0;
    }

    private static boolean isOperation(String expression) {
        return Calculable.OPERATION_SET.contains(expression);
    }

    private static boolean isANumber(String expression) {
        return expression.matches("-?[0-9]+") || expression.matches("-?[0-9]+\\.?[0-9]+")|| expression.matches("-?\\.[0-9]+");
    }


}
