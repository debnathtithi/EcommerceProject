package com.nagarro.ecommerce.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long productId ;
         
	     
	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false)
	    private String description;

	    @Column(nullable = false)
	    private BigDecimal price;
	    
	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonBackReference 
	    private List<OrderProduct> orderProducts;
	    
	    private Product() {
	        
	    }
	    
	    public Long getProductId() {
			return productId;
		}

		public String getName() {
			return name;
		}

		public List<OrderProduct> getOrderProducts() {
			return orderProducts;
		}
        
		public void setOrderProducts(List<OrderProduct> orderProducts) {
	        this.orderProducts = orderProducts;
	    }
		
		public String getDescription() {
			return description;
		}

	
		public BigDecimal getPrice() {
			return price;
		}


		public static class Builder {
		    private Product product = new Product();

		    public Builder() {
		    }

		    public Builder name(String name) {
		        product.name = name;
		        return this;
		    }

		    public Builder description(String description) {
		        product.description = description;
		        return this;
		    }

		    public Builder price(BigDecimal price) {
		        product.price = price;
		        return this;
		    }

		    public Product build() {
		        return product;
		    }
		}

}
