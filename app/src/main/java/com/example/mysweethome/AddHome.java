package com.example.mysweethome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.core.Amplify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AddHome extends AppCompatActivity {

    private String selected;
   // private EditText titleText;
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

        Button upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImagesIntent();
           //     Intent pickFile=new Intent(Intent.EXTRA_ALLOW_MULTIPLE);
//                @SuppressLint("IntentReset") Intent pickFile = new Intent(Intent.ACTION_GET_CONTENT);
//                pickFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                pickFile.setType("*/*");
//                pickFile=Intent.createChooser(pickFile,"Pick a Photo");
//                startActivityForResult(pickFile,5050);
            }
        });

        System.out.println("*********************************"+imageUris);

    }

    private void pickImagesIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Images"), PICK_IMAGES_CODE);
        System.out.println("---------------PICK-------------------"+imageUris);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_CODE){

            System.out.println("1111111111111111111111111");
            System.out.println("1111111111111111111111111");
            if (resultCode == Activity.RESULT_OK){

                System.out.println("22222222222222222222222222222");
                if (data.getClipData() != null){

                    System.out.println("33333333333333333333333333333333");
                    int pickedImagesNumber= data.getClipData().getItemCount();
                    for (int i = 0; i <pickedImagesNumber ; i++) {

                        Uri image = data.getClipData().getItemAt(i).getUri();
                        imageUris.add(image.toString());
                        System.out.println("------------------IF FOR----------------"+image);
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
    //    @Override
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            // String key=titleText.getText().toString();
//            String key="titleText.getText().toString()";
//            File exampleFile = new File(getApplicationContext().getFilesDir(), "images");
//            try {
//                InputStream inputStream=getContentResolver().openInputStream(data.getData());
//                OutputStream outputStream=new FileOutputStream(exampleFile);
//                byte[]buf=new byte[1024];
//                int len;
//                while ((len=inputStream.read(buf))>0){
//                    outputStream.write(buf,0,len);
//                }
//                inputStream.close();
//                outputStream.close();
//
//            } catch (Exception exception) {
//                Log.e("MyAmplifyApp", "Upload failed", exception);
//            }
//
//            Amplify.Storage.uploadFile(
//                key,
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }
}