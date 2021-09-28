package com.example.mysweethome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class profilePage extends AppCompatActivity {

    //userName

    TextView textView;
    TextView textView1;
    BottomNavigationItemView logout;
    ImageView circleImageView;
    BottomNavigationItemView bottom;
    BottomNavigationItemView home;
    String extras;
    Uri uri;
    String currentUserId;
//    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_profile_page);
        bottom = findViewById(R.id.Account);

        textView = findViewById(R.id.textView3);
        textView1 = findViewById(R.id.updateProfile);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFileFromDevice();
            }
        });

        TextView userName = findViewById(R.id.textView2);
        extras = getIntent().getStringExtra("userName");
//        userName.setText(extras);

        if (extras != null) {
            textView.setText(extras);
            Amplify.Storage.downloadFile(
                    extras,
                    new File(getApplicationContext().getFilesDir() + "/Example Key.jpg"),
                    result2 -> {
                        circleImageView.setImageBitmap(BitmapFactory.decodeFile(result2.getFile().getPath()));
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result2.getFile().getName());
                    },
                    error -> Log.e("MyAmplifyApp", "Download Failure", error)
            );
        }

        home = findViewById(R.id.Home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton addHome = findViewById(R.id.addHome);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddHome = new Intent(profilePage.this, AddHome.class);
                startActivity(goToAddHome);
            }
        });




        circleImageView = findViewById(R.id.profile_image);
        logout = findViewById(R.id.logButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> {
                            Amplify.Auth.fetchAuthSession(
                                    result -> {
                                        if (!result.isSignedIn()) {
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
                                    result2 -> {
                                        circleImageView.setImageBitmap(BitmapFactory.decodeFile(result2.getFile().getPath()));
                                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result2.getFile().getName());
                                    },
                                    error -> Log.e("MyAmplifyApp", "Download Failure", error)
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
    protected void onStart() {
        super.onStart();

        Amplify.Auth.fetchAuthSession(

                result -> {
                    if (result.isSignedIn()) {

                        currentUserId = Amplify.Auth.getCurrentUser().getUserId();
                        System.out.println("*****************************" + currentUserId);

                    }
                    Log.i("AmplifyQuickstart", result.toString());

                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

        RecyclerView allDataFromAWS = findViewById(R.id.recyclerOwner);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allDataFromAWS.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });

        List<sweetHouse> allData = new ArrayList<>();
        allDataFromAWS.setLayoutManager(new LinearLayoutManager(this));
        allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) allData));
        try {
            Amplify.API.query(
                    ModelQuery.list(sweetHouse.class),
                    response -> {
                        System.out.println(response.toString());
                        for (sweetHouse house : response.getData().getItems()) {
                            System.out.println("ooooooooooooooooo" + house.getArea());
                            if (house.getUserId().equals(currentUserId)) {
                                allData.add(house);
                                System.out.println("based on id ----------------: " + house);
                            } else {
                                System.out.println("*********************NULL**********************");
                            }

                        }

                        handler.sendEmptyMessage(1);
                        Log.i("MyAmplifyApp", "Out of Loop!");

                    },
                    error -> Log.e("MyAmplifyApp", "Query failure", error)
            );
        } catch (Exception e) {
            System.out.println("*******************NO DATA***********************");
        }


        //recyclerOwner


    }
}