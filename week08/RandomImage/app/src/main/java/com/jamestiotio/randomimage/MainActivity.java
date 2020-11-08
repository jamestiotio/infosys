package com.jamestiotio.randomimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
				R.drawable.eevee, R.drawable.gyrados, R.drawable.judyhopps,
				R.drawable.nemo, R.drawable.nickwilde, R.drawable.pikachu, R.drawable.psyduck,
				R.drawable.snorlax, R.drawable.spearow, R.drawable.tomandjerry,
				R.drawable.yoda));

        charaButton = findViewById(R.id.charaButton);
        charaImage = findViewById(R.id.charaImage);

        charaButton.setOnClickListener(v -> {
            Random r = new Random();
            int randomIndex = r.nextInt(images.size());
            int id = images.get(randomIndex);
            charaImage.setImageResource(id);
        });

        firstCharaButton = findViewById(R.id.firstCharaButton);

        firstCharaButton.setOnClickListener(v -> {
            int id = images.get(0);
            charaImage.setImageResource(id);
        });
    }
}
