package com.msg_p_c.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.msg_p_c.model.Msg_p_cService;
import com.msg_p_c.model.Msg_p_cVO;

/**
 * Servlet implementation class Msg_p_cServlet
 */
@WebServlet("/Msg_p_cServlet")
public class Msg_p_cServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_p_cServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		Msg_p_cService msg_p_cSvc = new Msg_p_cService();
		//新增
		msg_p_cSvc.addMsg_p_c("1351453",1,1);
		//刪除
		//msg_p_cSvc.deleteMsg_p_c(2);
		//修改
		msg_p_cSvc.updateMsg_p_c("xxx",1);
		//查詢一筆
//		Msg_p_cVO msg_p_cVO=msg_p_cSvc.getOneMsg_p_c(1);
//		System.out.println(msg_p_cVO);
		//查詢全部
		List<Msg_p_cVO> msg_p_cVO=msg_p_cSvc.getAll();
		System.out.println(msg_p_cVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
