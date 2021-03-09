package com.trip_detail.model;

import java.sql.Time;

public class Trip_detailVO {
	private Integer	trip_detail_id;
	private Integer	trip_id;
	private Integer	trip_day;
	private Integer	trip_sort;
	private String	trip_detail_type;
	private String	trip_content;
	private Integer	place_id;
	private Time	trip_start_time;
	private Time	trip_end_time;
	private String	trip_remarks;
	private Integer	trip_cost;
	
	
	public Trip_detailVO() {
	}
	public Trip_detailVO(Integer trip_detail_id, Integer trip_id, Integer trip_day, Integer trip_sort,
			String trip_detail_type, String trip_content, Integer place_id, Time trip_start_time, Time trip_end_time,
			String trip_remarks, Integer trip_cost) {
		super();
		this.trip_detail_id = trip_detail_id;
		this.trip_id = trip_id;
		this.trip_day = trip_day;
		this.trip_sort = trip_sort;
		this.trip_detail_type = trip_detail_type;
		this.trip_content = trip_content;
		this.place_id = place_id;
		this.trip_start_time = trip_start_time;
		this.trip_end_time = trip_end_time;
		this.trip_remarks = trip_remarks;
		this.trip_cost = trip_cost;
	}
	public Integer getTrip_detail_id() {
		return trip_detail_id;
	}
	public void setTrip_detail_id(Integer trip_detail_id) {
		this.trip_detail_id = trip_detail_id;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Integer getTrip_day() {
		return trip_day;
	}
	public void setTrip_day(Integer trip_day) {
		this.trip_day = trip_day;
	}
	public Integer getTrip_sort() {
		return trip_sort;
	}
	public void setTrip_sort(Integer trip_sort) {
		this.trip_sort = trip_sort;
	}
	public String getTrip_detail_type() {
		return trip_detail_type;
	}
	public void setTrip_detail_type(String trip_detail_type) {
		this.trip_detail_type = trip_detail_type;
	}
	public String getTrip_content() {
		return trip_content;
	}
	public void setTrip_content(String trip_content) {
		this.trip_content = trip_content;
	}
	public Integer getPlace_id() {
		return place_id;
	}
	public void setPlace_id(Integer place_id) {
		this.place_id = place_id;
	}
	public Time getTrip_start_time() {
		return trip_start_time;
	}
	public void setTrip_start_time(Time trip_start_time) {
		this.trip_start_time = trip_start_time;
	}
	public Time getTrip_end_time() {
		return trip_end_time;
	}
	public void setTrip_end_time(Time trip_end_time) {
		this.trip_end_time = trip_end_time;
	}
	public String getTrip_remarks() {
		return trip_remarks;
	}
	public void setTrip_remarks(String trip_remarks) {
		this.trip_remarks = trip_remarks;
	}
	public Integer getTrip_cost() {
		return trip_cost;
	}
	public void setTrip_cost(Integer trip_cost) {
		this.trip_cost = trip_cost;
	}
	
	


}
