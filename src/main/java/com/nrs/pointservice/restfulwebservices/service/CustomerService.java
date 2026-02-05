package com.nrs.pointservice.restfulwebservices.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nrs.pointservice.restfulwebservices.model.Customer;


public interface CustomerService {
	
	Customer CreateCustomer(Customer customer);
	Customer getCustomerByCustomerId(long customerId);
	List<Customer> getAllCustomer();

}
