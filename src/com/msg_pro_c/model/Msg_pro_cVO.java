package com.msg_pro_c.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msg_pro_cVO implements Serializable{
	private Integer msg_pro_c_id;
	private Timestamp msg_pro_c_time;
	private Timestamp msg_pro_c_time_last;
	private Integer msg_pro_c_status;
	private String msg_pro_c_ip;
	private String msg_pro_c_text;
	private Integer users_id;
	private Integer msg_pro_id;
	private Integer sale_id;
	
	public Msg_pro_cVO() {
		
	}
	public Msg_pro_cVO(Integer msg_pro_c_id, Timestamp msg_pro_c_time, Timestamp msg_pro_c_time_last,
			Integer msg_pro_c_status, String msg_pro_c_ip, String msg_pro_c_text, Integer users_id, Integer msg_pro_id,
			Integer sale_id) {
		super();
		this.msg_pro_c_id = msg_pro_c_id;
		this.msg_pro_c_time = msg_pro_c_time;
		this.msg_pro_c_time_last = msg_pro_c_time_last;
		this.msg_pro_c_status = msg_pro_c_status;
		this.msg_pro_c_ip = msg_pro_c_ip;
		this.msg_pro_c_text = msg_pro_c_text;
		this.users_id = users_id;
		this.msg_pro_id = msg_pro_id;
		this.sale_id = sale_id;
	}
	public Integer getMsg_pro_c_id() {
		return msg_pro_c_id;
	}
	public void setMsg_pro_c_id(Integer msg_pro_c_id) {
		this.msg_pro_c_id = msg_pro_c_id;
	}
	public Timestamp getMsg_pro_c_time() {
		return msg_pro_c_time;
	}
	public void setMsg_pro_c_time(Timestamp msg_pro_c_time) {
		this.msg_pro_c_time = msg_pro_c_time;
	}
	public Timestamp getMsg_pro_c_time_last() {
		return msg_pro_c_time_last;
	}
	public void setMsg_pro_c_time_last(Timestamp msg_pro_c_time_last) {
		this.msg_pro_c_time_last = msg_pro_c_time_last;
	}
	public Integer getMsg_pro_c_status() {
		return msg_pro_c_status;
	}
	public void setMsg_pro_c_status(Integer msg_pro_c_status) {
		this.msg_pro_c_status = msg_pro_c_status;
	}
	public String getMsg_pro_c_ip() {
		return msg_pro_c_ip;
	}
	public void setMsg_pro_c_ip(String msg_pro_c_ip) {
		this.msg_pro_c_ip = msg_pro_c_ip;
	}
	public String getMsg_pro_c_text() {
		return msg_pro_c_text;
	}
	public void setMsg_pro_c_text(String msg_pro_c_text) {
		this.msg_pro_c_text = msg_pro_c_text;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Integer getMsg_pro_id() {
		return msg_pro_id;
	}
	public void setMsg_pro_id(Integer msg_pro_id) {
		this.msg_pro_id = msg_pro_id;
	}
	public Integer getSale_id() {
		return sale_id;
	}
	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
	
	
}
