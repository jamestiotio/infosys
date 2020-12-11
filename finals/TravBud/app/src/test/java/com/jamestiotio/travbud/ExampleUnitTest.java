package com.jamestiotio.travbud;

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
    public void travRecordDefaultBalance() {
        assertEquals(new BigDecimal("0.0"), new TravRecordImpl().getBalance());
    }

    @Test
    public void travRecordTwoRecords() throws TravRecordImpl.NegativeBalanceException {
        TravRecordImpl travRecords = new TravRecordImpl();
        travRecords.add(new ExchangeRecord(25.0, 150.0));
        travRecords.add(new ExpenditureRecord("food", 10.0));

        assertEquals(new BigDecimal("140.0"), travRecords.getBalance());
    }

    @Test(expected = TravRecordImpl.NegativeBalanceException.class)
    public void travRecordOneRecord() throws TravRecordImpl.NegativeBalanceException {
        TravRecordImpl travRecords = new TravRecordImpl();
        travRecords.add(new ExchangeRecord(25.0, 150.0));
        travRecords.add(new ExpenditureRecord("lodging", 200.0));
    }
}