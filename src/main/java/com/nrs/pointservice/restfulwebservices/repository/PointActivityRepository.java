package com.nrs.pointservice.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrs.pointservice.restfulwebservices.model.Customer;
import com.nrs.pointservice.restfulwebservices.model.PointActivity;

@Repository
public interface PointActivityRepository extends JpaRepository<PointActivity, Integer>{

	List<PointActivity> findByCustomerId(long customerId);

}
