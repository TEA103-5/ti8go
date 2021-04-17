package com.notec.model;

public class NoteCVO implements java.io.Serializable {
	private Integer note_c_id;
	private String note_c_title;
	private String note_c_content;
	private byte[] note_c_img;
	private Integer note_id;
	

	public NoteCVO() {
		
	}
	
	@Override
	public String toString() {
		return "NoteCVO：note_c_id=" + note_c_id 
				+ ", note_c_title=" + note_c_title 
				+ ", note_c_content=" + note_c_content
				+ ", note_c_img=" + note_c_img 
				+ ", note_id=" + note_id;
	}

	

	public Integer getNote_c_id() {
		return note_c_id;
	}
	public void setNote_c_id(Integer note_c_id) {
		this.note_c_id = note_c_id;
	}
	public String getNote_c_title() {
		return note_c_title;
	}
	public void setNote_c_title(String note_c_title) {
		this.note_c_title = note_c_title;
	}
	public String getNote_c_content() {
		return note_c_content;
	}
	public void setNote_c_content(String note_c_content) {
		this.note_c_content = note_c_content;
	}
	public byte[] getNote_c_img() {
		return note_c_img;
	}
	public void setNote_c_img(byte[] note_c_img) {
		this.note_c_img = note_c_img;
	}
	public Integer getNote_id() {
		return note_id;
	}
	public void setNote_id(Integer note_id) {
		this.note_id = note_id;
	}
	
}
