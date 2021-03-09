package com.reminder_a.model;

import java.util.List;





public class Reminder_aService {
	private Reminder_aDAO_interface dao;
	public Reminder_aService(){
		dao=new Reminder_aDAO();
	}
	
	//新增
	public Reminder_aVO addReminder_a(String reminder_a_text,Integer admins_id) {
		Reminder_aVO reminder_aVO=new Reminder_aVO();
		
		reminder_aVO.setReminder_a_text(reminder_a_text);
		reminder_aVO.setAdmins_id(admins_id);

		
		dao.insert(reminder_aVO);
		return reminder_aVO;
	}
	//修改
	public Reminder_aVO updateReminder_a(Integer reminder_a_status,Integer reminder_a_id) {
		Reminder_aVO reminder_aVO=new Reminder_aVO();
		
		reminder_aVO.setReminder_a_id(reminder_a_id);
		reminder_aVO.setReminder_a_status(reminder_a_status);
		
		dao.update(reminder_aVO);
		return reminder_aVO;
	}
	//刪除
	public void deleteReminder_a(Integer reminder_a_id) {
		dao.delete(reminder_a_id);
	}
	//查詢一筆
	public Reminder_aVO getOneReminder_a(Integer reminder_a_id) {
		return dao.findByPrimaryKey(reminder_a_id);
	}
	//查詢全部
	public List<Reminder_aVO> getAll() {
		return dao.getAll();
	}
}
