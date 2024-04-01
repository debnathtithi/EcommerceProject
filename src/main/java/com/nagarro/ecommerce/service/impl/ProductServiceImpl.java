package com.nagarro.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.nagarro.ecommerce.dto.ProductRequest;
import com.nagarro.ecommerce.entity.Product;
import com.nagarro.ecommerce.exception.ProductNotFoundException;
import com.nagarro.ecommerce.repository.ProductRepository;
import com.nagarro.ecommerce.service.ProductService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class ProductServiceImpl implements ProductService{
	
	
	   @Autowired
	    private ProductRepository productRepository;

	    @Override
	    @Transactional
	    public Product createProduct(@Valid ProductRequest productRequest) {
	    
	    	 Product product = new Product.Builder()
	                 .name(productRequest.getName())
	                 .description(productRequest.getDescription())
	                 .price(productRequest.getPrice())
	                 .build();
	        return productRepository.save(product);
	    }

		@Override
		public Product getProductById(Long productId) {
			return productRepository.findByProductId(productId)
	                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
		}

}
