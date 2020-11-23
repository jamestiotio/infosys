package com.jamestiotio.exchangerate;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class ConvertAmount implements View.OnClickListener {
    private final ExchangeRate currentExchangeRate;
    private final EditText value;
    private final TextView target;
    private final Activity activity;
    private final String TAG = "Logcat";

    public ConvertAmount(Activity activity, ExchangeRate currentExchangeRate, EditText value, TextView target) {
        this.currentExchangeRate = currentExchangeRate;
        this.value = value;
        this.target = target;
        this.activity = activity;
    }

    @Override
    public void onClick(View V) {
        String amount = value.getText().toString();
        if (amount.isEmpty()) {
            Toast.makeText(this.activity, R.string.empty_amount_inserted_warning, Toast.LENGTH_LONG).show();
            Log.i(TAG, activity.getResources().getString(R.string.empty_amount_inserted_warning));
        }
        else {
            BigDecimal result = this.currentExchangeRate.calculateAmount(amount);
            this.target.setText(result.toString());
        }
    }
}