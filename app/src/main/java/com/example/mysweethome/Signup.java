package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

        private static final String TAG = "SignUpActivity";
        private TextInputEditText username ;
        private TextInputEditText email ;
        private TextInputEditText password ;
        private Button signUpBtn ;
        private Handler toastHandler ;
        ImageView imageView;
        Animation top;
        Button goLogin;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
            imageView = findViewById(R.id.imag2);
            username = findViewById(R.id.username);
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            signUpBtn = findViewById(R.id.save);
            imageView.setAnimation(top);
            goLogin = findViewById(R.id.loginn);


            goLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent login = new Intent(Signup.this,Login.class);
                    startActivity(login);
                }
            });


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
                                goToConfirmation.putExtra("Name", username.getText().toString());
                                startActivity(goToConfirmation);

                            },
                            error -> {
                                Log.e("AuthQuickStart", "Sign up failed", error);

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        @SuppressLint("WrongConstant") final Toast toast = Toast.makeText(getApplicationContext(), "\"USER ALREADY EXIST  OR Password or Username does not match requirements\"", 5000);
                                        toast.show();
                                    }
                                });
                            }
                    );
                }
            });
        }
}