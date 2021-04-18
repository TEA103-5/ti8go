package com.admins.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.admins.model.AdminsService;
import com.admins.model.AdminsVO;



public class AdminsAjaxHandler extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json ; charset=UTF-8"); 
        PrintWriter out = res.getWriter();
        
		String action = req.getParameter("action");


		
		if ("if_Admins_email_exist".equals(action)) {

//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			HashMap result = new HashMap();
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String admins_email = req.getParameter("admins_email").trim();

				/*************************** 2.開始查詢資料 *****************************************/
				AdminsService adminsSvc = new AdminsService();
				AdminsVO adminsVO = null;
				List<AdminsVO> list = adminsSvc.getAll();

				for(AdminsVO oneAdminsVO : list) {
					if(oneAdminsVO.getAdmins_email().equals(admins_email)) {

						result.put("result" , "exist");
						JSONObject resultJSON = new JSONObject(result);
						out.println(resultJSON);
						return;
					}
					
				}
				
				result.put("result" , "not_exist");
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);

		}
		
		if ("admins_login".equals(action)) {
			
			
			HashMap result = new HashMap();
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String admins_email = req.getParameter("admins_email").trim();
			
			String admins_password = req.getParameter("admins_password").trim();
			/*************************** 2.開始查詢資料 *****************************************/
			AdminsService adminsSvc = new AdminsService();
			AdminsVO adminsVO = null;
			List<AdminsVO> list = adminsSvc.getAll();
			
			
			for(AdminsVO oneAdminsVO : list) {
				if(oneAdminsVO.getAdmins_email().equals(admins_email) && oneAdminsVO.getAdmins_password().equals(admins_password) ) {
					req.getSession().setAttribute("adminsVO" , oneAdminsVO);
					result.put("result" , "login_success");
					JSONObject resultJSON = new JSONObject(result);
					out.println(resultJSON);
					return;
				}
				
			}
			
			result.put("result" , "login_failure");
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			JSONObject resultJSON = new JSONObject(result);
			out.println(resultJSON);
		}
		
		
	}

}
