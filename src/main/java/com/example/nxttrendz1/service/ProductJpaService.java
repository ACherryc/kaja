/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxttrendz1.service;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.repository.ProductRepository;
import com.example.nxttrendz1.repository.ReviewJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    private ProductJpaRepository productJpaRepository;
    @Autowired
    private ReviewJpaRepository reviewJpaRepository;

    @Override
    public List<Product> getProducts() {
        return productJpaRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        try {
            return productJpaRepository.findById(productId).get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(Product product) {
        return productJpaRepository.save(product);

    }

    @Override
    public Product updateProduct(int productId, Product product) {
        try {
            Product newProduct = productJpaRepository.findById(productId).get();
            if (product.getProductName() != null) {
                newProduct.setProductName(product.getProductName());
            }

            if (product.getPrice() != 0) {
                newProduct.setPrice(product.getPrice());
            }
            return productJpaRepository.save(newProduct);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            List<Review> reviews = reviewJpaRepository.findByProduct(product);
            for (Review review : reviews) {
                review.setProduct(null);
            }
            reviewJpaRepository.saveAll(reviews);
            productJpaRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Review> getProductReviews(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            return reviewJpaRepository.findByProduct(product);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
