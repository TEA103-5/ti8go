package com.trip_detail.controller;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.trip.model.TripService;
import com.trip_detail.model.Trip_detailService;
import com.trip_detail.model.Trip_detailVO;




public class Trip_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	    if ("insertajax".equals(action)) { // 來自addEmp.jsp的請求  
			
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer trip_id = new Integer(req.getParameter("trip_id").trim());
					String trip_detail_type = req.getParameter("trip_detail_type").trim();
					
					Integer trip_day = null;
					try {
						trip_day = new Integer(req.getParameter("trip_day").trim());
					} catch (NumberFormatException e) {
						trip_day = 1;
						errorMsgs.add("天數請填數字.");
					}
					Integer trip_sort = null;
					try {
						trip_sort = new Integer(req.getParameter("trip_sort").trim());
					} catch (NumberFormatException e) {
						trip_sort = 1;
						errorMsgs.add("順序請填數字.");
					}
					
					String trip_content = req.getParameter("trip_content").trim();
					if (trip_content == null || trip_content.trim().length() == 0) {
						trip_content = "Tibame";
						errorMsgs.add("行程內容: 請勿空白");
					}
					
					java.sql.Time trip_start_time = null;
					try {
						trip_start_time = java.sql.Time.valueOf(req.getParameter("trip_start_time").trim());
					} catch (IllegalArgumentException e) {
						trip_start_time=java.sql.Time.valueOf("00:00:00");
						errorMsgs.add("請輸入開始時間!");
					}
					java.sql.Time trip_end_time = null;
					try {
						trip_end_time = java.sql.Time.valueOf(req.getParameter("trip_end_time").trim());
					} catch (IllegalArgumentException e) {
						trip_end_time=java.sql.Time.valueOf("00:00:00");
						errorMsgs.add("請輸入結束時間!");
					}
					
					

					String trip_remarks = req.getParameter("trip_remarks").trim();
					if (trip_remarks == null || trip_remarks.trim().length() == 0) {
						trip_remarks = "無";
						errorMsgs.add("備註請勿空白");
					}	
					
					Integer trip_cost = null;
					try {
						trip_cost = new Integer(req.getParameter("trip_cost").trim());
					} catch (NumberFormatException e) {
						trip_cost = 0;
						errorMsgs.add("花費請填數字.");
					}

					Integer place_id=null;
					if(req.getParameter("place_id")!=null) {
						
						place_id = new Integer(req.getParameter("place_id").trim());
					}else {
						
						place_id=1;
					}
					
					Trip_detailVO trip_detailVO = new Trip_detailVO();
					 trip_detailVO.setTrip_id(trip_id);
					 trip_detailVO.setTrip_day(trip_day);
					 trip_detailVO.setTrip_sort(trip_sort);
					 trip_detailVO.setTrip_detail_type(trip_detail_type);
					 trip_detailVO.setTrip_content(trip_content);
					 trip_detailVO.setPlace_id(place_id);
					 trip_detailVO.setTrip_start_time(trip_start_time);
					 trip_detailVO.setTrip_end_time(trip_end_time);
					 trip_detailVO.setTrip_remarks(trip_remarks);
					 trip_detailVO.setTrip_cost(trip_cost);

					// Send the use back to the form, if there were errors
//					if (!errorMsgs.isEmpty()) {
//	req.setAttribute("trip_detailVO", trip_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/trip_detail/addEmp.jsp");
//						failureView.forward(req, res);
//						return;
//					}
					
					/***************************2.開始新增資料***************************************/
					Trip_detailService trip_detailSvc = new Trip_detailService();
					trip_detailVO = trip_detailSvc.addEmp(trip_detailVO);
					System.out.println("tripDetail add ok");
	    			HashMap result = new HashMap();
	    			PrintWriter out = res.getWriter();
	    			result.put("trip_detail",trip_detailVO);
	    			JSONObject resultJSON = new JSONObject(result);
					out.println(resultJSON);
//					trip_id,trip_day,trip_sort,trip_detail_type,trip_content,trip_start_time,trip_end_time,trip_remarks,trip_cost
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
//					String url = "/trip_detail/listAllEmp.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					System.out.println(e);
					errorMsgs.add(e.getMessage());
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/trip_detail/addEmp.jsp");
//					failureView.forward(req, res);
				}
			}
		
		
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("trip_detail_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入行程明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer trip_detail_id = null;
				try {
					trip_detail_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("行程明細編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Trip_detailService trip_detailSvc = new Trip_detailService();
				Trip_detailVO trip_detailVO = trip_detailSvc.getOneEmp(trip_detail_id);
				if (trip_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("trip_detailVO", trip_detailVO); // 資料庫取出的empVO物件,存入req
				String url = "/trip_detail/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip_detail/select_page.jsp");
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
				Integer trip_detail_id = new Integer(req.getParameter("trip_detail_id"));
				
				/***************************2.開始查詢資料****************************************/
				Trip_detailService trip_detailSvc = new Trip_detailService();
				Trip_detailVO trip_detailVO = trip_detailSvc.getOneEmp(trip_detail_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("trip_detailVO", trip_detailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/trip_detail/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer trip_detail_id = new Integer(req.getParameter("trip_detail_id").trim());
				Integer trip_id = new Integer(req.getParameter("trip_id").trim());
				String trip_detail_type = req.getParameter("trip_detail_type").trim();
				
				Integer trip_day = null;
				try {
					trip_day = new Integer(req.getParameter("trip_day").trim());
				} catch (NumberFormatException e) {
					trip_day = 1;
					errorMsgs.add("天數請填數字.");
				}
				Integer trip_sort = null;
				try {
					trip_sort = new Integer(req.getParameter("trip_sort").trim());
				} catch (NumberFormatException e) {
					trip_sort = 1;
					errorMsgs.add("順序請填數字.");
				}
				
				String trip_content = req.getParameter("trip_content").trim();
				if (trip_content == null || trip_content.trim().length() == 0) {
					trip_content = "Tibame";
					errorMsgs.add("行程內容: 請勿空白");
				}
				
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
//				if (activities_name == null || activities_name.trim().length() == 0) {
//					errorMsgs.add("活動名稱: 請勿空白");
//				} else if(!activities_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//	            }

				java.sql.Time trip_start_time = null;
				try {
					trip_start_time = java.sql.Time.valueOf(req.getParameter("trip_start_time").trim());
				} catch (IllegalArgumentException e) {
					trip_start_time=java.sql.Time.valueOf("00:00:00");
					errorMsgs.add("請輸入開始時間!");
				}
				java.sql.Time trip_end_time = null;
				try {
					trip_end_time = java.sql.Time.valueOf(req.getParameter("trip_end_time").trim());
				} catch (IllegalArgumentException e) {
					trip_end_time=java.sql.Time.valueOf("00:00:00");
					errorMsgs.add("請輸入結束時間!");
				}
				
				

				String trip_remarks = req.getParameter("trip_remarks").trim();
				if (trip_remarks == null || trip_remarks.trim().length() == 0) {
					trip_remarks = "無";
					errorMsgs.add("備註請勿空白");
				}	
				
				Integer trip_cost = null;
				try {
					trip_cost = new Integer(req.getParameter("trip_cost").trim());
				} catch (NumberFormatException e) {
					trip_cost = 0;
					errorMsgs.add("花費請填數字.");
				}

				
				
				Trip_detailVO trip_detailVO = new Trip_detailVO();
				trip_detailVO.setTrip_detail_id(trip_detail_id);
				 trip_detailVO.setTrip_id(trip_id);
				 trip_detailVO.setTrip_day(trip_day);
				 trip_detailVO.setTrip_sort(trip_sort);
				 trip_detailVO.setTrip_detail_type(trip_detail_type);
				 trip_detailVO.setTrip_content(trip_content);
//				 trip_detailVO.setPlace_id(place_id);
				 trip_detailVO.setTrip_start_time(trip_start_time);
				 trip_detailVO.setTrip_end_time(trip_end_time);
				 trip_detailVO.setTrip_remarks(trip_remarks);
				 trip_detailVO.setTrip_cost(trip_cost);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("trip_detailVO", trip_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_detail/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Trip_detailService trip_detailSvc = new Trip_detailService();
				trip_detailVO = trip_detailSvc.updateEmp(trip_detail_id,trip_id,trip_day,trip_sort,trip_detail_type,trip_content,trip_start_time,trip_end_time,trip_remarks,trip_cost);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("trip_detailVO", trip_detailVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/trip_detail/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
				
				TripService tripSvc = new TripService();
				if(requestURL.equals("/trip/listDetail_ByTrip.jsp") || requestURL.equals("/trip/listAllDept.jsp"))
					req.setAttribute("listDetail_ByTrip",tripSvc.getDetailByTrip(trip_id)); // 資料庫取出的list物件,存入request

                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip_detail/update_emp_input.jsp");
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
				Integer trip_id = new Integer(req.getParameter("trip_id").trim());
				String trip_detail_type = req.getParameter("trip_detail_type").trim();
				
				Integer trip_day = null;
				try {
					trip_day = new Integer(req.getParameter("trip_day").trim());
				} catch (NumberFormatException e) {
					trip_day = 1;
					errorMsgs.add("天數請填數字.");
				}
				Integer trip_sort = null;
				try {
					trip_sort = new Integer(req.getParameter("trip_sort").trim());
				} catch (NumberFormatException e) {
					trip_sort = 1;
					errorMsgs.add("順序請填數字.");
				}
				
				String trip_content = req.getParameter("trip_content").trim();
				if (trip_content == null || trip_content.trim().length() == 0) {
					trip_content = "Tibame";
					errorMsgs.add("行程內容: 請勿空白");
				}
				
				java.sql.Time trip_start_time = null;
				try {
					trip_start_time = java.sql.Time.valueOf(req.getParameter("trip_start_time").trim());
				} catch (IllegalArgumentException e) {
					trip_start_time=java.sql.Time.valueOf("00:00:00");
					errorMsgs.add("請輸入開始時間!");
				}
				java.sql.Time trip_end_time = null;
				System.out.println(req.getParameter("trip_end_time"));
				try {
					trip_end_time = java.sql.Time.valueOf(req.getParameter("trip_end_time").trim()+":00");
				} catch (IllegalArgumentException e) {
					trip_end_time=java.sql.Time.valueOf("00:00:00");
					errorMsgs.add("請輸入結束時間!");
				}
				
				

				String trip_remarks = req.getParameter("trip_remarks").trim();
				if (trip_remarks == null || trip_remarks.trim().length() == 0) {
					trip_remarks = "無";
					errorMsgs.add("備註請勿空白");
				}	
				
				Integer trip_cost = null;
				try {
					trip_cost = new Integer(req.getParameter("trip_cost").trim());
				} catch (NumberFormatException e) {
					trip_cost = 0;
					errorMsgs.add("花費請填數字.");
				}

				Integer place_id=null;
				if(req.getParameter("place_id")!=null) {
					
					place_id = new Integer(req.getParameter("place_id").trim());
				}else {
					
					place_id=1;
				}
				
				Trip_detailVO trip_detailVO = new Trip_detailVO();
				 trip_detailVO.setTrip_id(trip_id);
				 trip_detailVO.setTrip_day(trip_day);
				 trip_detailVO.setTrip_sort(trip_sort);
				 trip_detailVO.setTrip_detail_type(trip_detail_type);
				 trip_detailVO.setTrip_content(trip_content);
				 trip_detailVO.setPlace_id(place_id);
				 trip_detailVO.setTrip_start_time(trip_start_time);
				 trip_detailVO.setTrip_end_time(trip_end_time);
				 trip_detailVO.setTrip_remarks(trip_remarks);
				 trip_detailVO.setTrip_cost(trip_cost);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("trip_detailVO", trip_detailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/trip_detail/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Trip_detailService trip_detailSvc = new Trip_detailService();
				trip_detailVO = trip_detailSvc.addEmp(trip_detailVO);
//				trip_id,trip_day,trip_sort,trip_detail_type,trip_content,trip_start_time,trip_end_time,trip_remarks,trip_cost
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/trip_detail/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip_detail/addEmp.jsp");
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
				Integer trip_detail_id = new Integer(req.getParameter("trip_detail_id"));
				
				/***************************2.開始刪除資料***************************************/
				Trip_detailService trip_detailSvc = new Trip_detailService();
				trip_detailSvc.deleteEmp(trip_detail_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/trip_detail/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/trip_detail/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
