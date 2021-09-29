package com.example.mysweethome;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    String extras;


    BottomNavigationItemView logout;


    BottomNavigationItemView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extras = getIntent().getStringExtra("userName");

        FloatingActionButton addHome = findViewById(R.id.addHome);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddHome = new Intent(MainActivity.this, AddHome.class);
                startActivity(goToAddHome);
            }
        });

        BottomNavigationView navView = findViewById(R.id.bottomNavView);
        navView.setBackground(null);

        bottom = findViewById(R.id.Account);

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profilePage.class);
                startActivity(intent);
            }
        });


        BottomNavigationItemView myHome = findViewById(R.id.Home);
        myHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


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
                    if (result.isSignedIn()) {
                        extras = Amplify.Auth.getCurrentUser().getUsername();
                    }
                    Log.i("AmplifyQuickstart", result.toString());

                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


    }
}