package com.nagarro.ecommerce.service.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.nagarro.ecommerce.entity.Order;
import com.nagarro.ecommerce.entity.OrderStatus;


@Component
public class OrderStatusUpdateFactoryImpl implements OrderStatusUpdateFactory {
	
	
	String dummyAwbNumber = null;

    @Override
    public Order updateOrderStatus(Order order, OrderStatus newStatus) {
        validateOrderStatusTransition(order.getStatus(), newStatus);

        if (order.getStatus() == OrderStatus.PICKED && newStatus == OrderStatus.PACKED) {
            dummyAwbNumber = generateDummyAwbNumber();
        }

        return new Order.Builder(order)
                .status(newStatus)
                .awbNumber(dummyAwbNumber)
                .build();
    }

    private void validateOrderStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
    	switch (currentStatus) {
        case PENDING:
            if (newStatus != OrderStatus.PICKED) {
                throw new IllegalStateException("Invalid transition from PENDING to " + newStatus);
            }
            break;
        case PICKED:
            if (newStatus != OrderStatus.PACKED) {
                throw new IllegalStateException("Invalid transition from PICKED to " + newStatus);
            }
            break;
        case PACKED:
            if (newStatus != OrderStatus.DELIVERED) {
                throw new IllegalStateException("Invalid transition from PACKED to " + newStatus);
            }
            break;
        default:
            throw new IllegalStateException("Invalid current order status: " + currentStatus);
    	}
    }

    private String generateDummyAwbNumber() {
        return "DUMMYAWB" + UUID.randomUUID().toString();
    }

}
