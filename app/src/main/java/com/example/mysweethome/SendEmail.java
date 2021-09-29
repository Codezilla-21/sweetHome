package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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


    }
}