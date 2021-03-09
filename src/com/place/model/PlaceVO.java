package com.place.model;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;

public class PlaceVO {

	private Integer place_id;
	private String place_name;
	private String place_address;
	private BigDecimal place_longitude;
	private BigDecimal place_latitude;
	private String place_tel;
	private String place_region;
	private String place_type;
	private String place_index;
	private InputStream place_pic1;   //  用這個型別ok嗎?
	private InputStream place_pic2;
	private InputStream place_pic3;
	private Integer place_state;
	private Integer users_id;
	private Integer business_time;   // 目前用不到, 

	
	public PlaceVO() {};
	
	public PlaceVO(Integer place_id, String place_name, String place_address, BigDecimal place_longitude,
			BigDecimal place_latitude, String place_tel, String place_region, String place_type, String place_index,
			InputStream place_pic1, InputStream place_pic2, InputStream place_pic3, Integer place_state, Integer users_id,
			Integer business_time) {
		super();
		this.place_id = place_id;
		this.place_name = place_name;
		this.place_address = place_address;
		this.place_longitude = place_longitude;
		this.place_latitude = place_latitude;
		this.place_tel = place_tel;
		this.place_region = place_region;
		this.place_type = place_type;
		this.place_index = place_index;
		this.place_pic1 = place_pic1;
		this.place_pic2 = place_pic2;
		this.place_pic3 = place_pic3;
		this.place_state = place_state;
		this.users_id = users_id;
		this.business_time = business_time;
	}

	public Integer getPlace_id() {
		return place_id;
	}

	public void setPlace_id(Integer place_id) {
		this.place_id = place_id;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getPlace_address() {
		return place_address;
	}

	public void setPlace_address(String place_address) {
		this.place_address = place_address;
	}

	public BigDecimal getPlace_longitude() {
		return place_longitude;
	}

	public void setPlace_longitude(BigDecimal place_longitude) {
		this.place_longitude = place_longitude;
	}

	public BigDecimal getPlace_latitude() {
		return place_latitude;
	}

	public void setPlace_latitude(BigDecimal place_latitude) {
		this.place_latitude = place_latitude;
	}

	public String getPlace_tel() {
		return place_tel;
	}

	public void setPlace_tel(String place_tel) {
		this.place_tel = place_tel;
	}

	public String getPlace_region() {
		return place_region;
	}

	public void setPlace_region(String place_region) {
		this.place_region = place_region;
	}

	public String getPlace_type() {
		return place_type;
	}

	public void setPlace_type(String place_type) {
		this.place_type = place_type;
	}

	public String getPlace_index() {
		return place_index;
	}

	public void setPlace_index(String place_index) {
		this.place_index = place_index;
	}

	public InputStream getPlace_pic1() {
		return place_pic1;
	}

	public void setPlace_pic1(InputStream place_pic1) {
		this.place_pic1 = place_pic1;
	}

	public InputStream getPlace_pic2() {
		return place_pic2;
	}

	public void setPlace_pic2(InputStream place_pic2) {
		this.place_pic2 = place_pic2;
	}

	public InputStream getPlace_pic3() {
		return place_pic3;
	}

	public void setPlace_pic3(InputStream place_pic3) {
		this.place_pic3 = place_pic3;
	}

	public Integer getPlace_state() {
		return place_state;
	}

	public void setPlace_state(Integer place_state) {
		this.place_state = place_state;
	}

	public Integer getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}

	public Integer getBusiness_time() {
		return business_time;
	}

	public void setBusiness_time(Integer business_time) {
		this.business_time = business_time;
	}

}
