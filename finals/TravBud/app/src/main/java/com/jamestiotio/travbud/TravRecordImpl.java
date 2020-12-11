package com.jamestiotio.travbud;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class TravRecordImpl {
    private List<TravRecord> records;
    public static final String RECORDS_KEY = "records";

    public TravRecordImpl() {
        this.records = new ArrayList<>();
    }

    public TravRecordImpl(JSONObject json) {
        List<TravRecord> records = new ArrayList<TravRecord>();
        try {
            if (json.has(RECORDS_KEY)) {
                Object obj = json.get(RECORDS_KEY);
                if (obj instanceof JSONArray) { // it is an array
                    JSONArray jarr = json.getJSONArray(RECORDS_KEY);
                    for (int i = 0; i < jarr.length(); i++) {
                        JSONObject o = (JSONObject) jarr.get(i);
                        if (o.has("purpose")) {
                            records.add(new ExpenditureRecord(o));
                        }
                        else if (o.has("homeAmt")) {
                            records.add(new ExchangeRecord(o));
                        }
                    }
                } else { // when there is only one element in the
                    // JSONarray, the JSONarray is flattened into a
                    // single JSON object, by the toString() method.
                    // i.e. [ { "key": 1 } ] is flattened into { "key" : 1 }
                    // hence the for-loop is not required.
                    JSONObject o = json.getJSONObject(RECORDS_KEY);
                    if (o.has("purpose")) {
                        records.add(new ExpenditureRecord(o));
                    }
                    else if (o.has("homeAmt")) {
                        records.add(new ExchangeRecord(o));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.records = records;
    }

    public int count() {
        return this.records.size();
    }

    public TravRecord get(int i) {
        return this.records.get(i);
    }

    public BigDecimal getBalance() {
        BigDecimal totalBalance = new BigDecimal("0.0");

        if ((this.records != null) && !(this.records.isEmpty())) {
            for (TravRecord record: this.records) {
                totalBalance = totalBalance.add(BigDecimal.valueOf(record.getAmount()));
            }
        }

        return totalBalance;
    }

    public static class NegativeBalanceException extends Exception {
    }

    public void add(TravRecord r) throws NegativeBalanceException {
        BigDecimal currentBalance = this.getBalance();
        if (r.getClass() == ExpenditureRecord.class) {
            if (currentBalance.compareTo(BigDecimal.valueOf(r.getAmount()).negate()) < 0) {
                throw new NegativeBalanceException();
            }
        }

        this.records.add(r);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        for (TravRecord r : this.records) {
            try {
                json.accumulate(RECORDS_KEY, r.toJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
}
