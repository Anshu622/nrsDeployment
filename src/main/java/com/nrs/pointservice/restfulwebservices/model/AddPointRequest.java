package com.nrs.pointservice.restfulwebservices.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Component
public class AddPointRequest {
    private double point;
    @Enumerated(EnumType.STRING)
    @Column(name = "action", length = 20)
    private PointAction action;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "reason", length = 20)
    private PointReason reason;
	public AddPointRequest(double point, PointAction action, PointReason reason) {
		super();
		this.point = point;
		this.action = action;
		this.reason = reason;
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
	public AddPointRequest() {
		super();
	}
	@Override
	public String toString() {
		return "AddPointRequest [point=" + point + ", action=" + action + ", reason=" + reason + "]";
	}

    
}
