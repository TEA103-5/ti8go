package com.msg_p_c.model;

import java.util.List;


public interface Msg_p_cDAO_interface {
	  public void insert(Msg_p_cVO msg_p_cVO);
	    public void update(Msg_p_cVO msg_p_cVO);
	    public void delete(Integer msg_p_c_id);
	    public Msg_p_cVO findByPrimaryKey(Integer msg_p_c_id);
	    public List<Msg_p_cVO> getAll();
}
