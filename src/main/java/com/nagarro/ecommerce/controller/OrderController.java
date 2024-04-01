package com.nagarro.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ecommerce.dto.OrderRequest;
import com.nagarro.ecommerce.dto.OrderStatusUpdateRequest;
import com.nagarro.ecommerce.entity.Order;
import com.nagarro.ecommerce.exception.OrderNotFoundException;
import com.nagarro.ecommerce.service.OrderService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	 
	 
	@Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        try {
        	logger.info("Received request to create order: {}", orderRequest);
            String orderId = orderService.saveOrder(orderRequest);
            logger.info("Order created successfully. Order ID: {}", orderId);
            return new ResponseEntity<>("Order created successfully. Order ID: " + orderId, HttpStatus.CREATED);
        } catch (Exception e) {
        	logger.error("Error creating order", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        try {
        	logger.info("Fetching order by ID: {}", orderId);
            Order order = orderService.getOrderById(orderId);
            logger.info("Order fetched successfully.");
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
        	logger.error("Order not found", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
        	logger.error("Error getting order by ID", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateOrderStatus(@Valid @RequestBody OrderStatusUpdateRequest orderStatusUpdateRequest) {

        try {
        	logger.info("Received request to update order status: {}", orderStatusUpdateRequest);
            String awbNumber = orderService.updateOrderStatus(orderStatusUpdateRequest);
            
            if (awbNumber != null && !awbNumber.isEmpty()) {
                logger.info("Order status updated successfully. AWB Number: {}", awbNumber);
                return new ResponseEntity<>("Order status updated successfully. AWB Number: " + awbNumber, HttpStatus.OK);
            } else {
                logger.info("Order status updated successfully.");
                return new ResponseEntity<>("Order status updated successfully.", HttpStatus.OK);
            }
        } catch (OrderNotFoundException e) {
        	logger.error("Order not found: {}", orderStatusUpdateRequest.getOrderId());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
        	logger.error("Invalid argument: {}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	logger.error("Error while updating order status: {}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
