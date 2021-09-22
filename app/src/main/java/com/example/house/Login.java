package com.example.house;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    //get UserName, PassWord
    private static final String TAG = "SignInActivity";
    private EditText username ;
    private EditText password ;
    private Button singInBtn ;
    private Button singUpBtn ;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goToHome);
                return false;
            }
        });

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        singInBtn = findViewById(R.id.login);
        singUpBtn = findViewById(R.id.signup);

        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSingUpBtn = new Intent(Login.this, MainActivity.class);
                startActivity(goToSingUpBtn);
            }
        });


    }
}