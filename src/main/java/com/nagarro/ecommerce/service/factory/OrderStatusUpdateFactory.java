package com.nagarro.ecommerce.service.factory;

import org.springframework.stereotype.Component;

import com.nagarro.ecommerce.entity.Order;
import com.nagarro.ecommerce.entity.OrderStatus;

@Component
public interface OrderStatusUpdateFactory {
	 Order updateOrderStatus(Order order, OrderStatus newStatus);
}
