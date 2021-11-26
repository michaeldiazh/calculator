package com.diazmic.calculator.parser;

import com.diazmic.calculator.calculation.interfaces.Calculable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExpressionParser {
    public static String[] createExpressionArray(String expression){
        if(expression == null || expression.isEmpty() ||expression.isBlank() )
            throw new IllegalArgumentException("Expression Is Empty, Blank, Or Null. Check Input");
        expression = expression.replaceAll("\\s","");
        if(expressionIsInvalid(expression)){
            throw new IllegalArgumentException("Expression contains non numbers and non operators.");
        }
        else if(parenthesesAreWrong(expression)){
            throw new IllegalArgumentException("Expression contains incorrect parentheses group!");
        }
        String[] extractedNumberArray = getAllNumbersFromExpression(expression);
        return makeExpressionArray(expression,extractedNumberArray);

    }

    private static String[] makeExpressionArray(String expression, String[] extractedNumberArray) {
        List<String> expressionList = new ArrayList<>();
        int eyesIndex = 0;
        int numbersIndex = 0;
        while(eyesIndex < expression.length()){
            String substringEye = expression.substring(eyesIndex,eyesIndex+1);
            if(isOperator(substringEye)){
                expressionList.add(substringEye);
                eyesIndex++;
            }
            else if(isNumber(substringEye)){
                String expectedNumber = expression.substring(eyesIndex,eyesIndex+extractedNumberArray[numbersIndex].length());
                if(!expectedNumber.equals(extractedNumberArray[numbersIndex])){
                    throw new IllegalArgumentException("Number Array is wrong. Please look at your input!");
                }
                expressionList.add(extractedNumberArray[numbersIndex]);
                eyesIndex += extractedNumberArray[numbersIndex].length();
                numbersIndex++;
            }
        }
        return expressionList.toArray(String[]::new);
    }

    private static boolean isNumber(String substringEye) {
        return substringEye.matches("^[0-9]$");
    }


    private static boolean isOperator(String substringEye) {
        return Calculable.OPERATION_SET.contains(substringEye) || substringEye.equals("(")||substringEye.equals(")");
    }

    private static boolean parenthesesAreWrong(String expression) {
        if(isFauxMultiplication(expression))
            return true;
        else if(areOperatorContainInParenthesesWithoutNumbers(expression))
            return true;
        else if(areParenthesesInsideHaveSpacesOrLonelyOperators(expression)){
            return true;
        }
        int parenthesesCtr = 0;
        String allParentheses = expression.replaceAll("([0-9+*\\-/.])+", "");
        for(String parentheses: allParentheses.split("")){
            parenthesesCtr = (parentheses.equals("(")) ? parenthesesCtr+1 :((parentheses.equals(")"))?parenthesesCtr-1:parenthesesCtr);
        }
        return parenthesesCtr!=0;
    }

    private static boolean areParenthesesInsideHaveSpacesOrLonelyOperators(String expression) {
       return expression.replaceAll("\\(+[\\s*\\+\\*\\-/]*\\)+","").length() != expression.length();
    }


    private static boolean isFauxMultiplication(String expression){
        return expression.replaceAll("([0-9]\\(+)+","").length() != expression.length() ||
                expression.replaceAll("(\\)+[0-9])+","").length() != expression.length();
    }

    private static boolean areOperatorContainInParenthesesWithoutNumbers(String expression){
        return expression.replaceAll("(\\(+[+\\-*/]+\\(+)+","").length() != expression.length() ||
        expression.replaceAll("(\\)+[+\\-*/]+\\)+)+","").length() != expression.length();
    }

    private static boolean expressionIsInvalid(String expression) {
        String expressionWithOutNumbersOrOperators = expression.replaceAll("([0-9+*\\-()./])+","");
        return !expressionWithOutNumbersOrOperators.isBlank() || expressionWithOutNumbersOrOperators.length() > 0;
    }

    private static String[] getAllNumbersFromExpression(String expression) {
        List<String> unfilteredStringList = new ArrayList<>(List.of(expression.split("[\\+\\-\\*/\\(\\)]+")));
        unfilteredStringList.removeAll(Collections.singleton(""));
        return unfilteredStringList.toArray(String[]::new);
    }
}
