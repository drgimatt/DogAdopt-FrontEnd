package com.doggo.dogadopt.retrofit;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.1.19:8080";


    public RetrofitService(){
        initializeRetrofit();
    }

    private void initializeRetrofit(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

    }

    public Retrofit getRetrofit(){
        return retrofit;
    }




}
