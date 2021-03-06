package com.msg_p_c.model;

import java.util.List;

public class Msg_p_cService {
	private Msg_p_cDAO_interface dao;
	public Msg_p_cService(){
		dao=new Msg_p_cDAO();
	}
	
	//新增
	public Msg_p_cVO addMsg_p_c(String msg_p_c_text,Integer users_id,Integer msg_p_id) {
		Msg_p_cVO msg_p_cVO=new Msg_p_cVO();
		
		msg_p_cVO.setMsg_p_c_text(msg_p_c_text);
		msg_p_cVO.setUsers_id(users_id);
		msg_p_cVO.setMsg_p_id(msg_p_id);
		
		dao.insert(msg_p_cVO);
		return msg_p_cVO;
	}
	//修改
	public Msg_p_cVO updateMsg_p_c(String msg_p_c_text,Integer msg_p_c_id) {
		Msg_p_cVO msg_p_cVO=new Msg_p_cVO();
		
		msg_p_cVO.setMsg_p_c_text(msg_p_c_text);
		msg_p_cVO.setMsg_p_c_id(msg_p_c_id);
		
		dao.update(msg_p_cVO);
		return msg_p_cVO;
	}
	//刪除
	public void deleteMsg_p_c(Integer msg_p_c_id) {
		dao.delete(msg_p_c_id);
	}
	//查詢一筆
	public Msg_p_cVO getOneMsg_p_c(Integer msg_p_c_id) {
		return dao.findByPrimaryKey(msg_p_c_id);
	}
	//查詢全部
	public List<Msg_p_cVO> getAll() {
		return dao.getAll();
	}
}
