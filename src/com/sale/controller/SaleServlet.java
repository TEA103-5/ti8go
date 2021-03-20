package com.sale.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.product.model.ProductVO;
import com.sale.model.*;



//@WebServlet("/SaleServlet")
@MultipartConfig
public class SaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SaleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
//		doGet(request, response);
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//req會有 action 和 empno 跟 他們對應的value
		
		
	    // 來自select_page_product.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
		if ("listEmps_ByDeptno_A".equals(action) || "listEmps_ByDeptno_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer saleid = new Integer(req.getParameter("deptno"));
				

				/*************************** 2.開始查詢資料 ****************************************/
				SaleService saleSvc = new SaleService();
				Set<ProductVO> set = saleSvc.getProdsBySaleid(saleid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listEmps_ByDeptno", set);    // 資料庫取出的set物件,存入request

				String url = null;
				if ("listEmps_ByDeptno_A".equals(action))
					url = "/sale/listProds_BySaleid.jsp";        // 成功轉交 sale/listProds_BySaleid.jsp
				else if ("listEmps_ByDeptno_B".equals(action))
					url = "/sale/ListAllSale.jsp";              // 成功轉交 dept/listAllDept.jsp

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
				//req再多放一個 叫 errorMsgs 的 string的LinkedList  叫 errorMsgs  
			try {
				/***************************1.接收請求參數- 輸入格式的錯誤處理**********************/
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {//如果是null或空字串
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { //如果這裡成立 代表前面的if成立
					RequestDispatcher failureView = req 
							.getRequestDispatcher("/select_page_sale.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}/*所以   如果empno是null或空值 就放一個"請輸入員工編號" 在錯誤訊息
				*  這裡它沒有再req.setAttribute("errorMsgs", errorMsgs);
				* 代表 放進req之後 再更動錯誤訊息是OK的
				* RequestDispatcher
				* Defines an object that receives requests from the clientand sends them to any 
				* resource (such as a servlet,HTML file, or JSP file) on the server. 
				* The servletcontainer creates the RequestDispatcher object,which is used as a wrapper 
				* around a server resource locatedat a particular path or given by a particular name. 
				* This interface is intended to wrap servlets,but a servlet container can create 
				* RequestDispatcherobjects to wrap any type of resource. 

				*/
				Integer empno = null;
				try {
					empno = new Integer(str);
				} catch (Exception e) {//不是空值 但不是Integer的話
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {//如果前者成立
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page_sale.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				SaleService saleSvc = new SaleService();
				SaleVO empVO = saleSvc.getOneSale(empno);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page_sale.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/***************************3.查詢完成 準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); //資料庫取出的empVO物件,存入req
				String url = "/sale/listOneSale.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //成功轉交listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page_sale.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { //來自ListAllSale.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				Integer empno = new Integer(req.getParameter("empno"));
				
				/***************************2.開始查詢資料****************************************/
				SaleService empSvc = new SaleService();
				SaleVO empVO = empSvc.getOneSale(empno);
								
				/***************************3.查詢完成 準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/sale/update_sale.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/sale/ListAllSale.jsp");
//				failureView.forward(req, res);
//			}
		}
		
	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Part part=null;
					part=req.getPart("upfile1");
			try {
				/***************************1.接收請求參數-輸入格式的錯誤處理**********************/
				Integer sale_status = null;
				
				try {
					sale_status = new Integer(req.getParameter("sstatus").trim());
				} catch (NumberFormatException e) {
					sale_status = 0;
					errorMsgs.add("帳號狀態請填整數");
				}
					if(!(sale_status==1||sale_status==0)) {
						errorMsgs.add("帳號狀態在0或1之間選擇");
					}
				
				Integer sale_audit_status = null;
				
				try {
				sale_audit_status = new Integer(req.getParameter("sastatus").trim());
			} catch (NumberFormatException e) {
				sale_audit_status = 0;
				errorMsgs.add("評分請填整數");
			}
				if(!(sale_audit_status==1||sale_audit_status==0)) {
					errorMsgs.add("照片審核狀態在0或1之間選擇");
				}
				
				Float sale_rate = null;
				try {
				sale_rate = new Float(req.getParameter("srate").trim());
			} catch (NumberFormatException e) {
				sale_rate = 0.0f;
				errorMsgs.add("評分請填數字");
			}
				if(sale_rate>5||sale_rate<0) {
					errorMsgs.add("評分在0~5之間");
				}
				
				Integer empno = new Integer(req.getParameter("empno").trim());
				if(part!=null) {
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				SaleService saleSvc = new SaleService();
//				System.out.println(sale_status);
				saleSvc.updateSaleP(empno,buf);
				}
				
				String sale_name = req.getParameter("sname");
				String sale_nickname = req.getParameter("snickname");
//				String sale_email = req.getParameter("semail");
				String sale_pwd = req.getParameter("spwd");
				String sale_phone = req.getParameter("sphone");
//				String ename = req.getParameter("ename");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				String pwdReg = "^[a-zA-Z0-9]{2,20}$";
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				String phoneReg = "[0-9]{10}";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				
				
				if(sale_pwd==null || sale_pwd.trim().length() == 0) {
					errorMsgs.add("販售者密碼: 請勿空白");
					sale_pwd="密碼請勿空白";
				}else if(!sale_pwd.trim().matches(pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼:只能是英文字母、數字");
				}
//				if(sale_email==null || sale_email.trim().length() == 0) {
//					errorMsgs.add("販售者信箱: 請勿空白");
//				}else if(!sale_email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("信箱都會打錯喔? 到底?");
//		            }
				if(sale_phone==null || sale_phone.trim().length() == 0) {
					errorMsgs.add("電話應該是不用第一時間輸入啦");
				}else if(!sale_phone.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話都會打錯喔? 到底?");
				}
				
				if(sale_name==null || sale_name.trim().length() == 0) {
					errorMsgs.add("販售者姓名: 請勿空白");
				}else if(!sale_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("販售者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				if(sale_nickname==null || sale_nickname.trim().length() == 0) {
					errorMsgs.add("販售者暱稱: 請勿空白");
				}else if(!sale_nickname.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("販售者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

//
				SaleVO empVO = new SaleVO();
				empVO.setSale_id(empno);
//				empVO.setSale_email(sale_email);
				empVO.setSale_pwd(sale_pwd);
				empVO.setSale_name(sale_name);
				empVO.setSale_nickname(sale_nickname);
				empVO.setSale_phone(sale_phone);
				empVO.setSale_status(sale_status);
				empVO.setSale_audit_status(sale_audit_status);
				empVO.setSale_rate(sale_rate);
				
				
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sale/update_sale.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}

					/***************************2.開始修改資料*****************************************/
				SaleService empSvc = new SaleService();
				empVO = empSvc.updateSale(sale_pwd, sale_audit_status, sale_name, sale_status, sale_phone, sale_nickname, sale_rate, empno);
				empVO=empSvc.getOneSale(empno);
				/***************************3.修改完成.準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的empVO物件,存入req
				String url = "/sale/listOneSale.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後.轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sale/update_sale.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	     if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				
				
				
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String sale_name = req.getParameter("sname");
					String sale_nickname = req.getParameter("snickname");
					String sale_email = req.getParameter("semail");
					String sale_pwd = req.getParameter("spwd");
					String sale_phone = req.getParameter("sphone");
//					String ename = req.getParameter("ename");
					String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					String pwdReg = "^[a-zA-Z0-9]{2,20}$";
					String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					String phoneReg = "[0-9]{10}";
//					if (ename == null || ename.trim().length() == 0) {
//						errorMsgs.add("員工姓名: 請勿空白");
//					} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//						errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//		            }
					if(sale_pwd==null || sale_pwd.trim().length() == 0) {
						errorMsgs.add("販售者密碼: 請勿空白");
						sale_pwd="密碼請勿空白";
					}else if(!sale_pwd.trim().matches(pwdReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("密碼:只能是英文字母、數字");
					}
					if(sale_email==null || sale_email.trim().length() == 0) {
						errorMsgs.add("販售者信箱: 請勿空白");
					}else if(!sale_email.trim().matches(emailReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("信箱都會打錯喔? 到底?");
			            }
					if(sale_phone==null || sale_phone.trim().length() == 0) {
						errorMsgs.add("電話應該是不用第一時間輸入啦");
					}else if(!sale_phone.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("電話都會打錯喔? 到底?");
					}
					
					if(sale_name==null || sale_name.trim().length() == 0) {
						errorMsgs.add("販售者姓名: 請勿空白");
					}else if(!sale_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("販售者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
					if(sale_nickname==null || sale_nickname.trim().length() == 0) {
						errorMsgs.add("販售者暱稱: 請勿空白");
					}else if(!sale_nickname.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("販售者姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}
					
					
					
					
//					String job = req.getParameter("job").trim();
//					if (job == null || job.trim().length() == 0) {
//						errorMsgs.add("職位請勿空白");
//					}
//					java.sql.Date hiredate = null;
//					try {
//						hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//					} catch (IllegalArgumentException e) {
//						hiredate=new java.sql.Date(System.currentTimeMillis());
//						errorMsgs.add("請輸入日期!");
//					}
//					
//					Double sal = null;
//					try {
//						sal = new Double(req.getParameter("sal").trim());
//					} catch (NumberFormatException e) {
//						sal = 0.0;
//						errorMsgs.add("薪水請填數字.");
//					}
//					
//					Double comm = null;
//					try {
//						comm = new Double(req.getParameter("comm").trim());
//					} catch (NumberFormatException e) {
//						comm = 0.0;
//						errorMsgs.add("獎金請填數字.");
//					}
//					
//					Integer deptno = new Integer(req.getParameter("deptno").trim());
//
					SaleVO empVO = new SaleVO();
					empVO.setSale_name(sale_name);
					empVO.setSale_nickname(sale_nickname);
					empVO.setSale_email(sale_email);
					empVO.setSale_pwd(sale_pwd);
					empVO.setSale_phone(sale_phone);
			
					
	
		
				
					
	
					
//
//					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/sale/addSale.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					SaleService empSvc = new SaleService();
					empVO = empSvc.addSale(sale_email, sale_pwd, sale_name, sale_phone, sale_nickname);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/sale/ListAllSale.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sale/addSale.jsp");
					failureView.forward(req, res);
				}
			}
	     
	     
	     
			if ("delete".equals(action)) { // 來自listAllSale.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer empno = new Integer(req.getParameter("empno"));
					
					/***************************2.開始刪除資料***************************************/
					SaleService empSvc = new SaleService();
					empSvc.deleteSale(empno);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/sale/ListAllSale.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sale/ListAllSale.jsp");
					failureView.forward(req, res);
				}
			}
	}


}
