package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class homeDetails extends AppCompatActivity {

    int position;
    Button prevBtn;
    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
    }
    @Override
    protected void onStart(){
        super.onStart();

        position=0;

        // getting extras from reccler view
        Intent intent= getIntent();
        Bundle bundle =  getIntent().getExtras();

        ArrayList<Parcelable> images = bundle.getParcelableArrayList("Images");
        String Location = intent.getExtras().getString("Address");
        String price = intent.getExtras().getString("Price");
        String type = intent.getExtras().getString("Type");
        String age = intent.getExtras().getString("Age");
        String room = intent.getExtras().getString("RoomNum");
        String floor = intent.getExtras().getString("FloorNum");
        String area = intent.getExtras().getString("Area");
        String rentOrSell = intent.getExtras().getString("RentSell");
        String extras = intent.getExtras().getString("PoolBalcony");
        String info = intent.getExtras().getString("Info");
        String email = intent.getExtras().getString("Email");


        ImageSwitcher imgSwitch = findViewById(R.id.imagesSwitcher);
        imgSwitch.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgView =  new ImageView(getApplicationContext());
                return imgView;
            }
        });



        ArrayList<Uri> imagesUris = new ArrayList();
        for (Parcelable image : images) {
            imagesUris.add(Uri.parse(image.toString()));

        }
        imgSwitch.setImageURI(imagesUris.get(0));

        prevBtn = findViewById(R.id.imgPrevious);
        nextBtn = findViewById(R.id.imgNext);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position > 0){
                    position--;
                    imgSwitch.setImageURI(imagesUris.get(position));

                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position< images.size()-1){
                    position++;
                    imgSwitch.setImageURI(imagesUris.get(position));
                }
            }
        });




        //check this example, this is how you will pass arraylist of strings (images)
//        Intent intent=new Intent(getApplicationContext(),NewActivity2 .class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("VAR1", models1);
//        intent.putExtras(bundle);
//        this.startActivity(intent);
//        Bundle bundle = getIntent().getExtras();
//        ArrayList<Model1> arraylist = bundle.getParcelableArrayList("VAR1");


        Button sendEmail = findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEmail =new Intent(homeDetails.this,SendEmail.class);
                goToEmail.putExtra("Email",email);
                startActivity(goToEmail);
            }
        });
    }
}