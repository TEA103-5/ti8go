package com.product.model;

import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable{
	private Integer product_id;
	private String product_name;
	private Integer product_status;
	private Timestamp product_update_time;
	private String product_content;//內容
	private String product_description;//描述
	private String product_categories;//分類
	private Integer product_price;
	private Integer product_stock;//庫存
	private byte[] product_pic;
	private Integer sale_id;
	private Integer product_rate;

	public Integer getProduct_rate() {
		return product_rate;
	}
	public void setProduct_rate(Integer product_rate) {
		this.product_rate = product_rate;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getProduct_status() {
		return product_status;
	}
	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}
	public Timestamp getProduct_update_time() {
		return product_update_time;
	}
	public void setProduct_update_time(Timestamp product_update_time) {
		this.product_update_time = product_update_time;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_categories() {
		return product_categories;
	}
	public void setProduct_categories(String product_categories) {
		this.product_categories = product_categories;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public Integer getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(Integer product_stock) {
		this.product_stock = product_stock;
	}
	public byte[] getProduct_pic() {
		return product_pic;
	}
	public void setProduct_pic(byte[] product_pic) {
		this.product_pic = product_pic;
	}
	public Integer getSale_id() {
		return sale_id;
	}
	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}
	

}
