package com.msg_n_c.model;

import java.util.List;

import com.msg_n.model.Msg_nVO;

public interface Msg_n_cDAO_interface {
	   public void insert(Msg_n_cVO msg_n_cVO);
	    public void update(Msg_n_cVO msg_n_cVO);
	    public void delete(Integer msg_n_c_id);
	    public Msg_n_cVO findByPrimaryKey(Integer msg_n_c_id);
	    public List<Msg_n_cVO> getAll();
}
