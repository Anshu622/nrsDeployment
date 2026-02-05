package com.nrs.pointservice.restfulwebservices.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nrs.pointservice.restfulwebservices.dto.OrderItemDto;
import com.nrs.pointservice.restfulwebservices.dto.OrderRequestDto;
import com.nrs.pointservice.restfulwebservices.exception.OrderNotFoundException;
import com.nrs.pointservice.restfulwebservices.model.Order;
import com.nrs.pointservice.restfulwebservices.model.OrderItem;
import com.nrs.pointservice.restfulwebservices.model.OrderStatus;
import com.nrs.pointservice.restfulwebservices.model.PointAction;
import com.nrs.pointservice.restfulwebservices.model.PointReason;
import com.nrs.pointservice.restfulwebservices.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final PointService pointService;
	
	public OrderServiceImpl(OrderRepository orderRepository, PointService pointService) {
		super();
		this.orderRepository = orderRepository;
		this.pointService = pointService;
	}
	
	@Override
	@Transactional
	
    public void saveOrderFromKafka(OrderRequestDto dto) {

        Order order = new Order();
        order.setOrderId(dto.getOrder_id());
        order.setCustomerId(dto.getCustomer_id());
        order.setOrderType(dto.getOrder_type());
        order.setTotalAmount(dto.getTotal_amount());
        order.setStatus(dto.getStatus());
        double points=dto.getTotal_amount()/10;
		
		order.setPointsToBeAwarded(points);
		order.setPointsAwarded(0);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        List<OrderItem> items = dto.getItems()
                .stream()
                .map(i -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(i.getProductId());
                    item.setQuantity(i.getQuantity());
                    item.setPrice(i.getPrice());
                    item.setTotal(i.getTotal());
                    item.setOrder(order);
                    return item;
                }).toList();

        order.setItems(items);

        orderRepository.save(order);
        
        
    }

//    private OrderItem mapToOrderItem(OrderItemDto dto, Order order) {
//        OrderItem item = new OrderItem();
//        item.setProductId(dto.getProductId());
//        item.setQuantity(dto.getQuantity());
//        item.setPrice(dto.getPrice());
//        item.setOrder(order);
//        return item;
//    }

//	@Override
//	public Order CreatedOrder(OrderRequestDto dto) {
//		// TODO Auto-generated method stub
//		
//		Order order=new Order();
//		order.setOrderId(dto.getOrder_id());
//		order.setCustomerId(dto.getCustomer_id());
//		order.setOrderType(dto.getOrder_type());
//		order.setStatus(dto.getStatus());
//		order.setTotalAmount(dto.getTotal_amount());
//		
//		double points=dto.getTotal_amount()/10;
//		
//		order.setPointsToBeAwarded(points);
//		order.setPointsAwarded(0);
//		order.setCreatedAt(dto.getCreated_at());
//		order.setUpdatedAt(dto.getUpdated_at());
//		
//		List<OrderItem> items=dto.getItems().stream().map(i -> {
//			OrderItem orderItem=new OrderItem();
//			orderItem.setProductId(i.getProductId());
//			orderItem.setQuantity(i.getQuantity());
//			orderItem.setPrice(i.getPrice());
//			orderItem.setTotal(i.getTotal());
//			orderItem.setOrder(order);
//			return orderItem;
//			
//		}).toList();
//		
//		order.setItems(items);
//		
//		return orderRepository.save(order);
//	}

	@Override
	public void updateOrderStatus(String orderId, OrderStatus status) {


		Order order=orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found :"+orderId));
		OrderStatus oldStatus = order.getStatus();

	    order.setStatus(status);
	    order.setUpdatedAt(LocalDateTime.now());

	   
	    if (oldStatus != OrderStatus.DELIVERED
	            && status == OrderStatus.DELIVERED
	            && order.getPointsAwarded() == 0) {

	        pointService.addPoint(
	                order.getCustomerId(),
	                order.getPointsToBeAwarded(),
	                PointAction.ADD,
	                PointReason.DELIVERED
	        );

	        order.setPointsAwarded(order.getPointsToBeAwarded());
	    }

	  
	    else if (oldStatus == OrderStatus.DELIVERED
	            && status == OrderStatus.RETURN
	            && order.getPointsAwarded() > 0) {

	        pointService.addPoint(
	                order.getCustomerId(),
	                order.getPointsAwarded(),
	                PointAction.DELETE,
	                PointReason.RETURN
	        );

	        order.setPointsAwarded(0);
	    }

		
		if (status == OrderStatus.CANCELLED
	            && oldStatus == OrderStatus.DELIVERED
	            && order.getPointsAwarded() > 0) {

	        pointService.addPoint(
	                order.getCustomerId(),
	                order.getPointsAwarded(),
	                PointAction.DELETE,
	                PointReason.CANCELLED
	        );

	        order.setPointsAwarded(0);
	    }
		
		
		
		
		
		

	    orderRepository.save(order);
		
		
	}
	@Override
	public List<Order> getAllOrder(){
		
		return orderRepository.findAll();	
	}

	@Override
	public Order getOrderByOrderId(String orderId) {
		// TODO Auto-generated method stu
		return orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found :"+orderId));
	
	}

	@Override
	public List<Order> getOrderByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		
		List<Order> orders=orderRepository.findByCustomerId(customerId);
		
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("Order not found for this customer");
		}
		return orders;
	
	}


	@Override
	public Order deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		
		Order orders=getOrderByOrderId(orderId);
		orderRepository.delete(orders);
		return orders;
	}

	

}
