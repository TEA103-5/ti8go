package com.msg_n.model;

import java.util.List;

public interface Msg_nDAO_interface {
    public void insert(Msg_nVO msg_nVO);
    public void update(Msg_nVO msg_nVO);
    public void delete(Integer msg_n_id);
    public Msg_nVO findByPrimaryKey(Integer msg_n_id);
    public List<Msg_nVO> getAll();
}
