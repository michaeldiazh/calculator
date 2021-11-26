package com.diazmic.calculator.calculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test for DoubleCalculator:
 *   Definitions for tests:
 *     - Addition Calculation:
 *          * Test 1.1: If the two inputs are correct, then return the addition of the two inputs.
 *          * Test 1.2: If one input is negative and other is positive, then return the addition of the two inputs.
 *          * Test 1.3: If both inputs are negative, then return the addition of the two inputs.
 *
 *      - Subtraction Calculation:
 *          * Test 2.1: If the two inputs are correct, then return the subtraction of the two inputs.
 *          * Test 2.2: If one input is negative and other is positive, then return the subtraction of the two inputs.
 *          * Test 2.3: If both inputs are negative, then return the subtraction of the two inputs.
 *
 *      - Multiplication Calculation:
 *          * Test 3.1: If the two inputs are correct, then return the multiplication of the two inputs.
 *          * Test 3.2:  If one input is negative and other is positive, then return the multiplication of the two inputs.
 *          * Test 3.3: If one input is negative and other is negative, then return the multiplication of the two inputs.
 *
 *      - Division Calculation:
 *          * Test 4.1: If the two inputs are correct, then return the division of the two inputs.
 *          * Test 4.2: If one input is negative and other is positive, then return the division of the two inputs.
 *          * Test 4.3: If one input is negative and other is negative, then return the division of the two inputs.
 *          * Test 4.4: If one input is a number and other is 0, then return the division of the two inputs.
 *
 *      - Null Calculation:
 *          * Test 5.1: If the two inputs are Null, then throw IllegalArgumentException.
 *          * Test 5.2: If one input is a number  and other is Null, then throw IllegalArgumentException.
 *          * Test 5.3: If one input is Null and other is number, then throw IllegalArgumentException.
 */
public class DoubleCalculatorTest {
    private final DoubleCalculator testDoubleCalculable = DoubleCalculator.doubleCalculator;

    // Test 1.1: If the two inputs are correct, then return the addition of the two inputs.
    @Test
    public void Test_1_1_Both_Inputs_Are_Correct_Return_Integer_Addition_Operator(){
        assert testDoubleCalculable.calculate(5.0,3.0,"+") == 8;
    }

    // Test 1.2: If one input is negative and other is positive, then return the addition of the two inputs.
    @Test
    public void Test_1_2_First_Input_Is_Negative_And_Second_Is_Positive_Return_Integer_Addition_Operator(){
        assert testDoubleCalculable.calculate(5.0,-3.0,"+") == 2;
    }

    // Test 1.3: If both inputs are negative, then return the addition of the two inputs.
    @Test
    public void Test_1_3_Both_Inputs_Are_Negative_Return_Integer_Addition_Operator(){
        assert testDoubleCalculable.calculate(-5.0,-3.0,"+") == -8;
    }

    // Test 2.1: If the two inputs are correct, then return the subtraction of the two inputs.
    @Test
    public void Test_2_1_Both_Inputs_Are_Correct_Return_Integer_Subtraction_Operator(){
        assert testDoubleCalculable.calculate(5.0,3.0,"-") == 2;
    }

    // Test 2.2: If one input is negative and other is positive, then return the subtraction of the two inputs.
    @Test
    public void Test_2_2_First_Input_Is_Negative_And_Second_Is_Positive_Return_Integer_Subtraction_Operator(){
        assert testDoubleCalculable.calculate(5.0,-3.0,"-") == 8;
    }

    // Test 2.3: If both inputs are negative, then return the subtraction of the two inputs.
    @Test
    public void Test_2_3_Both_Inputs_Are_Negative_Return_Integer_Subtraction_Operator(){
        assert testDoubleCalculable.calculate(-5.0,-3.0,"-") == -2;
    }

    // Test 3.1: If the two inputs are correct, then return the subtraction of the two inputs.
    @Test
    public void Test_3_1_Both_Inputs_Are_Correct_Return_Integer_Multiplication_Operator(){
        assert testDoubleCalculable.calculate(5.0,3.0,"*") == 15;
    }

    // Test 3.2: If one input is negative and other is positive, then return the subtraction of the two inputs.
    @Test
    public void Test_3_2_First_Input_Is_Negative_And_Second_Is_Positive_Return_Integer_Multiplication_Operator(){
        assert testDoubleCalculable.calculate(5.0,-3.0,"*") == -15;
    }

    // Test 3.3: If both inputs are negative, then return the subtraction of the two inputs.
    @Test
    public void Test_3_3_Both_Inputs_Are_Negative_Return_Integer_Multiplication_Operator(){
        assert testDoubleCalculable.calculate(-5.0,-3.0,"*") == 15;
    }

    // Test 4.1: If the two inputs are correct, then return the subtraction of the two inputs.
    @Test
    public void Test_4_1_Both_Inputs_Are_Correct_Return_Integer_Division_Operator(){
        assert testDoubleCalculable.calculate(5.0,3.0,"/") == (5.0/3.0);
    }

    // Test 4.2: If one input is negative and other is positive, then return the subtraction of the two inputs.
    @Test
    public void Test_4_2_First_Input_Is_Negative_And_Second_Is_Positive_Return_Integer_Division_Operator(){
        assert testDoubleCalculable.calculate(5.0,-3.0,"/") == (-5.0/3.0);
    }

    // Test 4.3: If both inputs are negative, then return the subtraction of the two inputs.
    @Test
    public void Test_4_3_Both_Inputs_Are_Negative_Return_Integer_Division_Operator(){
        assert testDoubleCalculable.calculate(-5.0,-3.0,"/") == (-5.0/-3.0);
    }

    // Test 4.4: If both inputs are negative, then return the subtraction of the two inputs.
    @Test
    public void Test_4_4_First_Input_Is_Negative_And_Second_Is_Zero_Throw_ArithmeticException(){
        Exception exception = Assertions.assertThrows(ArithmeticException.class,() ->testDoubleCalculable.calculate(-5.0,0.0,"/"));
        Assertions.assertEquals(exception.getMessage(),"Division of inputOne over 0. Output is undefined.");
    }

    // Test 5.1: If the two inputs are Null, then throw IllegalArgumentException.
    @Test
    public void Test_5_1_Both_Inputs_Are_Null_Throw_IllegalArgumentException(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,() ->testDoubleCalculable.calculate(null,null,"*"));
        Assertions.assertEquals(exception.getMessage(),"Both numbers are null. Please evaluate input.");
    }

    // Test 5.2: If one input is a number  and other is Null, then throw IllegalArgumentException.
    @Test
    public void Test_5_2_First_Input_Is_Null_Throw_IllegalArgumentException(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,() ->testDoubleCalculable.calculate(null,12.3,"*"));
        Assertions.assertEquals(exception.getMessage(),"First Number is null. Please evaluate input.");
    }

    // Test 5.3: If one input is Null and other is number, then throw IllegalArgumentException.
    @Test
    public void Test_5_3_Second_Input_Is_Null_Throw_IllegalArgumentException(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,() ->testDoubleCalculable.calculate(421.3,null,"*"));
        Assertions.assertEquals(exception.getMessage(),"Second Number is null. Please evaluate input.");
    }
}
