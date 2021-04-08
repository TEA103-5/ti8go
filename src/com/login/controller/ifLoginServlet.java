package com.login.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Base64;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.admins.model.AdminsService;
import com.login.model.LoginService;
import com.sale.model.SaleService;
import com.sale.model.SaleVO;
import com.users.model.UsersService;
import com.users.model.UsersVO;



/**
 * Servlet implementation class ifLoginServlet
 */
//@WebServlet("/ifLoginServlet")
//@MultipartConfig
public class ifLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ifLoginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String url=req.getParameter("requestURL");
		
		req.getSession().	invalidate();
		
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
		res.setContentType("application/json ; charset=UTF-8"); 
		String action = req.getParameter("action");
//		String url=req.getParameter("requestURL");
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HashMap result = new HashMap();
		LoginService lgSrc=new LoginService();
//		errorMsgs.add("帳號或密碼有問題");
//		errorMsgs.add("帳號或密碼有問題2");
//		result.put("errormsg" , errorMsgs);
//		System.out.println(req.getParameter("confirmPassword"));
//		PrintWriter out = res.getWriter();
//		JSONObject resultJSON = new JSONObject(result);
//		out.println(resultJSON);
		
		if ("check".equals(action)){
			String uid = req.getParameter("u_id").trim();
			String pwd = req.getParameter("pwd").trim();
			PrintWriter out = res.getWriter();
		
			SaleVO uVO=null;
			uVO=lgSrc.checkIdType(uid,pwd);
			if(uVO!=null) {
				
			result.put("hasVO" , false);
				
			}else {
				errorMsgs.add("帳號或密碼有問題");
			}
			if(!errorMsgs.isEmpty()) {
				result.put("errormsg" , errorMsgs);
			}
			JSONObject resultJSON = new JSONObject(result);
			out.println(resultJSON);
		}
		if ("login".equals(action)){
			String url=req.getParameter("requestURL");
	
		String id = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
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
				AdminsService adminsSrc = new AdminsService();
				req.getSession().setAttribute("adminsVO", adminsSrc.getOneadmins(uVO.getSale_id()));
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
