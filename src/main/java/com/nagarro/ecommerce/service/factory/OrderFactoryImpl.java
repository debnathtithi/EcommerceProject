package com.nagarro.ecommerce.service.factory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nagarro.ecommerce.dto.OrderRequest;
import com.nagarro.ecommerce.entity.Order;
import com.nagarro.ecommerce.entity.OrderProduct;
import com.nagarro.ecommerce.entity.OrderStatus;
import com.nagarro.ecommerce.entity.Product;
import com.nagarro.ecommerce.exception.ProductNotFoundException;
import com.nagarro.ecommerce.repository.ProductRepository;


@Component
public class OrderFactoryImpl implements OrderFactory{
	
	private final ProductRepository productRepository;

    public OrderFactoryImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        List<OrderProduct> orderProducts = orderRequest.getOrderProducts().stream()
                .map(opr -> {
                    Product product = productRepository.findByProductId(opr.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + opr.getProductId()));
                    return new OrderProduct(
                            null,
                            product,
                            opr.getQuantity()
                    );
                })
                .collect(Collectors.toList());

        return new Order.Builder()
                .orderId(generateUniqueOrderId())
                .customerName(orderRequest.getCustomerName())
                .shippingAddress(orderRequest.getShippingAddress())
                .paymentType(orderRequest.getPaymentType())
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .orderProducts(orderProducts)
                .orderAmount(calculateOrderAmount(orderProducts))
                .build();
    }

    private BigDecimal calculateOrderAmount(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(op -> op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String generateUniqueOrderId() {
        return "ORDER" + UUID.randomUUID().toString();
    }
}
