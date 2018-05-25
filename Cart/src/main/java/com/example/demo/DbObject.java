package com.example.demo;

public class DbObject {

    private String productId;
    private int unitStock;

    public DbObject(String productId, int unitStock) {
        this.productId = productId;
        this.unitStock = unitStock;
    }

    public DbObject() {
    }

    public String getProductId() {
        return productId;
    }

    public int getUnitStock() {
        return unitStock;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUnitStock(int unitStock) {
        this.unitStock = unitStock;
    }
}
