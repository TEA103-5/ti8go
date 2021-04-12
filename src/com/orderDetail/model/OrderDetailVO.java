package com.orderDetail.model;

public class OrderDetailVO implements java.io.Serializable{
	private Integer order_detail_id;
	private Integer order_detail_count;
	private Integer product_id;
	private Integer order_id;
	private Integer order_detail_status;
	

	public Integer getOrder_detail_status() {
		return order_detail_status;
	}

	public void setOrder_detail_status(Integer order_detail_status) {
		this.order_detail_status = order_detail_status;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getOrder_detail_count() {
		return order_detail_count;
	}

	public void setOrder_detail_count(Integer order_detail_count) {
		this.order_detail_count = order_detail_count;
	}

	public Integer getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

}
