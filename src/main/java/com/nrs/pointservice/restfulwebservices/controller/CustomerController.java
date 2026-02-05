package com.nrs.pointservice.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrs.pointservice.restfulwebservices.model.Customer;
import com.nrs.pointservice.restfulwebservices.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
		Customer cus=service.CreateCustomer(customer);
		return new ResponseEntity<>(cus,HttpStatus.OK);
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> list=service.getAllCustomer();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable long id){
		
		Customer cus=service.getCustomerByCustomerId(id);
		return ResponseEntity.ok(cus);
		
	}

}
