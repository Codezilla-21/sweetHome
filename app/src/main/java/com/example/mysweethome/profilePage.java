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

    FloatingActionButton logOut;
    BottomNavigationItemView bottom;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        bottom = findViewById(R.id.Account);
        logOut = findViewById(R.id.logButton);

        logOut.setOnClickListener(new View.OnClickListener() {
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

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profilePage.class);
                startActivity(intent);
            }
        });
        FloatingActionButton addHome = findViewById(R.id.addHome);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddHome = new Intent(getApplicationContext(), AddHome.class);
                startActivity(goToAddHome);
            }
        });

        BottomNavigationItemView search = findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(getApplicationContext(),SpinnerClass.class);
                startActivity(goToSearch);
            }
        });
        BottomNavigationItemView myHome = findViewById(R.id.Home);
        myHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton logout = findViewById(R.id.logButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> {
                            Amplify.Auth.fetchAuthSession(

                                    result -> {
                                        if (!result.isSignedIn()) {
                                            Intent goToLogin = new Intent(getApplicationContext(), Login.class);
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