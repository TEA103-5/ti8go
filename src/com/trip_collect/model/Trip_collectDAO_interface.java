package com.trip_collect.model;

import java.util.List;

import com.trip_collect.model.Trip_collectVO;

public interface Trip_collectDAO_interface {
	public void insert(Trip_collectVO trip_collectVO);
//	public void update(Trip_collectVO trip_collectVO);
	public void delete(Integer trip_id , Integer users_id);
	public Trip_collectVO findByPrimaryKey(Integer trip_id , Integer users_id);
	public List<Trip_collectVO> getAll();
	
	public List<Trip_collectVO> getAllByPrimaryKey(Integer users_id);
}
