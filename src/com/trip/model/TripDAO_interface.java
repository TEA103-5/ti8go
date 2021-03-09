package com.trip.model;

import java.util.List;

public interface TripDAO_interface {
    public void insert(TripVO tripVO);
    public void update(TripVO tripVO);
    public void delete(Integer trip_id);
    public TripVO findByPrimaryKey(Integer trip_id);
    public List<TripVO> getAll();
}
