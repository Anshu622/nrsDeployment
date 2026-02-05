package com.nrs.pointservice.restfulwebservices.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
		@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private long customerId;
	private String name;
	private long phone;
	private LocalDateTime dob;
	
	public Customer() {
		super();
	}

	public Customer(Integer id, long customerId, String name, long phone, LocalDateTime dob) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.name = name;
		this.phone = phone;
		this.dob = dob;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerId=" + customerId + ", name=" + name + ", phone=" + phone + ", dob="
				+ dob + "]";
	}

}
