package com.example.achint.ecommerce.Interface;

import com.example.achint.ecommerce.Model.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsersInterface {

    @POST("login")
    Call<Users> validateUser(@Body Users users);

    @POST("register")
    Call<Boolean> createUser(@Body Users users);

}
