package com.doggo.dogadopt.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.escandor.dogadopt.R;

import java.util.List;

public class startupActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button register;
    QueryProcessor queryProcessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        initializeComponents();

    }


        private void initializeComponents() {

            username = findViewById(R.id.usernameField);
            password = findViewById(R.id.passwordField);
            login = findViewById(R.id.login_button);
            register = findViewById(R.id.signup_button);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                        Toast.makeText(startupActivity.this,"Please enter credentials", Toast.LENGTH_SHORT).show();
                    } else {
                        checkAccount(username.getText().toString(),password.getText().toString());
                    }
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(startupActivity.this,"You have pressed the Signup button.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), updateActivity.class);
                    startActivity(i);
                }
            });


        }

        private void checkAccount(String uname, String pssword){

            queryProcessor = new QueryProcessor();
            queryProcessor.AccountReadAll();

            if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(startupActivity.this,"Please enter credentials", Toast.LENGTH_SHORT).show();
            } else {
            queryProcessor.setCbs(new CallBack() {
                @Override
                public void returnResult(Object obj) {
                    List<Account> accList = (List<Account>) obj;

                    for (Account acc: accList){
                        if((acc.getUsername().equals(uname)) && (acc.getPassword().equals(pssword))){
                            Account user = acc;
                            if(user.getRole().equals("ADMIN")){
                                Toast.makeText(startupActivity.this,"Login successful." + " Account Type: " + acc.getRole(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                                i.putExtra("userID",acc.getMyId());
                                startActivity(i);
                                finish();
                                break;
                            } else if (user.getRole().equals("USER")){
                                Toast.makeText(startupActivity.this,"Login successful." + " Account Type: " + acc.getRole(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), addActivity.class);
                                i.putExtra("userID",acc.getMyId());
                                startActivity(i);
                                finish();
                                break;
                            } else {
                                Toast.makeText(startupActivity.this,"Unsupported Account Type! Please use different account.", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        } else {
                            Toast.makeText(startupActivity.this,"Incorrect credentials. Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

            }

        }




}