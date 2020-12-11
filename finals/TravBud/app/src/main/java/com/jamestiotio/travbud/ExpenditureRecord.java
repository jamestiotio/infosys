package com.jamestiotio.travbud;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExpenditureRecord extends TravRecord {
    private String purpose;
    private BigDecimal foreignAmt;

    public String getPurpose() {
        return this.purpose;
    }

    public BigDecimal getForeignAmt() {
        return this.foreignAmt;
    }

    public ExpenditureRecord(String purpose, double foreignAmt) {
        this.purpose = purpose;
        this.foreignAmt = BigDecimal.valueOf(foreignAmt);
    }

    public ExpenditureRecord(JSONObject json) {
        try {
            this.purpose = json.getString("purpose");
            this.foreignAmt = new BigDecimal(json.getString("amount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        String description = "Spent S$" + this.getForeignAmt().setScale(0, RoundingMode.HALF_UP).toString() + " for " + this.getPurpose();
        return description;
    }

    @Override
    public double getAmount() {
        return this.getForeignAmt().negate().doubleValue();
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        json.put("date", dateFormat.format(getRecordDate()));
        json.put("purpose", this.getPurpose());
        json.put("amount", this.getForeignAmt().toString());
        return json;
    }
}
