package com.users.controller;

import java.io.IOException;
import java.io.InputStream;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.users.model.UsersService;
import com.users.model.UsersVO;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024*1024, maxRequestSize=5*5*1024*1024*1024)
public class UsersServlet extends HttpServlet {
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
		
		String requestUrl = req.getParameter("requestUrl");
		System.out.println("action= " + action + " requestUrl= " + requestUrl);

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("users_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "users/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer users_id = null;
				try {
					users_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "users/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				UsersService usersSvc = new UsersService();
				UsersVO usersVO = usersSvc.getOneusers(users_id);
				if (usersVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "users/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("usersVO", usersVO); // 資料庫取出的empVO物件,存入req
				req.getSession().setAttribute("usersVO", usersSvc.getOneusers(usersVO.getUsers_id()));
				String url = (requestUrl + "users/listOneUsers.jsp");

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
				Integer users_id = new Integer(req.getParameter("users_id"));
				
				/***************************2.開始刪除資料***************************************/
				UsersService usersSvc = new UsersService();
				usersSvc.deleteusers(users_id);
				System.out.println("DELETE TEST " + users_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/users/listAllUsers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/users/listAllUsers.jsp");

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
				String users_name = req.getParameter("users_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (users_name == null || users_name.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!users_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String users_mail = req.getParameter("users_mail").trim();
				if (users_mail == null || users_mail.trim().length() == 0) {
					errorMsgs.add("Mail請勿空白");
				}
				
				String users_pwd = req.getParameter("users_pwd").trim();
				if (users_pwd == null || users_pwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				
				String users_nickname = req.getParameter("users_nickname").trim();
				if (users_nickname == null || users_nickname.trim().length() == 0) {
					errorMsgs.add("匿名請勿空白");
				}
				
				Integer users_status = null ;
				try {
					users_status = new Integer(req.getParameter("users_status").trim());
				} catch (NumberFormatException e) {
					users_status = 1;
					errorMsgs.add("狀態請填數字.");
				}
				

				Integer users_sex = null ;
				try {
					users_sex = new Integer(req.getParameter("users_sex").trim());
				} catch (Exception e) {
					//users_sex = 1;
					errorMsgs.add("請選擇性別.");
				}
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				String users_birthday = req.getParameter("users_birthday").trim();
				if (users_birthday == null || users_birthday.trim().length() == 0) {
					errorMsgs.add("生日請勿空白");
				}
				
				String users_id_number = req.getParameter("users_id_number").trim();
				if (users_id_number == null || users_id_number.trim().length() == 0) {
					errorMsgs.add("身分證請勿空白");
				}
				
				String users_phone = req.getParameter("users_phone").trim();
				if (users_phone == null || users_phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}
				
				Part part=req.getPart("users_users_pic");
			
				InputStream in = part.getInputStream();
				byte[] users_users_pic = new byte[in.available()];
				in.read(users_users_pic);
				in.close();
				
				
//				Integer users_id = new Integer(req.getParameter("users_id").trim());

				UsersVO usersVO = new UsersVO();
				usersVO.setUsers_mail(users_mail);
				usersVO.setUsers_pwd(users_pwd);
				usersVO.setUsers_status(users_status);
				usersVO.setUsers_nickname(users_nickname);
				usersVO.setUsers_name(users_name);
				usersVO.setUsers_sex(users_sex);
				usersVO.setUsers_birthday(users_birthday);
				usersVO.setUsers_id_number(users_id_number);
				usersVO.setUsers_users_pic(users_users_pic);
				usersVO.setUsers_phone(users_phone);
		//		usersVO.setUsers_id(users_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("usersVO", usersVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/users/addUsers.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				UsersService usersSvc = new UsersService();
				usersVO = usersSvc.addusers( users_mail, users_pwd, users_status, 
						users_nickname, users_name, users_sex, users_birthday, users_id_number,
						users_users_pic, users_phone);			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				usersVO = usersSvc.getOneusers(users_mail);
				req.setAttribute("usersVO", usersVO);
				req.getSession().setAttribute("usersVO", usersSvc.getOneusers(usersVO.getUsers_id()));
				String url = "/front-end/users/listOneUsers.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/users/addUsers.jsp");
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
					Integer users_id = new Integer(req.getParameter("users_id"));
					/***************************2.開始查詢資料****************************************/
					UsersService usersSvc = new UsersService();
					UsersVO usersVO = usersSvc.getOneusers(users_id);
									
					/***************************3.查詢完成 準備轉交(Send the Success view)************/
					req.setAttribute("usersVO", usersVO);         // 資料庫取出的empVO物件,存入req
					String url = requestUrl + "/account.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				System.out.println("TEST_1 " + action + " users_id " + users_id + " url " + url);				
					successView.forward(req, res);
					return;
//				System.out.println("TEST_2 " + action + " users_id " + users_id + " url " + url);				

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "/tables.jsp");
					failureView.forward(req, res);
				}
			}
			
			if ("deactivate".equals(action)) { //來自listAllEmp.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				UsersService usersSvc = new UsersService();
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer users_id = new Integer(req.getParameter("users_id"));
					
					/*除接收ID外，將狀態修改成停用，其他取回原參數*/
					Integer users_status = 0 ;

					String users_name = usersSvc.getOneusers(users_id).getUsers_name();
					
					String users_mail = usersSvc.getOneusers(users_id).getUsers_mail();
										
					String users_pwd = usersSvc.getOneusers(users_id).getUsers_pwd();
					
					String users_nickname = usersSvc.getOneusers(users_id).getUsers_nickname();
						
					Integer users_sex = usersSvc.getOneusers(users_id).getUsers_sex();
								
					String users_birthday = usersSvc.getOneusers(users_id).getUsers_birthday();
							
					String users_id_number = usersSvc.getOneusers(users_id).getUsers_id_number();	
					
					String users_phone = usersSvc.getOneusers(users_id).getUsers_phone();
			
					byte[] users_users_pic = usersSvc.getOneusers(users_id).getUsers_users_pic();
							
					UsersVO usersVO = new UsersVO();
					usersVO.setUsers_mail(users_mail);
					usersVO.setUsers_pwd(users_pwd);
					usersVO.setUsers_status(users_status);
					usersVO.setUsers_nickname(users_nickname);
					usersVO.setUsers_name(users_name);
					usersVO.setUsers_sex(users_sex);
					usersVO.setUsers_birthday(users_birthday);
					usersVO.setUsers_id_number(users_id_number);
					usersVO.setUsers_users_pic(users_users_pic);
					usersVO.setUsers_phone(users_phone);
					usersVO.setUsers_id(users_id);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("usersVO", usersVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestUrl + "/tables.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					usersVO = usersSvc.updateusers(users_id, users_mail, users_pwd, users_status,
							users_nickname, users_name, users_sex, users_birthday, users_id_number, 
							users_users_pic, users_phone);		
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					usersVO = usersSvc.getOneusers(users_id);
					req.getSession().setAttribute("usersVO", usersSvc.getOneusers(usersVO.getUsers_id()));
					req.setAttribute("usersVO", usersVO); 
					String url = requestUrl + "/tables.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "/tables.jsp");
					failureView.forward(req, res);
				}
			}
			
			
			if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				UsersService usersSvc = new UsersService();
				try {
					/***************************1.接收請求參數-輸入格式的錯誤處理**********************/
					Integer users_id = new Integer(req.getParameter("users_id").trim());
					
					String users_name = req.getParameter("users_name");
					String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
					if (users_name == null || users_name.trim().length() == 0) {
						errorMsgs.add("會員姓名: 請勿空白");
					} else if(!users_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					String users_mail = req.getParameter("users_mail").trim();
					
					if (users_mail == null || users_mail.trim().length() == 0) {
						errorMsgs.add("Mail請勿空白");
					}
					
					String users_pwd = req.getParameter("users_pwd").trim();
					if (users_pwd == null || users_pwd.trim().length() == 0) {
						errorMsgs.add("密碼請勿空白");
					}
					
					String users_nickname = req.getParameter("users_nickname").trim();
					if (users_nickname == null || users_nickname.trim().length() == 0) {
						errorMsgs.add("匿名請勿空白");
					}
					
					Integer users_status = null ;
					try {
						users_status = new Integer(req.getParameter("users_status").trim());
					} catch (NumberFormatException e) {
						users_status = 1;
						errorMsgs.add("狀態請填數字.");
					}
					
					Integer users_sex = null ;
					try {
						users_sex = new Integer(req.getParameter("users_sex").trim());
					} catch (Exception e) {
						errorMsgs.add("性別請填數字.");
					}
								
					String users_birthday = req.getParameter("users_birthday").trim();
					if (users_birthday == null || users_birthday.trim().length() == 0) {
						errorMsgs.add("生日請勿空白");
					}
					
					String users_id_number = req.getParameter("users_id_number").trim();
				
					if (users_id_number == null || users_id_number.trim().length() == 0) {
						errorMsgs.add("身分證請勿空白");
					} 
//					else if(!users_id_number.trim().matches(idReg)) { //以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("身分證號碼格式錯誤...");
//			            }
					
					String users_phone = req.getParameter("users_phone").trim();
					if (users_phone == null || users_phone.trim().length() == 0) {
						errorMsgs.add("電話請勿空白");
					}
					
					Part part=req.getPart("users_users_pic");
					InputStream in = part.getInputStream();
					byte[] users_users_pic = new byte[in.available()];
					
					if (req.getPart("users_users_pic").getSize() > 0) {
					in.read(users_users_pic);
					in.close();
					} else {
						users_users_pic = usersSvc.getOneusers(users_id).getUsers_users_pic();
					}
					
					
				

					UsersVO usersVO = new UsersVO();
					usersVO.setUsers_mail(users_mail);
					usersVO.setUsers_pwd(users_pwd);
					usersVO.setUsers_status(users_status);
					usersVO.setUsers_nickname(users_nickname);
					usersVO.setUsers_name(users_name);
					usersVO.setUsers_sex(users_sex);
					usersVO.setUsers_birthday(users_birthday);
					usersVO.setUsers_id_number(users_id_number);
					usersVO.setUsers_users_pic(users_users_pic);
					usersVO.setUsers_phone(users_phone);
					usersVO.setUsers_id(users_id);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("usersVO", usersVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestUrl + "/account.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					usersVO = usersSvc.updateusers(users_id, users_mail, users_pwd, users_status, users_nickname, users_name, users_sex, users_birthday, users_id_number, users_users_pic, users_phone);		
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					usersVO = usersSvc.getOneusers(users_id);
					req.getSession().setAttribute("usersVO", usersSvc.getOneusers(usersVO.getUsers_id()));
					req.setAttribute("usersVO", usersVO); 
					String url ;
					
					if ("/front-end/users".equals(requestUrl)) { 
						url = requestUrl + "/account.jsp"; 
					} else	{		
						url = requestUrl + "/tables.jsp";
					}
		System.out.println("url= " + url);			
					
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "/account.jsp");
					failureView.forward(req, res);
				}
			}
	
			if ("insertUsers".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String users_mail = req.getParameter("users_mail").trim();

				//	String mailReg = "/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/";
							//"/^[A-Za-z][12]\\d{8}$/";
					if (users_mail == null || users_mail.trim().length() == 0) {
						errorMsgs.add("Mail請勿空白");
					} 
					
//					String users_name = req.getParameter("users_name");
//					String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//					if (users_name == null || users_name.trim().length() == 0) {
//						errorMsgs.add("會員姓名: 請勿空白");
//					} else if(!users_name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//		            }
					
					
					String users_pwd = req.getParameter("users_pwd").trim();
					//String pwdReg = "/^.*(?=.{5,16})(?=.*\\d)(?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[!@#$%^&*?\\(\\)]).*$/" ;
					if (users_pwd == null || users_pwd.trim().length() == 0) {
						errorMsgs.add("密碼請勿空白");
					}//else if(!users_pwd.trim().matches(pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					//	errorMsgs.add("密碼 最短5位，最長16位 {6,16}\r\n" + 
					//					"必須包含1個數字\r\n" + 
					//  				"必須包含2個小寫字母\r\n" + 
					//					"必須包含2個大寫字母\r\n" + 
					//					"必須包含1個特殊字符");
			        //}

						
					Integer users_status = 1 ;
					
					
//					Integer users_id = new Integer(req.getParameter("users_id").trim());

					UsersVO usersVO = new UsersVO();
					usersVO.setUsers_mail(users_mail);
					usersVO.setUsers_pwd(users_pwd);
					usersVO.setUsers_status(users_status);


					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("usersVO", usersVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestUrl + "/login.jsp");
						failureView.forward(req, res);
						return;
					}

					/***************************2.開始新增資料***************************************/
					UsersService usersSvc = new UsersService();
					usersVO = usersSvc.addusers_new(users_mail, users_pwd, users_status);	
					usersVO = usersSvc.getOneusers(usersVO.getUsers_id());
					/***************************3.新增完成,準備轉交(Send the Success view)***********/

					req.setAttribute("usersVO", usersVO);
					req.getSession().setAttribute("usersVO", usersSvc.getOneusers(usersVO.getUsers_id()));
					String url = requestUrl + "/account.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestUrl + "/login.jsp");
					failureView.forward(req, res);
				}
			}
		
	}
}
