package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoMfaSettings;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSettings;
import com.amplifyframework.core.Amplify;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        EditText newPassword = findViewById(R.id.reset1);
        Button submitNewPassword =  findViewById(R.id.resetPassword);

//        AWSMobileClient.getInstance().forgotPassword("username", new Callback<ForgotPasswordResult>() {
//            @Override
//            public void onResult(final ForgotPasswordResult result) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.d(TAG, "forgot password state: " + result.getState());
//                        switch (result.getState()) {
//                            case CONFIRMATION_CODE:
//                                makeToast("Confirmation code is sent to reset password");
//                                break;
//                            default:
//                                Log.e(TAG, "un-supported forgot password state");
//                                break;
//                        }
//                    }
//                });
//            }
//
//        submitNewPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Amplify.Auth.confirmResetPassword(
//                        newPassword.getText().toString(),
//                        "confirmation code you received",
//                        () -> Log.i("AuthQuickstart", "New password confirmed"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//                Amplify.Auth.updatePassword(
//                        "Old",
//                        newPassword.getText().toString(),
//                        () -> Log.i("AuthQuickstart", "Updated password successfully"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );


//            }


//        });
    }
}