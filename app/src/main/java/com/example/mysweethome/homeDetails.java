package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

public class homeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
    }
    @Override
    protected void onStart(){
        super.onStart();

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



        //check this example, this is how you will pass arraylist of strings (images)
//        Intent intent=new Intent(getApplicationContext(),NewActivity2 .class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("VAR1", models1);
//        intent.putExtras(bundle);
//        this.startActivity(intent);
//        Bundle bundle = getIntent().getExtras();
//        ArrayList<Model1> arraylist = bundle.getParcelableArrayList("VAR1");
    }
}