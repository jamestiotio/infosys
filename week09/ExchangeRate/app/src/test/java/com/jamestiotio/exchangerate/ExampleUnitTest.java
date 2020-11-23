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

    // The no-arg constructor of the ExchangeRate class
    // shall initialize the exchange rate to 2.95000
    @Test
    public void exchangeRateDefaultRate() {
        String defaultExchangeRate = "2.95000";
        assertEquals(defaultExchangeRate, new ExchangeRate().getExchangeRate().toString());
    }

    @Test
    public void exchangeRateOneArg(){
        String exchangeRate = "0.12345";
        assertEquals(exchangeRate, new ExchangeRate(exchangeRate).getExchangeRate().toString());
    }

    @Test
    public void exchangeRateTwoArg(){
        String home = "1.0";
        String foreign = "3.0";
        String result = "0.33333";
        assertEquals(result, new ExchangeRate(home, foreign).getExchangeRate().toString());
    }

    // getExchangeRate() shall return a BigDecimal object
    @Test
    public void exchangeRateTwoArgBigDecimal(){
        String home = "1.0";
        String foreign = "3.0";
        String result = "0.33333";

        assertEquals(new BigDecimal(result), new ExchangeRate(home, foreign).getExchangeRate());
    }

    // calculateAmount() shall return a BigDecimal object
    @Test
    public void calculateAmountBigDecimal(){
        String home = "1.0";
        String foreign = "3.0";
        String amount = "100";
        String result = "33.333";

        assertEquals(new BigDecimal(result), new ExchangeRate(home, foreign)
                .calculateAmount(amount));
    }

    // In writing tests, think of ways to "break" your methods
    // For infinite inputs, divide them in logical ways
    // For example: positive vs. negative
    @Test
    public void calculateAmountBigDecimalNegative(){
        String home = "1.0";
        String foreign = "3.0";
        String amount = "-100"; // Negative
        String result = "-33.33300"; // Negative

        assertEquals(new BigDecimal(result), new ExchangeRate(home, foreign)
                .calculateAmount(amount));
    }

    // An empty string passed to the one-arg constructor of
    // ExchangeRate shall throw a NumberFormatException
    @Test(expected = NumberFormatException.class)
    public void exchangeRateOneArgThrowException(){
        new ExchangeRate("");
    }

    // A string "abcde" passed to the two-args constructor
    // shall throw a NumberFormatException
    @Test(expected = NumberFormatException.class)
    public void exchangeRateTwoArgThrowException(){
        new ExchangeRate("100","abcde");
    }

    //TODO 5.4 Write unit tests to check the ExchangeRate class

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
}