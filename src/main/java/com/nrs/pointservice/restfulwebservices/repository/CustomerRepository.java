package com.nrs.pointservice.restfulwebservices.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nrs.pointservice.restfulwebservices.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	

	boolean existsByCustomerId(long customerId);

	Optional<Customer> findByCustomerId(long customerId);

	

}
 