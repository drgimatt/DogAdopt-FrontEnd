package com.doggo.dogadopt;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.retrofit.RequestProcessor;
import com.escandor.dogadopt.R;

import java.util.List;

public class startupActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button register;
    RequestProcessor requestProcessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        initializeComponents();
        Button buttonOpenActivity = findViewById(R.id.login_button);

//        buttonOpenActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // new XML layout
//                setContentView(R.layout.activity_dash_admin);
//            }
//        });

    }


        private void initializeComponents() {

            username = findViewById(R.id.usernameField);
            password = findViewById(R.id.passwordField);
            login = findViewById(R.id.login_button);
            register = findViewById(R.id.signup_button);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    requestProcessor = new RequestProcessor();
//
//                    requestProcessor.AccountRead(1);
//                    requestProcessor.setCbs(new CallBack() {
//                        @Override
//                        public void returnResult(Object obj) {
//                            Account lst = (Account) obj;
//                            System.out.println("lumabas ka");
//
//                        }
//                    });
                    checkAccount(username.getText().toString(),password.getText().toString());
                    //requestProcessor.AccountRead(1);



                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(startupActivity.this,"You have pressed the Signup button.", Toast.LENGTH_SHORT).show();
                }
            });


        }

        private void checkAccount(String uname, String pssword){

            requestProcessor = new RequestProcessor();
            requestProcessor.AccountReadAll();

            requestProcessor.setCbs(new CallBack() {
                @Override
                public void returnResult(Object obj) {
                    List<Account> accList = (List<Account>) obj;

                    for (Account acc: accList){
                        if((acc.getUsername().equals(uname)) && (acc.getPassword().equals(pssword))){
                            Toast.makeText(startupActivity.this,"You have pressed the login button." + " Account Type: " + acc.getRole(), Toast.LENGTH_SHORT).show();
                            setContentView(R.layout.activity_dash_admin);
                        }
                    }

                }
            });



        }




}