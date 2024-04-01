package com.nagarro.ecommerce.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderProductRequest {
	    @NotNull(message = "Product ID cannot be null")
	    private Long productId;
	    @Positive(message = "Quantity must be a positive value")
	    private int quantity;


	    public OrderProductRequest() {
	        // Default constructor
	    }

		public OrderProductRequest(Long productId, int quantity) {
			
			this.productId = productId;
			this.quantity = quantity;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
	   
}
