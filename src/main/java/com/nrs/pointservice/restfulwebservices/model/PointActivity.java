package com.nrs.pointservice.restfulwebservices.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class PointActivity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private long customerId;
	
	private double point;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "action", length = 10)
	private PointAction action;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "reason", length = 20)
	private PointReason reason;
	
	private LocalDateTime date;

	public PointActivity() {
		super();
	}

	public PointActivity(Integer id, long customerId, double point, PointAction action, PointReason reason,
			LocalDateTime date) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.point = point;
		this.action = action;
		this.reason = reason;
		this.date = date;
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

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public PointAction getAction() {
		return action;
	}

	public void setAction(PointAction action) {
		this.action = action;
	}

	public PointReason getReason() {
		return reason;
	}

	public void setReason(PointReason reason) {
		this.reason = reason;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PointActivity [id=" + id + ", customerId=" + customerId + ", point=" + point + ", action=" + action
				+ ", reason=" + reason + ", date=" + date + "]";
	}
	
}
