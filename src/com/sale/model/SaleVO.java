package com.sale.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class SaleVO implements Serializable{
	private Integer sale_id;
	private String sale_email;
	private String sale_pwd;
	private String sale_name;
	private Integer sale_audit_status;
	private byte[] sale_audit_pic;// 似乎比想像麻煩@@ 暫時先不新增
	private Integer sale_status;
	private String sale_phone;
	private String sale_nickname;
	private Float sale_rate;
	private Timestamp sale_time_create;
	private Integer sale_money;





	public Integer getSale_money() {
		return sale_money;
	}

	public void setSale_money(Integer sale_money) {
		this.sale_money = sale_money;
	}

	public Integer getSale_id() {
		return sale_id;
	}

	public void setSale_id(Integer sale_id) {
		this.sale_id = sale_id;
	}

	public String getSale_email() {
		return sale_email;
	}

	public void setSale_email(String sale_email) {
		this.sale_email = sale_email;
	}

	public String getSale_pwd() {
		return sale_pwd;
	}

	public void setSale_pwd(String sale_pwd) {
		this.sale_pwd = sale_pwd;
	}

	public String getSale_name() {
		return sale_name;
	}

	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}

	public Integer getSale_audit_status() {
		return sale_audit_status;
	}

	public void setSale_audit_status(Integer sale_audit_status) {
		this.sale_audit_status = sale_audit_status;
	}

	public byte[] getSale_audit_pic() {
		return sale_audit_pic;
	}

	public void setSale_audit_pic(byte[] sale_audit_pic) {
		this.sale_audit_pic = sale_audit_pic;
	}

	public Integer getSale_status() {
		return sale_status;
	}

	public void setSale_status(Integer sale_status) {
		this.sale_status = sale_status;
	}

	public String getSale_phone() {
		return sale_phone;
	}

	public void setSale_phone(String sale_phone) {
		this.sale_phone = sale_phone;
	}

	public String getSale_nickname() {
		return sale_nickname;
	}

	public void setSale_nickname(String sale_nickname) {
		this.sale_nickname = sale_nickname;
	}

	public Float getSale_rate() {
		return sale_rate;
	}

	public void setSale_rate(Float sale_rate) {
		this.sale_rate = sale_rate;
	}

	public Timestamp getSale_time_create() {
		return sale_time_create;
	}

	public void setSale_time_create(Timestamp sale_time_create) {
		this.sale_time_create = sale_time_create;
	}

}
