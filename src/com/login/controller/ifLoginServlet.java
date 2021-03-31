package com.login.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.model.LoginService;
import com.sale.model.SaleService;
import com.sale.model.SaleVO;
import com.users.model.UsersService;
import com.users.model.UsersVO;

/**
 * Servlet implementation class ifLoginServlet
 */
//@WebServlet("/ifLoginServlet")
public class ifLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ifLoginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String url=req.getParameter("requestURL");
		
		req.getSession().invalidate();
		
		RequestDispatcher failureView = req
				.getRequestDispatcher(url);
		failureView.forward(req, res);	
	}
	
	// 1.創帳號 2.登入 3.判斷是否登入
	//1.的時候要判斷帳號與3個TABLE中的帳號有無重複
	//2.要判斷登入者的身分 在session中放入資料 並 將其導向各自的網頁
	//3.若session中沒有對應的資料 將其導向登入頁 3似乎比較適合用doget?
	
	//4.資安? 先不考慮
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if ("login".equals(action)){
		String id = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		LoginService lgSrc=new LoginService();
		String url=req.getParameter("requestURL");
		//這邊是傳帳密進去,找type之後順便驗證密碼 密碼不對也回傳null,有東西則回傳 帳號type,pk號碼 因為懶得用set 所以用saleVO來接
		SaleVO uVO=null;
				uVO=lgSrc.checkIdType(id,pwd);
		if(uVO!=null) {
			
			if("users".equals(uVO.getSale_name())) {
				UsersService uSrc=new UsersService();
				req.getSession().setAttribute("usersVO", uSrc.getOneusers(uVO.getSale_id()));
				url="/front-end/index.jsp";
			}else if("sale".equals(uVO.getSale_name())) {
				SaleService sSrc=new SaleService();
				req.getSession().setAttribute("saleVO",sSrc.getOneSale(uVO.getSale_id()));
				url="/sale-end/saleEndIndex.jsp";
			}else if("admins".equals(uVO.getSale_name())) {
				//缺
				//缺
				url="/back-end/backindex.jsp";
			}
		}else {
			//System.out.println(uVO);
			errorMsgs.add("帳號或密碼有問題");
		}
		
		RequestDispatcher failureView = req
				.getRequestDispatcher(url);
		failureView.forward(req, res);	
		}
		
	}

}
