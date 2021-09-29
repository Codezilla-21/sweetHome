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
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        BottomNavigationItemView bottom = findViewById(R.id.Account);

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profilePage.class);
                startActivity(intent);
            }
        });


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



        BottomNavigationItemView  logout = findViewById(R.id.logButton);
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
        List<sweetHouse> allData = new ArrayList<>();
        allDataFromAWS.setLayoutManager(new LinearLayoutManager(this));
        allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) allData));


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
                if (selected.equals("Selected House Type") && toPrice.getText().toString().equals("0") && fromPrice.getText().toString().equals("0")){
                    System.out.println("--------------------ARR1----------------------------------");
                    for (sweetHouse allDatum : allData) {
                        arr1.add(allDatum);
                        System.out.println(allDatum.getId());
                    }
                    System.out.println("******************************");
                    System.out.println(arr1);
                    allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) allData));
                }

                List arr5 = new ArrayList<>();
                if (!selected.equals("Selected House Type") && toPrice.getText().toString().equals("0") && fromPrice.getText().toString().equals("0")){
                    System.out.println("--------------------ARR5----------------------------------");
                    System.out.println(selected);
                        for (sweetHouse allDatum : allData) {
                            System.out.println(allDatum.getType());
                            if(selected.equals(allDatum.getType())){
                            arr5.add(allDatum);
                            System.out.println(allDatum.getId());}
                        }
                    System.out.println("******************************");
                    System.out.println(arr5);
                    allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr5));
                }else if (selected.equals(str1) && !toPrice.getText().toString().equals("0") && !fromPrice.getText().toString().equals("0")){
                    System.out.println(str1);
                    List<sweetHouse> arr2= new ArrayList<>();
                    System.out.println("-------------------------ARR2-----------------------------");
                    for (sweetHouse allDatum : allData) {
                        System.out.println(allDatum.getPrice());
                        if (allDatum.getPrice() <= Integer.parseInt(toPrice.getText().toString()) && allDatum.getPrice() >= Integer.parseInt(fromPrice.getText().toString())){
                            arr2.add(allDatum);
                            System.out.println(allDatum.getId());
                        }}
                    System.out.println("******************************");
                    System.out.println(arr2);
                    allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr2));

                }else if (selected.equals(str2) && !toPrice.getText().toString().equals("0") && !fromPrice.getText().toString().equals("0")){
                    System.out.println(str2);
                    List<sweetHouse> arr3 = new ArrayList<>();
                    System.out.println("--------------------ARR3----------------------------------");
                    for (sweetHouse allDatum : allData) {
                        if (allDatum.getPrice() <= Integer.parseInt(toPrice.getText().toString()) && allDatum.getPrice() >= Integer.parseInt(fromPrice.getText().toString())){
                            arr3.add(allDatum);
                            System.out.println(allDatum.getId());
                        }
                    }
                    System.out.println("******************************");
                    System.out.println(arr3);
                    allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr3));
                }else if (selected.equals(str3) && !toPrice.getText().toString().equals("0") && !fromPrice.getText().toString().equals("0") ){
                    System.out.println(str3);
                    List<sweetHouse> arr4 = new ArrayList<>();
                    System.out.println("------------------------ARR4------------------------------");
                    System.out.println("Price: "+toPrice.getText().toString());
                    for (sweetHouse allDatum : allData) {
                        if (allDatum.getPrice() <= Integer.parseInt(toPrice.getText().toString()) && allDatum.getPrice() >= Integer.parseInt(fromPrice.getText().toString())){
                            arr4.add(allDatum);
                            System.out.println(allDatum.getId());
                        }
                    }
                    System.out.println("******************************");
                    System.out.println(arr4);
                    allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) arr4));
                }

            }
        });

    }
}