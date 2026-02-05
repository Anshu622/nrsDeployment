package com.nrs.pointservice.restfulwebservices.exception;

public class OrderNotFoundException extends RuntimeException{
	public OrderNotFoundException(String message) {
		super(message);
	}
}
