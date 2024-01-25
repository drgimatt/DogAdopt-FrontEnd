package com.doggo.dogadopt.retrofit;

import android.util.Log;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;

import java.io.ByteArrayOutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestProcessor {

    Account accountData = new Account();
    RetrofitService retrofitService = new RetrofitService();
    AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);
    DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);

    public void DogAdd(byte[] photoBytes, String name, String breed, String age, String doa, String personality, String status, String gender){
        RequestBody imageBody = RequestBody.create(MediaType.parse("image*/"),photoBytes);
        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"),name);
        RequestBody breedBody = RequestBody.create(MediaType.parse("text/plain"),breed);
        RequestBody ageBody = RequestBody.create(MediaType.parse("text/plain"),age);
        RequestBody doaBody = RequestBody.create(MediaType.parse("text/plain"),doa);
        RequestBody personalityBody = RequestBody.create(MediaType.parse("text/plain"),personality);
        RequestBody statusBody = RequestBody.create(MediaType.parse("text/plain"),status);
        RequestBody genderBody = RequestBody.create(MediaType.parse("text/plain"),gender);

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("photo","file",imageBody);
        dogApi.addDogSubmit(imagePart,nameBody,breedBody,ageBody,doaBody,personalityBody,statusBody,genderBody).enqueue(new Callback<Dog>() {
                @Override
                public void onResponse(Call<Dog> call, Response<Dog> response) {
                }

                @Override
                public void onFailure(Call<Dog> call, Throwable t) {
                }
            });
    }

    public void DogUpdate(int ID, byte[] photoBytes, String name, String breed, String age, String doa, String personality, String status, String gender){
        RequestBody imageBody = RequestBody.create(MediaType.parse("image*/"),photoBytes);
        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"),name);
        RequestBody breedBody = RequestBody.create(MediaType.parse("text/plain"),breed);
        RequestBody ageBody = RequestBody.create(MediaType.parse("text/plain"),age);
        RequestBody doaBody = RequestBody.create(MediaType.parse("text/plain"),doa);
        RequestBody personalityBody = RequestBody.create(MediaType.parse("text/plain"),personality);
        RequestBody statusBody = RequestBody.create(MediaType.parse("text/plain"),status);
        RequestBody genderBody = RequestBody.create(MediaType.parse("text/plain"),gender);

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("photo","file",imageBody);
        dogApi.updateDog((long) ID,imagePart,nameBody,breedBody,ageBody,doaBody,personalityBody,statusBody,genderBody).enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
            }
        });
    }

    public void AccountAdd(String firstName, String lastName, String address, String username, String password, String role){
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setMyAddress(address);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        accountApi.createAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
            }
        });
    }

    public void AccountUpdate(int ID, String firstName, String lastName, String address, String username, String password, String role){
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setMyAddress(address);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        accountApi.updateAccount(ID, account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
            }
        });
    }

    public Account AccountRead(int ID){

        Call<Account> call = accountApi.getAccount(ID);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
//                accountData.setMyId(response.body().getMyId());
//                accountData.setRole(response.body().getRole());
//                accountData.setPassword(response.body().getPassword());
//                accountData.setUsername(response.body().getUsername().toString());
//                accountData.setMyAddress(response.body().getMyAddress());
//                accountData.setFirstName(response.body().getFirstName());
//                accountData.setLastName(response.body().getLastName());
                accountData = response.body();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });

        return accountData;
    }

}
