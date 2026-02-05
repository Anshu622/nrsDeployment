package com.nrs.pointservice.restfulwebservices.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nrs.pointservice.restfulwebservices.model.OrderStatus;
import com.nrs.pointservice.restfulwebservices.model.OrderType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderRequestDto {
	@JsonProperty("order_id")
	private String order_id;
	
	@JsonProperty("customer_id")
	private long customer_id;
	
	@JsonProperty("order_type")
	@Enumerated(EnumType.STRING)
	private OrderType order_type;
	
	@JsonProperty("total_amount")
	private double total_amount;
	
	@JsonProperty("status")
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20)
	private OrderStatus status;
	
	@JsonIgnore
    private LocalDateTime created_at;
	
	@JsonIgnore
	private LocalDateTime updated_at;
	
	private List<OrderItemDto> items;

	public OrderRequestDto() {
		super();
	}

	public OrderRequestDto(String order_id, long customer_id, OrderType order_type, double total_amount,
			OrderStatus status, LocalDateTime created_at, LocalDateTime updated_at, List<OrderItemDto> items) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.order_type = order_type;
		this.total_amount = total_amount;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.items = items;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public OrderType getOrder_type() {
		return order_type;
	}

	public void setOrder_type(OrderType order_type) {
		this.order_type = order_type;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderRequestDto [order_id=" + order_id + ", customer_id=" + customer_id + ", order_type=" + order_type
				+ ", total_amount=" + total_amount + ", status=" + status + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", items=" + items + "]";
	}
	
	
}
