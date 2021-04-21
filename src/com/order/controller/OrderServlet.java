package com.order.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.orderDetail.model.OrderDetailVO;
import com.users.model.UsersService;



public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		res.setContentType("application/json ; charset=UTF-8");
		String action = req.getParameter("action");

		
		if ("listOrderBySaleId".equals(action)) {
			String url = null;
			url =  "/sale-end/order/listAllProductBySale.jsp";  
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer sale_id=null;
				if(req.getParameter("sale_id")!=null) {
					
					sale_id = new Integer(req.getParameter("sale_id"));
				}
	
				/*************************** 2.開始查詢資料 ****************************************/
				OrderService uSvc = new OrderService();
				List<OrderVO> set = uSvc.getProBySaleId(sale_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listProBySale_id", set);    // 資料庫取出的set物件,存入request

			
//				if ("listEmps_ByDeptno_A".equals(action))
					      // 成功轉交 sale/listProds_BySaleid.jsp
			
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		

		/*************************** 士翔開始 ****************************************/		
		if ("insertajax".equals(action)) { // 來自addEmp.jsp的請求
			
			//將orderdtail們(pid & count) 轉成VO放入hashmap
			//建立order並取得新創order pk * //autocommit false
			//將hashmap傳入DAO再轉成VO輸入資料庫 * //若有意外需要rollback
														  //autocommit true
			
			HashMap result = new HashMap();
			PrintWriter out = res.getWriter();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			int b=0;
//			System.out.println(req.getParameter("array["+b+"][name]"));
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
				Integer arraysize = new Integer(req.getParameter("arraysize").trim());
				for(int i=0;i<arraysize;i++) {
					OrderDetailVO ovo=new OrderDetailVO();
					ovo.setProduct_id(new Integer(req.getParameter("array["+i+"][name]").trim()));
					ovo.setOrder_detail_count(new Integer(req.getParameter("array["+i+"][count]").trim()));
					result.put(i,ovo);
				}
				
				String address = req.getParameter("address");
				String number = req.getParameter("card_number");
			//	String numberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,16}$";
//				if (number == null || number.trim().length() == 0) {
//					errorMsgs.add("內容: 請勿空白");
//				} else if (!number.trim().matches(numberReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到16之間");
//				}
				Integer Users_id = new Integer(req.getParameter("users_id").trim());

				java.sql.Date date = new java.sql.Date(new Date().getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowTime = sdf.format(date);
				Timestamp dates = Timestamp.valueOf(nowTime);
				
				
				

				OrderVO empVO = new OrderVO();
//				empVO.setOrder_status(status);
				empVO.setCard_number(number);
				empVO.setUsers_id(Users_id);
				empVO.setOrder_date(dates);
				empVO.setOrder_address(address);
				
				
	
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("OrderVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/order/addOrder.jsp");
//					failureView.forward(req, res);
//					return;
//				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderService empSvc = new OrderService();
			
			 	empVO = empSvc.addOrder2(empVO,result);
			 	
			 	
//				result.put(1, empVO);
			//	System.out.println(result.get(1));
//				empVO=(OrderVO) result.get(1);
//				System.out.println(empVO.getOrder_date());
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/sale-end/order/listAllOrder.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e);
				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/order/addOrder.jsp");
//				failureView.forward(req, res);
			}
		}  
		
		/*************************** 士翔結束 ****************************************/		
		
		
		if ("listOrderByUsersId_A".equals(action) 
//				|| "listEmps_ByDeptno_B".equals(action)
				) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer uid = new Integer(req.getParameter("usersId"));
				

				/*************************** 2.開始查詢資料 ****************************************/
				OrderService uSvc = new OrderService();
				Set<OrderVO> set = uSvc.getOrderByUsersId(uid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listOrderByUsersId", set);    // 資料庫取出的set物件,存入request

				String url = null;
//				if ("listEmps_ByDeptno_A".equals(action))
					url = "/sale-end/order/listOrderByUsersId.jsp";        // 成功轉交 sale/listProds_BySaleid.jsp
				
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

				String url = "/sale-end/order/update_Order.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/order/listAllOrder.jsp");

				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("URL");
			
		

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

//			
				
//				String status = req.getParameter("Card_number");
				String statusReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,2}$";
//				if (status == null || status.trim().length() == 0) {
//					errorMsgs.add("卡號: 請勿空白");
//				} else if (!status.trim().matches(statusReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("卡號: 只能是中、英文字母、數字和_ , 且長度必需在1到2之間");
//				}
				
			
				String number = req.getParameter("Card_number");
				String numberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,16}$";
				if (number == null || number.trim().length() == 0) {
					errorMsgs.add("卡號: 請勿空白");
				} else if (!number.trim().matches(numberReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("卡號: 只能是中、英文字母、數字和_ , 且長度必需在2到16之間");

				}
				
				
				


				Integer Users_id = new Integer(req.getParameter("users_id").trim());
			

				OrderVO empVO = new OrderVO();
				empVO.setOrder_id(empno);
				empVO.setOrder_status(status);
				empVO.setCard_number(number);
				empVO.setUsers_id(Users_id);

				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("orderVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/order/update_Order.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				OrderService empSvc = new OrderService();
			 	 empSvc.updateOrder(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				if (requestURL.equals("/sale-end/order/listOrderByUsersId.jsp")
//						|| requestURL.equals("/sale-end/order/listAllOrder.jsp")) {
//					req.setAttribute("listUsersByOrder", empSvc.getOrderByUsersId(Users_id)); // 資料庫取出的list物件,存入request
//				}
			 	req.setAttribute("OrderVO",empSvc.getOneOrder(empno));
				String url = "/sale-end/order/update_Order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);


				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req

						.getRequestDispatcher("/sale-end/order/update_Order.jsp");

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
			
				
				String number = req.getParameter("card_number");
				String numberReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,16}$";
				if (number == null || number.trim().length() == 0) {
					errorMsgs.add("內容: 請勿空白");
				} else if (!number.trim().matches(numberReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到16之間");
				}
				
				
				

				Integer Users_id = new Integer(req.getParameter("users_id").trim());

			

				OrderVO empVO = new OrderVO();
				empVO.setOrder_status(status);

				empVO.setCard_number(number);
				empVO.setUsers_id(Users_id);

				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("OrderVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/order/addOrder.jsp");

					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderService empSvc = new OrderService();
			 	empVO = empSvc.addOrder(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/sale-end/order/listAllOrder.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/order/addOrder.jsp");

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

				String url = "/sale-end/order/listAllOrder.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req

						.getRequestDispatcher("/sale-end/order/listAllOrder.jsp");

				failureView.forward(req, res);
			}
		}

	}
}
