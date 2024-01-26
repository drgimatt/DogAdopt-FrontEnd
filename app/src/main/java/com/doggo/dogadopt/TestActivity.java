package com.doggo.dogadopt;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.retrofit.AccountApi;
import com.doggo.dogadopt.retrofit.RequestProcessor;
import com.doggo.dogadopt.retrofit.RetrofitService;
import com.escandor.dogadopt.R;

public class TestActivity extends AppCompatActivity {

    Account account = new Account();
    TextView dogName;
    TextView dogBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initializeComponents();

    }

    private void initializeComponents(){

        dogName = findViewById(R.id.DogNameDisplay);
        dogBreed = findViewById(R.id.DogBreedDisplay);

        RequestProcessor request = new RequestProcessor();
        Account acc = request.AccountRead(1);


        Log.i("TestActivity","May nakukuha siya: "+acc);

        dogName.setText(acc.getFirstName());
        dogBreed.setText(acc.getUsername());



    }




}
