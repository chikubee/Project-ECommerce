package com.example.achint.ecommerce.Interface;

import com.example.achint.ecommerce.Model.ProductData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductInteface {
//    @POST("addOrUpdate")
//    Call<Object> addOrUpdate(@Body ProductData product);

    @GET("getAllProducts")
    Call<ProductData[]>  getAllProducts();
//
//    @GET("delete/{employeeId}")
//    Call<Boolean> deleteEmployee(@Path("employeeId") String employeeId);
}
