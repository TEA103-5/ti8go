package com.admins.model;

import java.util.*;


public interface AdminsDAO_interface {
	public void insert(AdminsVO adminsVO);
	public void update(AdminsVO adminsVO);
	public void delete(Integer admins_id);
	public AdminsVO findByPK(Integer admins_id);
	public List<AdminsVO> getAll();
}
