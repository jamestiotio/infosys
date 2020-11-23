package com.jamestiotio.exchangerate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonConvert;
    Button buttonSetExchangeRate;
    EditText editTextValue;
    TextView textViewResult;
    TextView textViewExchangeRate;
    public final String TAG = "Logcat";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String RATE_KEY = "Rate_Key";
    ExchangeRate exchangeRateCalculation;
    String exchangeRateStored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String defaultValue = getResources().getString(R.string.default_exchange_rate);
        exchangeRateStored = mPreferences.getString(RATE_KEY, defaultValue);

        Intent intentSubToMain = getIntent();
        String home = intentSubToMain.getStringExtra(SubActivity.A_KEY);
        String foreign = intentSubToMain.getStringExtra(SubActivity.B_KEY);

        if (home == null && foreign == null) {
            exchangeRateCalculation = new ExchangeRate(exchangeRateStored);
        }
        else {
            exchangeRateCalculation = new ExchangeRate(home, foreign);
        }

        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewExchangeRate = findViewById(R.id.textViewExchangeRate);
        textViewResult = findViewById(R.id.textViewResult);

        textViewExchangeRate.setText(String.valueOf(exchangeRateCalculation.getExchangeRate().doubleValue()));
        buttonConvert.setOnClickListener(new ConvertAmount(MainActivity.this,
                exchangeRateCalculation,
                editTextValue,
                textViewResult));

        buttonSetExchangeRate = findViewById(R.id.buttonSetExchangeRate);
        buttonSetExchangeRate.setOnClickListener(new StartSubActivity(MainActivity.this,
                SubActivity.class));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view,
                "Replace with your own action",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        Log.i(TAG, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(RATE_KEY, exchangeRateCalculation.getExchangeRate().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_set_exchange_rate) {
            Intent intent = new Intent(this, SubActivity.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.menu_open_map_app) {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("geo").opaquePart("0.0").appendQueryParameter("q", "Changi Airport");
            Uri uriGeoLocation = builder.build();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uriGeoLocation);

            if (intent.resolveActivity(this.getPackageManager()) != null) {
                this.startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

}