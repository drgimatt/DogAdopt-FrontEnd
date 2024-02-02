package com.doggo.dogadopt.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.ListAdapter;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.RequestProcessor;
import com.escandor.dogadopt.R;

import java.util.List;

public class TestActivity extends AppCompatActivity {


    ListView lView;
    ListAdapter lAdapter;
    RequestProcessor processor = new RequestProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_temp);
        ProgressDialog progress = new ProgressDialog(this);
        //progress.setTitle("Generating List");
        progress.setMessage("Please wait...");
        progress.setCancelable(false);
        progress.show();
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                List<Dog> dogList = (List<Dog>) obj;
                lView = (ListView) findViewById(R.id.dogList);
                lAdapter = new ListAdapter(TestActivity.this, dogList.toArray(new Dog[0]),"ADMIN");
                lView.setAdapter(lAdapter);
                progress.dismiss();
            }
        });



    }

    private void initializeComponents(){



    }




}
