package com.place.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws UnsupportedEncodingException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("place_id");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer place_id = null;
				try {
					place_id = new Integer(str);
				}catch(Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷 
				}
				
				/***************************2.開始查詢資料*****************************************/
				PlaceService PlaceSvc = new PlaceService();
				PlaceVO placeVO = PlaceSvc.getOnePlace(place_id);
				if(placeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/place/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷 
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("placeVO", placeVO);
				String url = "/place/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/place/select_page.jsp");
			}
			
		}
		
		
		
	}

}
