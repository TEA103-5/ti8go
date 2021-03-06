package com.product_collect.model;

import java.sql.Timestamp;

public class Product_collectVO {
	private Integer product_collect_id;
	private Integer	product_id;
	private Integer	users_id;
	private Timestamp product_collect_time;
	public Integer getProduct_collect_id() {
		return product_collect_id;
	}
	public void setProduct_collect_id(Integer product_collect_id) {
		this.product_collect_id = product_collect_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Timestamp getProduct_collect_time() {
		return product_collect_time;
	}
	public void setProduct_collect_time(Timestamp product_collect_time) {
		this.product_collect_time = product_collect_time;
	}
	
	
}
