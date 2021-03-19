package com.users.model;

public class UsersVO implements java.io.Serializable{
	private Integer users_id;
	private String 	users_mail;
	private String 	users_pwd;
	private Integer users_status;
	private String 	users_nickname;
	private String 	users_name;
	private Integer uesrs_sex;
	private String 	uesrs_birthday;
	private String 	users_id_number;
	private Byte[]	users_users_pic;
	private String  user_phone;
	
	
	
	@Override
	public String toString() {
		return "usersVO [users_id=" + users_id + ", users_mail=" + users_mail + ", users_pwd=" + users_pwd
				+ ", users_status=" + users_status + ", users_nickname=" + users_nickname + ", users_name=" + users_name
				+ ", uesrs_sex=" + uesrs_sex + ", uesrs_birthday=" + uesrs_birthday + ", users_id_number="
				+ users_id_number + ", user_phone=" + user_phone + "]";
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
	public Integer getUesrs_sex() {
		return uesrs_sex;
	}
	public void setUesrs_sex(Integer uesrs_sex) {
		this.uesrs_sex = uesrs_sex;
	}
	public String getUesrs_birthday() {
		return uesrs_birthday;
	}
	public void setUesrs_birthday(String uesrs_birthday) {
		this.uesrs_birthday = uesrs_birthday;
	}
	public String getUsers_id_number() {
		return users_id_number;
	}
	public void setUsers_id_number(String users_id_number) {
		this.users_id_number = users_id_number;
	}
	public Byte[] getUsers_users_pic() {
		return users_users_pic;
	}
	public void setUsers_users_pic(Byte[] users_users_pic) {
		this.users_users_pic = users_users_pic;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	
	
}
