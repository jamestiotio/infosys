package com.jamestiotio.travbud;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExchangeRecord extends TravRecord {
    private BigDecimal homeAmt;
    private BigDecimal foreignAmt;

    public BigDecimal getHomeAmt() {
        return this.homeAmt;
    }

    public BigDecimal getForeignAmt() {
        return this.foreignAmt;
    }

    public ExchangeRecord(double homeAmt, double foreignAmt) {
        this.homeAmt = BigDecimal.valueOf(homeAmt);
        this.foreignAmt = BigDecimal.valueOf(foreignAmt);
    }

    public ExchangeRecord(JSONObject json) {
        try {
            this.homeAmt = new BigDecimal(json.getString("homeAmt"));
            this.foreignAmt = new BigDecimal(json.getString("foreignAmt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        String description = "Changed S$" + this.getHomeAmt().setScale(0, RoundingMode.HALF_UP).toString() + " to foreign currency $" + this.getForeignAmt().setScale(0, RoundingMode.HALF_UP).toString();
        return description;
    }

    @Override
    public double getAmount() {
        return this.getForeignAmt().doubleValue();
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        json.put("date", dateFormat.format(getRecordDate()));
        json.put("homeAmt", this.getHomeAmt().toString());
        json.put("foreignAmt", this.getForeignAmt().toString());
        return json;
    }
}
