package com.jamestiotio.randomimage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> images;
    Button charaButton;
    ImageView charaImage;
    Button firstCharaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>(Arrays.asList(R.drawable.ashketchum, R.drawable.bartsimpson,
                R.drawable.bulbasaur, R.drawable.edogawaconan, R.drawable.default_pokemon,
                R.drawable.eevee, R.drawable.gyrados, R.drawable.judyhopps, R.drawable.nemo,
                R.drawable.nickwilde, R.drawable.pikachu, R.drawable.psyduck, R.drawable.snorlax,
                R.drawable.spearow, R.drawable.tomandjerry, R.drawable.yoda));

        charaImage = findViewById(R.id.charaImage);

        charaButton = findViewById(R.id.charaButton);
        charaButton.setOnClickListener(new LoadRandomImageInSet(charaImage, images));

        firstCharaButton = findViewById(R.id.firstCharaButton);
        firstCharaButton.setOnClickListener(new LoadFirstImageInSet(charaImage, images));
    }
}
