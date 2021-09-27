package com.example.mysweethome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class profilePage extends AppCompatActivity {

    //userName

    TextView textView;
    TextView textView1;
    ImageView imageView;
    ImageView circleImageView;
    BottomNavigationItemView bottom;
    String extras;
    Uri uri;
//    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        bottom = findViewById(R.id.Account);

        textView = findViewById(R.id.textView3);
        textView1 = findViewById(R.id.updateProfile);
        extras = getIntent().getStringExtra("userName");
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFileFromDevice();
            }
        });

        if (extras != null) {
            textView.setText(extras);
            Amplify.Storage.downloadFile(
                    extras,
                    new File(getApplicationContext().getFilesDir() + "/Example Key.jpg"),
                    result2 ->{
                        circleImageView.setImageBitmap(BitmapFactory.decodeFile(result2.getFile().getPath()));
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result2.getFile().getName());
                    },
                    error -> Log.e("MyAmplifyApp",  "Download Failure", error)
            );
        }


        circleImageView = findViewById(R.id.profile_image);
        imageView = findViewById(R.id.logButton);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> {
                            Amplify.Auth.fetchAuthSession(
                                    result -> {
                                        if (!result.isSignedIn()){
                                            Intent goToLogin = new Intent(profilePage.this, Login.class);
                                            startActivity(goToLogin);
                                        }
                                        Log.i("AmplifyQuickstart", result.toString());

                                    },
                                    error -> Log.e("AmplifyQuickstart", error.toString())
                            );

                            Log.i("AuthQuickstart", "Signed out globally");
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );

            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(profilePage.this)
//                        .crop()               //Crop image(Optional), Check Customization for more option
//                        .compress(1024)        //Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)   //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilePage.this, profilePage.class);
                intent.putExtra("userName", extras.toString());
                startActivity(intent);
            }
        });

    }

    private void uploadInputStream() {
        if (uri != null) {
            try {
                InputStream exampleInputStream = getContentResolver().openInputStream(uri);
                Amplify.Storage.uploadInputStream(
                        extras,
                        exampleInputStream,
                        result -> {
                            Amplify.Storage.downloadFile(
                                    extras,
                                    new File(getApplicationContext().getFilesDir() + "/Example Key.jpg"),
                                    result2 ->{
                                        circleImageView.setImageBitmap(BitmapFactory.decodeFile(result2.getFile().getPath()));
                                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result2.getFile().getName());
                                    },
                                    error -> Log.e("MyAmplifyApp",  "Download Failure", error)
                            );
                            Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey());
                        },
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                        //circleImageView
                );
            } catch (FileNotFoundException error) {
                Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        uploadInputStream();
    }

    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose File!");
        startActivityForResult(chooseFile, 2048);

    }

    @Override
    protected void onStart(){
        super.onStart();

        //recyclerOwner


    }
}