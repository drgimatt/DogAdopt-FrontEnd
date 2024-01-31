package com.doggo.dogadopt.retrofit;
import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.247.232:8080";


    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(customGson()))
                .build();

    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    private Gson customGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Create Custom Deserializer for dog class
        JsonDeserializer<Dog> dogJsonDeserializer = (json, typeOfT, context) -> {

            JsonObject jsonObject = json.getAsJsonObject();

            String myFormat = "yyyy-MM-dd"; // Choose the format you desire
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            try {
                return new Dog(
                        jsonObject.get("id").getAsLong(),
                        Base64.getDecoder().decode(jsonObject.get("photo").getAsString()),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("breed").getAsString(),
                        jsonObject.get("age").getAsInt(),
                        new java.sql.Date(sdf.parse(jsonObject.get("doa").getAsString()).getTime()),
                        jsonObject.get("personality").getAsString(),
                        jsonObject.get("status").getAsString(),
                        jsonObject.get("gender").getAsString()


                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        };

        gsonBuilder.registerTypeAdapter(Dog.class, dogJsonDeserializer);

        return gsonBuilder.create();
    }
}
