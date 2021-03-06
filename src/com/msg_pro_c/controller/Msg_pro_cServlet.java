package com.msg_pro_c.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msg_pro_c.model.Msg_pro_cVO;
import com.msg_pro_c.model.Msg_pro_cService;

/**
 * Servlet implementation class Msg_pro_cServlet
 */
@WebServlet("/Msg_pro_cServlet")
public class Msg_pro_cServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_pro_cServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Msg_pro_cService msg_pro_cSvc = new Msg_pro_cService();
		//新增
		msg_pro_cSvc.addMsg_pro_c("1351453",1,1);
		msg_pro_cSvc.addMsg_pro_cBySale("1351453",1,1);
		//刪除
		//msg_pro_cSvc.deleteMsg_pro_c(2);
		//修改
		msg_pro_cSvc.updateMsg_pro_c("xxx",1);
		//查詢一筆
//		Msg_pro_cVO msg_pro_cVO=msg_pro_cSvc.getOneMsg_pro_c(1);
//		System.out.println(msg_pro_cVO);
		//查詢全部
		List<Msg_pro_cVO> msg_pro_cVO=msg_pro_cSvc.getAll();
		System.out.println(msg_pro_cVO);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
