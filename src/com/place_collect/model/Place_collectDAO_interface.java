package com.place_collect.model;

import java.util.List;

public interface Place_collectDAO_interface {
	public void insert(Place_collectVO place_collectVO);
	public void update(Place_collectVO place_collectVO);
	public void delete(Integer place_id , Integer users_id);
	public Place_collectVO findByPrimaryKey(Integer users_id);
	public List<Place_collectVO> getAll();
}
