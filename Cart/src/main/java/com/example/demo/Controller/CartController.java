package com.example.demo.Controller;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add-to-cart")
    public boolean addToCart(@RequestParam String productId, @RequestParam int unitStock, @RequestParam String userId)
    {
        boolean check = cartService.addToCart(new ProductDto(productId, unitStock, userId));

        System.out.println(check);

        return check;
    }

    @RequestMapping(value = "/remove-from-cart")
    public boolean removeFromCart(@RequestParam String productId, @RequestParam int unitStock, @RequestParam String userId)
    {
        boolean check = cartService.removeFromCart(new ProductDto(productId, unitStock, userId));

        return check;
    }

    @RequestMapping(value = "/cart-list-items")
    public List<ProductDto> cartListItems(@RequestParam String userId)
    {
        List<ProductDto> productDtoList = cartService.cartListItems(new ProductDto(userId));

        return productDtoList;
    }

}