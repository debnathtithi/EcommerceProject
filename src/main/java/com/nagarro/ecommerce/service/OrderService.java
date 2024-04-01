package com.nagarro.ecommerce.service;

import com.nagarro.ecommerce.dto.OrderRequest;
import com.nagarro.ecommerce.dto.OrderStatusUpdateRequest;
import com.nagarro.ecommerce.entity.Order;

import jakarta.validation.Valid;


public interface OrderService {
	String saveOrder(@Valid OrderRequest orderRequest);

    String updateOrderStatus(@Valid OrderStatusUpdateRequest orderStatusUpdateRequest);

    Order getOrderById(String orderId);
}
