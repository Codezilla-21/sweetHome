package com.example.mysweethome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.sweetHouse;

import java.util.ArrayList;
import java.util.List;

public class SpinnerClass extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_class);

        spinner = findViewById(R.id.spinner);

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
        allDataFromAWS.setAdapter(new ViewAdapter((ArrayList<sweetHouse>) allData));


        Amplify.API.query(
                ModelQuery.list(sweetHouse.class),
                response -> {
                    System.out.println(response.toString());

                        for (sweetHouse house : response.getData().getItems()) {
                            allData.add(house);
                                System.out.println("based on id ----------------: "+house);
                            }

                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "Out of Loop!");

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }
}