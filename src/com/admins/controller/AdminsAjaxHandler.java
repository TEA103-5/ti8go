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

import util.MailService_google;
import util.TokenProcessor;



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
					
					if(req.getSession().getAttribute("location") != null) {
						result.put("location", req.getSession().getAttribute("location").toString() );
					}
					
					JSONObject resultJSON = new JSONObject(result);
					out.println(resultJSON);
					return;
				}
				
			}
			
			result.put("result" , "login_failure");
			

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			JSONObject resultJSON = new JSONObject(result);
			out.println(resultJSON);
			return;
		}
		
		
		
		if("forgotPassword".equals(action)) {
			
			HashMap result = new HashMap();
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String admins_email = req.getParameter("admins_email").trim();
			
System.out.println("輸入的信箱=" + admins_email);
			
			/*************************** 2.開始查詢資料 *****************************************/
			AdminsService adminsSvc = new AdminsService();
			AdminsVO adminsVO = null;
			List<AdminsVO> list = adminsSvc.getAll();
			
			for(AdminsVO oneAdminsVO : list) {
				if(oneAdminsVO.getAdmins_email().equals(admins_email)) {
					adminsVO = oneAdminsVO ;
				}
			}
			
			if(adminsVO == null) {
				// 即使不符還是回傳已寄出的結果, 但實際上並沒有寄信
				result.put("result" , "已寄出");
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
				return;
			}
			
System.out.println( "符合的信箱=" + adminsVO.getAdmins_email());
			
			Integer admins_id = adminsVO.getAdmins_id();
			admins_email = adminsVO.getAdmins_email();
			String admins_name = adminsVO.getAdmins_name();
			
System.out.println("舊密碼=" + adminsVO.getAdmins_password());
			TokenProcessor tokenProcessor = TokenProcessor.getInstance();
			String admins_password = tokenProcessor.makeToken();
System.out.println("新密碼=" + admins_password);
			
			//String admins_password = adminsVO.getAdmins_password();
			Integer admins_sex = adminsVO.getAdmins_sex();
			String admins_authority = adminsVO.getAdmins_authority();
			String admins_position = adminsVO.getAdmins_position();
						
			String to = admins_email;
			String subject = "密碼通知";
			String ch_name = admins_name;
			String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + admins_password + "\n" +" (已經啟用)"; 
			
			MailService_google mailService = new MailService_google();
			mailService.sendMail(to, subject, messageText);

try {
	adminsSvc.updateadmins(admins_id, admins_email, admins_name, admins_password, admins_sex, admins_authority, admins_position);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

			result.put("result" , "已寄出");
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			JSONObject resultJSON = new JSONObject(result);
			out.println(resultJSON);
			return;
		}
		
		
	}

}
