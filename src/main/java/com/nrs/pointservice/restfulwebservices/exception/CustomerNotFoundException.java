package com.nrs.pointservice.restfulwebservices.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
