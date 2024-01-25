package com.doggo.dogadopt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.model.Legacy;
import com.doggo.dogadopt.retrofit.AccountApi;
import com.doggo.dogadopt.retrofit.DogApi;
import com.doggo.dogadopt.retrofit.LegacyApi;
import com.doggo.dogadopt.retrofit.RetrofitService;
import com.escandor.dogadopt.R;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button SelectImage;
    ImageView imagePrev;

    int SELECT_PICTURE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();


    }

    void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    imagePrev.setImageURI(selectedImageUri);

                }

            }


        }
    }

        private void initializeComponents() {
            EditText input_username = findViewById(R.id.input_username);
            EditText input_email = findViewById(R.id.input_email);
            Button btn_Add = findViewById(R.id.btn_Add);

            RetrofitService retrofitService = new RetrofitService();
            LegacyApi legacyApi = retrofitService.getRetrofit().create(LegacyApi.class);
            AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);
            DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

            SelectImage = findViewById(R.id.uploadImage);
            imagePrev = findViewById(R.id.previewImage);

            SelectImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    imageChooser();
                }

            });


            btn_Add.setOnClickListener(view -> {
                String username = String.valueOf(input_username.getText());
                String email = String.valueOf(input_email.getText());

                Legacy legacy = new Legacy();
                legacy.setUsername(username);
                legacy.setEmail(email);

                Bitmap bitmap = ((BitmapDrawable) imagePrev.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();


                RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), imageInByte);

                Dog dog = new Dog(null, imageInByte, username, email, 0, null, null, null, null);


                // DogApi.addDogSubmit(dog);


// Create MultipartBody.Part for the photo


//            Account account = new Account();
//            account.setUsername(username);
//            account.setPassword(email);
//            account.setFirstName("");
//            account.setLastName("");
//            account.setRole("USER");
//            account.setMyAddress("");


//            accountApi.createAccount(account)
//                    .enqueue(new Callback<Account>() {
//                        @Override
//                        public void onResponse(Call<Account> call, Response<Account> response) {
//                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<Account> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
//                        }
//                    });


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