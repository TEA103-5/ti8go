package com.msg_t.model;

import java.util.List;



public class Msg_tService {
	private Msg_tDAO_interface dao;
	public Msg_tService(){
		dao=new Msg_tDAO();
	}
	
	//新增
	public Msg_tVO addMsg_t(String msg_t_text,Integer users_id,Integer trip_id) {
		Msg_tVO msg_tVO=new Msg_tVO();
		
		msg_tVO.setMsg_t_text(msg_t_text);
		msg_tVO.setUsers_id(users_id);
		msg_tVO.setTrip_id(trip_id);
		
		dao.insert(msg_tVO);
		return msg_tVO;
	}
	//修改
	public Msg_tVO updateMsg_t(String msg_t_text,Integer msg_t_id) {
		Msg_tVO msg_tVO=new Msg_tVO();
		
		msg_tVO.setMsg_t_text(msg_t_text);
		msg_tVO.setMsg_t_id(msg_t_id);
		
		dao.update(msg_tVO);
		return msg_tVO;
	}
	//刪除
	public void deleteMsg_t(Integer msg_t_id) {
		dao.delete(msg_t_id);
	}
	//查詢一筆
	public Msg_tVO getOneMsg_t(Integer msg_t_id) {
		return dao.findByPrimaryKey(msg_t_id);
	}
	//查詢全部
	public List<Msg_tVO> getAll() {
		return dao.getAll();
	}
}
