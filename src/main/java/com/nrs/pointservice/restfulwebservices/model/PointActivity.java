package com.nrs.pointservice.restfulwebservices.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
}
