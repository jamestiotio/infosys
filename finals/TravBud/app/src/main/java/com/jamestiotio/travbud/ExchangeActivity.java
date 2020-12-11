package com.jamestiotio.travbud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

public class ExchangeActivity extends AppCompatActivity {
    EditText editTextHomeAmt;
    EditText editTextForeignAmt;
    Button buttonSubmit;
    public final static String HOME_AMOUNT_KEY = "HOME_AMOUNT";
    public final static String FOREIGN_AMOUNT_KEY = "FOREIGN_AMOUNT";
    final static String TAG = "ExchangeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        editTextForeignAmt = findViewById(R.id.edittext_foreign_amount);
        editTextHomeAmt = findViewById(R.id.edittext_home_amount);
        buttonSubmit = findViewById(R.id.button_exchange);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String homeAmtStr = editTextHomeAmt.getText().toString();
                String foreignAmtStr = editTextForeignAmt.getText().toString();
                try {
                    BigDecimal homeAmt = new BigDecimal(homeAmtStr);
                    BigDecimal foreignAmt = new BigDecimal(foreignAmtStr);
                    if (homeAmt.compareTo(new BigDecimal(0)) < 0) {
                        Toast.makeText(ExchangeActivity.this, "Please enter a positive number", Toast.LENGTH_LONG).show();
                    } else if (foreignAmt.compareTo(new BigDecimal(0)) < 0) {
                        Toast.makeText(ExchangeActivity.this, "Please enter a positive number", Toast.LENGTH_LONG).show();
                    } else {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(HOME_AMOUNT_KEY, homeAmt.doubleValue());
                        returnIntent.putExtra(FOREIGN_AMOUNT_KEY, foreignAmt.doubleValue());
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                } catch (NumberFormatException e) {
                    Log.i(TAG, "NumberFormatException" );
                    Toast.makeText(ExchangeActivity.this, "Please enter a numeric value", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}