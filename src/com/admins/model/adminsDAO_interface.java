package com.admins.model;

import java.util.*;


public interface adminsDAO_interface {
	public void insert(adminsVO adminsVO);
	public void update(adminsVO adminsVO);
	public void delete(Integer admins_id);
	public adminsVO findByPK(Integer admins_id);
	public List<adminsVO> getAll();
}
