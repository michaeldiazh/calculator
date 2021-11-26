package com.diazmic.calculator.engine;

import com.diazmic.calculator.calculation.DoubleCalculator;
import com.diazmic.calculator.calculation.interfaces.Calculable;
import com.diazmic.calculator.conversion.StringToNumberConverter;
import com.diazmic.calculator.conversion.interfaces.Convertible;
import com.diazmic.calculator.engine.BinaryCalculationEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EngineTest {
    @InjectMocks
    private BinaryCalculationEngine testEngine;

    @Mock
    private StringToNumberConverter mockConvertible;

    @Mock
    private DoubleCalculator mockCalculable;

    @BeforeEach
    private void setUp(){
        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void Test_1_1_SimpleExpression_Return_Postfix_Array(){
        String[] expectArray = {"2","3","+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"2","+","3"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_2_SimpleExpression_Return_Postfix_Array(){
        String[] expectArray = {"2","3", "+", "4" ,"-" ,"5", "6", "*" ,"7" ,"/", "+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"2","+","3","-","4","+","5","*","6","/","7"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_3_SimpleExpression_With_NegativeNumberInBeginning_Return_Postfix_Array(){
        String[] expectArray = {"-2","3","+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"-","2","+","3"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_4_SimpleExpression_With_TwoNegativeNumbersInBeginningAndEnd_Return_Postfix_Array(){
        String[] expectArray = {"-2","-3","+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"-","2","+","-","3"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_5_IntermediateExpression_With_TwoNegativeNumbersInBeginningAndEnd_Return_Postfix_Array(){
        String[] expectArray = {"-2","3.5","6","*","+","-5","4","-7","*","+","-"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"-","2","+","3.5","*","6","-","-","5","+","4","*","-7"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_6_IntermediateExpression_With_Parentheses_Return_Postfix_Array(){
        String[] expectArray = {"3","2","5","6","+","7","8","+","*","*","+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"3","+","2","*","(","(","5","+","6",")","*","(","7","+","8",")",")"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_7_IntermediateExpression_With_StartParentheses_Return_Postfix_Array(){
        String[] expectArray = {"3","2","5","6","+","7","8","+","*","*","+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"3","+","2","*","(","(","5","+","6",")","*","(","7","+","8",")",")"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_8_IntermediateExpression_With_Negative_Numbers_And_Parentheses_Return_Postfix_Array(){
        String[] expectArray = {"-3.0","2142.3","-5","6","+","7.3","8","+","*","*","+"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"-3.0","+","2142.3","*","(","(","-5","+","6",")","*","(","7.3","+","8",")",")"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_9_AdvanceExpression_With_Negative_Numbers_And_Parentheses_Return_Postfix_Array(){
        String[] expectArray = {"-3.0","-2142.3","-5","6","+","7.3","8","+","*","*","-"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"-3.0","-","-2142.3","*","(","(","(","-5","+","6",")","*","(","7.3","+","8",")",")",")"});
        arrayChecker(expectArray,actualArray);
    }

    @Test
    public void Test_1_10_AdvanceExpression_With_Negative_Numbers_And_Parentheses_Return_Postfix_Array(){
        String[] expectArray = {"-3","2","+","3","+","4","*","6","2","*","-"};
        String[] actualArray = testEngine.convertForCalculation(new String[]{"(","(","-3","+","2",")","+","3",")","*","4","-","(","6","*","2",")"});
        arrayChecker(expectArray,actualArray);
    }


    @Test
    public void Test_2_1_CalculateSimpleExpression(){
        String[] postFixExpression = {"-2","-3","+"};
        double expectedResult = -5.0;
        Mockito.when(mockConvertible.convertToDouble("-2.0")).thenReturn(-2.0);
        Mockito.when(mockConvertible.convertToDouble("-3.0")).thenReturn(-3.0);
        Mockito.when(mockCalculable.calculate(-2.0,-3.0,"+")).thenReturn(-2.0+-3.0);
        Assertions.assertEquals(expectedResult,testEngine.calculate(postFixExpression));
    }

    @Test
    public void Test_2_2_IntermediateExpression_With_Negative_Numbers_And_Parentheses_Return_Postfix_Array(){
        String[] postFixExpression = {"-3.0","2142.3","-5","6","+","7.3","8","+","*","*","+"};
        Mockito.when(mockConvertible.convertToDouble("7.3")).thenReturn(7.3);
        Mockito.when(mockConvertible.convertToDouble("2142.3")).thenReturn(2142.3);
        Mockito.when(mockConvertible.convertToDouble("-3.0")).thenReturn(-3.0);
        Mockito.when(mockConvertible.convertToDouble("6.0")).thenReturn(6.0);
        Mockito.when(mockConvertible.convertToDouble("-5.0")).thenReturn(-5.0);
        Mockito.when(mockConvertible.convertToDouble("8.0")).thenReturn(8.0);
        Mockito.when(mockCalculable.calculate(-5.0,6.0,"+")).thenReturn(1.0);
        Mockito.when(mockCalculable.calculate(7.3,8.0,"+")).thenReturn(15.3);
        Mockito.when(mockCalculable.calculate(1.0,15.3,"*")).thenReturn(15.3);
        Mockito.when(mockCalculable.calculate(2142.3,15.3,"*")).thenReturn(15.3*2142.3);
        Mockito.when(mockCalculable.calculate(-3.0,32777.19,"+")).thenReturn(-3.0+(15.3*2142.3));
        Assertions.assertEquals(32774.19,testEngine.calculate(postFixExpression));

    }

    @Test
    public void Test_2_3_IntermediateExpression_With_Negative_Numbers_And_Parentheses_Return_Postfix_Array(){
        String[] postFixExpression = {"-3.0","2142.3","-5","6","+","7.3","8","+","*","*","+"};
        Mockito.when(mockConvertible.convertToDouble("7.3")).thenReturn(7.3);
        Mockito.when(mockConvertible.convertToDouble("2142.3")).thenReturn(2142.3);
        Mockito.when(mockConvertible.convertToDouble("-3.0")).thenReturn(-3.0);
        Mockito.when(mockConvertible.convertToDouble("6.0")).thenReturn(6.0);
        Mockito.when(mockConvertible.convertToDouble("-5.0")).thenReturn(-5.0);
        Mockito.when(mockConvertible.convertToDouble("8.0")).thenReturn(8.0);
        Mockito.when(mockCalculable.calculate(-5.0,6.0,"+")).thenReturn(1.0);
        Mockito.when(mockCalculable.calculate(7.3,8.0,"+")).thenReturn(15.3);
        Mockito.when(mockCalculable.calculate(1.0,15.3,"*")).thenReturn(15.3);
        Mockito.when(mockCalculable.calculate(2142.3,15.3,"*")).thenReturn(15.3*2142.3);
        Mockito.when(mockCalculable.calculate(-3.0,32777.19,"+")).thenReturn(-3.0+(15.3*2142.3));
        Assertions.assertEquals(32774.19,testEngine.calculate(postFixExpression));

    }

    private void arrayChecker(String[] expectArray, String[] actualArray) {
        Assertions.assertEquals(expectArray.length,actualArray.length);
        for(int i = 0; i < expectArray.length ; i++){
            Assertions.assertEquals(expectArray[i],actualArray[i]);
        }
    }

}
