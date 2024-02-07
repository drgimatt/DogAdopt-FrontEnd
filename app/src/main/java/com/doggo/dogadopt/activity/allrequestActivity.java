package com.doggo.dogadopt.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.DogListAdapter;
import com.doggo.dogadopt.R;
import com.doggo.dogadopt.RequestListAdapter;
import com.doggo.dogadopt.model.Request;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;

import java.util.List;

public class allrequestActivity extends AppCompatActivity{

    ListView lView;
    RequestListAdapter rListAdapter;
    List<Request> requestList;
    QueryProcessor processor = new QueryProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_admin);

        processor.RequestReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                requestList = (List<Request>) obj;
                lView = findViewById(R.id.requestList);
                rListAdapter = new RequestListAdapter(allrequestActivity.this, requestList.toArray(new Request[0]));
                lView.setAdapter(rListAdapter);
            }
        });
    }


}
