package com.doggo.dogadopt;


import android.os.Bundle;
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

    }


        private void initializeComponents() {

            username = findViewById(R.id.usernameField);
            password = findViewById(R.id.passwordField);
            login = findViewById(R.id.login_button);
            register = findViewById(R.id.signup_button);


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<Account> lst = null;
                    requestProcessor.AccountReadAll(); // problematic line
                    //lst = requestProcessor.AccountReadAll();
                    Toast.makeText(startupActivity.this,"You have pressed the login button.", Toast.LENGTH_SHORT).show();

//                    for (Account account : lst){
//                        if (account.getUsername() == username.getText().toString()){
//                            Toast.makeText(startupActivity.this,"Tumama siya", Toast.LENGTH_SHORT).show();
//                        }
//                    }


                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(startupActivity.this,"You have pressed the Signup button.", Toast.LENGTH_SHORT).show();
                }
            });


        }
}