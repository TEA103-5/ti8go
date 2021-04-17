package com.note.model;

import java.sql.Timestamp;
import java.sql.Date;

public class NoteVO implements java.io.Serializable{
	private Integer note_id;
	private Integer note_classid;
	private Timestamp note_date;
	private Date travel_start;
	private String note_title;
	private String note_description;
	private Timestamp note_update;
	private Integer users_id;
	private Integer trip_id;
	private Integer note_like;
	

	public Integer getNote_id() {
		return note_id;
	}
	public void setNote_id(Integer note_id) {
		this.note_id = note_id;
	}
	public Integer getNote_classid() {
		return note_classid;
	}
	public void setNote_classid(Integer note_classid) {
		this.note_classid = note_classid;
	}
	public Timestamp getNote_date() {
		return note_date;
	}
	public void setNote_date(Timestamp note_date) {
		this.note_date = note_date;
	}
	public Date getTravel_start() {
		return travel_start;
	}
	public void setTravel_start(Date travel_start) {
		this.travel_start = travel_start;
	}
	public String getNote_title() {
		return note_title;
	}
	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}
	public String getNote_description() {
		return note_description;
	}
	public void setNote_description(String note_description) {
		this.note_description = note_description;
	}
	public Timestamp getNote_update() {
		return note_update;
	}
	public void setNote_update(Timestamp note_update) {
		this.note_update = note_update;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Integer getNote_like() {
		return note_like;
	}
	public void setNote_like(Integer note_like) {
		this.note_like = note_like;
	}

}
