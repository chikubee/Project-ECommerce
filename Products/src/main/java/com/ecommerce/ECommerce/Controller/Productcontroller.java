package com.ecommerce.ECommerce.Controller;


import com.ecommerce.ECommerce.DTO.ProductDto;
import com.ecommerce.ECommerce.Model.Product;
import com.ecommerce.ECommerce.Service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class Productcontroller {

    @Autowired
    private ProductServiceInterface productService;

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public String createProduct(@RequestBody ProductDto product){
        return productService.createProduct(product);
    }

    @RequestMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> products = productService.getProductList();
        return products;
    }

    @RequestMapping("/getProductById/{productId}")
    public List<Product> getProductById(@PathVariable(value = "productId") String productId){
        List<Product> product = productService.getProductById(productId);
        return product;
    }

    @RequestMapping("/getMerchantRating")
    public int getMerchantRating(@PathVariable(value = "productId") String productId){
        return productService.getMerchantRating(productId);
    }

    @RequestMapping("/get-product-by-category")
    public List<Product> getProductsByCategory(@RequestParam String productCategory){
        return productService.getProductsByCategory(productCategory);
    }

    @RequestMapping("/getProductSortByPrice")
    public List<Product> getProductSortByPrice(){
        return productService.getProductsSortByPrice();
    }

    @RequestMapping("/getProductSortByRating")
    public List<Product> getProductSortByRating(){
        return productService.getProductSortByRating();
    }
}


