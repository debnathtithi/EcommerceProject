package com.nagarro.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ecommerce.dto.ProductRequest;
import com.nagarro.ecommerce.entity.Product;
import com.nagarro.ecommerce.exception.ProductNotFoundException;
import com.nagarro.ecommerce.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        try {
            Product createdProduct = productService.createProduct(productRequest);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
