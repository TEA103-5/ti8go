package com.login.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.admins.model.AdminsService;
import com.login.model.LoginService;
import com.orderDetail.model.OrderDetailVO;
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
		String url="/front-end/order/addOrder.jsp";
				if(req.getParameter("requestURL")!=null) {
					
					url=req.getParameter("requestURL");
				}
		
		req.getSession().invalidate();
		
		RequestDispatcher failureView = req
				.getRequestDispatcher(url);
		failureView.forward(req, res);	
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json ; charset=UTF-8"); 
		String action = req.getParameter("action");
		String url=req.getParameter("requestURL");
		
		
		
		Cookie[] cookies = req.getCookies();


		for (int i = 0; i < cookies.length; i++) {
		    Cookie c = cookies[i];
		    System.out.println(c.getName() + c.getValue() );
		      //cookie name
		     //cookie value

		  }
		
		
		
		
		
		
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
		if ("userlogin".equals(action)){
			   Cookie cookie = new Cookie("auser", "acaterpillar");
               cookie.setMaxAge(7 * 24 * 60 * 60);
               res.addCookie(cookie);
			String uid = req.getParameter("u_id").trim();
			String pwd = req.getParameter("pwd").trim();
		
			
			SaleVO uVO=null;
			uVO=lgSrc.usersLogin(uid, pwd);
			if(uVO!=null) {

				UsersService uSrc=new UsersService();

				result.put("hasVO" , false);
				result.put("uVO", true);
				result.put("uid" ,  uVO.getSale_id());
				req.getSession().invalidate();
				req.getSession().setAttribute("usersVO", uSrc.getOneusers(uVO.getSale_id()));	

			}else {
				errorMsgs.add("帳號或密碼有問題");
			}
			if(!errorMsgs.isEmpty()) {
				result.put("errormsg" , errorMsgs);
			}
//			Integer arraysize =null;
//			arraysize = new Integer(req.getParameter("arraysize").trim());
			if(url!=null) {
//				Integer arraysize = new Integer(req.getParameter("arraysize").trim());
//				System.out.println(arraysize);
//				if(arraysize>0) {
//					List<OrderDetailVO> list=new ArrayList<OrderDetailVO>();
//					OrderDetailVO ovo;
//					System.out.println("array:"+req.getParameter("array[0][productName]"));
//					String uids = req.getParameter("array");
//					String[] uuid = uids.split(",");
//					for(int i=0;i<uuid.length;i++ ){
//					System.out.println("uuid["+ i +"]="+ uuid[i]);
//					String[] uuids=uuid[i].split(",");
//					for(int j=0;j<uuids.length;j++ ){
//						System.out.println("uuids["+ i +"]="+ uuids[j]);
//					}
//					}
//					for(int i=0;i<arraysize;i++) {
//						ovo=new OrderDetailVO();
//						ovo.setProduct_id(new Integer(req.getParameter("array["+i+"][name]").trim()));
//						ovo.setOrder_detail_count(new Integer(req.getParameter("array["+i+"][count]").trim()));
//						list.add(ovo);
//					}
//					req.setAttribute("dlist", list);
//					req.setAttribute("array", req.getParameter("array"));
					
//				}
				
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);	
			}else {
				PrintWriter out = res.getWriter();
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
				
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/order/addOrder.jsp");
//				failureView.forward(req, res);	
			}
		}
		
		if ("check".equals(action)){
			String uid = req.getParameter("u_id").trim();
			String pwd = req.getParameter("pwd").trim();
			PrintWriter out = res.getWriter();
		
			SaleVO uVO=null;
			uVO=lgSrc.checkIdType(uid,pwd);
			if(uVO!=null) {
				
			result.put("hasVO" , false);
			if("users".equals(uVO.getSale_name())) {
				result.put("uVO", true);
				result.put("uid" ,  uVO.getSale_id());
			}
				
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

	
		String id = req.getParameter("email");
		String pwd = req.getParameter("pwd");
		
		SaleVO uVO=null;
		uVO=lgSrc.checkIdType(id,pwd);
		if(uVO!=null) {
			
			if("users".equals(uVO.getSale_name())) {
				UsersService uSrc=new UsersService();
				req.getSession().setAttribute("usersVO", uSrc.getOneusers(uVO.getSale_id()));
			if("/front-end/order/addOrder.jsp".equals(url)) {
					
				}else {
					url="/front-end/index.jsp";
				}
			}else if("sale".equals(uVO.getSale_name())) {
				SaleService sSrc=new SaleService();
				req.getSession().setAttribute("saleVO",sSrc.getOneSale(uVO.getSale_id()));
	
				url="/sale-end/saleEndIndex.jsp";
			}else if("admins".equals(uVO.getSale_name())) {
				AdminsService adminsSrc = new AdminsService();
				req.getSession().setAttribute("adminsVO",adminsSrc.getOneadmins(uVO.getSale_id()));
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
