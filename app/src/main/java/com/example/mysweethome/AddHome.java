package com.example.mysweethome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
//import com.google.android.libraries.places.api.model.Place;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AddHome extends AppCompatActivity {

    private String selected;
    private ArrayList<String> imageUris;
    private static final int PICK_IMAGES_CODE=0;
    String address;
    Boolean poolB=false;
    Boolean balconyB=false;
    EditText area;
    EditText floor;
    EditText price;
    EditText rooms;
    EditText age;
    EditText info;
    String rentOrSellST="";
    EditText emailContacting;
    String currentUserId;

    Boolean isImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);

        imageUris=  new ArrayList<>();


        Amplify.Auth.fetchAuthSession(

                result -> {
                    if (result.isSignedIn()){
                        currentUserId= Amplify.Auth.getCurrentUser().getUserId();
                    }
                    Log.i("AmplifyQuickstart", result.toString());

                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

    }
    @SuppressLint("WrongViewCast")
    @Override
    protected void onStart() {
        super.onStart();



        Button pickLocation= findViewById(R.id.addLocation);
        pickLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map = new Intent(AddHome.this, Map.class);



                startActivity(map);
            }
        });

        area = findViewById(R.id.area);
        floor= findViewById(R.id.floor);
        price= findViewById(R.id.price);
        rooms= findViewById(R.id.numberOfRooms);
        age= findViewById(R.id.ageOfBuild);
        info= findViewById(R.id.moreDetails);
        emailContacting= findViewById(R.id.emailContact);


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



        Button upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImagesIntent();
                isImages = true;
            }
        });

        RadioButton balcony =(RadioButton)  findViewById(R.id.isBalcony);
        RadioButton  pool =(RadioButton)  findViewById(R.id.isPool);
        RadioButton  rent =(RadioButton)  findViewById(R.id.addForRent);
        RadioButton  sell =(RadioButton)  findViewById(R.id.addForSell);

        balcony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balconyB=true;
            }
        });
        pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poolB=true;
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rentOrSellST="For Rent";
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rentOrSellST="For Sell";
            }
        });

        Button addToDatabase= findViewById(R.id.addToDatabase);
        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(AddHome.this);
                address = sharedPreferences2.getString("Address", "Jordan");

                if(currentUserId != null){
                    try{
                        if (isImages){
                            sweetHouse house = sweetHouse.builder()
                                    .area(area.getText().toString())
                                    .location(PreferenceManager.getDefaultSharedPreferences(AddHome.this).getString("Address", "Jordan"))
                                    .numberOfRooms(rooms.getText().toString())
                                    .floors(floor.getText().toString())
                                    .price(Integer.parseInt(price.getText().toString()))
                                    .ageOfBuild(age.getText().toString())
                                    .pool(poolB)
                                    .rentOfSell(rentOrSellST)
                                    .image(imageUris)
                                    .balcony(balconyB)
                                    .type(selected)
                                    .email(emailContacting.getText().toString())
                                    .userId(currentUserId)
                                    .moreInfo(info.getText().toString())
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(house),
                                    result -> Log.i("MyAmplifyApp", "Added successfully"),
                                    error -> Log.e("MyAmplifyApp",  "Error ", error)
                            );

                        }

                    }catch(Exception e ){

                        Toast.makeText(AddHome.this, "Please Pick Location First And Fill Required Information And Add Some Images", Toast.LENGTH_LONG).show();
                    }
                }

                Intent backToMain = new Intent(AddHome.this, MainActivity.class);
                startActivity(backToMain);
            }
        });

    }

    private void pickImagesIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Images"), PICK_IMAGES_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGES_CODE){
            System.out.println("Result: "+ resultCode);
            if (resultCode == Activity.RESULT_OK){

                if (data.getClipData() != null){

                    int pickedImagesNumber= data.getClipData().getItemCount();
                    for (int i = 0; i <pickedImagesNumber ; i++) {

                        Uri image = data.getClipData().getItemAt(i).getUri();
                        String imageString = image.toString();
                        imageUris.add(image.toString());
                        try {
                            InputStream exampleInputStream = getContentResolver().openInputStream(image);
                            Amplify.Storage.uploadInputStream(
                                    imageString,
                                    exampleInputStream,
                                    result -> {
                                        Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey());
                                    },
                                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                                    //circleImageView
                            );
                        } catch (FileNotFoundException error) {
                            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
                        }
                    }

                    System.out.println("------------------IF----------------"+imageUris);
                }else{
                    // for single image
                    Uri image = data.getData();
                    imageUris.add(image.toString());
                }
            }
        }
    }
}