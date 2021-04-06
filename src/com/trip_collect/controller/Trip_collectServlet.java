package com.trip_collect.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trip_collect.model.Trip_collectService;
import com.trip_collect.model.Trip_collectVO;

public class Trip_collectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String source = req.getParameter("source");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer trip_id = new Integer(req.getParameter("trip_id").trim());

				Integer users_id = new Integer(req.getParameter("users_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				Trip_collectService trip_collectSvc = new Trip_collectService();
				Trip_collectVO trip_collectVO = trip_collectSvc.getOneTrip_collect(trip_id, users_id);
				if (trip_collectVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("trip_collectVO", trip_collectVO); //
				String url = "/trip_collect/listOneTrip_collect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer trip_id = null;
				try {
					trip_id = new Integer(req.getParameter("trip_id").trim());
				} catch (NumberFormatException e) {
					trip_id = 1;
					errorMsgs.add("地點編號請填數字");
				}

				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 1;
					errorMsgs.add("使用者請填數字");
				}

				Trip_collectVO trip_collectVO = new Trip_collectVO();

				trip_collectVO.setTrip_id(trip_id);
				trip_collectVO.setUsers_id(users_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("trip_collectVO", trip_collectVO); // 含有輸入格式錯誤的trip_collectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/addTrip_collect.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Trip_collectService trip_collectSvc = new Trip_collectService();
				trip_collectVO = trip_collectSvc.addTrip_collect(trip_id, users_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/trip_collect/listAllTrip_collect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllTrip_collect.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/addTrip_collect.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllTrip_collect.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 ***************************************/
				Integer trip_id = new Integer(req.getParameter("trip_id"));
				Integer users_id = new Integer(req.getParameter("users_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Trip_collectService trip_collectSvc = new Trip_collectService();
				trip_collectSvc.deleteTrip_collect(trip_id, users_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				if ("fromGetAllByPrimaryKey".equals(source)) { // 從getAllByPrimaryKey.jsp來的delete請求
					String url = "/trip_collect/getAllByPrimaryKey.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else { // 從listAllTrip_collect.jsp來的delete請求
					String url = "/trip_collect/listAllTrip_collect.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/listAllTrip_collect.jsp");
				failureView.forward(req, res);

			}
		}

		// 自己新增的方法, 對應介面getAllByPrimaryKey(Integer users_id)
		if ("getAllByPrimaryKey".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer users_id = new Integer(req.getParameter("users_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				Trip_collectService trip_collectSvc = new Trip_collectService();
				List<Trip_collectVO> list = trip_collectSvc.getAllByPrimaryKey(users_id);

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list);
				String url = "/trip_collect/getAllByPrimaryKey.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/trip_collect/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
