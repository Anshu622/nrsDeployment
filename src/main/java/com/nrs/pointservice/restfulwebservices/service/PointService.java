package com.nrs.pointservice.restfulwebservices.service;

import java.util.List;

import com.nrs.pointservice.restfulwebservices.model.Point;
import com.nrs.pointservice.restfulwebservices.model.PointAction;
import com.nrs.pointservice.restfulwebservices.model.PointActivity;
import com.nrs.pointservice.restfulwebservices.model.PointReason;

public interface PointService {
	Point getPointsByCustomerId(long customerId);
	Point addPoint(long customerId,double point,PointAction action,PointReason reason);
	List<PointActivity> getPointHostory(long customerId);
	

}
