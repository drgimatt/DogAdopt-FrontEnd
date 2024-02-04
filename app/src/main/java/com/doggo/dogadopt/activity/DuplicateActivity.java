package com.doggo.dogadopt.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.escandor.dogadopt.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DuplicateActivity extends AppCompatActivity {

    Button SelectImage;
    ImageView imagePrev;
    EditText input_username;
    EditText input_email;
    Button btn_Add;
    Button btn_chg;
    byte[] imageInByte;
    Bitmap bitmap;
    int SELECT_PICTURE = 200;

    QueryProcessor processor = new QueryProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        initializeComponents();

    }

    public void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }

    }

        private void initializeComponents() {


//            input_username = findViewById(R.id.input_username);
//            input_email = findViewById(R.id.input_email);
//            btn_Add = findViewById(R.id.btn_Add);
//            btn_chg = findViewById(R.id.changeButton);
//            SelectImage = findViewById(R.id.uploadImage);
//            imagePrev = findViewById(R.id.previewImage);

            SelectImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    imageChooser();
                }

            });


            btn_chg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DuplicateActivity.this, TestActivity.class));
                }
            });

            btn_Add.setOnClickListener(view -> {
                    uploadForm();
            });

        }

        private void uploadForm(){

//            RetrofitService retrofitService = new RetrofitService();
//            LegacyApi legacyApi = retrofitService.getRetrofit().create(LegacyApi.class);
//            AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);
//            DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

            String username = String.valueOf(input_username.getText());
            String email = String.valueOf(input_email.getText());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();

            processor.DogAdd(imageInByte,username,email,"5","2024-01-25","","","");

//            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"),username);
//            RequestBody breedBody = RequestBody.create(MediaType.parse("text/plain"),email);
//            RequestBody ageBody = RequestBody.create(MediaType.parse("text/plain"),"5");
//            RequestBody doaBody = RequestBody.create(MediaType.parse("text/plain"),"2024-10-25");
//            RequestBody personalityBody = RequestBody.create(MediaType.parse("text/plain"),"5");
//            RequestBody statusBody = RequestBody.create(MediaType.parse("text/plain"),"5");
//            RequestBody genderBody = RequestBody.create(MediaType.parse("text/plain"),"5");
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            imageInByte = baos.toByteArray();
//
//
//            RequestBody fileBody = RequestBody.create(MediaType.parse("image*/"),imageInByte);
//            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("photo","file",fileBody);
//
//            dogApi.addDogSubmit(imagePart,nameBody,breedBody,ageBody,doaBody,personalityBody,statusBody,genderBody).enqueue(new Callback<Dog>() {
//                @Override
//                public void onResponse(Call<Dog> call, Response<Dog> response) {
//                    Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<Dog> call, Throwable t) {
//                    Toast.makeText(MainActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
//                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error Occurred",t);
//                }
//            });

        }


}