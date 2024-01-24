package com.doggo.dogadopt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doggo.dogadopt.model.User;
import com.doggo.dogadopt.retrofit.RetrofitService;
import com.doggo.dogadopt.retrofit.UserApi;
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
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        btn_Add.setOnClickListener(view -> {
            String username = String.valueOf(input_username.getText());
            String email = String.valueOf(input_email.getText());

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);

            userApi.save(user)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
                        }
                    });




        });






    }



}