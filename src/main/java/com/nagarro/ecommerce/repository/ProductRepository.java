package com.nagarro.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	
	 Optional<Product> findByProductId(Long productId);
}
