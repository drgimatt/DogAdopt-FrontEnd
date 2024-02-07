package com.doggo.dogadopt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.R;
import com.doggo.dogadopt.RequestListAdapter;
import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.model.Request;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;

import java.util.ArrayList;
import java.util.List;

public class allrequestActivity extends AppCompatActivity{

    ListView lView;
    RequestListAdapter rListAdapter;
    List<Request> requestList;
    List<Dog> dogList;
    Account account;
    QueryProcessor processor = new QueryProcessor();
    LoadingDialog progress = new LoadingDialog(allrequestActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");
        progress.startLoadingAnimation("Initializing data...");
        processor.RequestReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                requestList = (List<Request>) obj;
            }
        });
        processor = new QueryProcessor();
        processor.DogReadAll();
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                dogList = (List<Dog>) obj;
                lView = findViewById(R.id.requestList);

                List<Request> filteredRequests = new ArrayList<>();

                if(account.getRole().equals("USER")){
                    for (Request utos: requestList){
                        if (utos.getUserId().equals(account.getMyId())){
                            filteredRequests.add(utos);
                        }
                    }
                }
                else {
                    filteredRequests = requestList;
                }

                rListAdapter = new RequestListAdapter(allrequestActivity.this, filteredRequests.toArray(new Request[0]), dogList.toArray(new Dog[0]), account);
                lView.setAdapter(rListAdapter);
                progress.dismissAnimation();
            }
        });
    }


}
