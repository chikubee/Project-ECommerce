package com.example.achint.ecommerce.Controller;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController extends Application{

    private static Retrofit retrofitForOrder = null;
    private static Retrofit retrofitForProducts = null;
    public static MainController mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MainController getInstance() {
        return mInstance;
    }

    public Retrofit getClientForOrder(String url) {
        if (null == retrofitForOrder) {
            OkHttpClient client = new OkHttpClient.Builder().build();

            retrofitForOrder = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitForOrder;
    }

    public Retrofit getClientForProducts(String url) {
        if (null == retrofitForProducts) {
            OkHttpClient client = new OkHttpClient.Builder().build();

            retrofitForProducts = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitForProducts;
    }
}
