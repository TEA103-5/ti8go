package com.card.model;

public class CardVO implements java.io.Serializable {
	private Integer card_id;
	private Integer users_id;
	private String card_number;
	private String card_date;
	private String card_last;
	private Integer card_default;
	
	
	@Override
	public String toString() {
		return "cardVO [users_id=" + users_id + ", card_number=" + card_number + ", card_date=" + card_date
				+ ", card_last=" + card_last + ", card_default=" + card_default + "]";
	}
	
	
	
	public Integer getCard_id() {
		return card_id;
	}



	public void setCard_id(Integer card_id) {
		this.card_id = card_id;
	}



	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCard_date() {
		return card_date;
	}
	public void setCard_date(String card_date) {
		this.card_date = card_date;
	}
	public String getCard_last() {
		return card_last;
	}
	public void setCard_last(String card_last) {
		this.card_last = card_last;
	}
	public Integer getCard_default() {
		return card_default;
	}
	public void setCard_default(Integer card_default) {
		this.card_default = card_default;
	}
	
	
}
