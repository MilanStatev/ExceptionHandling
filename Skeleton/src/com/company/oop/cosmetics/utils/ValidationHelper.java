package com.company.oop.cosmetics.utils;

import java.util.List;

public class ValidationHelper {

    private static final String STRING_LENGTH_ERROR = "%s should be between %d and %d symbols.";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "%s command expects %d parameters.";

    public static void validateNumInRange(int value, int min, int max, String type) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(String.format(STRING_LENGTH_ERROR, type, min, max));
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String type) {
        validateNumInRange(stringToValidate.length(), minLength, maxLength, type);
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters, String commandType) {
        if (list.size() < expectedNumberOfParameters || list.size() > expectedNumberOfParameters) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS, commandType,
                    expectedNumberOfParameters));
        }
    }
}
