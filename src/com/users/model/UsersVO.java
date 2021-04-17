package com.users.model;

import java.util.Arrays;

public class UsersVO implements java.io.Serializable{
	private Integer users_id;
	private String 	users_mail;
	private String 	users_pwd;
	private Integer users_status;
	private String 	users_nickname;
	private String 	users_name;
	private Integer users_sex;
	private String 	users_birthday;
	private String 	users_id_number;
	private byte[]	users_users_pic;
	private String  users_phone;
	private String  create_time;
	private String  update_time;
	
	
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "UsersVO [users_id=" + users_id + ", users_mail=" + users_mail + ", users_pwd=" + users_pwd
				+ ", users_status=" + users_status + ", users_nickname=" + users_nickname + ", users_name=" + users_name
				+ ", users_sex=" + users_sex + ", users_birthday=" + users_birthday + ", users_id_number="
				+ users_id_number + ", users_phone=" + users_phone + ", create_time=" + create_time + ", update_time="
				+ update_time + "]";
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public String getUsers_mail() {
		return users_mail;
	}
	public void setUsers_mail(String users_mail) {
		this.users_mail = users_mail;
	}
	public String getUsers_pwd() {
		return users_pwd;
	}
	public void setUsers_pwd(String users_pwd) {
		this.users_pwd = users_pwd;
	}
	public Integer getUsers_status() {
		return users_status;
	}
	public void setUsers_status(Integer users_status) {
		this.users_status = users_status;
	}
	public String getUsers_nickname() {
		return users_nickname;
	}
	public void setUsers_nickname(String users_nickname) {
		this.users_nickname = users_nickname;
	}
	public String getUsers_name() {
		return users_name;
	}
	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}
	public Integer getUsers_sex() {
		return users_sex;
	}
	public void setUsers_sex(Integer users_sex) {
		this.users_sex = users_sex;
	}
	public String getUsers_birthday() {
		return users_birthday;
	}
	public void setUsers_birthday(String users_birthday) {
		this.users_birthday = users_birthday;
	}
	public String getUsers_id_number() {
		return users_id_number;
	}
	public void setUsers_id_number(String users_id_number) {
		this.users_id_number = users_id_number;
	}
	public byte[] getUsers_users_pic() {
		return users_users_pic;
	}
	public void setUsers_users_pic(byte[] users_users_pic) {
		this.users_users_pic = users_users_pic;
	}
	public String getUsers_phone() {
		return users_phone;
	}
	public void setUsers_phone(String users_phone) {
		this.users_phone = users_phone;
	}
	
}
