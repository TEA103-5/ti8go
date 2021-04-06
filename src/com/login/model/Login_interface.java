package com.login.model;

import com.sale.model.SaleVO;


public interface Login_interface {
//這邊需要做的事情是 1.判斷登入帳號的type 2.看創的帳號有無重複
	//所以總結為 傳回帳號的type 若為null則此帳號不重複
	public SaleVO loginType(String account,String pwd);
	public String idcheck(String account);
}
