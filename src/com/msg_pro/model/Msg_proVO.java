package com.msg_pro.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Msg_proVO implements Serializable{
	private Integer msg_pro_id;//msg_p_id int
	private Timestamp msg_pro_time;//msg_p_time timestamp
	private Timestamp msg_pro_time_last;//msg_p_time_last timestamp
	private Integer msg_pro_status;//msg_p_status tinyint
	private String msg_pro_ip;//msg_p_ip char
	private String msg_pro_text;//msg_p_text varchar
	private Integer users_id;//users_id int
	private Integer product_id;//place_id int
	
	public Msg_proVO() {
		
	}
	public Msg_proVO(Integer msg_pro_id, Timestamp msg_pro_time, Timestamp msg_pro_time_last, Integer msg_pro_status,
			String msg_pro_ip, String msg_pro_text, Integer users_id, Integer product_id) {
		super();
		this.msg_pro_id = msg_pro_id;
		this.msg_pro_time = msg_pro_time;
		this.msg_pro_time_last = msg_pro_time_last;
		this.msg_pro_status = msg_pro_status;
		this.msg_pro_ip = msg_pro_ip;
		this.msg_pro_text = msg_pro_text;
		this.users_id = users_id;
		this.product_id = product_id;
	}
	public Integer getMsg_pro_id() {
		return msg_pro_id;
	}
	public void setMsg_pro_id(Integer msg_pro_id) {
		this.msg_pro_id = msg_pro_id;
	}
	public Timestamp getMsg_pro_time() {
		return msg_pro_time;
	}
	public void setMsg_pro_time(Timestamp msg_pro_time) {
		this.msg_pro_time = msg_pro_time;
	}
	public Timestamp getMsg_pro_time_last() {
		return msg_pro_time_last;
	}
	public void setMsg_pro_time_last(Timestamp msg_pro_time_last) {
		this.msg_pro_time_last = msg_pro_time_last;
	}
	public Integer getMsg_pro_status() {
		return msg_pro_status;
	}
	public void setMsg_pro_status(Integer msg_pro_status) {
		this.msg_pro_status = msg_pro_status;
	}
	public String getMsg_pro_ip() {
		return msg_pro_ip;
	}
	public void setMsg_pro_ip(String msg_pro_ip) {
		this.msg_pro_ip = msg_pro_ip;
	}
	public String getMsg_pro_text() {
		return msg_pro_text;
	}
	public void setMsg_pro_text(String msg_pro_text) {
		this.msg_pro_text = msg_pro_text;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	
}
