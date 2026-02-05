package com.nrs.pointservice.restfulwebservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrs.pointservice.restfulwebservices.model.Customer;
import com.nrs.pointservice.restfulwebservices.model.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer>{

	Optional<Point> findByCustomerId(long customerId);

	

}
