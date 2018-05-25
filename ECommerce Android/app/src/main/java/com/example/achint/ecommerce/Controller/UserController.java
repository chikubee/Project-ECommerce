package com.example.achint.ecommerce.Controller;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserController extends Application{
    private static Retrofit retrofit = null;
    public static UserController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static UserController getInstance() {
        return mInstance;
    }

    public Retrofit getClient() {
        if (null == retrofit) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS).build();
            // Set the base url

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.2.78:8080/easybuy/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
