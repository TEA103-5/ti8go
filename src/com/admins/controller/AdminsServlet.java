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

public class AdminsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
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
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/select_page.jsp");
				failureView.forward(req, res);
			}
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
				String url = "/admins/listAllAdmins.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admins/listAllAdmins.jsp");
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
					errorMsgs.add("管理者姓名: 請勿空白");
				} else if(!admins_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String admins_email = req.getParameter("admins_email").trim();
				if (admins_email == null || admins_email.trim().length() == 0) {
					errorMsgs.add("Mail請勿空白");
				}
				
				String admins_password = req.getParameter("admins_password").trim();
				if (admins_password == null || admins_password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				Integer admins_sex = null ;
				try {
					admins_sex = new Integer(req.getParameter("admins_sex").trim());
				} catch (NumberFormatException e) {
					admins_sex = 1;
					errorMsgs.add("性別請填數字.");
				}

				String admins_authority = req.getParameter("admins_authority").trim();
				if (admins_authority == null || admins_authority.trim().length() == 0) {
					errorMsgs.add("權限請勿空白");
				}
					
				String admins_position = req.getParameter("admins_position").trim();
				if (admins_position == null || admins_position.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
			//	Integer admins_id = new Integer(req.getParameter("admins_id").trim());

				AdminsVO adminsVO = new AdminsVO();
				
				adminsVO.setAdmins_name(admins_name);
				adminsVO.setAdmins_email(admins_email);
				adminsVO.setAdmins_password(admins_password);
				adminsVO.setAdmins_sex(admins_sex);
				adminsVO.setAdmins_authority(admins_authority);
				adminsVO.setAdmins_position(admins_position);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminsVO", adminsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admins/addAdmins.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdminsService adminsSvc = new AdminsService();
				adminsVO = adminsSvc.addadmins(admins_email, admins_name, admins_password, admins_sex, 
						admins_authority, admins_position);		
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/admins/listAllAdmins.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admins/addAdmins.jsp");
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
							.getRequestDispatcher("/admins/listAllAdmins.jsp");
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
						errorMsgs.add("管理者姓名: 請勿空白");
					} else if(!admins_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("管理者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					String admins_email = req.getParameter("admins_email").trim();
					if (admins_email == null || admins_email.trim().length() == 0) {
						errorMsgs.add("Mail請勿空白");
					}
					
					String admins_password = req.getParameter("admins_password").trim();
					if (admins_password == null || admins_password.trim().length() == 0) {
						errorMsgs.add("密碼請勿空白");
					}
					
					Integer admins_sex = null ;
					try {
						admins_sex = new Integer(req.getParameter("admins_sex").trim());
					} catch (NumberFormatException e) {
						admins_sex = 1;
						errorMsgs.add("性別請填數字.");
					}

					String admins_authority = req.getParameter("admins_authority").trim();
					if (admins_authority == null || admins_authority.trim().length() == 0) {
						errorMsgs.add("權限請勿空白");
					}
						
					String admins_position = req.getParameter("admins_position").trim();
					if (admins_position == null || admins_position.trim().length() == 0) {
						errorMsgs.add("職位請勿空白");
					}
					
					Integer admins_id = new Integer(req.getParameter("admins_id").trim());

					AdminsVO adminsVO = new AdminsVO();
					
					adminsVO.setAdmins_name(admins_name);
					adminsVO.setAdmins_email(admins_email);
					adminsVO.setAdmins_password(admins_password);
					adminsVO.setAdmins_sex(admins_sex);
					adminsVO.setAdmins_authority(admins_authority);
					adminsVO.setAdmins_position(admins_position);
					adminsVO.setAdmins_id(admins_id);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("adminsVO", adminsVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/admins/addAdmins.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					AdminsService adminsSvc = new AdminsService();
					adminsVO = adminsSvc.updateadmins(admins_id, admins_email, admins_name, admins_password, 
							admins_sex, admins_authority, admins_position);		
					System.out.println("Admin Test " + admins_id );
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/admins/listAllAdmins.jsp";
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
