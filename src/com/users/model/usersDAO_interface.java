package com.users.model;

import java.util.*;

public interface usersDAO_interface {
	public void insert(usersVO usersVO);
	public void update(usersVO usersVO);
	public void delete(Integer users_id);
	public usersVO findByPK(Integer users_id);
	public List<usersVO> getAll();
}
