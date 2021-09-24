package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class Login extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private EditText email;
    private EditText password;
    private Button singInBtn;
    private Button singUpBtn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        singInBtn = findViewById(R.id.login);
        singUpBtn = findViewById(R.id.signup);


        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSingUpBtn = new Intent(Login.this, Signup.class);
                startActivity(goToSingUpBtn);
            }
        });

        singInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Amplify.Auth.fetchAuthSession(
                        result -> Log.i("AmplifyQuickstart", result.toString()),
                        error -> Log.e("AmplifyQuickstart", error.toString())
                );

                signIn(email.getText().toString(), password.getText().toString());
            }
        });
    }
    void signIn(String username, String password) {
        Amplify.Auth.signIn(
                username,
                password,
                result  -> {
                    Log.i(TAG, "signIn: worked " + result .toString());
                    Intent goToMain = new Intent(Login.this, MainActivity.class);
                    startActivity(goToMain);
                },
                error -> Log.e(TAG, "signIn: failed" + error.toString()));
    }
}