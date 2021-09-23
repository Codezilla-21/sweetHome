package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

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

                    AuthSignUpOptions options = AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
                            .build();
                    Amplify.Auth.signUp(username.getText().toString(), password.getText().toString(), options,
                            result -> {
                                Log.i("AuthQuickStart", "Result: " + result.toString());

                                Intent goToConfirmation = new Intent(Signup.this, ConfirmSignUp.class);
                                startActivity(goToConfirmation);

                            },
                            error -> {
                                Log.e("AuthQuickStart", "Sign up failed", error);

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        final Toast toast = Toast.makeText(getApplicationContext(), "\"USER ALREADY EXIST  OR Password or Username does not match requirements\"", 5000);
                                        toast.show();
                                    }
                                });
                              //  Toast.makeText(getApplicationContext(), "USER ALREADY EXIST  OR Password or Username does not match requirements", Toast.LENGTH_LONG).show();
                            }
                    );


                }
            });


        }

}