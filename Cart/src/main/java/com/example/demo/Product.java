package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Objects;

@Document(collection = Product.COLLECTION_NAME)
public class Product {

    public static final String COLLECTION_NAME = "CartDetails";

    @Id
    private String userId;
    private ArrayList<DbObject> productList;

    public Product(String userId, ArrayList<DbObject> productList) {
        this.userId = userId;
        this.productList = productList;
    }

    public Product() {
    }


    public String getUserId() {
        return userId;
    }

    public ArrayList<DbObject> getProductList() {
        return productList;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductList(ArrayList<DbObject> productList) {
        this.productList = productList;
    }


    @Override
    public String toString() {
        return "Product{" +
                "userId='" + userId + '\'' +
                ", productList=" + productList +
                '}';
    }
}


/*

@Document(collection = Product.COLLECTION_NAME)
public class Product {

    public static final String COLLECTION_NAME = "CartDetails";


//    private long Id;
    @Id
    private Long productId;
    private String userId;
    private int unitStock;



    public Product(String userId, Long productId, int unitStock) {
        this.userId = userId;
        this.productId = productId;
        this.unitStock = unitStock;
    }

    public Product(String userId) {
        this.userId = userId;
    }

    public Product() {
    }

    public String getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getUnitStock() {
        return unitStock;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUnitStock(int unitStock) {
        this.unitStock = unitStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return unitStock == product.unitStock &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(userId, product.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, userId, unitStock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                ", unitStock=" + unitStock +
                '}';
    }


    //######################## TO STRING METHOD
}


 */