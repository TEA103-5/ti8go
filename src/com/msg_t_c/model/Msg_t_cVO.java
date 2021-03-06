package com.msg_t_c.model;

import java.sql.Timestamp;

public class Msg_t_cVO {
	private Integer msg_t_c_id;
	private Timestamp msg_t_c_time;
	private Timestamp msg_t_c_time_last;
	private Integer msg_t_c_status;
	private String msg_t_c_ip;
	private String msg_t_c_text;
	private Integer users_id;
	private Integer msg_t_id;
	
	public Msg_t_cVO() {
		
	}
	public Msg_t_cVO(Integer msg_t_c_id, Timestamp msg_t_c_time, Timestamp msg_t_c_time_last, Integer msg_t_c_status,
			String msg_t_c_ip, String msg_t_c_text, Integer users_id, Integer msg_t_id) {
		super();
		this.msg_t_c_id = msg_t_c_id;
		this.msg_t_c_time = msg_t_c_time;
		this.msg_t_c_time_last = msg_t_c_time_last;
		this.msg_t_c_status = msg_t_c_status;
		this.msg_t_c_ip = msg_t_c_ip;
		this.msg_t_c_text = msg_t_c_text;
		this.users_id = users_id;
		this.msg_t_id = msg_t_id;
	}
	public Integer getMsg_t_c_id() {
		return msg_t_c_id;
	}
	public void setMsg_t_c_id(Integer msg_t_c_id) {
		this.msg_t_c_id = msg_t_c_id;
	}
	public Timestamp getMsg_t_c_time() {
		return msg_t_c_time;
	}
	public void setMsg_t_c_time(Timestamp msg_t_c_time) {
		this.msg_t_c_time = msg_t_c_time;
	}
	public Timestamp getMsg_t_c_time_last() {
		return msg_t_c_time_last;
	}
	public void setMsg_t_c_time_last(Timestamp msg_t_c_time_last) {
		this.msg_t_c_time_last = msg_t_c_time_last;
	}
	public Integer getMsg_t_c_status() {
		return msg_t_c_status;
	}
	public void setMsg_t_c_status(Integer msg_t_c_status) {
		this.msg_t_c_status = msg_t_c_status;
	}
	public String getMsg_t_c_ip() {
		return msg_t_c_ip;
	}
	public void setMsg_t_c_ip(String msg_t_c_ip) {
		this.msg_t_c_ip = msg_t_c_ip;
	}
	public String getMsg_t_c_text() {
		return msg_t_c_text;
	}
	public void setMsg_t_c_text(String msg_t_c_text) {
		this.msg_t_c_text = msg_t_c_text;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Integer getMsg_t_id() {
		return msg_t_id;
	}
	public void setMsg_t_id(Integer msg_t_id) {
		this.msg_t_id = msg_t_id;
	}
	
	
}
