package com.nrs.pointservice.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrs.pointservice.restfulwebservices.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
