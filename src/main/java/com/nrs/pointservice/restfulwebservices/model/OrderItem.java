package com.nrs.pointservice.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String productId;
	
	private int quantity;
	
	private double price;
	
	private double total;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	@JsonBackReference
	private Order order;

	public OrderItem() {
		super();
	}

	public OrderItem(Integer id, String productId, int quantity, double price, double total, Order order) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", price=" + price
				+ ", total=" + total + ", order=" + order + "]";
	}
	
	

}
