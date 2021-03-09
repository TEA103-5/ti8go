package com.reminder_a.model;

import java.sql.Timestamp;

public class Reminder_aVO {
	private Integer Reminder_a_id;
	private Timestamp Reminder_a_time;
	private String Reminder_a_text;
	private Integer Reminder_a_status;
	private Integer admins_id;
	@Override
	public String toString() {
		return "Reminder_aVO [Reminder_a_id=" + Reminder_a_id + ", Reminder_a_time=" + Reminder_a_time
				+ ", Reminder_a_text=" + Reminder_a_text + ", Reminder_a_status=" + Reminder_a_status + ", admins_id="
				+ admins_id + "]";
	}
	public Reminder_aVO(Integer reminder_a_id, Timestamp reminder_a_time, String reminder_a_text,
			Integer reminder_a_status, Integer admins_id) {
		super();
		Reminder_a_id = reminder_a_id;
		Reminder_a_time = reminder_a_time;
		Reminder_a_text = reminder_a_text;
		Reminder_a_status = reminder_a_status;
		this.admins_id = admins_id;
	}
	
	public Reminder_aVO() {
		
	}
	public Integer getReminder_a_id() {
		return Reminder_a_id;
	}
	public void setReminder_a_id(Integer reminder_a_id) {
		Reminder_a_id = reminder_a_id;
	}
	public Timestamp getReminder_a_time() {
		return Reminder_a_time;
	}
	public void setReminder_a_time(Timestamp reminder_a_time) {
		Reminder_a_time = reminder_a_time;
	}
	public String getReminder_a_text() {
		return Reminder_a_text;
	}
	public void setReminder_a_text(String reminder_a_text) {
		Reminder_a_text = reminder_a_text;
	}
	public Integer getReminder_a_status() {
		return Reminder_a_status;
	}
	public void setReminder_a_status(Integer reminder_a_status) {
		Reminder_a_status = reminder_a_status;
	}
	public Integer getAdmins_id() {
		return admins_id;
	}
	public void setAdmins_id(Integer admins_id) {
		this.admins_id = admins_id;
	}
	
	
}
