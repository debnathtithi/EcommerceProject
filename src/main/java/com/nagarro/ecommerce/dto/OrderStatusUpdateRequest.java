package com.nagarro.ecommerce.dto;

import com.nagarro.ecommerce.entity.OrderStatus;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class OrderStatusUpdateRequest {
	@NotBlank(message = "Order ID is required")
	private String orderId;
	@NotNull(message = "Status is required")
	private OrderStatus status;

    public OrderStatusUpdateRequest() {
        // Default constructor
    }

    public OrderStatusUpdateRequest(String orderId,OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

    

}
