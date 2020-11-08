package com.jamestiotio.randomimage;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class LoadFirstImageInSet implements View.OnClickListener {
    private final ImageView target;
    private final ArrayList<Integer> images;

    public LoadFirstImageInSet(ImageView target, ArrayList<Integer> images) {
        this.target = target;
        this.images = new ArrayList<>(images);
    }

    @Override
    public void onClick(View v) {
        int id = this.images.get(0);
        this.target.setImageResource(id);
    }
}
