package com.reminder_s.model;

import java.sql.Timestamp;

public class Reminder_sVO {
	private Integer Reminder_s_id;
	private Timestamp Reminder_s_time;
	private String Reminder_s_text;
	private Integer Reminder_s_status;
	private Integer sale_id;
	
	public Reminder_sVO() {
		
	}
	@Override
	public String toString() {
		return "Reminder_sVO [Reminder_s_id=" + Reminder_s_id + ", Reminder_s_time=" + Reminder_s_time
				+ ", Reminder_s_text=" + Reminder_s_text + ", Reminder_s_status=" + Reminder_s_status + ", sale_id="
				+ sale_id + "]";
	}
	public Reminder_sVO(Integer reminder_s_id, Timestamp reminder_s_time, String reminder_s_text,
			Integer reminder_s_status, Integer sale_id) {
		super();
		Reminder_s_id = reminder_s_id;
		Reminder_s_time = reminder_s_time;
		Reminder_s_text = reminder_s_text;
		Reminder_s_status = reminder_s_status;
		this.sale_id = sale_id;
	}
	public Integer getReminder_s_id() {
		return Reminder_s_id;
	}
	public void setReminder_s_id(Integer reminder_s_id) {
		Reminder_s_id = reminder_s_id;
	}
	public Timestamp getReminder_s_time() {
		return Reminder_s_time;
	}
	public void setReminder_s_time(Timestamp reminder_s_time) {
		Reminder_s_time = reminder_s_time;
	}
	public String getReminder_s_text() {
		return Reminder_s_text;
	}
	public void setReminder_s_text(String reminder_s_text) {
		Reminder_s_text = reminder_s_text;
	}
	public Integer getReminder_s_status() {
		return Reminder_s_status;
	}
	public void setReminder_s_status(Integer reminder_s_status) {
		Reminder_s_status = reminder_s_status;
	}
	public Integer getSale_id() {
		return sale_id;
	}
	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
	
	
}
