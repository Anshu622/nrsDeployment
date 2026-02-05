package com.nrs.pointservice.restfulwebservices.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrs.pointservice.restfulwebservices.dto.OrderStatusDto;
import com.nrs.pointservice.restfulwebservices.service.OrderService;

@Component
public class OrderStatusConsumer {

	 private final OrderService orderService;
	 private final ObjectMapper objectMapper;
//    private final ObjectMapper objectMapper = new ObjectMapper();
	 
	 public OrderStatusConsumer(ObjectMapper objectMapper, OrderService orderService) {
	        this.objectMapper = objectMapper;
	        this.orderService = orderService;
	    }

    @KafkaListener(
        topics = "order-status",
        groupId = "orders-status-quickstart-consumer"
    )
    public void consume(String message) {
        try {
            OrderStatusDto dto =
                    objectMapper.readValue(message, OrderStatusDto.class);

            System.out.println("Consumed Order Status:");
            
            System.out.println("Order ID: " + dto.getOrder_Id());
            System.out.println("Status: " + dto.getStatus());
//            System.out.println(message);
            // Optional: update order status in DB
             orderService.updateOrderStatus(dto.getOrder_Id(),dto.getStatus() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
