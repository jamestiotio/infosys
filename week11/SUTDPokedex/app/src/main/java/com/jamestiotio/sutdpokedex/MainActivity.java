package com.jamestiotio.sutdpokedex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CharaAdapter charaAdapter;
    ImageView imageViewAdded;

    DataSource dataSource;

    final String KEY_DATA = "data";
    final String LOGCAT = "RV";
    final String PREF_FILE = "mainsharedpref";
    final int REQUEST_CODE_IMAGE = 1000;

    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.charaRecyclerView);
        imageViewAdded = findViewById(R.id.imageViewAdded);

        mPreferences = getSharedPreferences(PREF_FILE, MODE_PRIVATE);
        String json = mPreferences.getString(KEY_DATA, "");
        if (!json.isEmpty()) {
            Gson gson = new Gson();
            dataSource = gson.fromJson(json, DataSource.class);
        }
        else {
            ArrayList<Integer> drawableId = new ArrayList<>();
            drawableId.add(R.drawable.bulbasaur);
            drawableId.add(R.drawable.eevee);
            drawableId.add(R.drawable.gyrados);
            drawableId.add(R.drawable.pikachu);
            drawableId.add(R.drawable.psyduck);
            drawableId.add(R.drawable.snorlax);
            drawableId.add(R.drawable.spearow);
            drawableId.add(R.drawable.squirtle);

            dataSource = Utils.firstLoadImages(this,
                    drawableId);
        }

        Log.i("Pikachu", dataSource.getName(3));
        Log.i("Pikachu", dataSource.getPath(3));

        charaAdapter = new CharaAdapter( this, dataSource);
        recyclerView.setAdapter(charaAdapter);
        /** Explore using new GridLayoutManager */
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        //TODO 12.9 [OPTIONAL] Add code to delete a RecyclerView item upon swiping. See notes for the code.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataEntry.class);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor prefsEditor = mPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataSource);
        prefsEditor.putString(KEY_DATA,json);
        prefsEditor.apply();
    }

    /*
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

        return super.onOptionsItemSelected(item);
    }
    */

    // MainActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK){
            String name = data.getStringExtra( DataEntry.KEY_NAME);
            String path = data.getStringExtra( DataEntry.KEY_PATH);
            dataSource.addData(name, path);
            charaAdapter.notifyDataSetChanged();

            Bitmap bitmap = dataSource.getImage( dataSource.getSize() - 1);
            imageViewAdded.setImageBitmap(bitmap);
        }
    }
}