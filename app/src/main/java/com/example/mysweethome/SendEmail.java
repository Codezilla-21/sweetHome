package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SendEmail extends AppCompatActivity {

    EditText editTextTo,editTextSubject,editTextMessage;

    TextView send;
    String ownerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        ownerEmail= getIntent().getStringExtra("Email");

        editTextTo=(EditText)findViewById(R.id.editText1);
        editTextSubject=(EditText)findViewById(R.id.editText2);
        editTextMessage=(EditText)findViewById(R.id.editText3);

        send=(TextView) findViewById(R.id.button1);




        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                editTextTo.setText(ownerEmail);
                String subject=editTextSubject.getText().toString();
                String message=editTextMessage.getText().toString();


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ ownerEmail});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }

        });


        BottomNavigationItemView bottom = findViewById(R.id.Account);

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profilePage.class);

                startActivity(intent);
            }
        });

        FloatingActionButton addHome = findViewById(R.id.addHome);
        addHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddHome = new Intent(getApplicationContext(), AddHome.class);
                startActivity(goToAddHome);
            }
        });
        BottomNavigationView navView = findViewById(R.id.bottomNavView);
        navView.setBackground(null);


        BottomNavigationItemView myHome = findViewById(R.id.Home);
        myHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationItemView search = findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(getApplicationContext(),SpinnerClass.class);
                startActivity(goToSearch);
            }
        });

        BottomNavigationItemView logout;
        logout = findViewById(R.id.logButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> {
                            Amplify.Auth.fetchAuthSession(

                                    result -> {
                                        if (!result.isSignedIn()) {
                                            Intent goToLogin = new Intent(getApplicationContext(), Login.class);
                                            startActivity(goToLogin);
                                        }
                                        Log.i("AmplifyQuickstart", result.toString());

                                    },
                                    error -> Log.e("AmplifyQuickstart", error.toString())
                            );


                            Log.i("AuthQuickstart", "Signed out globally");
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );

            }
        });

    }
}