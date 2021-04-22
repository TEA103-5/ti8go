package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import org.json.JSONObject;

import com.login.model.LoginService;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.sale.model.SaleService;
import com.sale.model.SaleVO;

/**
 * Servlet implementation class ProductServlet
 */
//@WebServlet("/ProductServlet")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json ; charset=UTF-8");
		String action = req.getParameter("action");

//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		LoginService lgSrc=new LoginService();
//		errorMsgs.add("帳號或密碼有問題2");
//		System.out.println(req.getParameter("confirmPassword"));
//		HashMap result = new HashMap();
//		errorMsgs.add("帳號或密碼有問題");
//		result.put("errormsg" , errorMsgs);
//		PrintWriter out = res.getWriter();
//		JSONObject resultJSON = new JSONObject(result);
//		out.println(resultJSON);
System.out.println("131351351535");
		if ("deleteajax".equals(action)) { // 來自listAllEmp.jsp 或 /dept/listEmps_ByDeptno.jsp的請求
			HashMap result = new HashMap();
			PrintWriter out = res.getWriter();
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer empno = new Integer(req.getParameter("pId"));
				/*************************** 2.開始刪除資料 ***************************************/
				ProductService empSvc = new ProductService();
				ProductVO empVO = empSvc.getOneProduct(empno);
				System.out.println("1455fgdf5gdfg51fdg456");
				empSvc.deleteProduct(empno);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				result.put("res", "刪除成功");
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e);
				result.put("res", "刪除失敗"+e);
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
			}
		} // delete end

		if ("insertajax".equals(action)) {
			java.sql.Date date = new java.sql.Date(new Date().getTime());// 系統時間
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = sdf.format(date);// 將時間格式轉成Timestamp要求的格式.
			Timestamp dates = Timestamp.valueOf(nowTime);
			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
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
				empVO.setProduct_update_time(dates);
				Part part = null;
				part = req.getPart("upfile1");
				if (part != null && part.getSize() > 0) {
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					empVO.setProduct_pic(buf);

				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					return; // 程式中斷
//				}
				/*************************** 2.開始新增資料 ***************************************/
				ProductService empSvc = new ProductService();
				empVO = empSvc.addProduct(empVO);
				HashMap result = new HashMap();
				result.put("product", empVO);
				PrintWriter out = res.getWriter();
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e);
				errorMsgs.add(e.getMessage());
			}
		} // insert end

		if ("updatep".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			HashMap result = new HashMap();
//			errorMsgs.add("帳號或密碼有問題");
//			result.put("errormsg" , errorMsgs);
//			PrintWriter out = res.getWriter();
//			JSONObject resultJSON = new JSONObject(result);
//			out.println(resultJSON);
			Part part = null;
			part = req.getPart("upfile1");
			Integer empno = new Integer(req.getParameter("empno").trim());
			ProductVO empVO = new ProductVO();
			ProductService empSvc = new ProductService();
			// empVO=empSvc.getOneSale(empno);

			InputStream in = part.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();
//			SaleService saleSvc = new SaleService();
			empSvc.updateP(empno, buf);

		}
		if ("updateajax".equals(action)) { // 來自update_emp_input.jsp的請求
			// List<String> errorMsgs = new LinkedList<String>();
			HashMap result = new HashMap();
			// errorMsgs.add("帳號或密碼有問題");
			PrintWriter out = res.getWriter();

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer prodid = new Integer(req.getParameter("pId").trim());
				Integer stat = new Integer(req.getParameter("pStatus"));

//				System.out.println(req.getParameter("stat1"));//on && null
//				if(req!=null) {
//					stat=1;
//				}else {
//					stat=0;
//				}
				Integer pric = new Integer(req.getParameter("pPrice").trim());
				Integer stoc = new Integer(req.getParameter("pStock").trim());
				Integer saleid = new Integer(req.getParameter("pSaleId").trim());
				String name = req.getParameter("pName");
				String cont = req.getParameter("pContent");
				String desc = req.getParameter("pDescription");
				String cate = req.getParameter("pCategories");
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProduct(prodid);
				prodVO.setProduct_id(prodid);
				prodVO.setProduct_status(stat);
				prodVO.setProduct_price(pric);
				prodVO.setProduct_stock(stoc);
				prodVO.setProduct_name(name);
				prodVO.setProduct_content(cont);
				prodVO.setProduct_description(desc);
				prodVO.setProduct_categories(cate);

//				String picc = null;
//				picc = req.getParameter("picc");
//
//				Part part = null;
//				part = req.getPart("upfile1");
//				if (part.getSize()>0) {
//
//					try {
//						InputStream in = part.getInputStream();
//						byte[] buf = new byte[in.available()];
//						in.read(buf);
//						in.close();
//						ProductService saleSvc = new ProductService();
//						saleSvc.updateP(prodid, buf);
//					} catch (Exception e) {
//						System.out.println(e);
//					}
//				}

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", prodVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/product/updateProduct.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}

				/*************************** 2.開始修改資料 *****************************************/

				prodVO = prodSvc.updateProduct(name, stat, cont, desc, cate, pric, stoc, prodid);
				result.put("res", "成功");
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println(e);
				result.put("res", "失敗");
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);
			}
		} // update end

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page_product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer prodno = null;
				try {
					prodno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page_product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ProductService prodSvc = new ProductService();
				ProductVO prodVO = prodSvc.getOneProduct(prodno);
				if (prodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page_product.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("prodVO", prodVO); // 資料庫取出的empVO物件,存入req
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page_product.jsp");
				failureView.forward(req, res);
			}
		} // if ("getOne_For_Display" end

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或 /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或
																// 【/dept/listEmps_ByDeptno.jsp】 或 【
																// /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService empSvc = new ProductService();
				ProductVO empVO = empSvc.getOneProduct(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/sale-end/product/updateProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // getOne_For_Update end

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或
																// 【/dept/listEmps_ByDeptno.jsp】 或 【
																// /dept/listAllDept.jsp】
			System.out.println(requestURL);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer prodid = new Integer(req.getParameter("empno").trim());
				Integer stat;
//				System.out.println(req.getParameter("stat1"));//on && null
				if (req.getParameter("stat1") != null) {
					stat = 1;
				} else {
					stat = 0;
				}
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

				String picc = null;
				picc = req.getParameter("picc");

				Part part = null;
				part = req.getPart("upfile1");
				if (part.getSize() > 0) {

					try {
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						in.close();
						ProductService saleSvc = new ProductService();
						saleSvc.updateP(prodid, buf);
					} catch (Exception e) {
						System.out.println(e);
					}
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", prodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/product/updateProduct.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ProductService prodSvc = new ProductService();
				prodVO = prodSvc.updateProduct(name, stat, cont, desc, cate, pric, stoc, prodid);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				SaleService saleSvc = new SaleService();

				if (requestURL.equals("/sale-end/product/listProductsBySale.jsp")
						|| requestURL.equals("/product/listAllProduct.jsp")) {// 這裡應該是oneproduct的頁面送出修改要放一個(打錯的意思~~)
					req.setAttribute("listEmps_ByDeptno",
							saleSvc.getProdsBySaleid(prodSvc.getOneProduct(prodid).getSale_id())); // 資料庫取出的list物件,存入request
					// req.setAttribute("empVO",prodVO); // 資料庫取出的list物件,存入request

				}

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/product/updateProduct.jsp");
				failureView.forward(req, res);
			}
		} // update end

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

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
				Part part = null;
				part = req.getPart("upfile1");
				if (part.getSize() > 0) {
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					empVO.setProduct_pic(buf);

				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始新增資料 ***************************************/
				ProductService empSvc = new ProductService();
				empVO = empSvc.addProduct(empVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/sale-end/product/listAllProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sale-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		} // insert end

		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或 /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或
																// 【/dept/listEmps_ByDeptno.jsp】 或 【
																// /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService empSvc = new ProductService();
				ProductVO empVO = empSvc.getOneProduct(empno);
				empSvc.deleteProduct(empno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				SaleService deptSvc = new SaleService();
				if (requestURL.equals("/sale/listProds_BySaleid.jsp") || requestURL.equals("/sale/ListAllSale.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
					req.setAttribute("listEmps_ByDeptno", deptSvc.getProdsBySaleid(empVO.getSale_id())); // 資料庫取出的list物件,存入request

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // delete end

	} // dopost end
//servlet end
}
