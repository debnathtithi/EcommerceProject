package com.nagarro.ecommerce.service.factory;

import org.springframework.stereotype.Component;

import com.nagarro.ecommerce.dto.OrderRequest;
import com.nagarro.ecommerce.entity.Order;

@Component
public interface OrderFactory {
	  Order createOrder(OrderRequest orderRequest);
}
