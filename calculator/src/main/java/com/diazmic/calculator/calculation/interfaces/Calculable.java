package com.diazmic.calculator.calculation.interfaces;

import java.util.Set;

/**
 * Calculate input strings via an operation character.
 * Return the correct type from operation.
 * @param <Type> Type of parameters client is using. Either it is {@code double} or {@code int}.
 */

public interface Calculable<Type> {
    /**
     * TODO:
     *  For All Implementation, Unit Test must be written and pass:
     *      - Addition Calculation:
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
     *      - Null Calculation:
     *          * Test 5.1: If the two inputs are Null, then throw IllegalArgumentException.
     *          * Test 5.2: If one input is a number  and other is Null, then throw IllegalArgumentException.
     *          * Test 5.3: If one input is Null and other is number, then throw IllegalArgumentException.
     */

    final Set<String> OPERATION_SET = Set.of("+","-","*","/");

    /**
     * Calculates binary expression of two character inputs with its operator.
     * Returns type {@code double} or {@code int}.
     * @param inputOne First input to the expression.
     * @param inputTwo Second input to the expression
     * @param operation Binary OperatorCompartor. Needs to be from OPERATION_SET.
     * @return Output of calculation. Either {@code double} or {@code int}.
     */
    public Type calculate(Type inputOne, Type inputTwo, String operation) throws ArithmeticException, IllegalArgumentException;

}
