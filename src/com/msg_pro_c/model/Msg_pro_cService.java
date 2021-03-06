package com.msg_pro_c.model;

import java.util.List;

public class Msg_pro_cService {
	private Msg_pro_cDAO_interface dao;
	public Msg_pro_cService(){
		dao=new Msg_pro_cDAO();
	}
	
	//新增
	public Msg_pro_cVO addMsg_pro_c(String msg_pro_c_text,Integer users_id,Integer msg_pro_id) {
		Msg_pro_cVO msg_pro_cVO=new Msg_pro_cVO();
		
		msg_pro_cVO.setMsg_pro_c_text(msg_pro_c_text);
		msg_pro_cVO.setUsers_id(users_id);
		msg_pro_cVO.setMsg_pro_id(msg_pro_id);
		
		dao.insert(msg_pro_cVO);
		return msg_pro_cVO;
	}
	//新增
	public Msg_pro_cVO addMsg_pro_cBySale(String msg_pro_c_text,Integer sale_id,Integer msg_pro_id) {
		Msg_pro_cVO msg_pro_cVO=new Msg_pro_cVO();
		
		msg_pro_cVO.setMsg_pro_c_text(msg_pro_c_text);
		msg_pro_cVO.setSale_id(sale_id);
		msg_pro_cVO.setMsg_pro_id(msg_pro_id);
		
		dao.insertBySale(msg_pro_cVO);
		return msg_pro_cVO;
	}
	//修改
	public Msg_pro_cVO updateMsg_pro_c(String msg_pro_c_text,Integer msg_pro_c_id) {
		Msg_pro_cVO msg_pro_cVO=new Msg_pro_cVO();
		
		msg_pro_cVO.setMsg_pro_c_text(msg_pro_c_text);
		msg_pro_cVO.setMsg_pro_c_id(msg_pro_c_id);
		
		dao.update(msg_pro_cVO);
		return msg_pro_cVO;
	}
	//刪除
	public void deleteMsg_n_c(Integer msg_pro_c_id) {
		dao.delete(msg_pro_c_id);
	}
	//查詢一筆
	public Msg_pro_cVO getOneMsg_pro_c(Integer msg_pro_c_id) {
		return dao.findByPrimaryKey(msg_pro_c_id);
	}
	//查詢全部
	public List<Msg_pro_cVO> getAll() {
		return dao.getAll();
	}
}
