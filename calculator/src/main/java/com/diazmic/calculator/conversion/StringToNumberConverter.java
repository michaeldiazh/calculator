package com.diazmic.calculator.conversion;

import com.diazmic.calculator.conversion.interfaces.Convertible;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Component
public class StringToNumberConverter implements Convertible{
    public static final StringToNumberConverter stringToNumberConverter = new StringToNumberConverter();

    @Override
    public int convertToInteger(String input) throws IllegalArgumentException {
        if(input == null){
            throw new NullPointerException("String is null. Please check input.");
        }
        else if(stringInputIsNotValid(input)){
            throw new IllegalArgumentException("String is not a valid number. Please check input");
        }
        else if(isDecimal(input)){
            throw new IllegalArgumentException("String is not a integer, it is a double. Please check input");
        }
        return convertInteger(input);
    }

    @Override
    public double convertToDouble(String input) throws IllegalArgumentException {
        if(input == null){
            throw new NullPointerException("String is null. Please check input.");
        }
        else if(stringInputIsNotValid(input)){
            throw new IllegalArgumentException("String is not a valid number. Please check input");
        }
        else if(!isDecimal(input)){
            throw new IllegalArgumentException("String is not a double, it is a integer. Please check input");
        }
        return convertDouble(input);
    }

    /**
     * Check if the input is a decimal.
     * If it is a decimal, than return {@code true}, otherwise, return {@code false}.
     * @param input The input {@code String} that is being checked.
     * @return {@code true} if input is a decimal, otherwise return {@code false}
     */
    private boolean isDecimal(String input){
        return Pattern.matches(Convertible.DECIMAL_REGEX,input);
    }

    /**
     * Check if input is a number. Note it is check by the regex expression {@code Convertible.DECIMAL_REGEX} or {@code Convertible.INTEGER_REGEX}
     * Return {@code true} if {@code String} is not valid, otherwise it returns {@code false}
     * @param input The input {@code String} that is being checked.
     * @return {@code true} if input is not a number, otherwise return {@code false}
     */
    private boolean stringInputIsNotValid(String input) {
        boolean isAInteger = Pattern.matches(Convertible.INTEGER_REGEX,input);
        boolean isADecimal = Pattern.matches(Convertible.DECIMAL_REGEX,input);
        return input.isBlank() || input.isEmpty() || (!isADecimal && !isAInteger);
    }

    /**
     * Converts targetString to an integer.
     * Return the converted {@code int} value.
     * @param targetString {@code String} that is being converted.
     * @return Converted {@code int} value.
     */
    private int convertInteger(String targetString) {
        return stringIsNegativeDecimal(targetString) ? (-1) * Integer.parseInt(targetString.substring(1)): Integer.parseInt(targetString);
    }

    /**
     * Converts targetString to a double.
     * Return the converted {@code double} value.
     * @param targetString {@code String} that is being converted.
     * @return Converted {@code double} value.
     */
    private double convertDouble(String targetString){
        if(Pattern.matches(Convertible.ZERO_DECIMAL_REGEX,targetString)){
            return 0.0;
        }
        return stringIsNegativeDecimal(targetString) ? (-1) * Double.parseDouble(targetString.substring(1)): Double.parseDouble(targetString);
    }

    /**
     * Checks if the {@code String} is a negative integer. Note it is check by the regex expression {@code Convertible.NEGATIVE_INTEGER_REGEX}.
     * Return {@code true} if {@code String} is a negative integer, otherwise it returns {@code false}.
     *
     * @param targetString The input {@code String} that is being checked.
     * @return {@code true} if {@code String} is a negative integer, otherwise it returns {@code false}.
     */
    private boolean stringIsNegativeInteger(String targetString) {
        return Pattern.matches(Convertible.NEGATIVE_INTEGER_REGEX, targetString);
    }

    /**
     * Checks if the {@code String} is a negative decimal. Note it is check by the regex expression {@code Convertible.NEGATIVE_DECIMAL_REGEX}.
     * Return {@code true} if {@code String} is a negative decimal, otherwise it returns {@code false}.
     * @param targetString The input {@code String} that is being checked.
     * @return {@code true} if {@code String} is a negative decimal, otherwise it returns {@code false}.
     */
    private boolean stringIsNegativeDecimal(String targetString) {
        return Pattern.matches(Convertible.NEGATIVE_DECIMAL_REGEX, targetString);
    }

}
