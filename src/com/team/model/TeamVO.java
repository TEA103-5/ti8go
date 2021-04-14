package com.team.model;

public class TeamVO implements java.io.Serializable{
	private Integer trip_id;
	private Integer users_id;

	
	
	public TeamVO() {
	}
	public TeamVO(Integer trip_id, Integer users_id) {
		super();
		this.trip_id = trip_id;
		this.users_id = users_id;
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
	
	
}
