package com.doggo.dogadopt.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.ListAdapter;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.doggo.dogadopt.R;

import java.util.List;

public class TestActivity extends AppCompatActivity {


    Long userID;
    ListView lView;
    ListAdapter lAdapter;
    QueryProcessor processor = new QueryProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_final);
        Button add_dog_btn = findViewById(R.id.admin_addDog_button);
        ProgressDialog progress = new ProgressDialog(this);
        //progress.setTitle("Generating List");
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
        Intent intent = getIntent();
        userID = intent.getLongExtra("userID",0);
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                List<Dog> dogList = (List<Dog>) obj;
                lView = (ListView) findViewById(R.id.dogList);
                lAdapter = new ListAdapter(TestActivity.this, dogList.toArray(new Dog[0]),"USER",userID);
                lView.setAdapter(lAdapter);
                progress.dismiss();
            }
        });

        add_dog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), addActivity.class);
                startActivity(i);
            }
        });

    }





}
