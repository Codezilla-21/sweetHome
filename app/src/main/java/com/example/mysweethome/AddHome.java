package com.example.mysweethome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddHome extends AppCompatActivity {

    private String selected;
    private ArrayList<String> imageUris;
    private static final int PICK_IMAGES_CODE=0;
    int position  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);
    }
    @Override
    protected void onStart() {
        super.onStart();


        Spinner spinner = findViewById(R.id.type);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apartment");
        arrayList.add("Villa");
        arrayList.add("Flat");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                selected=tutorialsName;

                //  Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        imageUris= new ArrayList<>();

        Button upload=findViewById(R.id.addLocation);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImagesIntent();
            }
        });

    }

    private void pickImagesIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Images"), PICK_IMAGES_CODE);
        //  System.out.println("---------------PICK-------------------"+imageUris);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_CODE){
            if (resultCode == Activity.RESULT_OK){
                if (data.getClipData() != null){
                    int pickedImagesNumber= data.getClipData().getItemCount();
                    for (int i = 0; i <pickedImagesNumber ; i++) {

                        Uri image = data.getClipData().getItemAt(i).getUri();
                        imageUris.add(image.toString());
                    }
                }
                else{
                    Uri image = data.getData();
                    imageUris.add(image.toString());
                }
            }
        }
    }
}