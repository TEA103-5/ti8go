package com.login.model;

import com.sale.model.SaleVO;

public class LoginService {
	private Login_interface dao;
	public LoginService(){
		dao=new LoginDAO();
	}
	
	
	public SaleVO usersLogin(String id,String pwd) {
		return dao.usersLogin(id,pwd);
	}
	public SaleVO checkIdType(String id,String pwd) {
		return dao.loginType(id,pwd);
	}
	public String checkId(String id) {	
		return dao.idcheck(id);
	}
}
