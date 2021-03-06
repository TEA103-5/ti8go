package com.note_collect.model;

import java.sql.Timestamp;

public class Note_collectVO {
	private Integer note_collect_id;
	private Integer note_id;
	private Integer users_id;
	private Timestamp note_collect_time;
	
	public Integer getNote_collect_id() {
		return note_collect_id;
	}
	public void setNote_collect_id(Integer note_collect_id) {
		this.note_collect_id = note_collect_id;
	}
	public Integer getNote_id() {
		return note_id;
	}
	public void setNote_id(Integer note_id) {
		this.note_id = note_id;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Timestamp getNote_collect_time() {
		return note_collect_time;
	}
	public void setNote_collect_time(Timestamp note_collect_time) {
		this.note_collect_time = note_collect_time;
	}
	
	
}
