package com.jamestiotio.sutdpokedex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataEntry extends AppCompatActivity {
    EditText editTextNameEntry;
    Button buttonSelectImage;
    Button buttonOK;
    ImageView imageViewSelected;
    Bitmap bitmap;
    final static int REQUEST_IMAGE_GET = 2000;
    final static String KEY_PATH = "Image";
    final static String KEY_NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        editTextNameEntry = findViewById(R.id.editTextNameEntry);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);
        buttonOK = findViewById(R.id.buttonOK);

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }

            }
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = Activity.RESULT_OK;
                Intent resultIntent = new Intent();
                String name = editTextNameEntry.getText().toString();
                String path = Utils.saveToInternalStorage(bitmap, name,
                        DataEntry.this);
                resultIntent.putExtra(KEY_NAME, name);
                resultIntent.putExtra(KEY_PATH, path);
                setResult(resultCode,resultIntent);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            //the image selected is passed as a Uri
            Uri fullPhotoUri = data.getData();
            //display the image selected
            imageViewSelected.setImageURI(fullPhotoUri);

            try {
                bitmap = MediaStore.Images.Media.getBitmap( this.getContentResolver(),
                        fullPhotoUri);
            }
            catch (FileNotFoundException ex) {
                //write a Toast
            }
            catch (IOException ex) {
                //write a Toast
            }
        }
    }
}
