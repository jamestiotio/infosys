package com.jamestiotio.exchangerate;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class StartSubActivity implements View.OnClickListener {
    private final Activity currentActivity;
    private final Class<SubActivity> targetActivity;

    StartSubActivity(Activity currentActivity, Class<SubActivity> targetActivity) {
        this.currentActivity = currentActivity;
        this.targetActivity = targetActivity;
    }

    @Override
    public void onClick(View V) {
        Intent intent = new Intent(this.currentActivity, this.targetActivity);
        this.currentActivity.startActivity(intent);
    }
}