package com.group_activities.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.applicant.model.ApplicantVO;
import com.group_activities.model.Group_activitiesService;
import com.group_activities.model.Group_activitiesVO;
import com.team.model.TeamVO;
import com.trip.model.TripService;


public class Group_activitiesServlet extends HttpServlet {
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
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group_activities/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer activities_id = null;
				try {
					activities_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group_activities/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Group_activitiesService group_activitiesSvc = new Group_activitiesService();
				Group_activitiesVO group_activitiesVO = group_activitiesSvc.getOneEmp(activities_id);
				if (group_activitiesVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group_activities/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_activitiesVO", group_activitiesVO); // 資料庫取出的empVO物件,存入req
				Set<ApplicantVO> set = group_activitiesSvc.getApplicantByTrip(activities_id);
				req.setAttribute("listApplicant_ByActivities", set);
				
				String url = "/group_activities/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group_activities/select_page.jsp");
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
				
				/***************************2.開始查詢資料****************************************/
				Group_activitiesService group_activitiesSvc = new Group_activitiesService();
				Group_activitiesVO group_activitiesVO = group_activitiesSvc.getOneEmp(activities_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("group_activitiesVO", group_activitiesVO);         // 資料庫取出的empVO物件,存入req
				String url = "/group_activities/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group_activities/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer activities_id = new Integer(req.getParameter("activities_id").trim());
				
				String activities_name = req.getParameter("activities_name").trim();
				if (activities_name == null || activities_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				}
				
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
//				if (activities_name == null || activities_name.trim().length() == 0) {
//					errorMsgs.add("活動名稱: 請勿空白");
//				} else if(!activities_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//	            }
				
				String activities_description = req.getParameter("activities_description").trim();
				if (activities_description == null || activities_description.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				}	
				
				java.sql.Date activities_deadline = null;
				try {
					activities_deadline = java.sql.Date.valueOf(req.getParameter("activities_deadline").trim());
				} catch (IllegalArgumentException e) {
					activities_deadline=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date activities_start = null;
				try {
					activities_start = java.sql.Date.valueOf(req.getParameter("activities_start").trim());
				} catch (IllegalArgumentException e) {
					activities_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date activities_end = null;
				try {
					activities_end = java.sql.Date.valueOf(req.getParameter("activities_end").trim());
				} catch (IllegalArgumentException e) {
					activities_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				Integer activities_state = null;
				try {
					activities_state = new Integer(req.getParameter("activities_state").trim());
				} catch (NumberFormatException e) {
					activities_state = 1;
					errorMsgs.add("活動狀態請填數字.");
				}
				Integer activities_max_users = null;
				try {
					activities_max_users = new Integer(req.getParameter("activities_max_users").trim());
				} catch (NumberFormatException e) {
					activities_max_users = 10;
					errorMsgs.add("報名人數上限請填數字.");
				}

				
				
Integer trip_id = new Integer(req.getParameter("trip_id").trim());
Integer users_id = new Integer(req.getParameter("users_id").trim());
Integer activities_users_count = new Integer(req.getParameter("activities_users_count").trim());
Timestamp activities_create_time = java.sql.Timestamp.valueOf(req.getParameter("activities_create_time").trim());
			

				
				Group_activitiesVO group_activitiesVO = new Group_activitiesVO();
				group_activitiesVO.setActivities_id(activities_id);
				group_activitiesVO.setTrip_id(trip_id);
				group_activitiesVO.setActivities_state(activities_state);
				group_activitiesVO.setActivities_deadline(activities_deadline);
				group_activitiesVO.setActivities_max_users(activities_max_users);
				group_activitiesVO.setActivities_users_count(activities_users_count);
				group_activitiesVO.setActivities_start(activities_start);
				group_activitiesVO.setActivities_end(activities_end);
				group_activitiesVO.setActivities_name(activities_name);
				group_activitiesVO.setActivities_description(activities_description);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("group_activitiesVO", group_activitiesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group_activities/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Group_activitiesService group_activitiesSvc = new Group_activitiesService();
				group_activitiesVO = group_activitiesSvc.updateEmp(activities_id, trip_id,activities_state,activities_deadline,activities_max_users,activities_users_count,activities_start,activities_end,activities_name,activities_description);
				group_activitiesVO.setUsers_id(users_id);
				group_activitiesVO.setActivities_create_time(activities_create_time);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_activitiesVO", group_activitiesVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/group_activities/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group_activities/update_emp_input.jsp");
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
				String activities_name = req.getParameter("activities_name").trim();
				if (activities_name == null || activities_name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				}
				
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
//				if (activities_name == null || activities_name.trim().length() == 0) {
//					errorMsgs.add("活動名稱: 請勿空白");
//				} else if(!activities_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//	            }
				
				String activities_description = req.getParameter("activities_description").trim();
				if (activities_description == null || activities_description.trim().length() == 0) {
					errorMsgs.add("活動描述請勿空白");
				}	
				
				java.sql.Date activities_deadline = null;
				try {
					activities_deadline = java.sql.Date.valueOf(req.getParameter("activities_deadline").trim());
				} catch (IllegalArgumentException e) {
					activities_deadline=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date activities_start = null;
				try {
					activities_start = java.sql.Date.valueOf(req.getParameter("activities_start").trim());
				} catch (IllegalArgumentException e) {
					activities_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date activities_end = null;
				try {
					activities_end = java.sql.Date.valueOf(req.getParameter("activities_end").trim());
				} catch (IllegalArgumentException e) {
					activities_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				Integer activities_state = null;
				try {
					activities_state = new Integer(req.getParameter("activities_state").trim());
				} catch (NumberFormatException e) {
					activities_state = 1;
					errorMsgs.add("活動狀態請填數字.");
				}
				Integer activities_max_users = null;
				try {
					activities_max_users = new Integer(req.getParameter("activities_max_users").trim());
				} catch (NumberFormatException e) {
					activities_max_users = 10;
					errorMsgs.add("報名人數上限請填數字.");
				}
				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 1;
					errorMsgs.add("發佈者上限請填數字.");
				}

				
				
Integer trip_id = new Integer(req.getParameter("trip_id").trim());
//Integer activities_users_count = new Integer(req.getParameter("activities_users_count").trim());
//Timestamp activities_create_time = java.sql.Timestamp.valueOf(req.getParameter("activities_create_time").trim());
			

				
				Group_activitiesVO group_activitiesVO = new Group_activitiesVO();
				group_activitiesVO.setTrip_id(trip_id);
				group_activitiesVO.setUsers_id(users_id);
				group_activitiesVO.setActivities_state(activities_state);
				group_activitiesVO.setActivities_deadline(activities_deadline);
				group_activitiesVO.setActivities_max_users(activities_max_users);
//				group_activitiesVO.setActivities_users_count(activities_users_count);
				group_activitiesVO.setActivities_start(activities_start);
				group_activitiesVO.setActivities_end(activities_end);
				group_activitiesVO.setActivities_name(activities_name);
				group_activitiesVO.setActivities_description(activities_description);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("group_activitiesVO", group_activitiesVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group_activities/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Group_activitiesService group_activitiesSvc = new Group_activitiesService();
				group_activitiesVO = group_activitiesSvc.addEmp(trip_id,users_id,activities_state,activities_deadline,activities_max_users,activities_start,activities_end,activities_name,activities_description);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/group_activities/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group_activities/addEmp.jsp");
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
				
				/***************************2.開始刪除資料***************************************/
				Group_activitiesService group_activitiesSvc = new Group_activitiesService();
				group_activitiesSvc.deleteEmp(activities_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/group_activities/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group_activities/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
if ("listApplicant_ByActivities_A".equals(action) || "listApplicant_ByActivities_B".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer activities_id = new Integer(req.getParameter("activities_id"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				Group_activitiesService group_activitiesSvc = new Group_activitiesService();
				Set<ApplicantVO> set = group_activitiesSvc.getApplicantByTrip(activities_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listApplicant_ByActivities", set);    // 資料庫取出的set物件,存入request
				
				String url = null;
				if ("listApplicant_ByActivities_A".equals(action))
					url = "/group_activities/listApplicant_ByActivities.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listApplicant_ByActivities_B".equals(action))
					url = "/group_activities/listAllEmp.jsp";              // 成功轉交 dept/listAllDept.jsp
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

}
