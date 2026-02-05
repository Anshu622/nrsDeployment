package com.nrs.pointservice.restfulwebservices.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrs.pointservice.restfulwebservices.dto.OrderRequestDto;
import com.nrs.pointservice.restfulwebservices.model.Order;
import com.nrs.pointservice.restfulwebservices.model.OrderStatus;
import com.nrs.pointservice.restfulwebservices.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
//	@PostMapping("/orders")
//	 public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto dto){
//		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreatedOrder(dto));
//		 
//	 }
	
//	@PutMapping("/{id}/status")
//	public ResponseEntity<Order> updateStatus(@PathVariable String id,@RequestParam OrderStatus status){
//		return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
//	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrder(){
		return ResponseEntity.ok(orderService.getAllOrder());
	}
	
	@GetMapping("/orders/order/{orderId}")
	public ResponseEntity<Order> getOrderByOrderId(@PathVariable String orderId){
		Order order=orderService.getOrderByOrderId(orderId);
		if (order == null) {
            return ResponseEntity.notFound().build();
        }

		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/orders/customer/{customerId}")
	public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable long customerId){
		return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
	}
	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable String orderId){
		return ResponseEntity.ok(orderService.deleteOrder(orderId));
	}
	
}
