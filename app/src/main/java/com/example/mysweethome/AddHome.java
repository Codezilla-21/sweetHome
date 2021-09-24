package com.example.mysweethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddHome extends AppCompatActivity {

    String selected;

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
                System.out.println("---------------+"+ selected);
                //  Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        System.out.println("---------------+"+ selected);
    }
}