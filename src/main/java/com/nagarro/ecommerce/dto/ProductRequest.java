package com.nagarro.ecommerce.dto;

import java.math.BigDecimal;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {
	@NotBlank(message = "Product name is required")
	private String name;
	@NotBlank(message = "Product description is required")
    private String description;
	@NotNull(message = "Price is required")
    private BigDecimal price;
    
    public ProductRequest() {
		
	}
    
	public ProductRequest(String name, String description, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
    
    
}
