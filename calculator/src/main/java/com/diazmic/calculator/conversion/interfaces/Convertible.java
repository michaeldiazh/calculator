package com.diazmic.calculator.conversion.interfaces;

/**
 *  Converts String to Integer or Decimal (Double)
 *  Note that :
 *      - {@code convertToInteger} converts string input to an integer.
 *      - {@code convertToDouble} converts string input to an double.
 */
public interface Convertible {
    /**
     * TODO:
     *  For All Implementation, Unit Test must be written and pass:
     *      + convertToInteger:
     *          - Incorrect String Exceptions:
     *              * Test 1.1: If input is a non-number string, then throw IllegalArgumentException.
     *              * Test 1.2: If input is a string that has a mix of numbers and non-numbers, then throw IllegalArgumentException.
     *              * Test 1.3: If input is a string that has a mix and non-numbers and numbers,a decimal point, and non-numbers, then throw IllegalArgumentException.
     *              * Test 1.4: If input is a string that has non-numbers, a decimal point, and a mix and non-numbers and numbers, then throw IllegalArgumentException.
     *              * Test 1.5: If input is a string that a mix and non-numbers and numbers, a decimal point, and a mix of non-numbers and numbers, then throw IllegalArgumentException.
     *              * Test 1.6: If input is a string starts with a decimal point and then follows with non-numbers, then throw IllegalArgumentException.
     *              * Test 1.7: If input is a string starts with a decimal point and then follows with a mix of non-numbers and numbers, then throw IllegalArgumentException.
     *              * Test 1.8: If input is a string starts with numbers and then follows with a decimal point, then throw IllegalArgumentException.
     *              * Test 1.9: If input is a string starts with mix of non numbers and numbers and then follows with a decimal point, then throw IllegalArgumentException.
     *              * Test 1.10: If input is a null, then throw NullPointerException.
     *              * Test 1.11: If input is blank or empty, then throw IllegalArgumentException.
     *              * Test 1.12: If input only has a Decimal Point, then throw IllegalArgumentException.
     *          - Decimal Exceptions:
     *              * Test 2.1: If input is a string starts with a decimal point and then follows with non-numbers, then throw IllegalArgumentException.
     *              * Test 2.2: If input is a negative or positive decimal (string starts with numbers with a decimal point and follows with more numbers), then throw IllegalArgumentException
     *              * Test 2.3: If input is 0.0, then throw IllegalArgumentException
     *          - Integer Conversion:
     *              * Test 3.1: If input matches correctly, then return the integer.
     *              * Test 3.2: If input matches correctly and is negative, then return the negative integer.
     *              * Test 3.3: If input matches zero (negative or positive), then return zero.
     *      + convertToDouble:
     *          - Incorrect String Exceptions:
     *              **** MATCHES WITH "Incorrect String Exceptions" TEST FROM "convertToInteger" *****
     *          - Integer Exceptions:
     *              * Test 4.1: If input is a negative or positive integer, then throw IllegalArgumentException.
     *              * Test 4.2: If input is a 0, then throw IllegalArgumentException
     *          - Double Conversion:
     *              * Test 5.1: If input matches correctly, then return the double.
     *              * Test 5.2: If input matches correctly and is negative, then return the negative double.
     *              * Test 5.3: If input matches zero (negative or positive), then return zero.
     */


    public final String DECIMAL_REGEX = "^-?[0-9]*\\.[0-9]+$";
    public final String INTEGER_REGEX = "^-?\\d+$";
    public final String NEGATIVE_INTEGER_REGEX = "^-\\d+";
    public final String NEGATIVE_DECIMAL_REGEX = "^-[0-9]*\\.[0-9]+$";
    public final String ZERO_DECIMAL_REGEX = "^-?0*\\.0+$";

    /**
     * Converts input string to its equivalent {@code int} value.
     * Returns equivalent {@code int} value, otherwise it will throw {@code IllegalArgumentException}
     * @param input The input {@code String} that will be converted.
     * @return equivalent {@code int} value.
     * @throws IllegalArgumentException This is thrown when input {@code String} is not an {@code int}.
     */
    public int convertToInteger(String input) throws IllegalArgumentException;

    /**
     * Converts input string to its equivalent {@code double} value.
     * Returns equivalent {@code double} value, otherwise it will throw {@code IllegalArgumentException}
     * @param input The input {@code String} that will be converted.
     * @return equivalent {@code double} value.
     * @throws IllegalArgumentException This is thrown when input {@code String} is not an {@code double}.
     */
    public double convertToDouble(String input) throws IllegalArgumentException;
}
