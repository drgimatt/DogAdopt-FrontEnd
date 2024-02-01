package com.doggo.dogadopt.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.AdminListAdapter;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.RequestProcessor;
import com.escandor.dogadopt.R;

import java.util.List;

public class TestActivity extends AppCompatActivity {


    ListView lView;
    AdminListAdapter lAdapter;
    RequestProcessor processor = new RequestProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_temp);
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                List<Dog> dogList = (List<Dog>) obj;
                lView = (ListView) findViewById(R.id.dogList);
                lAdapter = new AdminListAdapter(TestActivity.this, dogList.toArray(new Dog[0]));
                lView.setAdapter(lAdapter);
            }
        });



    }

    private void initializeComponents(){



    }




}
