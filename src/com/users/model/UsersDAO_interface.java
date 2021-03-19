package com.users.model;

import java.util.*;

public interface UsersDAO_interface {
	public void insert(UsersVO usersVO);
	public void update(UsersVO usersVO);
	public void delete(Integer users_id);
	public UsersVO findByPK(Integer users_id);
	public List<UsersVO> getAll();
}
