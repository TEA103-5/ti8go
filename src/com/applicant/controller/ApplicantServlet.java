package com.applicant.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.applicant.model.ApplicantService;
import com.applicant.model.ApplicantVO;


public class ApplicantServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				
				
				String str = req.getParameter("activities_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				String str1 = req.getParameter("users_id");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入使用者編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/applicant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer activities_id = null;
				Integer users_id = null;
				
				try {
					activities_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				try {
					users_id = new Integer(str1);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/applicant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ApplicantService applicantSvc = new ApplicantService();
				ApplicantVO applicantVO = applicantSvc.getOneEmp(activities_id, users_id);
				if (applicantVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/applicant/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("applicantVO", applicantVO); // 資料庫取出的empVO物件,存入req
				String url = "/applicant/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/applicant/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				
				Integer activities_id = new Integer(req.getParameter("activities_id"));
				Integer users_id = new Integer(req.getParameter("users_id"));
				/***************************2.開始查詢資料****************************************/
				ApplicantService applicantSvc = new ApplicantService();
				ApplicantVO applicantVO = applicantSvc.getOneEmp(activities_id ,  users_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("applicantVO", applicantVO);         // 資料庫取出的empVO物件,存入req
				String url = "/applicant/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/applicant/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer activities_id = new Integer(req.getParameter("activities_id").trim());
//				Integer users_id = new Integer(req.getParameter("users_id").trim());
//				
//				Timestamp registration_time = java.sql.Timestamp.valueOf(req.getParameter("registration_time").trim());
//
//
//				Integer users_id1 = null;
//				try {
//					users_id1 = new Integer(req.getParameter("users_id1").trim());
//				} catch (NumberFormatException e) {
//					users_id1 = 1;
//					errorMsgs.add("使用者編號請填數字.");
//				}
//
//
//				ApplicantVO applicantVO = new ApplicantVO();
//				
//				applicantVO.setActivities_id(activities_id);
//				applicantVO.setUsers_id(users_id1);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("applicantVO", applicantVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/applicant/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				ApplicantService applicantSvc = new ApplicantService();
//				applicantVO = applicantSvc.updateEmp(activities_id, users_id, users_id1);
//				applicantVO.setRegistration_time(registration_time);
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("applicantVO", applicantVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/applicant/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/applicant/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				Integer activities_id = null;
				try {
					activities_id = new Integer(req.getParameter("activities_id").trim());
				} catch (NumberFormatException e) {
					activities_id = 1;
					errorMsgs.add("使用者編號請填數字.");
				}
				
				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 1;
					errorMsgs.add("使用者編號請填數字.");
				}
				
				if (users_id<1 || users_id>10) {
					errorMsgs.add("使用者編號需在1到10之間");
				}

				
				ApplicantService applicantSvc = new ApplicantService();
				ApplicantVO applicantVO = applicantSvc.getOneEmp(activities_id, users_id);
				
			
				if (applicantVO != null) {
					errorMsgs.add("重複報名");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("applicantVO", applicantVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/applicant/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ApplicantService applicantSvc1 = new ApplicantService();
				applicantVO = applicantSvc1.addEmp(activities_id, users_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/applicant/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/applicant/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer activities_id = new Integer(req.getParameter("activities_id"));
				Integer users_id = new Integer(req.getParameter("users_id"));
				
				/***************************2.開始刪除資料***************************************/
				ApplicantService applicantSvc = new ApplicantService();
				applicantSvc.deleteEmp(activities_id ,  users_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/applicant/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/applicant/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
