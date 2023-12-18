/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxttrendz1.controller;

import com.example.nxttrendz1.model.Product;

import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.service.ProductJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    public ProductJpaService productJpaService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productJpaService.getProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable("productId") int productId) {
        return productJpaService.getProductById(productId);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productJpaService.addProduct(product);

    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable("productId") int productId, @RequestBody Product product) {
        return productJpaService.updateProduct(productId, product);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteproduct(@PathVariable("productId") int productId) {
        productJpaService.deleteProduct(productId);
    }

    @GetMapping("/products/{productId}/reviews")
    public List<Review> getProductReviews(@PathVariable("productId") int productId) {
        return productJpaService.getProductReviews(productId);
    }

}
