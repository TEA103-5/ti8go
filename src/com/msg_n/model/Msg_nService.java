package com.msg_n.model;

import java.util.List;

public class Msg_nService {
	private Msg_nDAO_interface dao;
	public Msg_nService(){
		dao=new Msg_nDAO();
	}
	
	//新增
	public Msg_nVO addMsg_n(String msg_n_text,Integer users_id,Integer note_id) {
		Msg_nVO msg_nVO=new Msg_nVO();
		
		msg_nVO.setMsg_n_text(msg_n_text);
		msg_nVO.setUsers_id(users_id);
		msg_nVO.setNote_id(note_id);
		
		dao.insert(msg_nVO);
		return msg_nVO;
	}
	//修改
	public Msg_nVO updateMsg_n(String msg_n_text,Integer msg_n_id) {
		Msg_nVO msg_nVO=new Msg_nVO();
		
		msg_nVO.setMsg_n_text(msg_n_text);
		msg_nVO.setMsg_n_id(msg_n_id);
		
		dao.update(msg_nVO);
		return msg_nVO;
	}
	//刪除
	public void deleteMsg_n(Integer msg_n_id) {
		dao.delete(msg_n_id);
	}
	//查詢一筆
	public Msg_nVO getOneMsg_n(Integer msg_n_id) {
		return dao.findByPrimaryKey(msg_n_id);
	}
	//查詢全部
	public List<Msg_nVO> getAll() {
		return dao.getAll();
	}
}
