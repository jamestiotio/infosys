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
    private String sharedPrefFile = "com.jamestiotio.exchangerate.subsharedprefs";
    public final static String A_KEY = "A_KEY";  // A: Home
    public final static String B_KEY = "B_KEY";  // B: Foreign
    private String homeTextStored;
    private String foreignTextStored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        this.mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        this.homeTextStored = mPreferences.getString(A_KEY, "");
        this.foreignTextStored = mPreferences.getString(B_KEY, "");

        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);

        editTextSubValueOfA.setText(this.homeTextStored);
        editTextSubValueOfB.setText(this.foreignTextStored);

        buttonBackToCalculator.setOnClickListener(new SetExchangeRateCalculator(SubActivity.this,
                MainActivity.class,
                editTextSubValueOfA,
                editTextSubValueOfB,
                A_KEY,
                B_KEY,
                mPreferences));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(A_KEY, editTextSubValueOfA.getText().toString());
        editor.putString(B_KEY, editTextSubValueOfB.getText().toString());
        editor.apply();
    }

}