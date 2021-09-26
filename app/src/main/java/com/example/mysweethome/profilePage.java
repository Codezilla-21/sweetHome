package com.example.mysweethome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class profilePage extends AppCompatActivity {

    //userName

    TextView textView;
    TextView textView1;
    ImageView imageView;
    CircleImageView circleImageView;
    BottomNavigationItemView bottom;
//    FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        bottom = findViewById(R.id.Account);

        textView = findViewById(R.id.textView3);
        textView1 = findViewById(R.id.updateProfile);
//        floatingActionButton = findViewById(R.id.floatingActionButton2);
        String extras = getIntent().getStringExtra("userName");
        if (extras != null){
            textView.setText(extras);
        }

        circleImageView = findViewById(R.id.profile_image);
        imageView=findViewById(R.id.logButton);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTOSignIn =new Intent(profilePage.this,Login.class);
                startActivity(goTOSignIn);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(profilePage.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profilePage.this, profilePage.class);
                intent.putExtra("userName",extras.toString());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();

            // Use Uri object instead of File to avoid storage permissions
            circleImageView.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}