package com.trip_collect.model;

import java.sql.Timestamp;

public class Trip_collectVO {
	private Integer trip_collect_id;
	private Integer trip_id;
	private Integer users_id;
	private Timestamp trip_collect_time;
	public Integer getTrip_collect_id() {
		return trip_collect_id;
	}
	public void setTrip_collect_id(Integer trip_collect_id) {
		this.trip_collect_id = trip_collect_id;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Timestamp getTrip_collect_time() {
		return trip_collect_time;
	}
	public void setTrip_collect_time(Timestamp trip_collect_time) {
		this.trip_collect_time = trip_collect_time;
	}
	
	
}
