package com.doggo.dogadopt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.retrofit.RequestProcessor;
import com.escandor.dogadopt.R;

public class TestActivity extends AppCompatActivity {


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

        Account account = request.AccountRead(92);

        dogName.setText(account.getFirstName());
        dogBreed.setText(account.getUsername());



    }




}
