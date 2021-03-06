package com.reminder_s.model;

import java.util.List;

public class Reminder_sService {
	private Reminder_sDAO_interface dao;
	public Reminder_sService(){
		dao=new Reminder_sDAO();
	}
	
	//新增
	public Reminder_sVO addReminder_s(String reminder_s_text,Integer sale_id) {
		Reminder_sVO reminder_sVO=new Reminder_sVO();
		
		reminder_sVO.setReminder_s_text(reminder_s_text);
		reminder_sVO.setSale_id(sale_id);

		
		dao.insert(reminder_sVO);
		return reminder_sVO;
	}
	//修改
	public Reminder_sVO updateReminder_s(Integer reminder_s_status,Integer reminder_s_id) {
		Reminder_sVO reminder_sVO=new Reminder_sVO();
		
		reminder_sVO.setReminder_s_id(reminder_s_id);
		reminder_sVO.setReminder_s_status(reminder_s_status);
		
		dao.update(reminder_sVO);
		return reminder_sVO;
	}
	//刪除
	public void deleteReminder_s(Integer reminder_s_id) {
		dao.delete(reminder_s_id);
	}
	//查詢一筆
	public Reminder_sVO getOneReminder_s(Integer reminder_s_id) {
		return dao.findByPrimaryKey(reminder_s_id);
	}
	//查詢全部
	public List<Reminder_sVO> getAll() {
		return dao.getAll();
	}
}
