package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
<<<<<<< HEAD
=======
import java.sql.Timestamp;
import java.util.Date;
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
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
<<<<<<< HEAD
import com.sale.model.SaleService;



=======
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311

/**
 * Servlet implementation class ProductServlet
 */
<<<<<<< HEAD
//@WebServlet("/ProductServlet")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
=======
@MultipartConfig
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
<<<<<<< HEAD
		
		
=======

>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
<<<<<<< HEAD
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page_product.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prodno = null;
				try {
					prodno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page_product.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProduct(prodno);
				if (prodVO == null) {
=======
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
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
<<<<<<< HEAD
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page_product.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("prodVO", prodVO); // 資料庫取出的empVO物件,存入req
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page_product.jsp");
				failureView.forward(req, res);
			}
		}//if ("getOne_For_Display" end
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求
=======
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
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
<<<<<<< HEAD
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始查詢資料****************************************/
				ProductService empSvc = new ProductService();
				ProductVO empVO = empSvc.getOneProduct(empno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/product/update_prod_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//getOne_For_Update end
		
		
	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
=======

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

>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
<<<<<<< HEAD
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer prodid = new Integer(req.getParameter("empno").trim());
				Integer stat = new Integer(req.getParameter("stat").trim());
				Integer pric = new Integer(req.getParameter("pric").trim());
				Integer stoc = new Integer(req.getParameter("stoc").trim());
				Integer saleid = new Integer(req.getParameter("saleid").trim());
				String name = req.getParameter("name");
				String cont = req.getParameter("cont");
				String desc = req.getParameter("desc");
				String cate = req.getParameter("cate");
				
				ProductVO prodVO = new ProductVO();
				prodVO.setProduct_id(prodid);
				prodVO.setProduct_status(stat);
				prodVO.setProduct_price(pric);
				prodVO.setProduct_stock(stoc);
				prodVO.setProduct_name(name);
				prodVO.setProduct_content(cont);
				prodVO.setProduct_description(desc);
				prodVO.setProduct_categories(cate);

				String picc=null;
				picc=req.getParameter("picc");
				
				
				Part part=null;
				part=req.getPart("upfile1");
				if(part!=null&&"F".equals(picc)) {
					
				try {
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				ProductService saleSvc = new ProductService();
				saleSvc.updateP(prodid,buf);
				}catch(Exception e) {
					System.out.println(e);
				}
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", prodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/update_prod_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService prodSvc = new ProductService();
				prodVO = prodSvc.updateProduct(name,stat,cont,desc
						,cate,pric,stoc
						,prodid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				SaleService saleSvc = new SaleService();
				
				if(requestURL.equals("/sale/listProds_BySaleid.jsp") || requestURL.equals("/product/listAllProduct.jsp")) {
					req.setAttribute("listEmps_ByDeptno",saleSvc.getProdsBySaleid(saleid)); // 資料庫取出的list物件,存入request
					//req.setAttribute("empVO",prodVO); // 資料庫取出的list物件,存入request

				}
			
                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/update_prod_input.jsp");
				failureView.forward(req, res);
			}
		}//update end
		
		
	   if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					
					String name = req.getParameter("name");
					String desc = req.getParameter("desc");
					String cont = req.getParameter("cont");
					String cate = req.getParameter("cate");
					Integer pric = null;
					try {
						pric = new Integer(req.getParameter("pric").trim());
					} catch (NumberFormatException e) {
						pric = 0;
						errorMsgs.add("薪水請填數字.");
					}
					
					Integer stoc = null;
					try {
						stoc = new Integer(req.getParameter("stoc").trim());
					} catch (NumberFormatException e) {
						stoc = 0;
						errorMsgs.add("庫存請填數字.");
					}
					Integer sale_id = new Integer(req.getParameter("deptno").trim());
					ProductVO empVO = new ProductVO();
					empVO.setProduct_name(name);
					empVO.setProduct_description(desc);
					empVO.setProduct_content(cont);
					empVO.setProduct_categories(cate);
					empVO.setProduct_price(pric);
					empVO.setProduct_stock(stoc);
					empVO.setSale_id(sale_id);
						
					/***************************2.開始新增資料***************************************/
					ProductService empSvc = new ProductService();
					empVO = empSvc.addProduct(empVO);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/product/listAllProduct.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
				}
			}//insert end
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求
=======

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
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
<<<<<<< HEAD
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

=======
	
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
			try {
				/***************************1.接收請求參數***************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始刪除資料***************************************/
				ProductService empSvc = new ProductService();
<<<<<<< HEAD
				ProductVO empVO = empSvc.getOneProduct(empno);
				empSvc.deleteProduct(empno);
							
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				SaleService deptSvc = new SaleService();
				if(requestURL.equals("/sale/listProds_BySaleid.jsp") || requestURL.equals("/sale/ListAllSale.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
				req.setAttribute("listEmps_ByDeptno",deptSvc.getProdsBySaleid(empVO.getSale_id())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
=======
				empSvc.deleteProduct(empno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
<<<<<<< HEAD
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//delete end
		
		
		
	
	}	//dopost end
//servlet end
=======
						.getRequestDispatcher("/Product/listAllProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}

>>>>>>> 3e594b52eacb61f705d71b97ed382d01a8494311
}
