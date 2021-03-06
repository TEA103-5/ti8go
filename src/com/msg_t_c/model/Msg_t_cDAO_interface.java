package com.msg_t_c.model;

import java.util.List;


public interface Msg_t_cDAO_interface {
	  public void insert(Msg_t_cVO msg_t_cVO);
	    public void update(Msg_t_cVO msg_t_cVO);
	    public void delete(Integer msg_t_c_id);
	    public Msg_t_cVO findByPrimaryKey(Integer msg_t_c_id);
	    public List<Msg_t_cVO> getAll();
}
