package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ProductServlet
 */
@MultipartConfig
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
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
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/selectProduct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer empno = null;
				try {
					empno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 要是erro不是空的
					RequestDispatcher failureView = req.getRequestDispatcher("/selectProduct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ProductService empSvc = new ProductService();
				ProductVO empVO = empSvc.getOneProduct(empno);
				System.out.println(empVO);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/selectProduct.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/Product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/selectProduct.jsp");
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
				ProductService empSvc = new ProductService();
				ProductVO empVO = empSvc.getOneProduct(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/Product/update_Product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Product/listAllProduct.jsp");
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

				String pname = req.getParameter("product_name");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!pname.trim().matches(pnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer status = null;
				try {
					status = new Integer(req.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					status = 0;
					errorMsgs.add("狀態請填數字.");
				}
//				Timestamp time = null;
//				try {
//					time = Timestamp.valueOf(req.getParameter("product_update_time").trim());
//				} catch (IllegalArgumentException e) {
//					time = new Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				String content = req.getParameter("product_content");
				String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容: 請勿空白");
				} else if (!content.trim().matches(contentReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
				}
				
				String description = req.getParameter("product_description");
				String descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("描述: 請勿空白");
				} else if (!description.trim().matches(descriptionReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("描述: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
				}
				
				String categories = req.getParameter("product_categories");
				String categoriesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (categories == null || categories.trim().length() == 0) {
					errorMsgs.add("分類: 請勿空白");
				} else if (!categories.trim().matches(categoriesReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("分類: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}


				Integer price = null;
				try {
					price = new Integer(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}

				Integer stock = null;
				try {
					stock = new Integer(req.getParameter("product_stock").trim());
				} catch (NumberFormatException e) {
					stock = 0;
					errorMsgs.add("庫存請填數字.");
				}
			
				Part part=null;
				byte[] buf;
				ProductService empSvc1 = new ProductService();
				if(req.getPart("product_pic").getSize() > 0) {
				part=req.getPart("product_pic");
				InputStream in = part.getInputStream();
				 buf =new byte[in.available()];
				in.read(buf);
				in.close();
				}else {
				 buf = (empSvc1.getOneProduct(empno)).getProduct_pic();
				}
//		
				
				System.out.println("part2");
			
				

//			
				//byte[] pic = new byte[](req.getParameter("product_pic"));
			
				
				

			//	Integer sale_id = new Integer(req.getParameter("sale_id").trim());
			

				ProductVO empVO = new ProductVO();
				empVO.setProduct_id(empno);
				empVO.setProduct_name(pname);
				empVO.setProduct_status(status);
				empVO.setProduct_content(content);
				empVO.setProduct_description(description);
				empVO.setProduct_categories(categories);
				empVO.setProduct_price(price);
				empVO.setProduct_stock(stock);
				
				empVO.setProduct_pic(buf);
				
				
				
				
				//empVO.setProduct_update_time(time);
			//	empVO.setSale_id(sale_id);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Product/update_Product.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService empSvc = new ProductService();
				//empVO = empSvc.addProduct(pname,status,time,content,description,categories,price,sale_id);
			 	empVO = empSvc.updateProduct(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			 	req.setAttribute("empVO", empVO);
			 	String url = "/Product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (RuntimeException e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Product/update_Product.jsp");
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
				String pname = req.getParameter("product_name");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!pname.trim().matches(pnameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer status = null;
				try {
					status = new Integer(req.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					status = 0;
					errorMsgs.add("狀態請填數字.");
				}
//				
//				Timestamp time =Timestamp.valueOf(req.getParameter("product_id"));
//				//Integer time = new Integer(req.getParameter("product_id").trim());
				
				String content = req.getParameter("product_content");
				String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("內容: 請勿空白");
				} else if (!content.trim().matches(contentReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
				}
				
				String description = req.getParameter("product_description");
				String descriptionReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (description == null || description.trim().length() == 0) {
					errorMsgs.add("描述: 請勿空白");
				} else if (!description.trim().matches(descriptionReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("描述: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
				}
				
				String categories = req.getParameter("product_categories");
				String categoriesReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (categories == null || categories.trim().length() == 0) {
					errorMsgs.add("分類: 請勿空白");
				} else if (!categories.trim().matches(categoriesReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("分類: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}


				Integer price = null;
				try {
					price = new Integer(req.getParameter("product_price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("價格請填數字.");
				}

				Integer stock = null;
				try {
					stock = new Integer(req.getParameter("product_stock").trim());
				} catch (NumberFormatException e) {
					stock = 0;
					errorMsgs.add("庫存請填數字.");
				}
				
				 
			
				Part part=req.getPart("product_pic");
				
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();


			
				
				

			//	Integer sale_id = new Integer(req.getParameter("sale_id").trim());
			
				Integer product_id = new Integer(req.getParameter("product_id").trim());
				
				
				ProductVO empVO1 = new ProductVO();
				ProductVO empVO = new ProductVO();
				empVO.setProduct_name(pname);
				empVO.setProduct_status(status);
				empVO.setProduct_content(content);
				empVO.setProduct_description(description);
				empVO.setProduct_categories(categories);
				empVO.setProduct_price(price);
				empVO.setProduct_stock(stock);
				empVO.setProduct_pic(buf);
				
				
			//	empVO.setSale_id(sale_id);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService empSvc = new ProductService();
				//empVO = empSvc.addProduct(pname,status,time,content,description,categories,price,sale_id);
			 	empVO = empSvc.addProduct(empVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Product/addProduct.jsp");
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
				ProductService empSvc = new ProductService();
				empSvc.deleteProduct(empno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
