package com.msg_pro.model;

import java.util.List;

public class Msg_proService {
	private Msg_proDAO_interface dao;
	public Msg_proService(){
		dao=new Msg_proDAO();
	}
	
	//新增
	public Msg_proVO addMsg_pro(String msg_pro_text,Integer users_id,Integer product_id) {
		Msg_proVO msg_proVO=new Msg_proVO();
		
		msg_proVO.setMsg_pro_text(msg_pro_text);
		msg_proVO.setUsers_id(users_id);
		msg_proVO.setProduct_id(product_id);
		
		dao.insert(msg_proVO);
		return msg_proVO;
	}
	//修改
	public Msg_proVO updateMsg_pro(String msg_pro_text,Integer msg_pro_id) {
		Msg_proVO msg_proVO=new Msg_proVO();
		
		msg_proVO.setMsg_pro_text(msg_pro_text);
		msg_proVO.setMsg_pro_id(msg_pro_id);
		
		dao.update(msg_proVO);
		return msg_proVO;
	}
	//刪除
	public void deleteMsg_pro(Integer msg_pro_id) {
		dao.delete(msg_pro_id);
	}
	//查詢一筆
	public Msg_proVO getOneMsg_pro(Integer msg_pro_id) {
		return dao.findByPrimaryKey(msg_pro_id);
	}
	//查詢全部
	public List<Msg_proVO> getAll() {
		return dao.getAll();
	}
}
