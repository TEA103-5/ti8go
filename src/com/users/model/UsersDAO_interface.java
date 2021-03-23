package com.users.model;

import java.util.*;

public interface UsersDAO_interface {
	public void insert(UsersVO usersVO)throws Exception;
	public void update(UsersVO usersVO)throws Exception;
	public void delete(Integer users_id);
	public UsersVO findByPK(Integer users_id);
	public List<UsersVO> getAll();
}
