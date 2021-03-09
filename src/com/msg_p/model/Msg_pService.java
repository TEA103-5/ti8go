package com.msg_p.model;

import java.util.List;

public class Msg_pService {
	private Msg_pDAO_interface dao;
	public Msg_pService(){
		dao=new Msg_pDAO();
	}
	
	//新增
	public Msg_pVO addMsg_p(String msg_p_text,Integer users_id,Integer place_id) {
		Msg_pVO msg_pVO=new Msg_pVO();
		
		msg_pVO.setMsg_p_text(msg_p_text);
		msg_pVO.setUsers_id(users_id);
		msg_pVO.setPlace_id(place_id);
		
		dao.insert(msg_pVO);
		return msg_pVO;
	}
	//修改
	public Msg_pVO updateMsg_p(String msg_p_text,Integer msg_p_id) {
		Msg_pVO msg_pVO=new Msg_pVO();
		
		msg_pVO.setMsg_p_text(msg_p_text);
		msg_pVO.setMsg_p_id(msg_p_id);
		
		dao.update(msg_pVO);
		return msg_pVO;
	}
	//刪除
	public void deleteMsg_p(Integer msg_p_id) {
		dao.delete(msg_p_id);
	}
	//查詢一筆
	public Msg_pVO getOneMsg_p(Integer msg_p_id) {
		return dao.findByPrimaryKey(msg_p_id);
	}
	//查詢全部
	public List<Msg_pVO> getAll() {
		return dao.getAll();
	}
}
