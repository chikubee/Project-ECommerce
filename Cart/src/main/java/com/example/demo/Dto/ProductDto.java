package com.example.demo.Dto;

import java.util.Objects;

public class ProductDto {

    private String productId;
    private int unitStock;
    private String userId;

    public ProductDto(String productId, int unitStock, String userId) {
        this.productId = productId;
        this.unitStock = unitStock;
        this.userId = userId;
    }

    public ProductDto() {
    }

    public ProductDto(String userId) {
        this.userId = userId;
    }





    public String getProductId() {
        return productId;
    }

    public int getUnitStock() {
        return unitStock;
    }

    public String getUserId() {
        return userId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUnitStock(int unitStock) {
        this.unitStock = unitStock;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        ProductDto that = (ProductDto) o;
        return unitStock == that.unitStock &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, unitStock, userId);
    }
}
