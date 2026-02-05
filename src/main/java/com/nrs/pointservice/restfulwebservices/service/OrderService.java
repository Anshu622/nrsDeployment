package com.nrs.pointservice.restfulwebservices.service;

import java.util.List;

import com.nrs.pointservice.restfulwebservices.dto.OrderRequestDto;
import com.nrs.pointservice.restfulwebservices.model.Order;
import com.nrs.pointservice.restfulwebservices.model.OrderStatus;


public interface OrderService {
	
	void saveOrderFromKafka(OrderRequestDto dto);
	
	void updateOrderStatus(String orderId,OrderStatus status);
	
	Order getOrderByOrderId(String orderId);
	
	List<Order> getOrderByCustomerId(long customerId);

	Order deleteOrder(String orderId);

	List<Order> getAllOrder();
	
//	void processOrder(OrderRequestDto orderDTO);
}
