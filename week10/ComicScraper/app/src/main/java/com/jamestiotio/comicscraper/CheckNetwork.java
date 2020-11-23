package com.jamestiotio.comicscraper;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CheckNetwork implements View.OnClickListener {
    private final Activity activity;
    private final EditText comicNo;

    public CheckNetwork(Activity activity, EditText comicNo) {
        this.activity = activity;
        this.comicNo = comicNo;
    }

    @Override
    public void onClick(View v) {
        String comicNoValue = this.comicNo.getText().toString();

        if (Utils.isNetworkAvailable(activity)) {
            GetComic getComic = new GetComic(this.activity);
            getComic.execute(comicNoValue); // This is the algorithm
        }

        else {
            Toast.makeText(this.activity, R.string.error_no_network, Toast.LENGTH_LONG).show();
        }
    }
}