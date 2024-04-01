package com.nagarro.ecommerce.service;

import com.nagarro.ecommerce.dto.ProductRequest;
import com.nagarro.ecommerce.entity.Product;

import jakarta.validation.Valid;


public interface ProductService {
	  Product createProduct(@Valid ProductRequest productRequest);

	  Product getProductById(Long productId);
	  
}
