package com.nrs.pointservice.restfulwebservices.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false ,unique=true)
	private String orderId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "orderType", length = 20)
	private OrderType orderType;
	
	private long customerId;
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("status")
	@Column(name = "status", length = 20)
	private OrderStatus status;
	
	private double pointsToBeAwarded;
	
	private double pointsAwarded;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<OrderItem> items;

	public Order() {
		super();
	}

	public Order(Integer id, String orderId, OrderType orderType, long customerId, double totalAmount, OrderStatus status,
			double pointsToBeAwarded, double pointsAwarded, LocalDateTime createdAt, LocalDateTime updatedAt,
			List<OrderItem> items) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderType = orderType;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.pointsToBeAwarded = pointsToBeAwarded;
		this.pointsAwarded = pointsAwarded;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public double getPointsToBeAwarded() {
		return pointsToBeAwarded;
	}

	public void setPointsToBeAwarded(double pointsToBeAwarded) {
		this.pointsToBeAwarded = pointsToBeAwarded;
	}

	public double getPointsAwarded() {
		return pointsAwarded;
	}

	public void setPointsAwarded(double pointsAwarded) {
		this.pointsAwarded = pointsAwarded;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", orderType=" + orderType + ", customerId=" + customerId
				+ ", totalAmount=" + totalAmount + ", status=" + status + ", pointsToBeAwarded=" + pointsToBeAwarded
				+ ", PointsAwarded=" + pointsAwarded + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", items=" + items + "]";
	}
	
	
}
