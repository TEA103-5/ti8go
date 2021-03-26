package com.admins.controller;

import java.io.IOException;
import java.io.InputStream;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.admins.model.AdminsService;
import com.admins.model.AdminsVO;

public class AdminisServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			System.out.println("LOOK!");

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("admins_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer admins_id = null;
				try {
					admins_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdminsService adminsSvc = new AdminsService();
				AdminsVO adminsVO = adminsSvc.getOneadmins(admins_id);
				if (adminsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adminsVO", adminsVO); // 資料庫取出的empVO物件,存入req
				String url = "/admins/listOneAdmins.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/card/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		//--------------------------------------------------//
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer admins_id = new Integer(req.getParameter("admins_id"));
				
				/***************************2.開始刪除資料***************************************/
				AdminsService adminsSvc = new AdminsService();
				adminsSvc.deleteadmins(admins_id);
				System.out.println("DELETE TEST " + admins_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/admins/listAlladmins.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admins/listAlladmins.jsp");
				failureView.forward(req, res);
			}
		}
		
			if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String admins_name = req.getParameter("admins_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admins_name == null || admins_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!admins_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String admins_mail = req.getParameter("admins_mail").trim();
				if (admins_mail == null || admins_mail.trim().length() == 0) {
					errorMsgs.add("Mail請勿空白");
				}
				
				String admins_pwd = req.getParameter("admins_pwd").trim();
				if (admins_pwd == null || admins_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String admins_nickname = req.getParameter("admins_nickname").trim();
				if (admins_nickname == null || admins_nickname.trim().length() == 0) {
					errorMsgs.add("匿名請勿空白");
				}
				
				Integer admins_status = null ;
				try {
					admins_status = new Integer(req.getParameter("admins_status").trim());
				} catch (NumberFormatException e) {
					admins_status = 1;
					errorMsgs.add("狀態請填數字.");
				}
				

				Integer admins_sex = null ;
				try {
					admins_sex = new Integer(req.getParameter("admins_status").trim());
				} catch (NumberFormatException e) {
					admins_sex = 1;
					errorMsgs.add("性別請填數字.");
				}
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				String admins_birthday = req.getParameter("admins_birthday").trim();
				if (admins_birthday == null || admins_birthday.trim().length() == 0) {
					errorMsgs.add("生日請勿空白");
				}
				
				String admins_id_number = req.getParameter("admins_id_number").trim();
				if (admins_id_number == null || admins_id_number.trim().length() == 0) {
					errorMsgs.add("身分證請勿空白");
				}
				
				String admins_phone = req.getParameter("admins_phone").trim();
				if (admins_phone == null || admins_phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				Part part=req.getPart("admins_admins_pic");
			
				InputStream in = part.getInputStream();
				byte[] admins_admins_pic = new byte[in.available()];
				in.read(admins_admins_pic);
				in.close();
				
				
			//	Integer admins_id = new Integer(req.getParameter("admins_id").trim());

				AdminsVO adminsVO = new AdminsVO();
//				adminsVO.setadmins_mail(admins_mail);
//				adminsVO.setadmins_pwd(admins_pwd);
//				adminsVO.setadmins_status(admins_status);
//				adminsVO.setadmins_nickname(admins_nickname);
//				adminsVO.setadmins_name(admins_name);
//				adminsVO.setadmins_sex(admins_sex);
//				adminsVO.setadmins_birthday(admins_birthday);
//				adminsVO.setadmins_id_number(admins_id_number);
//				adminsVO.setadmins_admins_pic(admins_admins_pic);
//				adminsVO.setadmins_phone(admins_phone);
		//		adminsVO.setadmins_id(admins_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminsVO", adminsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/addadmins.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdminsService adminsSvc = new AdminsService();
//				adminsVO = adminsSvc.addadmins( admins_mail, admins_pwd, admins_status, 
//						admins_nickname, admins_name, admins_sex, admins_birthday, admins_id_number,
//						admins_admins_pic, admins_phone);			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/admins/listAlladmins.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admins/addadmins.jsp");
				failureView.forward(req, res);
			}
		}
		
			if ("getOne_For_Update".equals(action)) { //來自listAllEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer admins_id = new Integer(req.getParameter("admins_id"));
					
					/***************************2.開始查詢資料****************************************/
					AdminsService adminsSvc = new AdminsService();
					AdminsVO adminsVO = adminsSvc.getOneadmins(admins_id);
									
					/***************************3.查詢完成 準備轉交(Send the Success view)************/
					req.setAttribute("adminsVO", adminsVO);         // 資料庫取出的empVO物件,存入req
					String url = "/admins/update_admins_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/listAlladmins.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***************************1.接收請求參數-輸入格式的錯誤處理**********************/
					String admins_name = req.getParameter("admins_name");
					String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (admins_name == null || admins_name.trim().length() == 0) {
						errorMsgs.add("會員姓名: 請勿空白");
					} else if(!admins_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					String admins_mail = req.getParameter("admins_mail").trim();
					if (admins_mail == null || admins_mail.trim().length() == 0) {
						errorMsgs.add("Mail請勿空白");
					}
					
					String admins_pwd = req.getParameter("admins_pwd").trim();
					if (admins_pwd == null || admins_pwd.trim().length() == 0) {
						errorMsgs.add("密碼請勿空白");
					}
					
					String admins_nickname = req.getParameter("admins_nickname").trim();
					if (admins_nickname == null || admins_nickname.trim().length() == 0) {
						errorMsgs.add("匿名請勿空白");
					}
					
					Integer admins_status = null ;
					try {
						admins_status = new Integer(req.getParameter("admins_status").trim());
					} catch (NumberFormatException e) {
						admins_status = 1;
						errorMsgs.add("狀態請填數字.");
					}
					
					System.out.println(action + req.getParameter("admins_id"));

					Integer admins_sex = null ;
					try {
						admins_sex = new Integer(req.getParameter("admins_status").trim());
					} catch (NumberFormatException e) {
						admins_sex = 1;
						errorMsgs.add("性別請填數字.");
					}
//					java.sql.Date hiredate = null;
//					try {
//						hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//					} catch (IllegalArgumentException e) {
//						hiredate=new java.sql.Date(System.currentTimeMillis());
//						errorMsgs.add("請輸入日期!");
//					}
					
					String admins_birthday = req.getParameter("admins_birthday").trim();
					if (admins_birthday == null || admins_birthday.trim().length() == 0) {
						errorMsgs.add("生日請勿空白");
					}
					
					String admins_id_number = req.getParameter("admins_id_number").trim();
					if (admins_id_number == null || admins_id_number.trim().length() == 0) {
						errorMsgs.add("身分證請勿空白");
					}
					
					String admins_phone = req.getParameter("admins_phone").trim();
					if (admins_phone == null || admins_phone.trim().length() == 0) {
						errorMsgs.add("電話請勿空白");
					}
					
					Part part=req.getPart("admins_admins_pic");
				
					InputStream in = part.getInputStream();
					byte[] admins_admins_pic = new byte[in.available()];
					in.read(admins_admins_pic);
					in.close();
					
					
					Integer admins_id = new Integer(req.getParameter("admins_id").trim());

					AdminsVO adminsVO = new AdminsVO();
//					adminsVO.setadmins_mail(admins_mail);
//					adminsVO.setadmins_pwd(admins_pwd);
//					adminsVO.setadmins_status(admins_status);
//					adminsVO.setadmins_nickname(admins_nickname);
//					adminsVO.setadmins_name(admins_name);
//					adminsVO.setadmins_sex(admins_sex);
//					adminsVO.setadmins_birthday(admins_birthday);
//					adminsVO.setadmins_id_number(admins_id_number);
//					adminsVO.setadmins_admins_pic(admins_admins_pic);
//					adminsVO.setadmins_phone(admins_phone);
//					adminsVO.setadmins_id(admins_id);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("adminsVO", adminsVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/admins/update_admins_input.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					AdminsService adminsSvc = new AdminsService();
//					adminsVO = adminsSvc.updateadmins(admins_id, admins_mail, admins_pwd, admins_status, admins_nickname, admins_name, admins_sex, admins_birthday, admins_id_number, admins_admins_pic, admins_phone);		
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/admins/listAlladmins.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/update_admins_input.jsp");
					failureView.forward(req, res);
				}
			}
	
		
		
	}
}
