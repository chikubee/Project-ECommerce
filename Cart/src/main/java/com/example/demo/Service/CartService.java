package com.example.demo.Service;

import com.example.demo.Dto.ProductDto;

import java.util.List;

public interface CartService {

    boolean addToCart(ProductDto productDto);
    boolean removeFromCart(ProductDto productDto);
    List<ProductDto> cartListItems(ProductDto productDto);
}
