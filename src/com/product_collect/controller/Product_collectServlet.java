package com.product_collect.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product_collect.model.Product_collectService;
import com.product_collect.model.Product_collectVO;

public class Product_collectServlet extends HttpServlet {

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
				Integer product_id = new Integer(req.getParameter("product_id").trim());

				Integer users_id = new Integer(req.getParameter("users_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				Product_collectService product_collectSvc = new Product_collectService();
				Product_collectVO product_collectVO = product_collectSvc.getOneProduct_collect(product_id, users_id);
				if (product_collectVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("product_collectVO", product_collectVO); //
				String url = "/product_collect/listOneProduct_collect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer product_id = null;
				try {
					product_id = new Integer(req.getParameter("product_id").trim());
				} catch (NumberFormatException e) {
					product_id = 1;
					errorMsgs.add("地點編號請填數字");
				}

				Integer users_id = null;
				try {
					users_id = new Integer(req.getParameter("users_id").trim());
				} catch (NumberFormatException e) {
					users_id = 1;
					errorMsgs.add("使用者請填數字");
				}

				Product_collectVO product_collectVO = new Product_collectVO();

				product_collectVO.setProduct_id(product_id);
				product_collectVO.setUsers_id(users_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_collectVO", product_collectVO); // 含有輸入格式錯誤的product_collectVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/addProduct_collect.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Product_collectService product_collectSvc = new Product_collectService();
				product_collectVO = product_collectSvc.addProduct_collect(product_id, users_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/product_collect/listAllProduct_collect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct_collect.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/addProduct_collect.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllProduct_collect.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 ***************************************/
				Integer product_id = new Integer(req.getParameter("product_id"));
				Integer users_id = new Integer(req.getParameter("users_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Product_collectService product_collectSvc = new Product_collectService();
				product_collectSvc.deleteProduct_collect(product_id, users_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				if ("fromGetAllByPrimaryKey".equals(source)) { // 從getAllByPrimaryKey.jsp來的delete請求
					String url = "/product_collect/getAllByPrimaryKey.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else { // 從listAllProduct_collect.jsp來的delete請求
					String url = "/product_collect/listAllProduct_collect.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/listAllProduct_collect.jsp");
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
				Product_collectService product_collectSvc = new Product_collectService();
				List<Product_collectVO> list = product_collectSvc.getAllByPrimaryKey(users_id);

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list);
				String url = "/product_collect/getAllByPrimaryKey.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_collect/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
