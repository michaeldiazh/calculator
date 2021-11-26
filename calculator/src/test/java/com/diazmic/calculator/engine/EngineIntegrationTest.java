package com.diazmic.calculator.engine;

import com.diazmic.calculator.calculation.DoubleCalculator;
import com.diazmic.calculator.conversion.StringToNumberConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class EngineIntegrationTest {
    @Autowired
    private BinaryCalculationEngine testEngine;

    @Autowired
    private DoubleCalculator calculator;

    @Autowired
    private StringToNumberConverter converter;

    @Test
    public void Test_1_1_CalculateSimpleExpression(){
        String[] postFixExpression = {"-2","-3","+"};
        double expectedResult = -5.0;
        Assertions.assertEquals(expectedResult,testEngine.calculate(postFixExpression));
    }


    @Test
    public void Test_1_2_CalculateIntermediateExpression(){
        String[] postFixExpression = {"-3.0","2142.3","-5","6","+","7.3","8","+","*","*","+"};
        double expectedResult = 32774.19;
        Assertions.assertEquals(expectedResult,testEngine.calculate(postFixExpression));
    }

    @Test
    public void Test_1_3_CalculateAdvanceExpression(){
        double expectedResult = -31995.24628;
        Assertions.assertEquals(expectedResult,testEngine.calculate(testEngine.convertForCalculation(new String[]{"(", "(", "(", "124.4251", "+", "2421.3", ")", ")", "-", "12414.2421", "*", "(", "(", "-", "3.24", "+", "3.6", ")", "+", "5", ")", "+", "4.12", ")", "/", "2"})),0.00001);
    }

    @Test
    public void Test_1_4_CalculateSimpleParenthesesExpression(){
        double expectedResult = 3.5;
        Assertions.assertEquals(expectedResult,testEngine.calculate(testEngine.convertForCalculation(new String[]{"(","4","+","3",")","/","2"})),0.00001);
    }

    //(((((4+-5)*3)+(3+8))/2)+5)+7
    @Test
    public void Test_1_5_CalculateHighlyAdvanceInput(){
        double expectedResult = 16.0;
        Assertions.assertEquals(expectedResult,testEngine.calculate(testEngine.convertForCalculation(new String[]{"(","(","(","(","(","4","+","-","5",")","*","3",")","+","(","3","+","8",")",")","/","2",")","+","5",")","+","7"})));
    }



}
