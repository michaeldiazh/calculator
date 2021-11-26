package com.diazmic.calculator.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringToNumberConverterTest {
    public StringToNumberConverter testConverter = StringToNumberConverter.stringToNumberConverter;
    private final String illegalNumberArgumentExceptionString = "String is not a valid number. Please check input";
    private final String illegalIntegerArgumentExceptionString = "String is not a integer, it is a double. Please check input";
    private final String illegalDoubleArgumentExceptionString = "String is not a double, it is a integer. Please check input";
    private final String nullPointerExceptionString = "String is null. Please check input.";

    // Test 1.1: If input is a non-number string, then throw IllegalArgumentException.
    @Test
    public void Test_1_1_Input_Is_A_Non_Number_String_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("testString"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("testString"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.2: If input is a string that has a mix of numbers and non-numbers, then throw IllegalArgumentException.
    @Test
    public void Test_1_2_Input_Is_A_String_With_Mix_Of_Numbers_and_Non_Numbers_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("testString12345"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("testString12345"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.3: If input is a string that has a mix and non-numbers and numbers,a decimal point, and non-numbers, then throw IllegalArgumentException.
    @Test
    public void Test_1_3_Input_Is_A_String_With_Mix_Of_Numbers_and_Non_Numbers_and_a_Decimal_Point_and_Non_Numbers_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("testString12345.testDec"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("testString12345.testDec"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.4: If input is a string that has non-numbers, a decimal point, and a mix of non-numbers and numbers, then throw IllegalArgumentException.
    @Test
    public void Test_1_4_Input_Is_A_String_With_Non_Numbers_and_a_Decimal_Point_and_A_Mix_Of_Numbers_and_Non_Numbers_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("testString.testDec1575"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("testString.testDec84571"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.5: If input is a string that a mix and non-numbers and numbers, a decimal point, and a mix of non-numbers and numbers, then throw IllegalArgumentException.
    @Test
    public void Test_1_5_Input_Is_A_String_With_A_Mix_Of_Numbers_and_Non_Numbers_and_a_Decimal_Point_and_A_Mix_Of_Numbers_and_Non_Numbers_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("testString1541.testDec1575"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("testString8741.testDec84571"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.6: If input is a string starts with a decimal point and then follows with non-numbers, then throw IllegalArgumentException.
    @Test
    public void Test_1_6_Input_Is_A_String_With_A_Decimal_Point_and_Non_Numbers_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger(".testString"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble(".testString"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.7: If input is a string starts with a decimal point and then follows with a mix of non-numbers and numbers, then throw IllegalArgumentException.
    @Test
    public void Test_1_7_Input_Is_A_String_With_A_Decimal_Point_and_A_Mix_Of_Numbers_and_Non_Numbers_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger(".testString-945/41"));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble(".testString*9875*/"));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.8: If input is a string starts with numbers and then follows with a decimal point, then throw IllegalArgumentException.
    @Test
    public void Test_1_8_Input_Is_A_String_With_A_Number_And_A_Decimal_Point_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("123456."));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("12345."));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.9: If input is a string starts with mix of non numbers and numbers and then follows with a decimal point, then throw IllegalArgumentException.
    @Test
    public void Test_1_9_Input_Is_A_String_A_Mix_Of_Numbers_and_Non_Numbers_A_Decimal_Point_throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("123s456ef."));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("1r23f45q."));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.10: If input is a null, then throw NullPointerException.
    @Test
    public void Test_1_10_Input_Is_Null_Throw_NullPointerException(){
        Exception integerException = Assertions.assertThrows(NullPointerException.class,() ->testConverter.convertToInteger(null));
        Assertions.assertEquals(integerException.getMessage(),nullPointerExceptionString);
        Exception doubleException = Assertions.assertThrows(NullPointerException.class,() ->testConverter.convertToDouble(null));
        Assertions.assertEquals(doubleException.getMessage(),nullPointerExceptionString);
    }

    // Test 1.11: If input is blank or empty, then throw IllegalArgumentException.
    @Test
    public void Test_1_11_Input_Is_Blank_or_Empty_Throw_IllegalArgumentException(){
        Exception integerEmptyException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger(""));
        Assertions.assertEquals(integerEmptyException.getMessage(),illegalNumberArgumentExceptionString);
        Exception integerBlankException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger(" "));
        Assertions.assertEquals(integerBlankException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleEmptyException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble(""));
        Assertions.assertEquals(doubleEmptyException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleBlankException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble(" "));
        Assertions.assertEquals(doubleBlankException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 1.12: If input only has a Decimal Point, then throw IllegalArgumentException.
    @Test
    public void Test_1_12_Input_Only_Has_Decimal_Point_Throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("."));
        Assertions.assertEquals(integerException.getMessage(),illegalNumberArgumentExceptionString);
        Exception doubleException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("."));
        Assertions.assertEquals(doubleException.getMessage(),illegalNumberArgumentExceptionString);
    }

    // Test 2.1: If input is a string starts with a decimal point and then follows with a number, then throw IllegalArgumentException.
    @Test
    public void Test_2_1_Input_Only_Has_Decimal_Point_Throw_IllegalArgumentException(){
        Exception integerException = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger(".12345"));
        Assertions.assertEquals(illegalIntegerArgumentExceptionString,integerException.getMessage());
    }

    // Test 2.2: If input is a negative or positive decimal (string starts with numbers with a decimal point and follows with more numbers), then throw IllegalArgumentException
    @Test
    public void Test_2_2_Input_Is_Any_Double_Throw_IllegalArgumentException(){
        Exception integerExceptionOne = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("-.12345"));
        Assertions.assertEquals(illegalIntegerArgumentExceptionString,integerExceptionOne.getMessage());
        Exception integerExceptionTwo = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("-0.12345"));
        Assertions.assertEquals(illegalIntegerArgumentExceptionString,integerExceptionTwo.getMessage());
        Exception integerExceptionThree = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("0.12345"));
        Assertions.assertEquals(illegalIntegerArgumentExceptionString,integerExceptionThree.getMessage());
    }

    // Test 2.3: If input is 0.0, then throw IllegalArgumentException
    @Test
    public void Test_2_3_Input_Is_0_Double_Throw_IllegalArgumentException(){
        Exception integerExceptionOne = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger("0.0"));
        Assertions.assertEquals(illegalIntegerArgumentExceptionString,integerExceptionOne.getMessage());
        Exception integerExceptionTwo = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToInteger(".0"));
        Assertions.assertEquals(illegalIntegerArgumentExceptionString,integerExceptionTwo.getMessage());
    }

    // Test 3.1: If input matches correctly, then return the integer.
    @Test
    public void Test_3_1_Input_Is_Correct_Return_Correct_Integer(){
        Assertions.assertEquals(testConverter.convertToInteger("12"),12);
        Assertions.assertEquals(testConverter.convertToInteger("1212453124"),1212453124);
        Assertions.assertEquals(testConverter.convertToInteger("354115"),354115);
        Assertions.assertEquals(testConverter.convertToInteger("0354115"),354115);

    }

    // Test 3.2: If input matches correctly and is negative, then return the negative integer.
    @Test
    public void Test_3_2_Input_Is_Correct_And_Is_Negative_Return_Correct_Integer(){
        Assertions.assertEquals(testConverter.convertToInteger("-12"),-12);
        Assertions.assertEquals(testConverter.convertToInteger("-1212453124"),-1212453124);
        Assertions.assertEquals(testConverter.convertToInteger("-354115"),-354115);
    }

    // Test 3.3: If input matches zero (negative or positive), then return zero.
    @Test
    public void Test_3_2_Input_Is_Zero_Positive_or_Negative_Return_Zero(){
        Assertions.assertEquals(testConverter.convertToInteger("0"),0);
        Assertions.assertEquals(testConverter.convertToInteger("-0"),0);
    }

    // Test 4.1: If input is a negative or positive integer, then throw IllegalArgumentException.
    @Test
    public void Test_4_1_Input_Is_Any_Double_Throw_IllegalArgumentException(){
        Exception doubleExceptionOne = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("12345"));
        Assertions.assertEquals(illegalDoubleArgumentExceptionString,doubleExceptionOne.getMessage());
        Exception doubleExceptionTwo = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("-12345"));
        Assertions.assertEquals(illegalDoubleArgumentExceptionString,doubleExceptionTwo.getMessage());
    }

    // Test 4.2: If input is a 0, then throw IllegalArgumentException
    @Test
    public void Test_4_2_Input_Is_Zero_Integer_Throw_IllegalArgumentException(){
        Exception doubleExceptionOne = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("12345"));
        Assertions.assertEquals(illegalDoubleArgumentExceptionString,doubleExceptionOne.getMessage());
        Exception doubleExceptionTwo = Assertions.assertThrows(IllegalArgumentException.class,() ->testConverter.convertToDouble("-12345"));
        Assertions.assertEquals(illegalDoubleArgumentExceptionString,doubleExceptionTwo.getMessage());
    }

    // Test 5.1: If input matches correctly, then return the double.
    @Test
    public void Test_5_1_Input_Matches_Return_Double(){
        Assertions.assertEquals(testConverter.convertToDouble(".12"),0.12);
        Assertions.assertEquals(testConverter.convertToDouble("1.12"),1.12);
        Assertions.assertEquals(testConverter.convertToDouble("10.012"),10.012);
        Assertions.assertEquals(testConverter.convertToDouble("10.00"),10.00);
        Assertions.assertEquals(testConverter.convertToDouble("0.00001"),0.00001);
    }

    // Test 5.2: If input matches correctly and is negative, then return the negative double.
    @Test
    public void Test_5_2_Input_Matches_And_Negative_Return_Double(){
        Assertions.assertEquals(testConverter.convertToDouble("-0.12"),-0.12);
        Assertions.assertEquals(testConverter.convertToDouble("-.12"),-0.12);
        Assertions.assertEquals(testConverter.convertToDouble("-.012"),-0.012);
        Assertions.assertEquals(testConverter.convertToDouble("-10.00"),-10.00);
        Assertions.assertEquals(testConverter.convertToDouble("-0.00001"),-0.00001);
    }

    // Test 5.3: If input matches zero (negative or positive), then return zero.
    @Test
    public void Test_5_3_Input_Matches_Zero_Negative_Or_Positive_Return_Zero(){
        Assertions.assertEquals(0.0,testConverter.convertToDouble("-0.0"));
        Assertions.assertEquals(0.0,testConverter.convertToDouble("0.000"));
        Assertions.assertEquals(0.0,testConverter.convertToDouble("-0.000"));
        Assertions.assertEquals(0.0,testConverter.convertToDouble("-.00"));
        Assertions.assertEquals(0.0,testConverter.convertToDouble(".00"));
        Assertions.assertEquals(0.0,testConverter.convertToDouble("0.0"));
    }
}
