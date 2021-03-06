package com.reminder_s.model;

import java.util.List;



public interface Reminder_sDAO_interface {
	public void insert(Reminder_sVO reminder_sVO);

	public void update(Reminder_sVO reminder_sVO);

	public void delete(Integer reminder_s_id);

	public Reminder_sVO findByPrimaryKey(Integer reminder_s_id);

	public List<Reminder_sVO> getAll();
}
