package com.note.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.note.model.NoteService;
import com.note.model.NoteVO;


public class NoteAjaxHandler extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json ; charset=UTF-8"); 
        PrintWriter out = res.getWriter();
        
		String action = req.getParameter("action");
		
		
		if ("update_note_like".equals(action)) {

//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			HashMap result = new HashMap();
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer note_id = new Integer(req.getParameter("note_id").trim());
				
				NoteService noteSvc = new NoteService();
				NoteVO noteVO = noteSvc.getOneNote(note_id);
				
				Integer note_classid = noteVO.getNote_classid();
				Date travel_start = noteVO.getTravel_start();
				String note_title = noteVO.getNote_title();
				String note_description = noteVO.getNote_description();
				Integer trip_id = noteVO.getTrip_id();
				
				Integer note_like = null;

				if( noteVO.getNote_like()== 1 ) {

					note_like = 0 ;
					result.put("result" , "like_add");
				}else if(noteVO.getNote_like()== 0) {

					note_like = note_like + 1 ;
					result.put("result" , "like_add");
				}
				
				// 不知為何如果資料庫中users_id = null , 從資料庫中取出會變為零 , 放入0會導致沒有對應的users_id , 所以做處理改為null 
				Integer users_id = null ;
				if(noteVO.getUsers_id() == 0) {
					users_id = null;	
				}else {
					users_id = noteVO.getUsers_id();
				}
				
				/*************************** 2.開始查詢資料/修改資料 *****************************************/
				
				noteSvc.updateNote(note_id, note_classid, travel_start, note_title, note_description, users_id, 
						trip_id, note_like);
				
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);

		}
		
		
	}
}