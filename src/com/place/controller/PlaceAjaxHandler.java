package com.place.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

public class PlaceAjaxHandler extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json ; charset=UTF-8"); 
        PrintWriter out = res.getWriter();
        
		String action = req.getParameter("action");
		
		if ("change_place_state".equals(action)) {

//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			
			HashMap result = new HashMap();
			
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer place_id = new Integer(req.getParameter("place_id").trim());
				
				PlaceService placeSvc = new PlaceService();
				PlaceVO placeVO = placeSvc.getOnePlace(place_id);
				
				String place_name = placeVO.getPlace_name();
				String place_address = placeVO.getPlace_address();
				BigDecimal place_longitude = placeVO.getPlace_longitude();
				BigDecimal place_latitude = placeVO.getPlace_latitude();
				String place_tel = placeVO.getPlace_tel();
				String place_region = placeVO.getPlace_region();
				String place_type = placeVO.getPlace_type();
				String place_index = placeVO.getPlace_index();
				byte[] place_pic1 = placeVO.getPlace_pic1();
				byte[] place_pic2 = placeVO.getPlace_pic2();
				byte[] place_pic3 = placeVO.getPlace_pic3();
				Integer place_state = null;

				if( placeVO.getPlace_state()== 1 ) {
//					如目前為上架中則改為審核中
					place_state = 0 ;
					result.put("result" , "state_off");
				}else if(placeVO.getPlace_state()== 0) {
//					如目前為審核中則改為上架中
					place_state = 1 ;
					result.put("result" , "state_on");
				}
				
				Integer users_id = placeVO.getUsers_id();
				Integer business_time = placeVO.getBusiness_time();
				
				/*************************** 2.開始查詢資料/修改資料 *****************************************/
				
				placeSvc.updatePlace(place_id, place_name, place_address, place_longitude, place_latitude,
						place_tel, place_region, place_type, place_index, place_pic1, place_pic2, place_pic3,
						place_state, users_id, business_time);
				
				

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				JSONObject resultJSON = new JSONObject(result);
				out.println(resultJSON);

		}


		
		
	}

}
