package com.diazmic.calculator.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpressionParserTest {
    @Test
    public void Test_1_1_Alphabetical_Input_Throws_Exception(){
        Exception e = Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("asf2e2f"));
    }

    @Test
    public void Test_1_2_MixOfOperationsAndAlphabetical_Input_Throws_Exception(){
        Exception e = Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("asf2e2f+12f23+3qqc*()"));
    }

    @Test
    public void Test_1_3_MixOfOperationsAndAlphabetical_Input_Throws_Exception(){
        Exception e = Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("asf2e2f     +12f23+3qqc*()"));
    }

    @Test
    public void Test_1_4_WrongParentheses_FauxMultiplicationOnTheRight_ThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("124+324.12(2)"));
    }

    @Test
    public void Test_1_5_WrongParentheses_FauxMultiplicationOnTheLeft_ThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("(2)124+324.12"));
    }
    @Test
    public void Test_1_6_WrongParentheses_FauxMultiplicationSomewhereInExpression_ThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("12(2)4+324.12"));
    }

    @Test
    public void Test_1_7_WrongParentheses_FauxOperationSomewhereInExpression_ThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("124+324.12(+(3))"));
    }
    @Test
    public void Test_1_8_WrongParentheses_FauxMultiplicationSomewhereInExpression_ThrowsException(){
        Assertions.assertThrows(IllegalArgumentException.class,()->ExpressionParser.createExpressionArray("(()"));
    }

    @Test
    public void Test_1_9_WrongParentheses_FauxMultiplicationSomewhereInExpression_ThrowsException(){
        Assertions.assertDoesNotThrow(()->ExpressionParser.createExpressionArray("((124.4251+2421.3))-12414.2421"));
    }

    @Test
    public void Test_2_1_IntermediateExpression_With_Parentheses_ReturnCorrectArray(){
        Assertions.assertArrayEquals(new String[]{"(", "(", "124.4251", "+", "2421.3", ")", ")", "-", "12414.2421"}, ExpressionParser.createExpressionArray("((124.4251+2421.3))-12414.2421"));
    }


    @Test
    public void Test_2_2_AdvanceExpression_With_Parentheses_(){

        Assertions.assertArrayEquals(new String[]{"(","(", "(", "124.4251", "+", "2421.3", ")", ")", "-", "12414.2421","*","(","(","-","3.24","+","3.6",")","+","5",")","+","4.12",")","/","2"}, ExpressionParser.createExpressionArray("(((124.4251+2421.3))-12414.2421*((-3.24+3.6)+5)+4.12)/2"));
    }

}
