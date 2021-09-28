package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

//import com.example.handyopinion.R;

public class ForgetPassword extends AppCompatActivity {

//    EditText etEmail;
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Amplify.Auth.resetPassword(
                getIntent().getStringExtra("userName"),
                result -> Log.i("AuthQuickstart", result.toString()),
                error -> Log.e("AuthQuickstart", error.toString())
        );
        System.out.println("******************user "+ getIntent().getStringExtra("userName"));
        TextView continueForget= findViewById(R.id.bt_forget);
        TextView newPassword= findViewById(R.id.newPass);
        TextView code = findViewById(R.id.codeForget);
        continueForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.confirmResetPassword(
                        newPassword.getText().toString(),
                        code.getText().toString(),
                        () -> {
                            Log.i("AuthQuickstart", "New password confirmed");
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });
    }


}