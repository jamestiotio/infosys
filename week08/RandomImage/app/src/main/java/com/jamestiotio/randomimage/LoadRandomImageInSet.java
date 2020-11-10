package com.jamestiotio.randomimage;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.security.SecureRandom;

public class LoadRandomImageInSet implements View.OnClickListener {
    private final SecureRandom rng;
    private final ImageView target;
    private final ArrayList<Integer> images;

    public LoadRandomImageInSet(ImageView target, ArrayList<Integer> images) {
        this.rng = new SecureRandom();
        this.target = target;
        this.images = new ArrayList<>(images);
    }

    @Override
    public void onClick(View v) {
        int id = this.images.get(this.rng.nextInt(this.images.size()));
        this.target.setImageResource(id);
    }
}
