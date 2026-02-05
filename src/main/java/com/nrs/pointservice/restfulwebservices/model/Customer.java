package com.nrs.pointservice.restfulwebservices.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
	
		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private long customerId;
	private String name;
	private long phone;
	private LocalDateTime dob;
}
