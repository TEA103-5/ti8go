package com.reminder_a.model;

import java.util.List;



public interface Reminder_aDAO_interface {
	public void insert(Reminder_aVO reminder_aVO);
	public void update(Reminder_aVO reminder_aVO);
	public void delete(Integer reminder_a_id);
	public Reminder_aVO findByPrimaryKey(Integer reminder_a_id);
	public List<Reminder_aVO> getAll();
}
