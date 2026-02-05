package com.nrs.pointservice.restfulwebservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrs.pointservice.restfulwebservices.exception.CustomerNotFoundException;
import com.nrs.pointservice.restfulwebservices.model.Customer;
import com.nrs.pointservice.restfulwebservices.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer CreateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(repository.existsByCustomerId(customer.getCustomerId())) {
			throw new RuntimeException("Customer with this id already exist");
		}
		return repository.save(customer);
	}

	@Override
	public Customer getCustomerByCustomerId(long customerId) {
		// TODO Auto-generated method stub
		return repository.findByCustomerId(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with this userID :"+ customerId));
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
