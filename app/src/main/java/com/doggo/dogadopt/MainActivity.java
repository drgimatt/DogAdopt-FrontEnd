package com.doggo.dogadopt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Legacy;
import com.doggo.dogadopt.retrofit.AccountApi;
import com.doggo.dogadopt.retrofit.LegacyApi;
import com.doggo.dogadopt.retrofit.RetrofitService;
import com.escandor.dogadopt.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents(){
        EditText input_username = findViewById(R.id.input_username);
        EditText input_email = findViewById(R.id.input_email);
        Button btn_Add = findViewById(R.id.btn_Add);

        RetrofitService retrofitService = new RetrofitService();
        LegacyApi legacyApi = retrofitService.getRetrofit().create(LegacyApi.class);
        AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);

        btn_Add.setOnClickListener(view -> {
            String username = String.valueOf(input_username.getText());
            String email = String.valueOf(input_email.getText());

            Legacy legacy = new Legacy();
            legacy.setUsername(username);
            legacy.setEmail(email);

            Account account = new Account();
            account.setUsername(username);
            account.setPassword(email);
            account.setFirstName("");
            account.setLastName("");
            account.setRole("USER");
            account.setMyAddress("");




            accountApi.createAccount(account)
                    .enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
                        }
                    });



//            legacyApi.save(legacy)
//                    .enqueue(new Callback<Legacy>() {
//                        @Override
//                        public void onResponse(Call<Legacy> call, Response<Legacy> response) {
//                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Legacy> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
//                        }
//                    });

        });

    }



}