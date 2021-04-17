package com.trip_detail.model;

import java.util.List;


public interface Trip_detailDAO_interface {
	public void insert(Trip_detailVO trip_detailVO);
    public void update(Trip_detailVO  trip_detailVO);
    public void deleteT(Integer trip_id);
    public void delete(Integer	trip_detail_id);
    public Trip_detailVO findByPrimaryKey(Integer trip_detail_id);
    public List<Trip_detailVO> getAll();
    
    public void insert2 (Trip_detailVO trip_detailVO , java.sql.Connection con);
}
