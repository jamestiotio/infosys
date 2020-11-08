package com.jamestiotio.randomimage;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class LoadRandomImageInSet implements View.OnClickListener {
    private final Random rng;
    private final ImageView target;
    private final ArrayList<Integer> images;

    public LoadRandomImageInSet(ImageView target, ArrayList<Integer> images) {
        this.rng = new Random();
        this.target = target;
        this.images = new ArrayList<>(images);
    }

    @Override
    public void onClick(View v) {
        int id = this.images.get(this.rng.nextInt(this.images.size()));
        this.target.setImageResource(id);
    }
}