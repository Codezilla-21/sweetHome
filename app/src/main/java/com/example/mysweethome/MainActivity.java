package com.example.mysweethome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String extras;


    ImageView imageView ;
    BottomNavigationItemView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Amplify.Auth.fetchAuthSession(

                result -> {
                    if (result.isSignedIn()){
                        extras= Amplify.Auth.getCurrentUser().getUsername();
                        TextView userName = findViewById(R.id.textView2);
                        userName.setText(extras);
                    }
                    Log.i("AmplifyQuickstart", result.toString());

                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        FloatingActionButton addHome = findViewById(R.id.addHome);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddHome = new Intent(MainActivity.this, AddHome.class);
                startActivity(goToAddHome);
            }
        });

        BottomNavigationView navView=findViewById(R.id.bottomNavView);
        navView.setBackground(null);

        bottom = findViewById(R.id.Account);
        imageView=findViewById(R.id.logButton);
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
                                        Intent goToLogin = new Intent(MainActivity.this, Login.class);
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
        Amplify.Auth.fetchAuthSession(

                result -> {
                    if (result.isSignedIn()){
                        extras= Amplify.Auth.getCurrentUser().getUsername();
                    }
                    Log.i("AmplifyQuickstart", result.toString());

                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        TextView userName = findViewById(R.id.textView2);
        if (extras != null){
            userName.setText(extras);
        }

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profilePage.class);
                intent.putExtra("userName",extras.toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

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


        Amplify.API.query(
                ModelQuery.list(sweetHouse.class),
                response -> {
                    System.out.println(response.toString());
//TODO: ADD CONDITION TO GET THE DATA ADDED  BY CURRENT USER
                    for (sweetHouse house : response.getData().getItems()) {
                        allData.add(house);
                        System.out.println("based on id ----------------: "+house);
                    }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "Out of Loop!");

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
    }
}