package com.msg_p.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msg_pVO implements Serializable{
	private Integer msg_p_id;//msg_p_id int
	private Timestamp msg_p_time;//msg_p_time timestamp
	private Timestamp msg_p_time_last;//msg_p_time_last timestamp
	private Integer msg_p_status;//msg_p_status tinyint
	private String msg_p_ip;//msg_p_ip char
	private String msg_p_text;//msg_p_text varchar
	private Integer users_id;//users_id int
	private Integer place_id;//place_id int
	
	
	public Msg_pVO() {
	}
	public Msg_pVO(Integer msg_p_id, Timestamp msg_p_time, Timestamp msg_p_time_last, Integer msg_p_status,
			String msg_p_ip, String msg_p_text, Integer users_id, Integer place_id) {
		super();
		this.msg_p_id = msg_p_id;
		this.msg_p_time = msg_p_time;
		this.msg_p_time_last = msg_p_time_last;
		this.msg_p_status = msg_p_status;
		this.msg_p_ip = msg_p_ip;
		this.msg_p_text = msg_p_text;
		this.users_id = users_id;
		this.place_id = place_id;
	}
	public Integer getMsg_p_id() {
		return msg_p_id;
	}
	public void setMsg_p_id(Integer msg_p_id) {
		this.msg_p_id = msg_p_id;
	}
	public Timestamp getMsg_p_time() {
		return msg_p_time;
	}
	public void setMsg_p_time(Timestamp msg_p_time) {
		this.msg_p_time = msg_p_time;
	}
	public Timestamp getMsg_p_time_last() {
		return msg_p_time_last;
	}
	public void setMsg_p_time_last(Timestamp msg_p_time_last) {
		this.msg_p_time_last = msg_p_time_last;
	}
	public Integer getMsg_p_status() {
		return msg_p_status;
	}
	public void setMsg_p_status(Integer msg_p_status) {
		this.msg_p_status = msg_p_status;
	}
	public String getMsg_p_ip() {
		return msg_p_ip;
	}
	public void setMsg_p_ip(String msg_p_ip) {
		this.msg_p_ip = msg_p_ip;
	}
	public String getMsg_p_text() {
		return msg_p_text;
	}
	public void setMsg_p_text(String msg_p_text) {
		this.msg_p_text = msg_p_text;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Integer getPlace_id() {
		return place_id;
	}
	public void setPlace_id(Integer place_id) {
		this.place_id = place_id;
	}
	
}

