package com.group_activities.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Group_activitiesVO {

	private Integer	activities_id;
	private Integer	trip_id;
	private Integer	users_id;
	private Timestamp	activities_create_time;
	private Integer	activities_state;
	private Date	activities_deadline;
	private Integer	activities_max_users;
	private Integer	activities_users_count;
	private Date	activities_start;
	private Date	activities_end;
	private String	activities_name;
	private String	activities_description;
	
	
	
	public Group_activitiesVO() {
	}
	public Group_activitiesVO(Integer activities_id, Integer trip_id, Integer users_id,
			Timestamp activities_create_time, Integer activities_state, Date activities_deadline,
			Integer activities_max_users, Integer activities_users_count, Date activities_start, Date activities_end,
			String activities_name, String activities_description) {
		super();
		this.activities_id = activities_id;
		this.trip_id = trip_id;
		this.users_id = users_id;
		this.activities_create_time = activities_create_time;
		this.activities_state = activities_state;
		this.activities_deadline = activities_deadline;
		this.activities_max_users = activities_max_users;
		this.activities_users_count = activities_users_count;
		this.activities_start = activities_start;
		this.activities_end = activities_end;
		this.activities_name = activities_name;
		this.activities_description = activities_description;
	}
	public Integer getActivities_id() {
		return activities_id;
	}
	public void setActivities_id(Integer activities_id) {
		this.activities_id = activities_id;
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
	public Timestamp getActivities_create_time() {
		return activities_create_time;
	}
	public void setActivities_create_time(Timestamp activities_create_time) {
		this.activities_create_time = activities_create_time;
	}
	public Integer getActivities_state() {
		return activities_state;
	}
	public void setActivities_state(Integer activities_state) {
		this.activities_state = activities_state;
	}
	public Date getActivities_deadline() {
		return activities_deadline;
	}
	public void setActivities_deadline(Date activities_deadline) {
		this.activities_deadline = activities_deadline;
	}
	public Integer getActivities_max_users() {
		return activities_max_users;
	}
	public void setActivities_max_users(Integer activities_max_users) {
		this.activities_max_users = activities_max_users;
	}
	public Integer getActivities_users_count() {
		return activities_users_count;
	}
	public void setActivities_users_count(Integer activities_users_count) {
		this.activities_users_count = activities_users_count;
	}
	public Date getActivities_start() {
		return activities_start;
	}
	public void setActivities_start(Date activities_start) {
		this.activities_start = activities_start;
	}
	public Date getActivities_end() {
		return activities_end;
	}
	public void setActivities_end(Date activities_end) {
		this.activities_end = activities_end;
	}
	public String getActivities_name() {
		return activities_name;
	}
	public void setActivities_name(String activities_name) {
		this.activities_name = activities_name;
	}
	public String getActivities_description() {
		return activities_description;
	}
	public void setActivities_description(String activities_description) {
		this.activities_description = activities_description;
	}
	
	

	
}
