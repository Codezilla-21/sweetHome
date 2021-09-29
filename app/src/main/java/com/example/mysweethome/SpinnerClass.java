package com.example.mysweethome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;

import java.util.ArrayList;
import java.util.List;

public class SpinnerClass extends AppCompatActivity {
    private Spinner spinner;
    EditText fromPrice;
    EditText toPrice;
    String selected;
    List<sweetHouse> arr1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_class);


    }
    @Override
    protected void onStart(){
        super.onStart();

        RecyclerView allDataFromAWS = findViewById(R.id.recView);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allDataFromAWS.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });


        List<sweetHouse> allData = new ArrayList<>();
        allDataFromAWS.setLayoutManager(new LinearLayoutManager(this));


        Intent intent= getIntent();
        int price = intent.getExtras().getInt("Price");
        String type = intent.getExtras().getString("Type");

        Amplify.API.query(
                ModelQuery.list(sweetHouse.class),
                response -> {
                    System.out.println(response.toString());
                    for (sweetHouse house : response.getData().getItems()) {
                        allData.add(house);
                    }
                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "Out of Loop!");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


        //Ali -- Filtered
        spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Selected House Type");
        arrayList.add("Apartment");
        String str1 = "Apartment";
        arrayList.add("Villa");
        String str2 = "Villa";
        arrayList.add("Flat");
        String str3 = "Flat";
        fromPrice = findViewById(R.id.fromPrice);
        int fromPriceValue = 0;
        toPrice = findViewById(R.id.toPrice);
        int toPriceValue = 1000000000;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                selected=tutorialsName;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Button filter = findViewById(R.id.fitered);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Apartment,Villa,Flat*/
                arr1 = new ArrayList<>();
                if (selected.equals("Selected House Type") && toPrice.getText().toString().equals("") && fromPrice.getText().toString().equals("")){
                    for (sweetHouse allDatum : allData) {
                        arr1.add(allDatum);
                    }
                    allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) allData));
                }
                List<sweetHouse> arr2= new ArrayList<>();
                if(selected.equals(str1) && toPrice != null && fromPrice != null){
                    for (sweetHouse allDatum : allData) {
                        if (allDatum.getPrice() <= Integer.parseInt(toPrice.getText().toString()) && allDatum.getPrice() >= Integer.parseInt(fromPrice.getText().toString())){
                            arr2.add(allDatum);
                        }
                        allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr2));
                    }
                }
                List<sweetHouse> arr3 = new ArrayList<>();
                if(selected.equals(str2) && toPrice != null && fromPrice != null){
                    for (sweetHouse allDatum : allData) {
                        if (allDatum.getPrice() <= Integer.parseInt(toPrice.getText().toString()) && allDatum.getPrice() >= Integer.parseInt(fromPrice.getText().toString())){
                            arr3.add(allDatum);
                        }
                        allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr3));
                    }
                }
                List<sweetHouse> arr4 = new ArrayList<>();
                if(selected.equals(str3) && toPrice != null && fromPrice != null){
                    for (sweetHouse allDatum : allData) {
                        if (allDatum.getPrice() <= Integer.parseInt(toPrice.getText().toString()) && allDatum.getPrice() >= Integer.parseInt(fromPrice.getText().toString())){
                            arr1.add(allDatum);
                        }
                        allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr4));
                    }
                }
            }
        });

    }
}