package com.msg_t.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.msg_t.model.Msg_tService;
import com.msg_t.model.Msg_tVO;

/**
 * Servlet implementation class Msg_tServlet
 */
@WebServlet("/Msg_tServlet")
public class Msg_tServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_tServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Msg_tService msg_tSvc = new Msg_tService();
		//新增
		msg_tSvc.addMsg_t("1351453",1,1);
		//刪除
		//msg_tSvc.deleteMsg_t(2);
		//修改
		msg_tSvc.updateMsg_t("xxx",1);
		//查詢一筆
		Msg_tVO msg_tVO=msg_tSvc.getOneMsg_t(1);
		System.out.println(msg_tVO);
		//查詢全部
//		List<Msg_tVO> msg_tVO=msg_tSvc.getAll();
//		System.out.println(msg_tVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
