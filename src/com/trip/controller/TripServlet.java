package com.trip.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.team.model.TeamVO;
import com.trip.model.TripService;
import com.trip.model.TripVO;
import com.trip_detail.model.Trip_detailService;
import com.trip_detail.model.Trip_detailVO;
import com.users.model.UsersService;

public class TripServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json ; charset=UTF-8");
		String action = req.getParameter("action");
		
		
		
		if("delAjax".equals(action)) {
			Integer trip_id = new Integer(req.getParameter("trip_id").trim());
			TripService tripSvc = new TripService();
			tripSvc.delT(trip_id);
		}
		if("updateDay".equals(action)) {
			String day = req.getParameter("day");
    		Integer trip_id = new Integer(req.getParameter("trip_id").trim());
      		Integer read_authority  = new Integer(req.getParameter("read_authority").trim());
      		System.out.println(trip_id);
    	
    		TripService tripSvc = new TripService();
    		tripSvc.updateDay(day, trip_id,read_authority);
    		
    		
		}
		if("updateLook".equals(action)) {

			Integer trip_id = new Integer(req.getParameter("trip_id").trim());
			Integer trip_look  = new Integer(req.getParameter("trip_look").trim());
			trip_look++;
//System.out.println(trip_id);
			
			TripService tripSvc = new TripService();
			tripSvc.updateLook(trip_id,trip_look);
			
			
		}
		
        if ("insertajax".equals(action)) { // 來自addEmp.jsp的請求  
        	HashMap result = new HashMap();
        	PrintWriter out = res.getWriter();
        	List<String> errorMsgs = new LinkedList<String>();
        	// Store this set in the request scope, in case we need to
        	// send the ErrorPage view.
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
        		Integer users_id = new Integer(req.getParameter("users_id").trim());
        		Integer last_editor = new Integer(req.getParameter("last_editor").trim());
        		
        		
        		Integer trip_state = null;
        		try {
        			trip_state = new Integer(req.getParameter("trip_state").trim());
        		} catch (NumberFormatException e) {
        			trip_state = 1;
        			errorMsgs.add("行程狀態請填數字.");
        		}
        		Integer read_authority = null;
        		try {
        			read_authority = new Integer(req.getParameter("read_authority").trim());
        		} catch (NumberFormatException e) {
        			read_authority = 1;
        			errorMsgs.add("閱覽權限請填數字.");
        		}
        		Integer edit_authority = null;
        		try {
        			edit_authority = new Integer(req.getParameter("edit_authority").trim());
        		} catch (NumberFormatException e) {
        			edit_authority = 1;
        			errorMsgs.add("編輯權限請填數字.");
        		}
        		
        		String trip_area = req.getParameter("trip_area").trim();
        		if (trip_area == null || trip_area.trim().length() == 0) {
        			trip_area="台灣";
        			errorMsgs.add("地區請勿空白");
        		}	
        		
        		java.sql.Date trip_start = null;
        		try {
        			trip_start = java.sql.Date.valueOf(req.getParameter("trip_start").trim());
        		} catch (IllegalArgumentException e) {
        			trip_start=new java.sql.Date(System.currentTimeMillis());
        			errorMsgs.add("請輸入日期!");
        		}
        		java.sql.Date trip_end = null;
        		try {
        			trip_end = java.sql.Date.valueOf(req.getParameter("trip_end").trim());
        		} catch (IllegalArgumentException e) {
        			trip_end=new java.sql.Date(System.currentTimeMillis());
        			errorMsgs.add("請輸入日期!");
        		}
        		
        		String trip_name = req.getParameter("trip_name").trim();
        		if (trip_name == null || trip_name.trim().length() == 0) {
        			trip_name="行程名稱";
        			errorMsgs.add("行程名稱請勿空白");
        		}	
        		
        		String trip_description = req.getParameter("trip_description").trim();
        		if (trip_description == null || trip_description.trim().length() == 0) {
        			trip_description = "行程描述...";
        			errorMsgs.add("行程描述請勿空白");
        		}	
        		
        		String trip_type = req.getParameter("trip_type").trim();
        		if (trip_type == null || trip_type.trim().length() == 0) {
        			trip_type = "家庭旅遊";
        			errorMsgs.add("類型請勿空白");
        		}	
        		Integer trip_tot_cost = null;
        		try {
        			trip_tot_cost = new Integer(req.getParameter("trip_tot_cost").trim());
        		} catch (NumberFormatException e) {
        			trip_tot_cost = 0;
        			errorMsgs.add("費用總額請填數字.");
        		}
        		
        		String place_weather = req.getParameter("place_weather").trim();
        		if (place_weather == null || place_weather.trim().length() == 0) {
        			place_weather = "晴天";
        			errorMsgs.add("天氣請勿空白");
        		}	
        		
        		TripVO tripVO = new TripVO();
        		tripVO.setUsers_id(users_id);
        		tripVO.setLast_editor(last_editor);
        		tripVO.setTrip_state(trip_state);
        		tripVO.setRead_authority(read_authority);
        		tripVO.setEdit_authority(edit_authority);
        		tripVO.setTrip_area(trip_area);
        		tripVO.setTrip_start(trip_start);
        		tripVO.setTrip_end(trip_end);
        		tripVO.setTrip_name(trip_name);
        		tripVO.setTrip_description(trip_description);
        		tripVO.setTrip_type(trip_type);
        		tripVO.setTrip_tot_cost(trip_tot_cost);
        		tripVO.setPlace_weather(place_weather);
				java.sql.Date date = new java.sql.Date(new Date().getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowTime = sdf.format(date);
				Timestamp dates = Timestamp.valueOf(nowTime);
        		tripVO.setTrip_create_time(dates);
        		// Send the use back to the form, if there were errors
        		if (!errorMsgs.isEmpty()) {
//        			req.setAttribute("tripVO", tripVO); // 含有輸入格式錯誤的empVO物件,也存入req
//        			RequestDispatcher failureView = req
//        					.getRequestDispatcher("/trip/addEmp.jsp");
//        			failureView.forward(req, res);
        	  		result.put("errorMsgs",errorMsgs);
        			JSONObject resultJSON = new JSONObject(result);
    				out.println(resultJSON);
        			return;
        		}
        		
        		/***************************2.開始新增資料***************************************/
        		TripService tripSvc = new TripService();
        		tripVO = tripSvc.addEmp2(tripVO);
    			
    			
    			result.put("trip_id",tripVO.getTrip_id());
    			JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
        		/***************************3.新增完成,準備轉交(Send the Success view)***********/
//        		String url = "/trip/listAllEmp.jsp";
//        		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//        		successView.forward(req, res);				

        		/***************************其他可能的錯誤處理**********************************/
        	} catch (Exception e) {
        		System.out.println(e);
        		errorMsgs.add(e.getMessage());
        		result.put("errorMsgs",errorMsgs);
        		JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
//        		RequestDispatcher failureView = req
//        				.getRequestDispatcher("/trip/addEmp.jsp");
//        		failureView.forward(req, res);
        	}
        }

		
		
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("trip_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入行程編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer trip_id = null;
				try {
					trip_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("行程編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneEmp(trip_id);
				if (tripVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tripVO", tripVO); // 資料庫取出的empVO物件,存入req
				Set<Trip_detailVO> set = tripSvc.getDetailByTrip(trip_id);
				req.setAttribute("listDetail_ByTrip", set); 
				Set<TeamVO> set1 = tripSvc.getTeamByTrip(trip_id);
				req.setAttribute("listTeam_ByTrip", set1); 
				
				String url = "/trip/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
	

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer trip_id = new Integer(req.getParameter("trip_id"));
				
				/***************************2.開始查詢資料****************************************/
				TripService tripSvc = new TripService();
				TripVO tripVO = tripSvc.getOneEmp(trip_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("tripVO", tripVO);         // 資料庫取出的empVO物件,存入req
				String url = "/trip/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
//		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer trip_id = new Integer(req.getParameter("trip_id").trim());
				Integer users_id = new Integer(req.getParameter("users_id").trim());
				Integer last_editor = new Integer(req.getParameter("last_editor").trim());
				Integer trip_like = new Integer(req.getParameter("trip_like").trim());
				Integer trip_look = new Integer(req.getParameter("trip_look").trim());
				java.sql.Timestamp trip_create_time = java.sql.Timestamp.valueOf(req.getParameter("trip_create_time").trim());
				java.sql.Timestamp last_edit_time = java.sql.Timestamp.valueOf(req.getParameter("last_edit_time").trim());

				
				Integer trip_state = null;
				try {
					trip_state = new Integer(req.getParameter("trip_state").trim());
				} catch (NumberFormatException e) {
					trip_state = 1;
					errorMsgs.add("行程狀態:請填數字.");
				}
				Integer read_authority = null;
				try {
					read_authority = new Integer(req.getParameter("read_authority").trim());
				} catch (NumberFormatException e) {
					read_authority = 1;
					errorMsgs.add("閱覽權限請填數字.");
				}
				Integer edit_authority = null;
				try {
					edit_authority = new Integer(req.getParameter("edit_authority").trim());
				} catch (NumberFormatException e) {
					edit_authority = 1;
					errorMsgs.add("編輯權限請填數字.");
				}
				
				String trip_area = req.getParameter("trip_area").trim();
				if (trip_area == null || trip_area.trim().length() == 0) {
					errorMsgs.add("地區請勿空白");
				}	
				
				java.sql.Date trip_start = null;
				try {
					trip_start = java.sql.Date.valueOf(req.getParameter("trip_start").trim());
				} catch (IllegalArgumentException e) {
					trip_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date trip_end = null;
				try {
					trip_end = java.sql.Date.valueOf(req.getParameter("trip_end").trim());
				} catch (IllegalArgumentException e) {
					trip_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
							
				String trip_name = req.getParameter("trip_name").trim();
				if (trip_name == null || trip_name.trim().length() == 0) {
					errorMsgs.add("行程名稱請勿空白");
				}	
				
				String trip_description = req.getParameter("trip_description").trim();
				if (trip_description == null || trip_description.trim().length() == 0) {
					errorMsgs.add("行程描述請勿空白");
				}	
				
				String trip_type = req.getParameter("trip_type").trim();
				if (trip_type == null || trip_type.trim().length() == 0) {
					errorMsgs.add("類型請勿空白");
				}	
				Integer trip_tot_cost = null;
				try {
					trip_tot_cost = new Integer(req.getParameter("trip_tot_cost").trim());
				} catch (NumberFormatException e) {
					trip_tot_cost = 1;
					errorMsgs.add("費用總額請填數字.");
				}
				
				String place_weather = req.getParameter("place_weather").trim();
				if (place_weather == null || place_weather.trim().length() == 0) {
					errorMsgs.add("天氣請勿空白");
				}	





				TripVO tripVO = new TripVO();
				tripVO.setTrip_id(trip_id);
				tripVO.setUsers_id(users_id);
				 tripVO.setLast_editor(last_editor);
				 tripVO.setTrip_state(trip_state);
				 tripVO.setRead_authority(read_authority);
				 tripVO.setEdit_authority(edit_authority);
				 tripVO.setTrip_area(trip_area);
				 tripVO.setTrip_start(trip_start);
				 tripVO.setTrip_end(trip_end);
				 tripVO.setTrip_name(trip_name);
				 tripVO.setTrip_description(trip_description);
				 tripVO.setTrip_type(trip_type);
				 tripVO.setTrip_tot_cost(trip_tot_cost);
				 tripVO.setPlace_weather(place_weather);
				 
				 tripVO.setTrip_create_time(trip_create_time);
				 tripVO.setLast_edit_time(last_edit_time);
				 tripVO.setTrip_like(trip_like);
				 tripVO.setTrip_look(trip_look);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("tripVO", tripVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TripService tripSvc = new TripService();
				tripVO = tripSvc.updateEmp(trip_id,users_id,last_editor,trip_state,read_authority,edit_authority,trip_area,trip_start,trip_end,trip_name,trip_description,trip_type,trip_tot_cost,place_weather);
				tripVO = tripSvc.getOneEmp(trip_id);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				UsersService usersSvc = new UsersService();
				if(requestURL.equals("/users/listTrip_ByUsers.jsp") || requestURL.equals("/trip/listAllDept.jsp"))
					req.setAttribute("listTrip_ByUsers",usersSvc.getTripByUsers(users_id)); // 資料庫取出的list物件,存入request

                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip/update_emp_input.jsp");
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
				Integer users_id = new Integer(req.getParameter("users_id").trim());
				Integer last_editor = new Integer(req.getParameter("last_editor").trim());

				
				Integer trip_state = null;
				try {
					trip_state = new Integer(req.getParameter("trip_state").trim());
				} catch (NumberFormatException e) {
					trip_state = 1;
					errorMsgs.add("行程狀態請填數字.");
				}
				Integer read_authority = null;
				try {
					read_authority = new Integer(req.getParameter("read_authority").trim());
				} catch (NumberFormatException e) {
					read_authority = 1;
					errorMsgs.add("閱覽權限請填數字.");
				}
				Integer edit_authority = null;
				try {
					edit_authority = new Integer(req.getParameter("edit_authority").trim());
				} catch (NumberFormatException e) {
					edit_authority = 1;
					errorMsgs.add("編輯權限請填數字.");
				}
				
				String trip_area = req.getParameter("trip_area").trim();
				if (trip_area == null || trip_area.trim().length() == 0) {
					trip_area="台灣";
					errorMsgs.add("地區請勿空白");
				}	
				
				java.sql.Date trip_start = null;
				try {
					trip_start = java.sql.Date.valueOf(req.getParameter("trip_start").trim());
				} catch (IllegalArgumentException e) {
					trip_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				java.sql.Date trip_end = null;
				try {
					trip_end = java.sql.Date.valueOf(req.getParameter("trip_end").trim());
				} catch (IllegalArgumentException e) {
					trip_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
							
				String trip_name = req.getParameter("trip_name").trim();
				if (trip_name == null || trip_name.trim().length() == 0) {
					trip_name="行程名稱";
					errorMsgs.add("行程名稱請勿空白");
				}	
				
				String trip_description = req.getParameter("trip_description").trim();
				if (trip_description == null || trip_description.trim().length() == 0) {
					trip_description = "行程描述...";
					errorMsgs.add("行程描述請勿空白");
				}	
				
				String trip_type = req.getParameter("trip_type").trim();
				if (trip_type == null || trip_type.trim().length() == 0) {
					trip_type = "家庭旅遊";
					errorMsgs.add("類型請勿空白");
				}	
				Integer trip_tot_cost = null;
				try {
					trip_tot_cost = new Integer(req.getParameter("trip_tot_cost").trim());
				} catch (NumberFormatException e) {
					trip_tot_cost = 0;
					errorMsgs.add("費用總額請填數字.");
				}
				
				String place_weather = req.getParameter("place_weather").trim();
				if (place_weather == null || place_weather.trim().length() == 0) {
					place_weather = "晴天";
					errorMsgs.add("天氣請勿空白");
				}	

				TripVO tripVO = new TripVO();
				tripVO.setUsers_id(users_id);
				 tripVO.setLast_editor(last_editor);
				 tripVO.setTrip_state(trip_state);
				 tripVO.setRead_authority(read_authority);
				 tripVO.setEdit_authority(edit_authority);
				 tripVO.setTrip_area(trip_area);
				 tripVO.setTrip_start(trip_start);
				 tripVO.setTrip_end(trip_end);
				 tripVO.setTrip_name(trip_name);
				 tripVO.setTrip_description(trip_description);
				 tripVO.setTrip_type(trip_type);
				 tripVO.setTrip_tot_cost(trip_tot_cost);
				 tripVO.setPlace_weather(place_weather);
				 

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("tripVO", tripVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TripService tripSvc = new TripService();
				tripVO = tripSvc.addEmp(users_id,last_editor,trip_state,read_authority,edit_authority,trip_area,trip_start,trip_end,trip_name,trip_description,trip_type,trip_tot_cost,place_weather);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/trip/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
        if ("delTDbyTid".equals(action)) { // 來自listAllEmp.jsp
        	
        	List<String> errorMsgs = new LinkedList<String>();
        	// Store this set in the request scope, in case we need to
        	// send the ErrorPage view.
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		/***************************1.接收請求參數***************************************/
        		Integer trip_id = new Integer(req.getParameter("trip_id"));
        		
        		/***************************2.開始刪除資料***************************************/
        		Trip_detailService tripSvc = new Trip_detailService();
        		tripSvc.deleteT(trip_id);
   
        		/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//        		String url = "/trip/listAllEmp.jsp";
//        		RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//        		successView.forward(req, res);
    			HashMap result = new HashMap();
    			PrintWriter out = res.getWriter();
    			result.put("d", "succ");
    			JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
    		
        		/***************************其他可能的錯誤處理**********************************/
        	} catch (Exception e) {
        		System.out.println(e);
//        		errorMsgs.add("刪除資料失敗:"+e.getMessage());
//        		RequestDispatcher failureView = req
//        				.getRequestDispatcher("/trip/listAllEmp.jsp");
//        		failureView.forward(req, res);
        	}
        }
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer trip_id = new Integer(req.getParameter("trip_id"));
				
				/***************************2.開始刪除資料***************************************/
				TripService tripSvc = new TripService();
				tripSvc.deleteEmp(trip_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/trip/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listDetail_ByTrip_A".equals(action) || "listDetail_ByTrip_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer trip_id=null;

					
				 trip_id = new Integer(req.getParameter("trip_id"));
		
				System.out.println(trip_id);
				/*************************** 2.開始查詢資料 ****************************************/
				TripService tripSvc = new TripService();
				Set<Trip_detailVO> set = tripSvc.getDetailByTrip(trip_id);
				TripVO tvo =tripSvc.getOneEmp(trip_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				
				req.setAttribute("tripVO", tvo);    // 資料庫取出的set物件,存入request
				req.setAttribute("listDetail_ByTrip", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listDetail_ByTrip_A".equals(action))
					url = "/front-end/trip/selectOneTrip.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listDetail_ByTrip_B".equals(action))
					url = "/front-end/trip/editTrip.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if ("listTeam_ByTrip_A".equals(action) || "listTeam_ByTrip_B".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer trip_id = new Integer(req.getParameter("trip_id"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				TripService tripSvc = new TripService();
				Set<TeamVO> set = tripSvc.getTeamByTrip(trip_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listTeam_ByTrip", set);    // 資料庫取出的set物件,存入request
				
				String url = null;
				if ("listTeam_ByTrip_A".equals(action))
					url = "/trip/listTeam_ByTrip.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("listTeam_ByTrip_B".equals(action))
					url = "/trip/listAllEmp.jsp";              // 成功轉交 dept/listAllDept.jsp
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
