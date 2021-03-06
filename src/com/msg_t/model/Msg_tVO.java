package com.msg_t.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msg_tVO implements Serializable{
	private Integer msg_t_id;//msg_p_id int
	private Timestamp msg_t_time;//msg_p_time timestamp
	private Timestamp msg_t_time_last;//msg_p_time_last timestamp
	private Integer msg_t_status;//msg_p_status tinyint
	private String msg_t_ip;//msg_p_ip char
	private String msg_t_text;//msg_p_text varchar
	private Integer users_id;//users_id int
	private Integer trip_id;//place_id int
	
	
	public Msg_tVO() {
		
	}
	public Msg_tVO(Integer msg_t_id, Timestamp msg_t_time, Timestamp msg_t_time_last, Integer msg_t_status,
			String msg_t_ip, String msg_t_text, Integer users_id, Integer trip_id) {
		super();
		this.msg_t_id = msg_t_id;
		this.msg_t_time = msg_t_time;
		this.msg_t_time_last = msg_t_time_last;
		this.msg_t_status = msg_t_status;
		this.msg_t_ip = msg_t_ip;
		this.msg_t_text = msg_t_text;
		this.users_id = users_id;
		this.trip_id = trip_id;
	}
	public Integer getMsg_t_id() {
		return msg_t_id;
	}
	public void setMsg_t_id(Integer msg_t_id) {
		this.msg_t_id = msg_t_id;
	}
	public Timestamp getMsg_t_time() {
		return msg_t_time;
	}
	public void setMsg_t_time(Timestamp msg_t_time) {
		this.msg_t_time = msg_t_time;
	}
	public Timestamp getMsg_t_time_last() {
		return msg_t_time_last;
	}
	public void setMsg_t_time_last(Timestamp msg_t_time_last) {
		this.msg_t_time_last = msg_t_time_last;
	}
	public Integer getMsg_t_status() {
		return msg_t_status;
	}
	public void setMsg_t_status(Integer msg_t_status) {
		this.msg_t_status = msg_t_status;
	}
	public String getMsg_t_ip() {
		return msg_t_ip;
	}
	public void setMsg_t_ip(String msg_t_ip) {
		this.msg_t_ip = msg_t_ip;
	}
	public String getMsg_t_text() {
		return msg_t_text;
	}
	public void setMsg_t_text(String msg_t_text) {
		this.msg_t_text = msg_t_text;
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
	@Override
	public String toString() {
		return "Msg_tVO [msg_t_id=" + msg_t_id + ", msg_t_time=" + msg_t_time + ", msg_t_time_last=" + msg_t_time_last
				+ ", msg_t_status=" + msg_t_status + ", msg_t_ip=" + msg_t_ip + ", msg_t_text=" + msg_t_text
				+ ", users_id=" + users_id + ", trip_id=" + trip_id + "]";
	}
	
	
}
