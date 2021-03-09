package com.applicant.model;

import java.sql.Timestamp;

public class ApplicantVO implements java.io.Serializable{
	private Integer activities_id;
	private Integer users_id;
	private Timestamp registration_time;
	
	
	public ApplicantVO(Integer activities_id, Integer users_id, Timestamp registration_time) {
		super();
		this.activities_id = activities_id;
		this.users_id = users_id;
		this.registration_time = registration_time;
	}
	public ApplicantVO() {
	}
	public Integer getActivities_id() {
		return activities_id;
	}
	public void setActivities_id(Integer activities_id) {
		this.activities_id = activities_id;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Timestamp getRegistration_time() {
		return registration_time;
	}
	public void setRegistration_time(Timestamp registration_time) {
		this.registration_time = registration_time;
	}
	
	
	
}
