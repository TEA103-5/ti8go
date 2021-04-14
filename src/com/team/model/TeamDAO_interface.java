package com.team.model;

import java.util.List;


public interface TeamDAO_interface {
	public void insert(TeamVO taamVO);
    public void update(Integer trip_id, Integer users_id , Integer users_id1);
    public void delete(Integer trip_id, Integer users_id);
    public TeamVO findByPrimaryKey(Integer trip_id, Integer users_id);
    public List<TeamVO> getAll();
    
    public void insert2 (TeamVO teamVO , java.sql.Connection con);
}
