package com.jamestiotio.exchangerate;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void exchangeRateDefaultRate() {
        String defaultExchangeRate = "2.95000";
        assertEquals(new BigDecimal(defaultExchangeRate), new ExchangeRate().getExchangeRate());
    }

    @Test
    public void exchangeRate_Infinity() {

    }

    @Test
    public void exchangeRate_Empty() {

    }

    @Test
    public void exchangeRate_Valid() {

    }

    @Test
    public void exchangeRate_Nan() {

    }

    //TODO 5.4 Write tests for other constructors, calculateAmount() and empty string (NumberFormatException)
}