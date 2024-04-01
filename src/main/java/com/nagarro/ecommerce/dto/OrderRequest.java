package com.nagarro.ecommerce.dto;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class OrderRequest {
		 @NotEmpty(message = "Customer name cannot be empty")
		 private String customerName;
		 @NotEmpty(message = "Shipping address cannot be empty")
		 private String shippingAddress;
		 @NotEmpty(message = "Payment type cannot be empty")
		 private String paymentType;
		 @Valid
		 @NotEmpty(message = "Order products cannot be empty")
		 private List<OrderProductRequest> orderProducts;
		 
		 
		 public OrderRequest() {
			 		    
		 }
		 
		public OrderRequest(String customerName,String shippingAddress,String paymentType,List<OrderProductRequest> orderProducts) {
			this.customerName = customerName;
			this.shippingAddress = shippingAddress;
			this.paymentType = paymentType;
			this.orderProducts = orderProducts;
		}

		public String getCustomerName() {
			return customerName;
		}


		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}


		public String getShippingAddress() {
			return shippingAddress;
		}


		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}


		public String getPaymentType() {
			return paymentType;
		}


		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}


		public List<OrderProductRequest> getOrderProducts() {
			return orderProducts;
		}


		public void setOrderProducts(List<OrderProductRequest> orderProducts) {
			this.orderProducts = orderProducts;
		}
     
	
	 
}
