package com.doggo.dogadopt.retrofit;

import com.doggo.dogadopt.model.Legacy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LegacyApi {
    @GET("/")
    Call <List<Legacy>> getAllUsers();

    @POST("/add-legacy")
    Call<Legacy> save (@Body Legacy legacy);








}
