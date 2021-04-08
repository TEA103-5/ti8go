package com.admins.model;

import java.util.*;


public interface AdminsDAO_interface {
	public void insert(AdminsVO adminsVO) throws Exception;
	public void update(AdminsVO adminsVO) throws Exception;
	public void delete(Integer admins_id);
	public AdminsVO findByPK(Integer admins_id);
	public List<AdminsVO> getAll();
}
