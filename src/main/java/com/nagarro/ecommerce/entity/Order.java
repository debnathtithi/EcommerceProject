package com.nagarro.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`order`")
public class Order {
		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
	
		    @Column(nullable = false, unique = true)
		    private String orderId;
	
		    @Column(nullable = false)
		    private String customerName;
	
		    @Column(nullable = false)
		    private String shippingAddress;
	
		    @Column(nullable = false)
		    private BigDecimal orderAmount;
	
		    @Column(nullable = false)
		    private LocalDateTime orderDate;
	
		    @Column
		    private String awbNumber;
		    
		    @Enumerated(EnumType.STRING)
		    private OrderStatus status;
		    
		    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
		    @JsonBackReference
		    private List<OrderProduct> orderProducts;
	
		    @Column(nullable = false)
		    private String paymentType;
	
	
		    private Order() {
		        
		    }
        
	    
	       public OrderStatus getStatus() {
				return status;
			}

			public List<OrderProduct> getOrderProducts() {
				return orderProducts;
			}


			public String getPaymentType() {
				return paymentType;
			}

			public Long getId() {
		        return id;
		    }

		    public String getOrderId() {
		        return orderId;
		    }
		    
		    public String getCustomerName() {
		        return customerName;
		    }

		    public String getShippingAddress() {
		        return shippingAddress;
		    }

		    public BigDecimal getOrderAmount() {
		        return orderAmount;
		    }
		    
		    public LocalDateTime getOrderDate() {
		        return orderDate;
		    }
		    
		    public String getAwbNumber() {
		        return awbNumber;
		    }
	    
	    
	    public static class Builder {
	        private Order order = new Order();
	        
	        public Builder() {
	        }

	       
	        public Builder(Order existingOrder) {
	            this.order = existingOrder;
	        }
	        
			public Builder orderId(String orderId) {
	            order.orderId = orderId;
	            return this;
	        }

	        public Builder customerName(String customerName) {
	            order.customerName = customerName;
	            return this;
	        }

	        public Builder shippingAddress(String shippingAddress) {
	            order.shippingAddress = shippingAddress;
	            return this;
	        }

	        public Builder orderAmount(BigDecimal orderAmount) {
	            order.orderAmount = orderAmount;
	            return this;
	        }

	        public Builder orderDate(LocalDateTime orderDate) {
	            order.orderDate = orderDate;
	            return this;
	        }

	        public Builder awbNumber(String awbNumber) {
	            order.awbNumber = awbNumber;
	            return this;
	        }

	        public Builder status(OrderStatus status) {
	            order.status = status;
	            return this;
	        }

            public Builder orderProducts(List<OrderProduct> orderProducts) {
	            order.orderProducts = orderProducts;
	            orderProducts.forEach(op -> op.setOrder(order));
	            return this;
	        }

	        public Builder paymentType(String paymentType) {
	            order.paymentType = paymentType;
	            return this;
	        }

	        public Order build() {
	            return order;
	        }
	    }
     

	 
}

