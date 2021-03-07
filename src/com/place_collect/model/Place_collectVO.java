package com.place_collect.model;

import java.sql.Timestamp;

public class Place_collectVO {
	private Integer place_collect_id;
	private Integer place_id;
	private Integer users_id;
	private	Timestamp place_collect_time;
	public Integer getPlace_collect_id() {
		return place_collect_id;
	}
	public void setPlace_collect_id(Integer place_collect_id) {
		this.place_collect_id = place_collect_id;
	}
	public Integer getPlace_id() {
		return place_id;
	}
	public void setPlace_id(Integer place_id) {
		this.place_id = place_id;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Timestamp getPlace_collect_time() {
		return place_collect_time;
	}
	public void setPlace_collect_time(Timestamp place_collect_time) {
		this.place_collect_time = place_collect_time;
	}
	
}
