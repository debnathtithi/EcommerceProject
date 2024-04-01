package com.nagarro.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderId(String orderId);
}
