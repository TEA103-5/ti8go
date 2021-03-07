package com.trip_collect.model;

import java.util.List;

public interface Trip_collectDAO_interface {
	public void insert(Trip_collectVO trip_collectVO);
	public void update(Trip_collectVO trip_collectVO);
	public void delete(Integer trip_collect_id);
	public Trip_collectVO findByPrimaryKey(Integer trip_collect_id);
	public List<Trip_collectVO> getAll();
}
