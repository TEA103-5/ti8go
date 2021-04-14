package com.notec.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.note.model.NoteService;
import com.note.model.NoteVO;
import com.notec.model.NoteCService;
import com.notec.model.NoteCVO;


@MultipartConfig (location = "c:/ImagesforT8go")
public class NoteCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("note_c_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入內編序號");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/notec/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Integer note_c_id = null;
				try {
					note_c_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("內編序號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/notec/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/

				NoteCService noteCSvc = new NoteCService();
				NoteCVO noteCVO = noteCSvc.getOneNoteC(note_c_id);
				if (noteCVO == null) {
					errorMsgs.add("查無資料");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/notec/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("noteCVO", noteCVO); // 資料庫取出的empVO物件,存入req
				String url = "/notec/listOneNoteC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/notec/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 取一更新
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer note_c_id = new Integer(req.getParameter("note_c_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				NoteCService noteCSvc = new NoteCService();
				NoteCVO noteCVO = noteCSvc.getOneNoteC(note_c_id);
//				System.out.println(noteCVO);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("noteCVO", noteCVO); // 資料庫取出的noteVO物件,存入req
				String url = "/front-end/notefront/update_notecEdit.jsp";
//				String url = "/notec/update_noteC_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_note_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/notec/listAllNoteC.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer note_c_id = new Integer(req.getParameter("note_c_id").trim());

				String note_c_title = req.getParameter("note_c_title");
				String notectitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_.//!?~)]{2,10}$";
				if (note_c_title == null || note_c_title.trim().length() == 0) {
					errorMsgs.add("遊記標題: 請勿空白");
				} else if (!note_c_title.trim().matches(notectitleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("遊記標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String note_c_content = req.getParameter("note_c_content");
				String noteccontentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_.//!?~)]{2,10}$";
				if (note_c_content == null || note_c_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				} else if (!note_c_content.trim().matches(noteccontentReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("遊記標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer note_id = new Integer(req.getParameter("note_id").trim());

//byte[] note_c_img = req.getParameter("note_c_img").getBytes();
				byte[] note_c_img = null;
				Part part = req.getPart("note_c_img");
				if (part.getSize() != 0) {
					InputStream is = part.getInputStream();
					note_c_img = new byte[is.available()];
					is.read(note_c_img);
					is.close();
					part.write(part.getSubmittedFileName());
				} else {
					NoteCService noteCSvc = new NoteCService();
					note_c_img = noteCSvc.getOneNoteC(note_c_id).getNote_c_img();
				}
				
//				try {
//					InputStream is = part.getInputStream();
//					note_c_img = new byte[is.available()];
//					is.read(note_c_img);
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace(System.err);
//				}


				NoteCVO noteCVO = new NoteCVO();
				noteCVO.setNote_c_id(note_c_id);
				noteCVO.setNote_c_title(note_c_title);
				noteCVO.setNote_c_content(note_c_content);
				noteCVO.setNote_c_img(note_c_img);
				noteCVO.setNote_id(note_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noteCVO", noteCVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/notec/update_noteC_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
//				System.out.println("快跑跑跑");

				/*************************** 2.開始修改資料 *****************************************/
				NoteCService noteCSvc = new NoteCService();
				noteCVO = noteCSvc.updateNoteC(note_c_id, note_c_title, note_c_content, note_c_img, note_id);


//				noteCVO = noteCSvc.getOneNoteC(note_c_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("noteCVO", noteCVO);
				String url = "/front-end/notefront/listMyNote.jsp";
//				String url = "/notec/listOneNoteC.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/notec/update_noteC_input.jsp");
				failureView.forward(req, res);
			}
		}
		// 新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String note_c_title = req.getParameter("note_c_title");
				String notectitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_.//!?~)]{2,10}$";
				if (note_c_title == null || note_c_title.trim().length() == 0) {
					errorMsgs.add("遊記標題: 請勿空白");
				} else if (!note_c_title.trim().matches(notectitleReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("遊記標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String note_c_content = req.getParameter("note_c_content");
//				String noteccontentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_.//!?~)]{2,10}$";
				if (note_c_content == null || note_c_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				} 
//				else if (!note_c_content.trim().matches(noteccontentReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("內容: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}

				Integer note_id = new Integer(req.getParameter("note_id").trim());


				byte[] note_c_img = null;
				Part part = req.getPart("note_c_img");
				System.out.println("size="+part.getSize());
				
				if(part.getSize()!=0) {
					InputStream is = part.getInputStream();
					note_c_img = new byte[is.available()];
					is.read(note_c_img);
					is.close();
				} 
				
//				try {
//					InputStream is = part.getInputStream();
//					note_c_img = new byte[is.available()];
//					is.read(note_c_img);
//					is.close();
//					part.write(part.getSubmittedFileName());
//				} catch (IOException e) {
//					e.printStackTrace(System.err);
//				}
				

				NoteCVO noteCVO = new NoteCVO();
				noteCVO.setNote_c_title(note_c_title);
				noteCVO.setNote_c_title(note_c_content);
				noteCVO.setNote_c_img(note_c_img);
				noteCVO.setNote_id(note_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("noteCVO", noteCVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/notefront/notecEdit.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				NoteCService noteCSvc = new NoteCService();
				noteCVO = noteCSvc.addNoteC(note_c_title, note_c_content, note_c_img, note_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/notefront/notePostList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/notefront/notecEdit.jsp");
				failureView.forward(req, res);
			}
		}
//刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer note_c_id = new Integer(req.getParameter("note_c_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				NoteCService noteCSvc = new NoteCService();
				noteCSvc.deleteNoteC(note_c_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/notec/listAllNoteC.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/notec/listAllNoteC.jsp");
				failureView.forward(req, res);
			}

		}

	}
}
