package com.note.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.note.model.NoteDAO;
import com.note.model.NoteService;
import com.note.model.NoteVO;

public class NoteServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("note_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入遊記序號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/note/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer note_id = null;
				try {
					note_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("遊記序號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/note/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				NoteService noteSvc = new NoteService();
				NoteVO noteVO = noteSvc.getOneNote(note_id);
				if (noteVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/note/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("noteVO", noteVO);
				String url = "/note/listOneNote.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/note/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer note_id = new Integer(req.getParameter("note_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				NoteService noteSvc = new NoteService();
				NoteVO noteVO = noteSvc.getOneNote(note_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("noteVO", noteVO); // 資料庫取出的noteVO物件,存入req
				String url = "/note/update_note_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_note_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/note/listAllNote.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			Integer note_id = new Integer(req.getParameter("note_id").trim());;
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer note_id = new Integer(req.getParameter("note_id").trim());

				Integer note_classid = null;
				try {
					note_classid = new Integer(req.getParameter("note_classid").trim());
				} catch (NumberFormatException e) {
					note_classid = 1;
					errorMsgs.add("請填數字!");
				}

				java.sql.Date travel_start = null;
				try {
					travel_start = java.sql.Date.valueOf(req.getParameter("travel_start").trim());
				} catch (IllegalArgumentException e) {
					travel_start = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String note_title = req.getParameter("note_title");
				String notetitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_.//!?~)]{2,10}$";
				if (note_title == null || note_title.trim().length() == 0) {
					errorMsgs.add("遊記標題: 請勿空白");
				} else if (!note_title.trim().matches(notetitleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("遊記標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String note_description = req.getParameter("note_description").trim();
				if (note_description == null || note_description.trim().length() == 0) {
					errorMsgs.add("大綱請勿空白");
				}

				Integer users_id = new Integer(req.getParameter("users_id").trim());
				Integer trip_id = new Integer(req.getParameter("trip_id").trim());
				Integer note_like = null;
				try {
					note_like = new Integer(req.getParameter("note_like").trim());
				} catch (NumberFormatException e) {
					note_like = 0;
					errorMsgs.add("請填數字!");
				}

				NoteVO noteVO = new NoteVO();
				System.out.println("aaaaaaaaa");
				noteVO.setNote_id(note_id);
				noteVO.setNote_classid(note_classid);
				noteVO.setTravel_start(travel_start);
				noteVO.setNote_title(note_title);
				noteVO.setNote_description(note_description);
				noteVO.setUsers_id(users_id);
				noteVO.setTrip_id(trip_id);
				noteVO.setNote_like(note_like);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noteVO", noteVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/note/update_note_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				NoteService noteSvc = new NoteService();
				noteVO = noteSvc.updateNote(note_id, note_classid, travel_start, note_title, note_description, users_id,
						trip_id, note_like);
				noteVO = noteSvc.getOneNote(note_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

				req.setAttribute("noteVO", noteVO);
				String url = "/note/listOneNote.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
//				NoteDAO dao = new NoteDAO();
//				NoteVO noteVO = dao.findByPrimaryKey(note_id);
//				req.setAttribute("noteVO", noteVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/note/update_note_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer note_classid = new Integer(req.getParameter("note_classid").trim());
				java.sql.Date travel_start = null;
				try {
					travel_start = java.sql.Date.valueOf(req.getParameter("travel_start").trim());
				} catch (IllegalArgumentException e) {
					travel_start = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String note_title = req.getParameter("note_title");
				String notetitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (note_title == null || note_title.trim().length() == 0) {
					errorMsgs.add("遊記標題: 請勿空白");
				} else if (!note_title.trim().matches(notetitleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("遊記標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String note_description = req.getParameter("note_description").trim();
				if (note_description == null || note_description.trim().length() == 0) {
					errorMsgs.add("大綱請勿空白");
				}

				Integer users_id = new Integer(req.getParameter("users_id").trim());
				Integer trip_id = new Integer(req.getParameter("trip_id").trim());
				Integer note_like = new Integer(req.getParameter("note_like").trim());

				NoteVO noteVO = new NoteVO();
				noteVO.setNote_classid(note_classid);
				noteVO.setTravel_start(travel_start);
				noteVO.setNote_title(note_title);
				noteVO.setNote_description(note_description);
				noteVO.setUsers_id(users_id);
				noteVO.setTrip_id(trip_id);
				noteVO.setNote_like(note_like);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noteVO", noteVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/note/addNote.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				NoteService noteSvc = new NoteService();
				noteVO = noteSvc.addNote(note_classid, travel_start, note_title, note_description, users_id, trip_id,
						note_like);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/note/listAllNote.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/note/addNote.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer note_id = new Integer(req.getParameter("note_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				NoteService noteSvc = new NoteService();
				noteSvc.deleteNote(note_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/note/listAllNote.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/note/listAllNote.jsp");
				failureView.forward(req, res);
			}
		}

	}
}