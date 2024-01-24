package com.doggo.dogadopt.retrofit;

import com.doggo.dogadopt.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DogApi {

    @GET("/api/dogs")
    Call <List<Dog>> getDogs();

    @GET("/api/show-dog/{id}")
    Call <List<Dog>> getDog(@Path("id") int id);

    @POST("/api/add-dog")
    Call<Dog> addDogSubmit(@Body Dog dog);

    @PUT("/api/update-dog/{id}")
    Call<Dog> updateDog (@Path("id") int id, @Body Dog dog);

    @DELETE("/api/delete-dog/{id}")
    Call<Dog> deleteDog(@Path("id") int id);


}
