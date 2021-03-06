package com.msg_t_c.model;

import java.util.List;

import com.msg_n_c.model.Msg_n_cDAO;
import com.msg_n_c.model.Msg_n_cDAO_interface;
import com.msg_n_c.model.Msg_n_cVO;

public class Msg_t_cService {
	private Msg_t_cDAO_interface dao;
	public Msg_t_cService(){
		dao=new Msg_t_cDAO();
	}
	
	//新增
	public Msg_t_cVO addMsg_t_c(String msg_t_c_text,Integer users_id,Integer msg_t_id) {
		Msg_t_cVO msg_t_cVO=new Msg_t_cVO();
		
		msg_t_cVO.setMsg_t_c_text(msg_t_c_text);
		msg_t_cVO.setUsers_id(users_id);
		msg_t_cVO.setMsg_t_id(msg_t_id);
		
		dao.insert(msg_t_cVO);
		return msg_t_cVO;
	}
	//修改
	public Msg_t_cVO updateMsg_t_c(String msg_t_c_text,Integer msg_t_c_id) {
		Msg_t_cVO msg_t_cVO=new Msg_t_cVO();
		
		msg_t_cVO.setMsg_t_c_text(msg_t_c_text);
		msg_t_cVO.setMsg_t_c_id(msg_t_c_id);
		
		dao.update(msg_t_cVO);
		return msg_t_cVO;
	}
	//刪除
	public void deleteMsg_t_c(Integer msg_t_c_id) {
		dao.delete(msg_t_c_id);
	}
	//查詢一筆
	public Msg_t_cVO getOneMsg_t_c(Integer msg_t_c_id) {
		return dao.findByPrimaryKey(msg_t_c_id);
	}
	//查詢全部
	public List<Msg_t_cVO> getAll() {
		return dao.getAll();
	}
}
