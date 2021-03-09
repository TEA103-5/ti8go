package com.msg_t.model;

import java.util.List;

public interface Msg_tDAO_interface {
	 public void insert(Msg_tVO msg_tVO);
	    public void update(Msg_tVO msg_tVO);
	    public void delete(Integer msg_t_id);
	    public Msg_tVO findByPrimaryKey(Integer msg_t_id);
	    public List<Msg_tVO> getAll();
	    
}
