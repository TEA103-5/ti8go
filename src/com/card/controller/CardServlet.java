package com.card.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.card.model.CardService;
import com.card.model.CardVO;


public class CardServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("card_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer card_id = null;
				try {
					card_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CardService cardSvc = new CardService();
				CardVO cardVO = cardSvc.getOneCard(card_id);
				if (cardVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cardVO", cardVO); // 資料庫取出的empVO物件,存入req
				String url = "/card/listOneCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/card/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer card_id = new Integer(req.getParameter("card_id"));
				
				/***************************2.開始刪除資料***************************************/
				CardService cardSvc = new CardService();
				cardSvc.deleteCard(card_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/card/listAllCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/card/listAllCard.jsp");
				failureView.forward(req, res);
			}
		}
		
			if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer users_id = null;
			
					try {
						users_id = new Integer(req.getParameter("users_id").trim());
					} catch (NumberFormatException e) {
						users_id = 1;
						errorMsgs.add("使用者編號請填數字.");
					}
				
				
					String card_number = req.getParameter("card_number").trim();
					if (card_number == null || card_number.trim().length() == 0) {
						errorMsgs.add("信用卡卡號: 請勿空白");
					}
				
					String card_date = req.getParameter("card_date").trim();
					if (card_date == null || card_date.trim().length() == 0) {				
						errorMsgs.add("請輸入日期!");
					}
				
					String card_last = req.getParameter("card_last").trim();
					if (card_last == null || card_last.trim().length() == 0) {
						errorMsgs.add("末三碼: 請勿空白");
					}
				
				
					Integer card_default = null;
					try {
						card_default = new Integer(req.getParameter("card_default").trim());
					} catch (NumberFormatException e) {
						card_default = 1;
						errorMsgs.add("狀態請填數字.");
					}
				
				
//Integer deptno = new Integer(req.getParameter("deptno").trim());

				CardVO cardVO = new CardVO();
				cardVO.setUsers_id(users_id);
				cardVO.setCard_number(card_number);
				cardVO.setCard_date(card_date);
				cardVO.setCard_last(card_last);
				cardVO.setCard_default(card_default);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cardVO", cardVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/addCard.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				CardService cardSvc = new CardService();
				cardVO = cardSvc.addCard(users_id, card_number, card_date, card_last, card_default);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/card/listAllCard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/card/addCard.jsp");
//				failureView.forward(req, res);
//			}
		
		}
				if ("getOne_For_Update".equals(action)) { //來自listAllEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					Integer card_id = new Integer(req.getParameter("card_id"));
					
					/***************************2.開始查詢資料****************************************/
					CardService cardSvc = new CardService();
					CardVO cardVO = cardSvc.getOneCard(card_id);							
					/***************************3.查詢完成 準備轉交(Send the Success view)************/
					req.setAttribute("cardVO", cardVO);         // 資料庫取出的empVO物件,存入req
					String url = "/card/update_card_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp					
					successView.forward(req, res);
							/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/card/listAllCard.jsp");
					failureView.forward(req, res);
				}
			}
				
				if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
				
					try {
						/***************************1.接收請求參數-輸入格式的錯誤處理**********************/
						Integer card_id = new Integer(req.getParameter("card_id").trim());
					//	Integer users_id = new Integer(req.getParameter("users_id").trim());
						
						String card_number = req.getParameter("card_number").trim();
						if (card_number == null || card_number.trim().length() == 0) {
							errorMsgs.add("信用卡卡號: 請勿空白");
						}
					
						String card_date = req.getParameter("card_date").trim();
						if (card_date == null || card_date.trim().length() == 0) {				
							errorMsgs.add("請輸入日期!");
						}
					
						String card_last = req.getParameter("card_last").trim();
						if (card_last == null || card_last.trim().length() == 0) {
							errorMsgs.add("末三碼: 請勿空白");
						}
					
					
						Integer card_default = null;
						try {
							card_default = new Integer(req.getParameter("card_default").trim());
						} catch (NumberFormatException e) {
							card_default = 1;
							errorMsgs.add("狀態請填數字.");
						}
					
					
						Integer users_id = new Integer(req.getParameter("users_id").trim());

						CardVO cardVO = new CardVO();
						cardVO.setCard_id(card_id);
						cardVO.setUsers_id(users_id);
						cardVO.setCard_number(card_number);
						cardVO.setCard_date(card_date);
						cardVO.setCard_last(card_last);
						cardVO.setCard_default(card_default);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("cardVO", cardVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/card/update_emp_input.jsp");
						failureView.forward(req, res);
						return;
					}
						/***************************2.開始修改資料*****************************************/
						CardService cardSvc = new CardService();
						cardVO = cardSvc.updateCard(users_id, card_id, card_number, card_date, card_last, card_default);
						/***************************3.修改完成.準備轉交(Send the Success view)*************/
						req.setAttribute("cardVO", cardVO); // 資料庫update成功後,正確的empVO物件,存入req
						String url = "/card/listOneCard.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後.轉交listOneEmp.jsp
						successView.forward(req, res);

						/***************************其他可能的錯誤處理*************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/card/update_card_input.jsp");
						failureView.forward(req, res);
					}
				}
	}
	
}
