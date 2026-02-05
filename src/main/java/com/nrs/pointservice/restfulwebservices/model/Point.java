package com.nrs.pointservice.restfulwebservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	
	private long customerId;
	
	private double totalPoint;

	public Point() {
		super();
	}

	public Point(Integer id, long customerId, double totalPoint) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.totalPoint = totalPoint;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getCustomer() {
		return customerId;
	}

	public void setCustomer(long customerId) {
		this.customerId = customerId;
	}

	public double getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(double totalPoint) {
		this.totalPoint = totalPoint;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", customerId=" + customerId + ", totalPoint=" + totalPoint + "]";
	}

	
}
