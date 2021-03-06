package com.place.model;

import java.util.List;

public interface PlaceDAO_interface {
	public void insert(PlaceVO placeVO);
	public void update(PlaceVO placeVO);
	public void	delete(Integer place_id);
	public PlaceVO findByPrimaryKey(Integer place_id);
	public List<PlaceVO> getAll();
}
