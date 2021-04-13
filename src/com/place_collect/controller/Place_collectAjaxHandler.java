package com.place_collect.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.place_collect.model.Place_collectService;
import com.place_collect.model.Place_collectVO;

public class Place_collectAjaxHandler extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json ; charset=UTF-8"); 
        PrintWriter out = res.getWriter();
        
		String action = req.getParameter("action");

//		if ("ajax_hasPlace_collect".equals(action)) {
//
////			List<String> errorMsgs = new LinkedList<String>();
////			req.setAttribute("errorMsgs", errorMsgs);
//			
//			HashMap result = new HashMap();
//			
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				Integer place_id = new Integer(req.getParameter("place_id").trim());
//				
//				Integer users_id = new Integer(req.getParameter("users_id").trim());
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				Place_collectService place_collectSvc = new Place_collectService();
//				Place_collectVO place_collectVO = place_collectSvc.getOnePlace_collect(place_id , users_id);
//				
//				if(place_collectVO == null) {
//System.out.println("查無此筆收藏");
//					result.put("hasVO" , false);
//				}else {
//					result.put("hasVO" , true);
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				JSONObject resultJSON = new JSONObject(result);
//				out.println(resultJSON);
//
//		}

		
		if ("ajax_insert_or_delete_PlaceCollect".equals(action)) {

//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			HashMap result = new HashMap();
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer place_id = new Integer(req.getParameter("place_id").trim());
				
				Integer users_id = new Integer(req.getParameter("users_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				Place_collectService place_collectSvc = new Place_collectService();
//				Place_collectVO place_collectVO = place_collectSvc.getOnePlace_collect(place_id , users_id);
				
				if(place_collectSvc.getOnePlace_collect(place_id , users_id) == null) {
					// 如查無此筆收藏則新增此筆收藏
					place_collectSvc.addPlace_collect(place_id, users_id);
					result.put("result" , "insert_succss");
				}else {
					// 如有此筆收藏則刪除該筆收藏
					place_collectSvc.deletePlace_collect(place_id, users_id);
					result.put("result" , "delete_success");
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);

		}
//		
//		if ("ajax_delete_PlaceCollect".equals(action)) {
//
////			List<String> errorMsgs = new LinkedList<String>();
////			req.setAttribute("errorMsgs", errorMsgs);
//			
//			HashMap result = new HashMap();
//			
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				Integer place_id = new Integer(req.getParameter("place_id").trim());
//				
//				Integer users_id = new Integer(req.getParameter("users_id").trim());
//
//				/*************************** 2.開始查詢資料 *****************************************/
//				Place_collectService place_collectSvc = new Place_collectService();
//				Place_collectVO place_collectVO = place_collectSvc.getOnePlace_collect(place_id , users_id);
//				
//				if(place_collectVO == null) {
//System.out.println("查無此筆收藏");
//					result.put("hasVO" , false);
//				}else {
//					result.put("hasVO" , true);
//				}
//
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				JSONObject resultJSON = new JSONObject(result);
//				out.println(resultJSON);
//
//		}

		
		
	}

}
