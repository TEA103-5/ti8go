package com.reminder_u.model;

import java.util.List;

public interface Reminder_uDAO_interface {
	public void insert(Reminder_uVO reminder_uVO);
	public void update(Reminder_uVO reminder_uVO);
	public void delete(Integer reminder_u_id);
	public Reminder_uVO findByPrimaryKey(Integer reminder_u_id);
	public List<Reminder_uVO> getAll();
}
