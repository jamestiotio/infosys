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

public class ExpenditureActivity extends AppCompatActivity {
    EditText editTextAmt;
    EditText editTextPurpose;
    Button buttonSubmit;
    public final static String FOREIGN_AMOUNT_KEY = "FOREIGN_AMOUNT";
    public final static String PURPOSE_KEY = "PURPOSE";
    final static String TAG = "ExpenditureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);

        editTextAmt = findViewById(R.id.edittext_amount);
        editTextPurpose = findViewById(R.id.edittext_purpose);
        buttonSubmit = findViewById(R.id.button_expenditure);

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String amtStr = editTextAmt.getText().toString();
                String purposeStr = editTextPurpose.getText().toString();
                try {
                    BigDecimal amt = new BigDecimal(amtStr);
                    if (amt.compareTo(new BigDecimal(0)) < 1) {
                        Toast.makeText(ExpenditureActivity.this, "Please enter a positive number", Toast.LENGTH_LONG).show();
                    } else {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(FOREIGN_AMOUNT_KEY, amt.doubleValue());
                        returnIntent.putExtra(PURPOSE_KEY, purposeStr);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                } catch (NumberFormatException e) {
                    Log.i(TAG, "NumberFormatException" );
                    Toast.makeText(ExpenditureActivity.this, "Please enter a numeric value", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}