package com.msg_n_c.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msg_n_c.model.Msg_n_cService;
import com.msg_n_c.model.Msg_n_cVO;

/**
 * Servlet implementation class Msg_n_cServlet
 */
@WebServlet("/Msg_n_cServlet")
public class Msg_n_cServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_n_cServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		Msg_n_cService msg_n_cSvc = new Msg_n_cService();
		//新增
		msg_n_cSvc.addMsg_n_c("1351453",1,1);
		//刪除
		//msg_n_cSvc.deleteMsg_n_c(2);
		//修改
		msg_n_cSvc.updateMsg_n_c("xxx",1);
		//查詢一筆
//		Msg_n_cVO msg_n_cVO=msg_n_cSvc.getOneMsg_n_c(1);
//		System.out.println(msg_n_cVO);
		//查詢全部
		List<Msg_n_cVO> msg_n_cVO=msg_n_cSvc.getAll();
		System.out.println(msg_n_cVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
