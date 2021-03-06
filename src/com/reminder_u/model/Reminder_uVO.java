package com.reminder_u.model;

import java.sql.Timestamp;

public class Reminder_uVO {
	private Integer Reminder_u_id;
	private Timestamp Reminder_u_time;
	private String Reminder_u_text;
	private Integer Reminder_u_status;
	private Integer users_id;
	
	public Reminder_uVO() {
		
	}
	@Override
	public String toString() {
		return "Reminder_uVO [Reminder_u_id=" + Reminder_u_id + ", Reminder_u_time=" + Reminder_u_time
				+ ", Reminder_u_text=" + Reminder_u_text + ", Reminder_u_status=" + Reminder_u_status + ", users_id="
				+ users_id + "]";
	}
	public Reminder_uVO(Integer reminder_u_id, Timestamp reminder_u_time, String reminder_u_text,
			Integer reminder_u_status, Integer users_id) {
		super();
		Reminder_u_id = reminder_u_id;
		Reminder_u_time = reminder_u_time;
		Reminder_u_text = reminder_u_text;
		Reminder_u_status = reminder_u_status;
		this.users_id = users_id;
	}
	public Integer getReminder_u_id() {
		return Reminder_u_id;
	}
	public void setReminder_u_id(Integer reminder_u_id) {
		Reminder_u_id = reminder_u_id;
	}
	public Timestamp getReminder_u_time() {
		return Reminder_u_time;
	}
	public void setReminder_u_time(Timestamp reminder_u_time) {
		Reminder_u_time = reminder_u_time;
	}
	public String getReminder_u_text() {
		return Reminder_u_text;
	}
	public void setReminder_u_text(String reminder_u_text) {
		Reminder_u_text = reminder_u_text;
	}
	public Integer getReminder_u_status() {
		return Reminder_u_status;
	}
	public void setReminder_u_status(Integer reminder_u_status) {
		Reminder_u_status = reminder_u_status;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	
	
}
