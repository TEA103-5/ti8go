package com.place_collect.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.place.model.PlaceService;
import com.place_collect.model.Place_collectService;
import com.place_collect.model.Place_collectVO;

public class Place_collectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String source = req.getParameter("source");

//		if ("getOne_For_Display".equals(action)) {
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String str = req.getParameter("users_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入使用者編號");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				Integer users_id = null;
//				try {
//					users_id = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("使用者格式不正確");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				Place_collectService place_collectSvc = new Place_collectService();
//				Place_collectVO place_collectVO = place_collectSvc.getOnePlace_collect(users_id);
//				if (place_collectVO == null) {
//					errorMsgs.add("查無資料");
//				}
//
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("place_collectVO", place_collectVO); // 資料庫取出的empVO物件,存入req
//				String url = "/place_collect/listOnePlace_collect.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/select_page.jsp");
//				failureView.forward(req, res);
//			}
//
//		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer place_id = null;
				try {
					place_id = new Integer(req.getParameter("place_id").trim());
				} catch (NumberFormatException e) {
					place_id = 1;
					errorMsgs.add("地點編號請填數字");
				}

				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 1;
					errorMsgs.add("使用者請填數字");
				}

				Place_collectVO place_collectVO = new Place_collectVO();

				place_collectVO.setPlace_id(place_id);
				place_collectVO.setUsers_id(users_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("place_collectVO", place_collectVO); // 含有輸入格式錯誤的place_collectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/addPlace_collect.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Place_collectService place_collectSvc = new Place_collectService();
				place_collectVO = place_collectSvc.addPlace_collect(place_id, users_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/place_collect/listAllPlace_collect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPlace_collect.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/addPlace_collect.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllPlace_collect.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 ***************************************/
				Integer place_id = new Integer(req.getParameter("place_id"));
				Integer users_id = new Integer(req.getParameter("users_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Place_collectService place_collectSvc = new Place_collectService();
				place_collectSvc.deletePlace_collect(place_id, users_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				if("fromGetAllByPrimaryKey".equals(source)) { // 從getAllByPrimaryKey.jsp來的delete請求
					String url = "/place_collect/getAllByPrimaryKey.jsp" ;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else { // 從listAllPlace_collect.jsp來的delete請求
					String url = "/place_collect/listAllPlace_collect.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);					
				}
				
				

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/place_collect/listAllPlace_collect.jsp");
				failureView.forward(req, res);
			
			}
		}
		
		// 自己新增的方法, 對應介面getAllByPrimaryKey(Integer users_id)
//		if("getAllByPrimaryKey".equals(action)) {
//			
//			/***************************1.直接轉接***************************************/
//			String url = "/place_collect/getAllByPrimaryKey.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			
			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/***************************1.接收請求參數***************************************/
//			Integer users_id = new Integer(req.getParameter("users_id"));
//			
//			/*************************** 2.開始查詢資料 ***************************************/
//			Place_collectService place_collectSvc = new Place_collectService();
			
			
//		}

	}

}
