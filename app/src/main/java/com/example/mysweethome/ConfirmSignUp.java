package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

public class ConfirmSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sign_up);

        EditText code= findViewById(R.id.verification);
        TextView verificationText = findViewById(R.id.codeVerification);
        Button confirm = findViewById(R.id.confirmation);

         EditText username = findViewById(R.id.username);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.confirmSignUp(
                        username.getText().toString(),
                        code.getText().toString(),
                        result -> {
                            Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                            Intent backToSignIn = new Intent(ConfirmSignUp.this, Login.class);
                            startActivity(backToSignIn);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });
    }
}