package com.order.model;
import java.sql.Timestamp;

public class OrderVO implements java.io.Serializable{
	private Integer order_id;
	private Timestamp order_date;
	private Integer order_status;
	private Integer order_rate; // 售後評價分數
	private String card_number;
	private Integer user_id;

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public Integer getOrder_rate() {
		return order_rate;
	}

	public void setOrder_rate(Integer order_rate) {
		this.order_rate = order_rate;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
