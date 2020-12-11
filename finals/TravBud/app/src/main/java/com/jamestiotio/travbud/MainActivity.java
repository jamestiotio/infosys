package com.jamestiotio.travbud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    ListView summaryListview;
    TextView balanceTextview;

    private final String sharedPrefFile = "com.jamestiotio.travbud.mainsharedprefs";
    public final String PREF_KEY = "TRAV_RECORDS";
    final static String TAG = "MainActivity";
    SharedPreferences mPreferences;

    final int REQUEST_EXCHANGE_REC = 100;
    final int REQUEST_EXPENDITURE_REC = 200;
    TravRecordImpl travRecordImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        balanceTextview = findViewById(R.id.balanceTextView);
        setSupportActionBar(toolbar);

        this.mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String travRecordStored = mPreferences.getString(PREF_KEY, "");
        if (travRecordStored.isEmpty()) {
            this.travRecordImpl = new TravRecordImpl();
        }
        else {
            try {
                this.travRecordImpl = new TravRecordImpl(new JSONObject(travRecordStored));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        renderTravRecordImpl();
    }

    @SuppressLint("SetTextI18n")
    public void renderTravRecordImpl() {
        summaryListview = findViewById(R.id.summaryListivew);
        TravRecordAdapter travRecordAdapter = new TravRecordAdapter(MainActivity.this, travRecordImpl);
        summaryListview.setAdapter(travRecordAdapter);
        balanceTextview.setText(this.travRecordImpl.getBalance().setScale(0, RoundingMode.HALF_UP).toString());
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

        if (id == R.id.action_exchange) {
            Intent intent = new Intent(MainActivity.this, ExchangeActivity.class);
            startActivityForResult(intent, REQUEST_EXCHANGE_REC);
        } else if (id == R.id.action_expenditure) {
            Intent intent = new Intent(MainActivity.this, ExpenditureActivity.class);
            startActivityForResult(intent, REQUEST_EXPENDITURE_REC);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EXCHANGE_REC) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "failed to retrieve the exchange record.", Toast.LENGTH_LONG).show();
            } else {
                double foreignAmt = data.getDoubleExtra(ExchangeActivity.FOREIGN_AMOUNT_KEY, 0);
                double homeAmt = data.getDoubleExtra(ExchangeActivity.HOME_AMOUNT_KEY, 0);
                try {
                    this.travRecordImpl.add(new ExchangeRecord(homeAmt, foreignAmt));
                } catch (TravRecordImpl.NegativeBalanceException e) {
                    Toast.makeText(MainActivity.this, "You don't have sufficient balance for this operation", Toast.LENGTH_LONG).show();
                }
            }

        } else if (requestCode == REQUEST_EXPENDITURE_REC) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(MainActivity.this, "failed to retrieve the expenditure record.", Toast.LENGTH_LONG).show();
            } else {
                double foreignAmt = data.getDoubleExtra(ExpenditureActivity.FOREIGN_AMOUNT_KEY, 0);
                String p = data.getStringExtra(ExpenditureActivity.PURPOSE_KEY);
                try {
                    this.travRecordImpl.add(new ExpenditureRecord(p, foreignAmt));
                } catch (TravRecordImpl.NegativeBalanceException e) {
                    Toast.makeText(MainActivity.this, "You don't have sufficient balance for this operation", Toast.LENGTH_LONG).show();
                }
            }
        }
        renderTravRecordImpl();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(PREF_KEY, this.travRecordImpl.toJSON().toString());
        editor.apply();
    }
}