package com.jamestiotio.androidfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewSampleNodeValue;
    ImageView imageViewSatisfied;
    TextView textViewTally;
    final static int REQUEST_IMAGE_GET = 2000;

    final String SAMPLE_NODE = "pokemon";
    final String SATISFIED = "satisfied";
    final String TALLY = "tally";
    final String NO_SATISFIED = "number_satisfied";

    DatabaseReference mRootDatabaseRef;
    DatabaseReference mNodeRefPokemon;
    DatabaseReference mNodeRefSatisfied;
    DatabaseReference mNodeRefTally;

    final String sharedPrefFile = "sharedPref";
    SharedPreferences sharedPreferences;
    final String SATISFIED_KEY = "key_satisfied";
    int satisfiedTallyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSampleNodeValue = findViewById(R.id.textViewSampleNodeValue);
        textViewTally = findViewById(R.id.textViewTally);
        imageViewSatisfied = findViewById(R.id.imageViewSatisfied);

        /** This is to the root note
         /* The rest are to child nodes */
        mRootDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mNodeRefPokemon = mRootDatabaseRef.child(SAMPLE_NODE);
        mNodeRefSatisfied = mRootDatabaseRef.child(SATISFIED);
        mNodeRefTally = mRootDatabaseRef.child(TALLY);

        Log.i("Pokemon","" + mRootDatabaseRef.toString());
        Log.i("Pokemon", "" + mNodeRefPokemon.toString());

        /** executing mNodeRefSatisfied.push() creates child nodes with random ID
         *  mNoteRefTally.child("data") creates a child node if it didn't exist
         *  mNoteRefTally.child("data").setValue() assigns a value to the node
         *  explore what happens if you did this subsequently:
         *  mNoteRefTally.child("data").child("data1").setValue() */
        imageViewSatisfied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                mNodeRefSatisfied.push().setValue(timestamp.toString());
                mNodeRefTally.child(NO_SATISFIED).setValue(satisfiedTallyValue + 1);
                Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
            }
        });

        /** this is code to listen for changes in the value of any particular node */
        mNodeRefPokemon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String change = snapshot.getValue(String.class);
                textViewSampleNodeValue.setText(change);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.i("Pokemon","" + error.toString());
            }
        });

        /**this is code to listen for changes in the child nodes of any node
         * here, I am counting the number of child nodes */
        mNodeRefSatisfied.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                satisfiedTallyValue = (int) dataSnapshot.getChildrenCount();
                textViewTally.setText(Integer.toString(satisfiedTallyValue));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Pokemon", databaseError.toString());

            }
        });

        final ImageView imageViewBackground = findViewById(R.id.imageViewBackground);
        final StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        imageViewBackground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Task<ListResult> taskListResult = storageRef.child("backgrounds").listAll();
                taskListResult.continueWithTask(new Continuation<ListResult, Task<byte[]>>() {
                    @Override
                    public Task<byte[]> then(@NonNull Task<ListResult> task) throws Exception {
                        ListResult listResult = task.getResult();
                        ArrayList<StorageReference> refs = new ArrayList<>(listResult.getItems());
                        Random r = new Random();
                        int p = r.nextInt(refs.size()-1);
                        StorageReference ref = refs.get(p);
                        return FirebaseUtils.downloadToImageView(MainActivity.this, ref, imageViewBackground);
                    }
                });
            }
        });

        FloatingActionButton uploadButton = findViewById(R.id.uploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        String storedTallyValue = sharedPreferences.getString(SATISFIED_KEY,"0");
        textViewTally.setText(storedTallyValue);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putString(SATISFIED_KEY, Integer.toString(satisfiedTallyValue));
        preferencesEditor.apply();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            try {
                String filename = FirebaseUtils.getFileName(MainActivity.this, fullPhotoUri);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                System.out.println(filename);
                StorageReference imageRef = storageRef.child("/backgrounds/" + filename);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fullPhotoUri);
                FirebaseUtils.uploadImageToStorage(MainActivity.this, imageRef, bitmap);
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, R.string.io_error, Toast.LENGTH_LONG);
            }
        } else {
            Toast.makeText(MainActivity.this, R.string.file_not_found, Toast.LENGTH_LONG);
        }
    }
}