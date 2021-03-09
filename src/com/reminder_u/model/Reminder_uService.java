package com.reminder_u.model;

import java.util.List;


public class Reminder_uService {
	private Reminder_uDAO_interface dao;
	public Reminder_uService(){
		dao=new Reminder_uDAO();
	}
	
	//新增
	public Reminder_uVO addReminder_u(String reminder_u_text,Integer users_id) {
		Reminder_uVO reminder_uVO=new Reminder_uVO();
		
		reminder_uVO.setReminder_u_text(reminder_u_text);
		reminder_uVO.setUsers_id(users_id);

		
		dao.insert(reminder_uVO);
		return reminder_uVO;
	}
	//修改
	public Reminder_uVO updateReminder_u(Integer reminder_u_status,Integer reminder_u_id) {
		Reminder_uVO reminder_uVO=new Reminder_uVO();
		
		reminder_uVO.setReminder_u_id(reminder_u_id);
		reminder_uVO.setReminder_u_status(reminder_u_status);
		
		dao.update(reminder_uVO);
		return reminder_uVO;
	}
	//刪除
	public void deleteReminder_u(Integer reminder_u_id) {
		dao.delete(reminder_u_id);
	}
	//查詢一筆
	public Reminder_uVO getOneReminder_u(Integer reminder_u_id) {
		return dao.findByPrimaryKey(reminder_u_id);
	}
	//查詢全部
	public List<Reminder_uVO> getAll() {
		return dao.getAll();
	}
}
