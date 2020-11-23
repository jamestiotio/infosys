package com.jamestiotio.exchangerate;

import java.math.BigDecimal;

public class Utils {
    static boolean checkInvalidInputs(String value) throws NumberFormatException, IllegalArgumentException {
        if (value == null || value.equals("") || value.trim().length() == 0) {
            throw new NumberFormatException();
        }

        else {
            BigDecimal valueToCheck = new BigDecimal(value);

            if (valueToCheck.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException();
            }
        }

        return true;
    }

    static boolean checkEqualsZero(String value) throws ArithmeticException {
        BigDecimal valueToCheck = new BigDecimal(value);

        if (valueToCheck.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException();
        }

        return true;
    }
}