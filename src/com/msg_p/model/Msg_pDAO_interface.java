package com.msg_p.model;

import java.util.List;

public interface Msg_pDAO_interface {
    public void insert(Msg_pVO msg_pVO);
    public void update(Msg_pVO msg_pVO);
    public void delete(Integer msg_p_id);
    public Msg_pVO findByPrimaryKey(Integer msg_p_id);
    public List<Msg_pVO> getAll();
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<Msg_pVO> getAll(Map<String, String[]> map); 
}
