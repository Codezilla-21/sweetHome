package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.handyopinion.R;

public class ForgetPassword extends AppCompatActivity {

//    EditText etEmail;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_password);
//        viewInitializations();
//
//
//    }
//
//    void viewInitializations() {
//        etEmail = findViewById(R.id.et_email);
//
//        // To show back button in actionbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }
//
//    // Checking if the input in form is valid
//    boolean validateInput() {
//
//        if (etEmail.getText().toString().equals("")) {
//            etEmail.setError("Please Enter Email");
//            return false;
//        }
//
//        // checking the proper email format
//        if (!isEmailValid(etEmail.getText().toString())) {
//            etEmail.setError("Please Enter Valid Email");
//            return false;
//        }
//
//
//        return true;
//    }
//
//    boolean isEmailValid(String email) {
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }
//
//    // Hook Click Event
//
//    public void performCodeVerify (View v) {
//        if (validateInput()) {
//
//            // Input is valid, here send data to your server
//
//            String email = etEmail.getText().toString();
//
//            Intent intent = new Intent(this, ConfirmSignUp.class);
//            startActivity(intent);
//            Toast.makeText(this,"Email send to Register Email Address",Toast.LENGTH_SHORT).show();
//            // Here you can call you API
//            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
//
//        }
//    }

}