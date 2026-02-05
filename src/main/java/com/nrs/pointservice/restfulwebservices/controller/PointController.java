package com.nrs.pointservice.restfulwebservices.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrs.pointservice.restfulwebservices.model.AddPointRequest;
import com.nrs.pointservice.restfulwebservices.model.Point;
import com.nrs.pointservice.restfulwebservices.model.PointAction;
import com.nrs.pointservice.restfulwebservices.model.PointActivity;
import com.nrs.pointservice.restfulwebservices.model.PointReason;
import com.nrs.pointservice.restfulwebservices.service.PointService;

@RestController
@RequestMapping("/api/v1")
public class PointController {
	
	private final PointService service;

	public PointController(PointService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/points/{id}")
	public ResponseEntity<Point> getPointsByCustomerId(@PathVariable long id){
		
		
		return ResponseEntity.ok(service.getPointsByCustomerId(id));
		
	}
	
	@GetMapping("/points/history/{id}")
	public ResponseEntity<List<PointActivity>> getActivityHistory(@PathVariable long id){
		return ResponseEntity.ok(service.getPointHostory(id));
		
	}
	
	@PostMapping("/points/{id}")
	public ResponseEntity<Point> updatePoint(@PathVariable long id,@RequestBody AddPointRequest request) {

	    Point savedPoint = service.addPoint(
	            id,
	            request.getPoint(),
	            request.getAction(),
	            request.getReason()
	    );

	    return ResponseEntity.status(HttpStatus.CREATED).body(savedPoint);
		
	}

}
