package com.nrs.pointservice.restfulwebservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nrs.pointservice.restfulwebservices.model.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderStatusDto {
	@JsonProperty("order_id")
	private String order_Id;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("status")
	@Column(name = "status", length = 20)
	private OrderStatus status;

	public OrderStatusDto() {
		super();
	}

	public OrderStatusDto(String order_Id, OrderStatus status) {
		super();
		this.order_Id = order_Id;
		this.status = status;
	}

	public String getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(String order_Id) {
		this.order_Id = order_Id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderStatusDto [order_Id=" + order_Id + ", status=" + status + "]";
	}
	
	

}
