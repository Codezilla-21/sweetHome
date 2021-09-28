package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;

import java.io.File;
import java.util.ArrayList;

public class homeDetails extends AppCompatActivity {

    int position;
    ImageView prevBtn;
    ImageView nextBtn;

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

        ArrayList<String > images = intent.getStringArrayListExtra("Images");
        String Location = intent.getExtras().getString("Address");
        int price = intent.getExtras().getInt("Price");
        String type = intent.getExtras().getString("Type");
        String age = intent.getExtras().getString("Age");
        String room = intent.getExtras().getString("RoomNum");
        String floor = intent.getExtras().getString("FloorNum");
        String area = intent.getExtras().getString("Area");
        String rentOrSell = intent.getExtras().getString("RentSell");
        String extras = intent.getExtras().getString("PoolBalcony");
        String info = intent.getExtras().getString("Info");
        String email = intent.getExtras().getString("Email");
        String idItem = intent.getExtras().getString("ID");

        TextView infoText = findViewById(R.id.Information);
        if (type.equals("Villa") || type.equals("Apartment")){
            infoText.setText("Price: "+price+" - "+area+"m^2 - "+room+ " room - "+ floor+" floor(s) - "+"Age of building : "
                    +age+" year(s) - "+info);
        }else if (type.equals("Flat")){
            infoText.setText("Price: "+price+" - "+area+"m^2 - "+room+ " room - "+"Age of building : "
                    +age+" year(s) - "+info);
        }

        TextView rS = findViewById(R.id.rentSell);
        if (rentOrSell.equals("Sell")){
            rS.setText("For Sell - " + extras);
        }else{
            rS.setText("For Rent - "+extras);
        }

        TextView address = findViewById(R.id.address);
        address.setText(Location);


        ImageView imgSwitch = findViewById(R.id.imagesSwitcher);
        Amplify.Storage.downloadFile(
                String.valueOf(images.get(0)),
                new File(getApplicationContext().getFilesDir() + "/Example Key.jpg"),
                result2 -> {
                    imgSwitch.setImageBitmap(BitmapFactory.decodeFile(result2.getFile().getPath()));
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result2.getFile().getName());
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );


        prevBtn = findViewById(R.id.imgPrevious);
        nextBtn = findViewById(R.id.imgNext);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( images.size()>position && position > 0){
                    position--;
                    Amplify.Storage.downloadFile(
                            String.valueOf(images.get(position)),
                            new File(getApplicationContext().getFilesDir() + "/Example Key.jpg"),
                            result2 -> {
                                imgSwitch.setImageBitmap(BitmapFactory.decodeFile(result2.getFile().getPath()));
                                Log.i("MyAmplifyApp", "Successfully downloaded: " + result2.getFile().getName());
                            },
                            error -> Log.e("MyAmplifyApp", "Download Failure", error)
                    );

                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( position< images.size()-1){
                    position++;

                }
            }
        });



        TextView sendEmail = findViewById(R.id.sendEmail);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEmail =new Intent(homeDetails.this,SendEmail.class);
                goToEmail.putExtra("Email",email);
                startActivity(goToEmail);
            }
        });

        TextView delete= findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFunction(idItem);
                Intent backToProfile=new Intent(homeDetails.this,profilePage.class);
                startActivity(backToProfile);
            }
        });
    }
    private void deleteFunction(String id) {
        Amplify.API.query(
                ModelQuery.get(sweetHouse.class, id),
                response -> {
                    Log.i("MyAmplifyApp", ((sweetHouse) response.getData()).getId());
                    Amplify.API.mutate(ModelMutation.delete(response.getData()),
                            result -> {
                                Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId());
                            },
                            error -> {
                                Log.e("MyAmplifyApp", "Create failed", error);
                            });
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
    }
}