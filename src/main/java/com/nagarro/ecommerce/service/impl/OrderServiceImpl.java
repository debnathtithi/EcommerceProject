package com.nagarro.ecommerce.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.nagarro.ecommerce.dto.OrderRequest;
import com.nagarro.ecommerce.dto.OrderStatusUpdateRequest;
import com.nagarro.ecommerce.entity.Order;
import com.nagarro.ecommerce.exception.OrderNotFoundException;
import com.nagarro.ecommerce.repository.OrderRepository;
import com.nagarro.ecommerce.service.OrderService;
import com.nagarro.ecommerce.service.factory.OrderFactory;
import com.nagarro.ecommerce.service.factory.OrderStatusUpdateFactory;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Validated
public class OrderServiceImpl implements OrderService{
	
	
	    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	    
	    
		@Autowired
	    private OrderRepository orderRepository;
		
		@Autowired
	    private OrderFactory orderFactory; 
		
		@Autowired
		private OrderStatusUpdateFactory orderStatusUpdateFactory;
		
		
		@Override
	    @Transactional
	    public String saveOrder(@Valid OrderRequest orderRequest) {
			logger.info("Received request to save order: {}", orderRequest);
			
			Order order = orderFactory.createOrder(orderRequest);
	       
	        orderRepository.save(order);
	        
	        logger.info("Order saved successfully. Order ID: {}", order.getOrderId());
	        
	        return order.getOrderId();
        }
			

		@Override
		@Transactional
		public String updateOrderStatus(@Valid OrderStatusUpdateRequest orderStatusUpdateRequest) {
			logger.info("Initialising the process to update order status: {}", orderStatusUpdateRequest);
			
		    Order order = orderRepository.findByOrderId(orderStatusUpdateRequest.getOrderId())
		            .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderStatusUpdateRequest.getOrderId()));

		    
		    Order updatedOrder = orderStatusUpdateFactory.updateOrderStatus(order, orderStatusUpdateRequest.getStatus());
		    
            orderRepository.save(updatedOrder );
		    
            return updatedOrder.getAwbNumber();
		    
		}


	    @Override
	    public Order getOrderById(String orderId) {
	        return orderRepository.findByOrderId(orderId)
	                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
	    }
}

