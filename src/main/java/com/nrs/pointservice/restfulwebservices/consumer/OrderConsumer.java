package com.nrs.pointservice.restfulwebservices.consumer;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrs.pointservice.restfulwebservices.dto.OrderRequestDto;
import com.nrs.pointservice.restfulwebservices.service.OrderService;

@Component
public class OrderConsumer {

    private final ObjectMapper objectMapper;
    private final OrderService orderService;

    public OrderConsumer(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.orderService = orderService;
    }

    @KafkaListener(topics = "orders", groupId = "orders-quickstart-consumer")
    public void consume(String message) {
        try {
            OrderRequestDto dto =
                    objectMapper.readValue(message, OrderRequestDto.class);

            orderService.saveOrderFromKafka(dto);

            System.out.println("Order saved from Kafka: " + dto.getOrder_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
