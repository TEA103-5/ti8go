package com.product.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class ProductVO implements java.io.Serializable{
	private Integer product_id;
	private String product_name;
	private Integer product_status;
	private Timestamp product_update_time;
	private String product_content;
	private String product_description;
	private String product_categories;
	private Integer product_price;
	private Integer product_stock;
	private byte[] product_pic;
	private Integer sale_id;
	
	
	
	@Override
	public String toString() {
		return "ProductVO [product_id=" + product_id + ", product_name=" + product_name + ", product_status="
				+ product_status + ", product_update_time=" + product_update_time + ", product_content="
				+ product_content + ", product_description=" + product_description + ", product_categories="
				+ product_categories + ", product_price=" + product_price + ", product_stock=" + product_stock
				+ ", product_pic=" + Arrays.toString(product_pic) + ", sale_id=" + sale_id + "]";
	}
	public ProductVO() {
		
	}
	public ProductVO(Integer product_id, String product_name, Integer product_status, Timestamp product_update_time,
			String product_content, String product_description, String product_categories, Integer product_price,
			Integer product_stock, byte[] product_pic, Integer sale_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_status = product_status;
		this.product_update_time = product_update_time;
		this.product_content = product_content;
		this.product_description = product_description;
		this.product_categories = product_categories;
		this.product_price = product_price;
		this.product_stock = product_stock;
		this.product_pic = product_pic;
		this.sale_id = sale_id;
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
