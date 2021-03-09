package com.msg_pro.model;

import java.util.List;

public interface Msg_proDAO_interface {
    public void insert(Msg_proVO msg_proVO);
    public void update(Msg_proVO msg_proVO);
    public void delete(Integer msg_pro_id);
    public Msg_proVO findByPrimaryKey(Integer msg_pro_id);
    public List<Msg_proVO> getAll();
}
