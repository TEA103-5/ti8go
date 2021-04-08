package com.order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;


public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("ordid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/selectOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer ordid = null;
				try {
					ordid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("ID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 要是erro不是空的
					RequestDispatcher failureView = req.getRequestDispatcher("/selectOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				OrderService ordSvc = new OrderService();
				
				OrderVO ordVO = ordSvc.getOneOrder(ordid);
		
				System.out.println(ordVO);
				if (ordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/selectOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ordVO", ordVO); // 資料庫取出的empVO物件,存入req
				String url = "/Order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始查詢資料 ****************************************/
				OrderService empSvc = new OrderService();
				OrderVO empVO = empSvc.getOneOrder(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("OrderVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/Order/update_Order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
Integer empno = new Integer(req.getParameter("empno").trim());


				Integer status = null;
				try {
					status = new Integer(req.getParameter("Order_status").trim());
				} catch (NumberFormatException e) {
					status = 0;
					errorMsgs.add("狀態請填數字.");
				}
			
				Integer rate = null;
				try {
					rate = new Integer(req.getParameter("Order_rate").trim());
				} catch (NumberFormatException e) {
					rate = 0;
					errorMsgs.add("評價請填數字.");
				}
				
				String number = req.getParameter("Card_number");
				String numberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,16}$";
				if (number == null || number.trim().length() == 0) {
					errorMsgs.add("內容: 請勿空白");
				} else if (!number.trim().matches(numberReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到16之間");
				}
				
				
				

			//	Integer User_id = new Integer(req.getParameter("User_id").trim());
			

				OrderVO empVO = new OrderVO();
				empVO.setOrder_status(status);
				empVO.setOrder_rate(rate);
				empVO.setCard_number(number);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("OrderVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Order/update_Order.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderService empSvc = new OrderService();
			 	empVO = empSvc.updateOrder(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			 	req.setAttribute("OrderVO", empVO);
			 	String url = "/Order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Order/update_Order.jsp");
				failureView.forward(req, res);
			}
		}   
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer status = null;
				try {
					status = new Integer(req.getParameter("order_status").trim());
				} catch (NumberFormatException e) {
					status = 0;
					errorMsgs.add("狀態請填數字.");
				}
			
				Integer rate = null;
				try {
					rate = new Integer(req.getParameter("order_rate").trim());
				} catch (NumberFormatException e) {
					rate = 0;
					errorMsgs.add("評價請填數字.");
				}
				
				String number = req.getParameter("card_number");
				String numberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,16}$";
				if (number == null || number.trim().length() == 0) {
					errorMsgs.add("內容: 請勿空白");
				} else if (!number.trim().matches(numberReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到16之間");
				}
				
				
				

			//	Integer User_id = new Integer(req.getParameter("User_id").trim());
			

				OrderVO empVO = new OrderVO();
				empVO.setOrder_status(status);
				empVO.setOrder_rate(rate);
				empVO.setCard_number(number);
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("OrderVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Order/addOrder.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderService empSvc = new OrderService();
			 	empVO = empSvc.addOrder(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Order/addOrder.jsp");
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
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始刪除資料***************************************/
				OrderService empSvc = new OrderService();
				empSvc.deleteOrder(empno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Order/listAllOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Order/listAllOrder.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
