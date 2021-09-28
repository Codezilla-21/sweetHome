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



    BottomNavigationItemView logout;
    BottomNavigationView home;

    BottomNavigationItemView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extras = getIntent().getStringExtra("userName");
//        Amplify.Auth.fetchAuthSession(
//
//                result -> {
//                    if (result.isSignedIn()){
//                        extras= Amplify.Auth.getCurrentUser().getUsername();
//
//                        TextView userName = findViewById(R.id.textView2);
//                        userName.setText(extras);
//                    }
//                    Log.i("AmplifyQuickstart", result.toString());
//
//                },
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
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

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profilePage.class);
                intent.putExtra("userName",extras.toString());
                startActivity(intent);
            }
        });

        home = findViewById(R.id.Home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        logout=findViewById(R.id.logButton);
        logout.setOnClickListener(new View.OnClickListener() {
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



//    @Override
//    protected void onStart(){
//        super.onStart();
//
//
//    }
}}