package com.nrs.pointservice.restfulwebservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrs.pointservice.restfulwebservices.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{


	Optional<Order> findByOrderId(String orderId);

	List<Order> findByCustomerId(long customerId);
	
//	boolean existByOrderId(String orderId);

}
