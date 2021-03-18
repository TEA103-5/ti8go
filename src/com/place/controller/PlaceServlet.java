package com.place.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.place.model.PlaceService;
import com.place.model.PlaceVO;

public class PlaceServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("place_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/place/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer place_id = null;
				try {
					place_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/place/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_id);
				if (placeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/place/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("placeVO", placeVO);
				String url = "/place/listOnePlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/place/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllPlace.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer place_id = new Integer(req.getParameter("place_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("placeVO", placeVO);
				String url = "/place/update_place_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/place/listAllPlace.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_place_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer place_id = new Integer(req.getParameter("place_id").trim());

				String place_name = req.getParameter("place_name");
				String place_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (place_name == null || place_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!place_name.trim().matches(place_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String place_address = req.getParameter("place_address").trim();
				if (place_address == null || place_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				// 先用Double確認格式, 確定沒錯後再轉成BigDecimal
				Double longitude = null;
				BigDecimal place_longitude = null;
				try {
					longitude = new Double(req.getParameter("place_longitude").trim());
					if ((longitude < -180) || (longitude > 180)) {
						errorMsgs.add("不是有效的經度,經度輸入範圍為東經(180)~西經(-180)");
					} else {
						place_longitude = new BigDecimal(longitude);
					}
				} catch (NumberFormatException e) {
					place_longitude = new BigDecimal(0.0);
					errorMsgs.add("經度請填數字.");
				}

				// 先用Double確認格式, 確定沒錯後再轉成BigDecimal
				Double latitude = null;
				BigDecimal place_latitude = null;
				try {
					latitude = new Double(req.getParameter("place_latitude").trim());
					if ((latitude < -90) || (latitude > 90)) {
						errorMsgs.add("不是有效的緯度,緯度輸入範圍為北緯(90)~南緯(-90)");
					} else {
						place_latitude = new BigDecimal(latitude);
					}
				} catch (NumberFormatException e) {
					place_latitude = new BigDecimal(0.0);
					errorMsgs.add("緯度請填數字.");
				}

				String place_tel = req.getParameter("place_tel").trim();
				if (place_tel == null || place_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白(尚未改正規表示法)");
				}

				String place_region = req.getParameter("place_region").trim();
				if (place_region == null || place_region.trim().length() == 0) {
					errorMsgs.add("縣市請勿空白(尚未改正規表示法)");
				}

				String place_type = req.getParameter("place_type").trim();
				if (place_type == null || place_type.trim().length() == 0) {
					errorMsgs.add("地點類型請勿空白");
				}

				String place_index = req.getParameter("place_index").trim();
				if (place_index == null || place_index.trim().length() == 0) {
					errorMsgs.add("地點簡介請勿空白");
				}

//				圖片可為空白 , 但如有圖片要顯示在畫面上
				byte[] place_pic1 = { 1 };
				byte[] place_pic2 = { 1 };
				byte[] place_pic3 = { 1 };

				Integer place_state = null;
				try {
					place_state = new Integer(req.getParameter("place_state").trim());
				} catch (NumberFormatException e) {
					place_state = 0;
					errorMsgs.add("地點狀態請填數字.");
				}

				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 0;
					errorMsgs.add("建立者請填數字.");
				}

				Integer business_time = null;
				try {
					business_time = new Integer(req.getParameter("business_time").trim());
				} catch (NumberFormatException e) {
					business_time = 0;
					errorMsgs.add("營業時間請填數字.");
				}

//				讚數不需要錯誤處理

//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				PlaceVO placeVO = new PlaceVO();

//				System.out.println(place_id);	
				
				placeVO.setPlace_id(place_id);
				placeVO.setPlace_name(place_name);
				placeVO.setPlace_address(place_address);
				placeVO.setPlace_longitude(place_longitude);
				placeVO.setPlace_latitude(place_latitude);
				placeVO.setPlace_tel(place_tel);
				placeVO.setPlace_region(place_region);
				placeVO.setPlace_type(place_type);
				placeVO.setPlace_index(place_index);

//				placeVO.setPlace_pic1( place_pic1);
//				placeVO.setPlace_pic2( place_pic2);
//				placeVO.setPlace_pic3( place_pic3);
				placeVO.setPlace_state(place_state);
				placeVO.setUsers_id(users_id);
				placeVO.setBusiness_time(business_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("placeVO", placeVO); // 含有輸入格式錯誤的placeVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/place/update_place_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PlaceService placeSvc = new PlaceService();
				placeVO = placeSvc.updatePlace(place_id, place_name, place_address, place_longitude, place_latitude,
						place_tel, place_region, place_type, place_index, place_pic1, place_pic2, place_pic3,
						place_state, users_id, business_time);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("placeVO", placeVO); // 資料庫update成功後,正確的的placeVO物件,存入req
				String url = "/place/listOnePlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePlace.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/place/update_place_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addPlace.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String place_name = req.getParameter("place_name");
				String place_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (place_name == null || place_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!place_name.trim().matches(place_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String place_address = req.getParameter("place_address").trim();
				if (place_address == null || place_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				// 先用Double確認格式, 確定沒錯後再轉成BigDecimal
				Double longitude = null;
				BigDecimal place_longitude = null;
				try {
					longitude = new Double(req.getParameter("place_longitude").trim());
					if ((longitude < -180) || (longitude > 180)) {
						errorMsgs.add("不是有效的經度,經度輸入範圍為東經(180)~西經(-180)");
					} else {
						place_longitude = new BigDecimal(longitude);
					}
				} catch (NumberFormatException e) {
					place_longitude = new BigDecimal(0.0);
					errorMsgs.add("經度請填數字.");
				}

				// 先用Double確認格式, 確定沒錯後再轉成BigDecimal
				Double latitude = null;
				BigDecimal place_latitude = null;
				try {
					latitude = new Double(req.getParameter("place_latitude").trim());
					if ((latitude < -90) || (latitude > 90)) {
						errorMsgs.add("不是有效的緯度,緯度輸入範圍為北緯(90)~南緯(-90)");
					} else {
						place_latitude = new BigDecimal(latitude);
					}
				} catch (NumberFormatException e) {
					place_latitude = new BigDecimal(0.0);
					errorMsgs.add("緯度請填數字.");
				}

				String place_tel = req.getParameter("place_tel").trim();
				if (place_tel == null || place_tel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白(尚未改正規表示法)");
				}

				String place_region = req.getParameter("place_region").trim();
				if (place_region == null || place_region.trim().length() == 0) {
					errorMsgs.add("縣市請勿空白(尚未改正規表示法)");
				}

				String place_type = req.getParameter("place_type").trim();
				if (place_type == null || place_type.trim().length() == 0) {
					errorMsgs.add("地點類型請勿空白");
				}

				String place_index = req.getParameter("place_index").trim();
				if (place_index == null || place_index.trim().length() == 0) {
					errorMsgs.add("地點簡介請勿空白");
				}

//				圖片可為空白 , 但如有圖片要顯示在畫面上
				byte[] place_pic1 = { 1 };
				byte[] place_pic2 = { 1 };
				byte[] place_pic3 = { 1 };

				Integer place_state = null;
				try {
					place_state = new Integer(req.getParameter("place_state").trim());
				} catch (NumberFormatException e) {
					place_state = 0;
					errorMsgs.add("地點狀態請填數字.");
				}

				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 0;
					errorMsgs.add("建立者請填數字.");
				}

				Integer business_time = null;
				try {
					business_time = new Integer(req.getParameter("business_time").trim());
				} catch (NumberFormatException e) {
					business_time = 0;
					errorMsgs.add("營業時間請填數字.");
				}

//				Integer deptno = new Integer(req.getParameter("deptno").trim());

				PlaceVO placeVO = new PlaceVO();
				placeVO.setPlace_name(place_name);
				placeVO.setPlace_address(place_address);
				placeVO.setPlace_longitude(place_longitude);
				placeVO.setPlace_latitude(place_latitude);
				placeVO.setPlace_tel(place_tel);
				placeVO.setPlace_region(place_region);
				placeVO.setPlace_type(place_type);
				placeVO.setPlace_index(place_index);

//				placeVO.setPlace_pic1( place_pic1);
//				placeVO.setPlace_pic2( place_pic2);
//				placeVO.setPlace_pic3( place_pic3);
				placeVO.setPlace_state(place_state);
				placeVO.setUsers_id(users_id);
				placeVO.setBusiness_time(business_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("placeVO", placeVO); // 含有輸入格式錯誤的placeVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/place/addPlace.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				PlaceService placeSvc = new PlaceService();
				placeVO = placeSvc.addPlace(place_name, place_address, place_longitude, place_latitude, place_tel, place_region, place_type, place_index, place_pic1, place_pic2, place_pic3, place_state, users_id, business_time);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/place/listAllPlace.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/place/addPlace.jsp");
				failureView.forward(req, res);
			}

		}
	}

}
