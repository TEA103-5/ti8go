package com.orderDetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import java.util.Set;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;

public class OrderDetailServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if("updateS".equals(action) ) {
			Integer uid = new Integer(req.getParameter("detail_id"));
			OrderDetailService uSvc = new OrderDetailService();
			uSvc.updates(uid);
			
			
//			Integer sale_id = new Integer(req.getParameter("sale_id"));
//			req.setAttribute("sale_id",sale_id);
//			req.setAttribute("action","listOrderBySaleId");
			
			
			//System.out.println(req.getContextPath());
			
			RequestDispatcher successView = req.getRequestDispatcher("/sale-end/saleEndIndex.jsp");
			successView.forward(req, res);
		}
		if ("listAllOrderDetail_A".equals(action) 
//				|| "listEmps_ByDeptno_B".equals(action)
				) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer uid = new Integer(req.getParameter("ord"));
				

				/*************************** 2.開始查詢資料 ****************************************/
				OrderDetailService uSvc = new OrderDetailService();
				Set<OrderDetailVO> set = uSvc.getOrderByOrderDetail(uid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllOrderDetail", set);    // 資料庫取出的set物件,存入request

				String url = null;
//				if ("listEmps_ByDeptno_A".equals(action))
					url = "/sale-end/orderDetail/listOrderDetailByOrderId.jsp";        // 成功轉交 sale/listProds_BySaleid.jsp
				
//				else if ("listEmps_ByDeptno_B".equals(action))
//					url = "/sale/ListAllSale.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("ordtid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/selectOrderDetail.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/selectOrderDetail.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				OrderDetailService ordSvc = new OrderDetailService();
				
				OrderDetailVO ordVO = ordSvc.getOneOrderDetail(ordid);
		
				System.out.println("ordVO"+ordVO);
				if (ordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/selectOrderDetail.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ordtVO", ordVO); // 資料庫取出的empVO物件,存入req
		System.out.println("ordVO"+ordVO);
				String url = "/OrderDetail/listOneOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectOrderDetail.jsp");
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
				OrderDetailService empSvc = new OrderDetailService();
				OrderDetailVO empVO = empSvc.getOneOrderDetail(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("OrderDetailVO", empVO); // 資料庫取出的empVO物件,存入req

				String url = "/sale-end/orderDetail/update_OrderDetail.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/orderDetail/listAllOrderDetail.jsp");

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


				Integer count = null;
				try {
					count = new Integer(req.getParameter("detail_count").trim());
				} catch (NumberFormatException e) {
					count = 0;
					errorMsgs.add("數量請填數字.");
				}
			

//				Integer detail_id = null;
//				try {
//					detail_id = new Integer(req.getParameter("detail_id").trim());
//				} catch (NumberFormatException e) {
//					detail_id = 0;
//					errorMsgs.add("明細編號請填數字.");
//				}

				
				Integer order_id = null;
				try {
					order_id = new Integer(req.getParameter("order_id").trim());
				} catch (NumberFormatException e) {
					order_id = 0;
					errorMsgs.add("訂單編號請填數字.");
				}
				
				Integer product_id = null;
				try {
					product_id = new Integer(req.getParameter("product_id").trim());
				} catch (NumberFormatException e) {
					product_id = 0;
					errorMsgs.add("商品編號請填數字.");
				}
				

				Integer detail_status = null;
				try {
					detail_status = new Integer(req.getParameter("detail_status").trim());
				} catch (NumberFormatException e) {
					detail_status = 0;
					errorMsgs.add("明細狀態請填數字.");
				}
				


			//	Integer User_id = new Integer(req.getParameter("User_id").trim());
			

				OrderDetailVO empVO = new OrderDetailVO();
				empVO.setOrder_detail_count(count);

				empVO.setOrder_detail_id(empno);
				empVO.setOrder_id(order_id);
				empVO.setProduct_id(product_id);
				empVO.setOrder_detail_status(detail_status);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("OrderDetailVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/orderDetail/update_OrderDetail.jsp");

					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderDetailService empSvc = new OrderDetailService();
				//empVO = empSvc.addProduct(pname,status,time,content,description,categories,price,sale_id);
			 	empVO = empSvc.updateDetail(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			 	System.out.println(empVO);
			 	req.setAttribute("ordtVO", empVO);
			 	System.out.println(empVO);
			 	String url = "/sale-end/orderDetail/listOneOrderDetail.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req

						.getRequestDispatcher("/sale-end/orderDetail/update_OrderDetail.jsp");

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
				Integer count = null;
				try {
					count = new Integer(req.getParameter("detail_count").trim());
				} catch (NumberFormatException e) {
					count = 0;
					errorMsgs.add("數量請填數字.");
				}
			
				Integer detail_id = null;
				try {
					detail_id = new Integer(req.getParameter("detail_id").trim());
				} catch (NumberFormatException e) {
					detail_id = 0;
					errorMsgs.add("明細編號請填數字.");
				}
				
				Integer order_id = null;
				try {
					order_id = new Integer(req.getParameter("order_id").trim());
				} catch (NumberFormatException e) {
					order_id = 0;
					errorMsgs.add("訂單編號請填數字.");
				}
				
				Integer product_id = null;
				try {
					product_id = new Integer(req.getParameter("product_id").trim());
				} catch (NumberFormatException e) {
					product_id = 0;
					errorMsgs.add("商品編號請填數字.");
				}
				

				Integer detail_status = null;
				try {
					detail_status = new Integer(req.getParameter("detail_status").trim());
				} catch (NumberFormatException e) {
					detail_status = 0;
					errorMsgs.add("明細狀態請填數字.");
				}


				OrderDetailVO empVO = new OrderDetailVO();
				empVO.setOrder_detail_count(count);
				empVO.setOrder_detail_id(detail_id);
				empVO.setOrder_id(order_id);
				empVO.setProduct_id(product_id);

				empVO.setOrder_detail_status(detail_status);

				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("OrderDetailVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/orderDetail/addOrderDetail.jsp");

					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderDetailService empSvc = new OrderDetailService();
				//empVO = empSvc.addProduct(pname,status,time,content,description,categories,price,sale_id);
			 	empVO = empSvc.addDetail(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/sale-end/orderDetail/listAllOrderDetail.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/orderDetail/addOrderDetail.jsp");

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
				OrderDetailService empSvc = new OrderDetailService();
				empSvc.deleteOrderDetail(empno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								

				String url = "/sale-end/orderDetail/listAllOrderDetail.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req

						.getRequestDispatcher("/sale-end/orderDetail/listAllOrderDetail.jsp");

				failureView.forward(req, res);
			}
		}

	}
}
