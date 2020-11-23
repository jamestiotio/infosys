package com.jamestiotio.exchangerate;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfA;  // A: Home
    EditText editTextSubValueOfB;  // B: Foreign
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String A_KEY = "A_KEY";  // A: Home
    public final static String B_KEY = "B_KEY";  // B: Foreign

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget

        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        buttonBackToCalculator.setOnClickListener(new SetExchangeRateCalculator(SubActivity.this,
                MainActivity.class,
                editTextSubValueOfA,
                editTextSubValueOfB,
                A_KEY,
                B_KEY));
    }

    //TODO 4.10 Don't forget to override onPause()

    @Override
    protected void onPause() {
        super.onPause();
    }

}