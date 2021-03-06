package com.trip.model;

import java.sql.Date;
import java.sql.Timestamp;

public class TripVO { 
	private Integer	trip_id;
	private Integer	users_id;
	private Timestamp	trip_create_time;
	private Timestamp	last_edit_time;
	private Integer	last_editor;
	private Integer	trip_state;
	private Integer	trip_like;
	private Integer	trip_look;
	private Integer	read_authority;
	private Integer	edit_authority;
	private String	trip_area;
	private Date	trip_start;
	private Date	trip_end;
	private String	trip_name;
	private String	trip_description;
	private String	trip_type;
	private Integer	trip_tot_cost;
	private String	place_weather;

	
	public TripVO() {
	}
	public TripVO(Integer trip_id, Integer users_id, Timestamp trip_create_time, Timestamp last_edit_time,
			Integer last_editor, Integer trip_state, Integer trip_like, Integer trip_look, Integer read_authority, Integer edit_authority,
			String trip_area, Date trip_start, Date trip_end, String trip_name, String trip_description,
			String trip_type, Integer trip_tot_cost, String place_weather) {
		super();
		this.trip_id = trip_id;
		this.users_id = users_id;
		this.trip_create_time = trip_create_time;
		this.last_edit_time = last_edit_time;
		this.last_editor = last_editor;
		this.trip_state = trip_state;
		this.trip_like = trip_like;
		this.read_authority = read_authority;
		this.edit_authority = edit_authority;
		this.trip_area = trip_area;
		this.trip_start = trip_start;
		this.trip_end = trip_end;
		this.trip_name = trip_name;
		this.trip_description = trip_description;
		this.trip_type = trip_type;
		this.trip_tot_cost = trip_tot_cost;
		this.place_weather = place_weather;
		this.trip_look = trip_look;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Integer getUsers_id() {
		return users_id;
	}
	public void setUsers_id(Integer users_id) {
		this.users_id = users_id;
	}
	public Timestamp getTrip_create_time() {
		return trip_create_time;
	}
	public void setTrip_create_time(Timestamp trip_create_time) {
		this.trip_create_time = trip_create_time;
	}
	public Timestamp getLast_edit_time() {
		return last_edit_time;
	}
	public void setLast_edit_time(Timestamp last_edit_time) {
		this.last_edit_time = last_edit_time;
	}
	public Integer getLast_editor() {
		return last_editor;
	}
	public void setLast_editor(Integer last_editor) {
		this.last_editor = last_editor;
	}
	public Integer getTrip_state() {
		return trip_state;
	}
	public void setTrip_state(Integer trip_state) {
		this.trip_state = trip_state;
	}
	public Integer getTrip_like() {
		return trip_like;
	}
	public void setTrip_like(Integer trip_like) {
		this.trip_like = trip_like;
	}
	public Integer getRead_authority() {
		return read_authority;
	}
	public void setRead_authority(Integer read_authority) {
		this.read_authority = read_authority;
	}
	public Integer getEdit_authority() {
		return edit_authority;
	}
	public void setEdit_authority(Integer edit_authority) {
		this.edit_authority = edit_authority;
	}
	public String getTrip_area() {
		return trip_area;
	}
	public void setTrip_area(String trip_area) {
		this.trip_area = trip_area;
	}
	public Date getTrip_start() {
		return trip_start;
	}
	public void setTrip_start(Date trip_start) {
		this.trip_start = trip_start;
	}
	public Date getTrip_end() {
		return trip_end;
	}
	public void setTrip_end(Date trip_end) {
		this.trip_end = trip_end;
	}
	public String getTrip_name() {
		return trip_name;
	}
	public void setTrip_name(String trip_name) {
		this.trip_name = trip_name;
	}
	public String getTrip_description() {
		return trip_description;
	}
	public void setTrip_description(String trip_description) {
		this.trip_description = trip_description;
	}
	public String getTrip_type() {
		return trip_type;
	}
	public void setTrip_type(String trip_type) {
		this.trip_type = trip_type;
	}
	public Integer getTrip_tot_cost() {
		return trip_tot_cost;
	}
	public void setTrip_tot_cost(Integer trip_tot_cost) {
		this.trip_tot_cost = trip_tot_cost;
	}
	public String getPlace_weather() {
		return place_weather;
	}
	public void setPlace_weather(String place_weather) {
		this.place_weather = place_weather;
	}
	public Integer getTrip_look() {
		return trip_look;
	}
	public void setTrip_look(Integer trip_look) {
		this.trip_look = trip_look;
	}
	
	

}
