package com.msg_n.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msg_nVO implements Serializable{
	private Integer msg_n_id;
	private Timestamp msg_n_time;
	private Timestamp msg_n_time_last;
	private Integer msg_n_status;
	private String msg_n_ip;
	private String msg_n_text;
	private Integer users_id;
	private Integer note_id;
	
	public Msg_nVO() {
		
	}
	
	public Msg_nVO(Integer msg_n_id, Timestamp msg_n_time, Timestamp msg_n_time_last, Integer msg_n_status,
			String msg_n_ip, String msg_n_text, Integer users_id, Integer note_id) {
		super();
		this.msg_n_id = msg_n_id;
		this.msg_n_time = msg_n_time;
		this.msg_n_time_last = msg_n_time_last;
		this.msg_n_status = msg_n_status;
		this.msg_n_ip = msg_n_ip;
		this.msg_n_text = msg_n_text;
		this.users_id = users_id;
		this.note_id = note_id;
	}

	public Integer getMsg_n_id() {
		return msg_n_id;
	}

	public void setMsg_n_id(Integer msg_n_id) {
		this.msg_n_id = msg_n_id;
	}

	public Timestamp getMsg_n_time() {
		return msg_n_time;
	}

	public void setMsg_n_time(Timestamp msg_n_time) {
		this.msg_n_time = msg_n_time;
	}

	public Timestamp getMsg_n_time_last() {
		return msg_n_time_last;
	}

	public void setMsg_n_time_last(Timestamp msg_n_time_last) {
		this.msg_n_time_last = msg_n_time_last;
	}

	public Integer getMsg_n_status() {
		return msg_n_status;
	}

	public void setMsg_n_status(Integer msg_n_status) {
		this.msg_n_status = msg_n_status;
	}

	public String getMsg_n_ip() {
		return msg_n_ip;
	}

	public void setMsg_n_ip(String msg_n_ip) {
		this.msg_n_ip = msg_n_ip;
	}

	public String getMsg_n_text() {
		return msg_n_text;
	}

	public void setMsg_n_text(String msg_n_text) {
		this.msg_n_text = msg_n_text;
	}

	public Integer getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}

	public Integer getNote_id() {
		return note_id;
	}

	public void setNote_id(Integer note_id) {
		this.note_id = note_id;
	}
	
	
}
