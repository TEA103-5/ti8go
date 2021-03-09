package com.msg_p_c.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msg_p_cVO implements Serializable{
	private Integer msg_p_c_id;
	private Timestamp msg_p_c_time;
	private Timestamp msg_p_c_time_last;
	private Integer msg_p_c_status;
	private String msg_p_c_ip;
	private String msg_p_c_text;
	private Integer users_id;
	private Integer msg_p_id;
	
	public Msg_p_cVO() {
		
	}
	public Msg_p_cVO(Integer msg_p_c_id, Timestamp msg_p_c_time, Timestamp msg_p_c_time_last, Integer msg_p_c_status,
			String msg_p_c_ip, String msg_p_c_text, Integer users_id, Integer msg_p_id) {
		super();
		this.msg_p_c_id = msg_p_c_id;
		this.msg_p_c_time = msg_p_c_time;
		this.msg_p_c_time_last = msg_p_c_time_last;
		this.msg_p_c_status = msg_p_c_status;
		this.msg_p_c_ip = msg_p_c_ip;
		this.msg_p_c_text = msg_p_c_text;
		this.users_id = users_id;
		this.msg_p_id = msg_p_id;
	}
	public Integer getMsg_p_c_id() {
		return msg_p_c_id;
	}
	public void setMsg_p_c_id(Integer msg_p_c_id) {
		this.msg_p_c_id = msg_p_c_id;
	}
	public Timestamp getMsg_p_c_time() {
		return msg_p_c_time;
	}
	public void setMsg_p_c_time(Timestamp msg_p_c_time) {
		this.msg_p_c_time = msg_p_c_time;
	}
	public Timestamp getMsg_p_c_time_last() {
		return msg_p_c_time_last;
	}
	public void setMsg_p_c_time_last(Timestamp msg_p_c_time_last) {
		this.msg_p_c_time_last = msg_p_c_time_last;
	}
	public Integer getMsg_p_c_status() {
		return msg_p_c_status;
	}
	public void setMsg_p_c_status(Integer msg_p_c_status) {
		this.msg_p_c_status = msg_p_c_status;
	}
	public String getMsg_p_c_ip() {
		return msg_p_c_ip;
	}
	public void setMsg_p_c_ip(String msg_p_c_ip) {
		this.msg_p_c_ip = msg_p_c_ip;
	}
	public String getMsg_p_c_text() {
		return msg_p_c_text;
	}
	public void setMsg_p_c_text(String msg_p_c_text) {
		this.msg_p_c_text = msg_p_c_text;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Integer getMsg_p_id() {
		return msg_p_id;
	}
	public void setMsg_p_id(Integer msg_p_id) {
		this.msg_p_id = msg_p_id;
	}
	
	
}
