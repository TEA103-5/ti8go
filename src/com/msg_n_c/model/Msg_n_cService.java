package com.msg_n_c.model;

import java.util.List;

public class Msg_n_cService {
	private Msg_n_cDAO_interface dao;
	public Msg_n_cService(){
		dao=new Msg_n_cDAO();
	}
	
	//新增
	public Msg_n_cVO addMsg_n_c(String msg_n_c_text,Integer users_id,Integer msg_n_id) {
		Msg_n_cVO msg_n_cVO=new Msg_n_cVO();
		
		msg_n_cVO.setMsg_n_c_text(msg_n_c_text);
		msg_n_cVO.setUsers_id(users_id);
		msg_n_cVO.setMsg_n_id(msg_n_id);
		
		dao.insert(msg_n_cVO);
		return msg_n_cVO;
	}
	//修改
	public Msg_n_cVO updateMsg_n_c(String msg_n_c_text,Integer msg_n_c_id) {
		Msg_n_cVO msg_n_cVO=new Msg_n_cVO();
		
		msg_n_cVO.setMsg_n_c_text(msg_n_c_text);
		msg_n_cVO.setMsg_n_c_id(msg_n_c_id);
		
		dao.update(msg_n_cVO);
		return msg_n_cVO;
	}
	//刪除
	public void deleteMsg_n_c(Integer msg_n_c_id) {
		dao.delete(msg_n_c_id);
	}
	//查詢一筆
	public Msg_n_cVO getOneMsg_n_c(Integer msg_n_c_id) {
		return dao.findByPrimaryKey(msg_n_c_id);
	}
	//查詢全部
	public List<Msg_n_cVO> getAll() {
		return dao.getAll();
	}
}
