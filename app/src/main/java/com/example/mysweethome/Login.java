package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class Login extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private EditText email;
    private EditText password;
    private Button singInBtn;
    private Button singUpBtn;
    private Handler handler;
    ImageView imageView;
    Animation top;
    TextView forgetPasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        imageView = findViewById(R.id.imagg);

        try {
            //    Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        singInBtn = findViewById(R.id.login);
        singUpBtn = findViewById(R.id.signup);
        imageView.setAnimation(top);
        forgetPasswords = findViewById(R.id.forgetPassword);


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

                signIn(email.getText().toString(), password.getText().toString());

                Amplify.Auth.fetchAuthSession(
                        result -> {
                            if (result.isSignedIn()){
                                Intent goToMain = new Intent(Login.this, MainActivity.class);
                                goToMain.putExtra("userName",email.getText().toString());
                                startActivity(goToMain);
                            }
                            Log.i("AmplifyQuickstart", result.toString());

                        },
                        error -> Log.e("AmplifyQuickstart", error.toString())
                );
            }
        });

//        Intent intent=new Intent(Login.this,MainActivity.class);
//        intent.putExtra("userName", (Parcelable) email);
//
        forgetPasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

    }
    void signIn(String username, String password) {
        Amplify.Auth.signIn(
                username,
                password,
                result  -> {
                    Log.i(TAG, "signIn: worked " + result .toString());

                },
                error -> Log.e(TAG, "signIn: failed" + error.toString()));
    }







}