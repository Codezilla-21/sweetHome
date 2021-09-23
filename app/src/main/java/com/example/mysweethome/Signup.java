package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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