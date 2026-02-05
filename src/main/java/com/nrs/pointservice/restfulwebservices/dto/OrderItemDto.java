package com.nrs.pointservice.restfulwebservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemDto {
	@JsonProperty("product_id")
	private String productId;
	
	private int quantity;
	private double price;
	private double total;
	public OrderItemDto() {
		super();
	}
	public OrderItemDto(String productId, int quantity, double price, double total) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
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
	@Override
	public String toString() {
		return "OrderItemDto [productId=" + productId + ", quantity=" + quantity + ", price=" + price + ", total=" + total
				+ "]";
	}
	
	
}
