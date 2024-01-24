package com.doggo.dogadopt.retrofit;

import com.doggo.dogadopt.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @GET("/")
    Call <List<User>> getAllUsers();

    @POST("/add-user")
    Call<User> save (@Body User user);







}
