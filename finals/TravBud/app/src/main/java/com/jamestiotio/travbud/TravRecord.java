package com.jamestiotio.travbud;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TravRecord {
    private Date recordDate;
    public TravRecord() {
        recordDate = java.util.Calendar.getInstance().getTime();
    }
    public TravRecord(JSONObject json) {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            this.recordDate = dateFormat.parse(json.getString("date"));
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }
    public Date getRecordDate() {
        return this.recordDate;
    }

    abstract public String getDescription();
    abstract public double getAmount();
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        json.put("date", dateFormat.format(getRecordDate()));
        return json;
    }
}
