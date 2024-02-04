package com.doggo.dogadopt.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.escandor.dogadopt.R;

import java.util.List;
import java.util.prefs.Preferences;

public class startupActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button register;
    CheckBox persistentCredentials;
    QueryProcessor queryProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_startup);
        initializeComponents(sharedPref);

    }


        private void initializeComponents(SharedPreferences sharedPref) {


            username = findViewById(R.id.usernameField);
            password = findViewById(R.id.passwordField);
            login = findViewById(R.id.login_button);
            register = findViewById(R.id.signup_button);
            persistentCredentials = findViewById(R.id.persistent_signin);
            persistentCredentials.setChecked(sharedPref.getBoolean("persistentLogin",true));
            username.setText(sharedPref.getString("username",""));
            password.setText(sharedPref.getString("password",""));



            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                        Toast.makeText(startupActivity.this,"Please enter credentials", Toast.LENGTH_SHORT).show();
                    } else {
                        checkAccount(username.getText().toString(),password.getText().toString(),sharedPref);
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

        private void checkAccount(String uname, String pssword, SharedPreferences sharedPref){

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
                            SharedPreferences.Editor editor = sharedPref.edit();
                            if(persistentCredentials.isChecked()){
                            editor.putString("username",username.getText().toString());
                            editor.putString("password",password.getText().toString());
                            editor.putBoolean("persistentLogin",true);
                            editor.commit();
                            }
                            else{
                                editor.remove("username");
                                editor.remove("password");
                                editor.putBoolean("persistentLogin",false);
                                editor.commit();
                            }
                            if(user.getRole().equals("ADMIN")){
                                Toast.makeText(startupActivity.this,"Login successful." + " Account Type: " + acc.getRole(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                                i.putExtra("userID",acc.getMyId());
                                startActivity(i);
                                finish();

                            } else if (user.getRole().equals("USER")){
                                Toast.makeText(startupActivity.this,"Login successful." + " Account Type: " + acc.getRole(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), addActivity.class);
                                i.putExtra("userID",acc.getMyId());
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(startupActivity.this,"Unsupported Account Type! Please use different account.", Toast.LENGTH_SHORT).show();
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