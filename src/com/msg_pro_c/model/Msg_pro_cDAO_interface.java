package com.msg_pro_c.model;

import java.util.List;

public interface Msg_pro_cDAO_interface {
	   public void insert(Msg_pro_cVO msg_pro_cVO);
	   public void insertBySale(Msg_pro_cVO msg_pro_cVO);
	    public void update(Msg_pro_cVO msg_pro_cVO);
	    public void delete(Integer msg_pro_c_id);
	    public Msg_pro_cVO findByPrimaryKey(Integer msg_pro_c_id);
	    public List<Msg_pro_cVO> getAll();
}
