package com.example.house;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class Signup extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private EditText username ;
    private EditText email ;
    private EditText password ;
    private Button signUpBtn ;
    private Handler toastHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toastHandler = new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                Toast.makeText(getApplicationContext() , "signed up successfully " , Toast.LENGTH_LONG).show();
                return false;
            }
        });
        username = findViewById(R.id.username);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        signUpBtn = findViewById(R.id.save);

        signUpBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }
}