package com.admins.model;

public class AdminsVO {
	private Integer admins_id;
	private String admins_email;
	private String admins_name;
	private String admins_password;
	private Integer admins_sex;
	private String admins_authority;
	private String admins_position;
	private String admins_create_time;
	private String admins_edit_time;
	
	
	@Override
	public String toString() {
		return "AdminsVO [admins_id=" + admins_id + ", admins_email=" + admins_email + ", admins_name=" + admins_name
				+ ", admins_password=" + admins_password + ", admins_sex=" + admins_sex + ", admins_authority="
				+ admins_authority + ", admins_position=" + admins_position + ", admins_create_time="
				+ admins_create_time + ", admins_edit_time=" + admins_edit_time + "]";
	}


	public Integer getAdmins_id() {
		return admins_id;
	}


	public void setAdmins_id(Integer admins_id) {
		this.admins_id = admins_id;
	}


	public String getAdmins_email() {
		return admins_email;
	}


	public void setAdmins_email(String admins_email) {
		this.admins_email = admins_email;
	}


	public String getAdmins_name() {
		return admins_name;
	}


	public void setAdmins_name(String admins_name) {
		this.admins_name = admins_name;
	}


	public String getAdmins_password() {
		return admins_password;
	}


	public void setAdmins_password(String admins_password) {
		this.admins_password = admins_password;
	}


	public Integer getAdmins_sex() {
		return admins_sex;
	}


	public void setAdmins_sex(Integer admins_sex) {
		this.admins_sex = admins_sex;
	}


	public String getAdmins_authority() {
		return admins_authority;
	}


	public void setAdmins_authority(String admins_authority) {
		this.admins_authority = admins_authority;
	}


	public String getAdmins_position() {
		return admins_position;
	}


	public void setAdmins_position(String admins_position) {
		this.admins_position = admins_position;
	}


	public String getAdmins_create_time() {
		return admins_create_time;
	}


	public void setAdmins_create_time(String admins_create_time) {
		this.admins_create_time = admins_create_time;
	}


	public String getAdmins_edit_time() {
		return admins_edit_time;
	}


	public void setAdmins_edit_time(String admins_edit_time) {
		this.admins_edit_time = admins_edit_time;
	}
		

}
