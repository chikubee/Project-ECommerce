package com.example.demo.Repository;


import com.example.demo.DbObject;
import com.example.demo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Product, String> {

}
