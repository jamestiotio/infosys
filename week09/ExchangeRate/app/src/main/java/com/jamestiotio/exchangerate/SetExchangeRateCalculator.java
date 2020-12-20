package com.jamestiotio.exchangerate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetExchangeRateCalculator implements View.OnClickListener {
    private final Activity currentActivity;
    private final Class<MainActivity> targetActivity;
    private final EditText home;
    private final EditText foreign;
    private final String HOME_KEY; // A: Home
    private final String FOREIGN_KEY; // B: Foreign
    private SharedPreferences mPreferences;

    public SetExchangeRateCalculator(Activity currentActivity,
                                     Class<MainActivity> targetActivity,
                                     EditText home,
                                     EditText foreign,
                                     String HOME_KEY,
                                     String FOREIGN_KEY,
                                     SharedPreferences mPreferences) {
        this.home = home;
        this.foreign = foreign;
        this.currentActivity = currentActivity;
        this.targetActivity = targetActivity;
        this.HOME_KEY = HOME_KEY;
        this.FOREIGN_KEY = FOREIGN_KEY;
        this.mPreferences = mPreferences;
    }

    @Override
    public void onClick(View V) {
        try {
            String homeValue = this.home.getText().toString();
            String foreignValue = this.foreign.getText().toString();
            Utils.checkInvalidInputs(homeValue);
            Utils.checkInvalidInputs(foreignValue);
            Utils.checkEqualsZero(foreignValue);

            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(this.HOME_KEY, homeValue);
            editor.putString(this.FOREIGN_KEY, foreignValue);
            editor.apply();

            Intent intent = new Intent(this.currentActivity, this.targetActivity);
            intent.putExtra(this.HOME_KEY, homeValue);
            intent.putExtra(this.FOREIGN_KEY, foreignValue);
            this.currentActivity.startActivity(intent);
        }

        catch (NumberFormatException ex) {
            Toast.makeText(this.currentActivity,
                    this.currentActivity.getResources().getString(R.string.empty_amount_inserted_warning),
                    Toast.LENGTH_LONG).show();
        }

        catch (IllegalArgumentException ex) {
            Toast.makeText(this.currentActivity,
                    this.currentActivity.getResources().getString(R.string.negative_amount_inserted_warning),
                    Toast.LENGTH_LONG).show();
        }

        catch (ArithmeticException ex) {
            Toast.makeText(this.currentActivity,
                    this.currentActivity.getResources().getString(R.string.division_by_zero_warning),
                    Toast.LENGTH_LONG).show();
        }

        catch (Exception ex) {
            Toast.makeText(this.currentActivity, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}