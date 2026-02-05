package com.nrs.pointservice.restfulwebservices.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrs.pointservice.restfulwebservices.exception.CustomerNotFoundException;
import com.nrs.pointservice.restfulwebservices.model.Point;
import com.nrs.pointservice.restfulwebservices.model.PointAction;
import com.nrs.pointservice.restfulwebservices.model.PointActivity;
import com.nrs.pointservice.restfulwebservices.model.PointReason;
import com.nrs.pointservice.restfulwebservices.repository.CustomerRepository;
import com.nrs.pointservice.restfulwebservices.repository.PointActivityRepository;
import com.nrs.pointservice.restfulwebservices.repository.PointRepository;

import jakarta.transaction.Transactional;

@Service
public class PointServiceImpl implements PointService{
	
	
	private final PointRepository pointRepository;
	
	private final CustomerRepository customerRepository;
	
	
	private final PointActivityRepository pointActivityRepository;
	
	public PointServiceImpl(PointRepository pointRepository, CustomerRepository customerRepository,
			PointActivityRepository pointActivityRepository) {
		super();
		this.pointRepository = pointRepository;
		this.customerRepository = customerRepository;
		this.pointActivityRepository = pointActivityRepository;
	}

	@Override
	public Point getPointsByCustomerId(long customerId) {
		
		customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with this userID :"+ customerId));
		return pointRepository.findByCustomerId(customerId).orElseGet(() -> {
		    Point p = new Point();
		    p.setCustomerId(customerId);
		    p.setTotalPoint(0);
		    return p;
		});
	}

	

	@Override
	public List<PointActivity> getPointHostory(long customerId) {
		customerRepository.findByCustomerId(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with this userID :"+ customerId));
		
		return pointActivityRepository.findByCustomerId(customerId);
	}
	
	@Override
	@Transactional
	public Point addPoint(long customerId,
	                      double point,
	                      PointAction action,
	                      PointReason reason) {

	    if (point <= 0) {
	        throw new IllegalArgumentException("Point must be positive");
	    }

	    // Validate customer
	    customerRepository.findByCustomerId(customerId)
	            .orElseThrow(() ->
	                    new CustomerNotFoundException(
	                            "Customer not found with this userID :" + customerId));

	    // Get or create point record
	    Point poi = pointRepository.findByCustomerId(customerId)
	            .orElseGet(() -> {
	                Point p = new Point();
	                p.setCustomerId(customerId);
	                p.setTotalPoint(0);
	                return p;
	            });

	    double updatedTotal;

	    if (action == PointAction.ADD) {
	        // Order delivered → add points
	        updatedTotal = poi.getTotalPoint() + point;

	    } else if (action == PointAction.DELETE) {
	        // Order returned → remove awarded points (never below 0)
	        updatedTotal = Math.max(0, poi.getTotalPoint() - point);

	    } else {
	        updatedTotal = poi.getTotalPoint();
	    }

	    poi.setTotalPoint(updatedTotal);
	    Point savedPoint = pointRepository.save(poi);

	    // Activity log (negative for DELETE)
	    PointActivity activity = new PointActivity();
	    activity.setCustomerId(customerId);
	    activity.setPoint(action == PointAction.DELETE ? -point : point);
	    activity.setAction(action);
	    activity.setReason(reason);
	    activity.setDate(LocalDateTime.now());

	    pointActivityRepository.save(activity);

	    return savedPoint;
	}




	

}
