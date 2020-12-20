package com.jamestiotio.exchangerate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExchangeRate {
    private BigDecimal exchangeRate;
    private static String defaultRate = "2.95000";
    private static final int DEFAULT_PRECISION = 5;
    private int precision = DEFAULT_PRECISION;
    private MathContext mathContext;

    ExchangeRate() {
        this.exchangeRate = new BigDecimal(defaultRate);
        this.instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String exchangeRate) {
        this.exchangeRate = new BigDecimal(exchangeRate);
        this.instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String home, String foreign) {
        this.instantiateMathContext(DEFAULT_PRECISION);
        BigDecimal homeValue = new BigDecimal(home);
        homeValue = homeValue.setScale(2, RoundingMode.HALF_UP);
        BigDecimal foreignValue = new BigDecimal(foreign);
        exchangeRate = homeValue.divide(foreignValue, mathContext);
    }

    BigDecimal getExchangeRate() {
        return this.exchangeRate;
    }

    BigDecimal calculateAmount(String foreign) {
        BigDecimal amount = new BigDecimal(foreign);
        return amount.multiply(this.exchangeRate);
    }

    void setPrecision(int precision) {
        this.precision = precision;
        instantiateMathContext(precision);
    }

    private void instantiateMathContext(int precision) {
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
    }
}