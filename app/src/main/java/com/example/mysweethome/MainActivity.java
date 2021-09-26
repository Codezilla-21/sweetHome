package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;


import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ImageView imageView ;
    BottomNavigationItemView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addHome = findViewById(R.id.addHome);
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
                Intent goTOSignIn =new Intent(MainActivity.this,Login.class);
                startActivity(goTOSignIn);
            }
        });

        String extras = getIntent().getStringExtra("userName");
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


        // move this to home details to recyclerview
        Button sendEmail = findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEmail =new Intent(MainActivity.this,SendEmail.class);
                startActivity(goToEmail);
            }
        });

    }
}