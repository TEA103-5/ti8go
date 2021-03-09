package com.msg_t_c.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msg_t_c.model.Msg_t_cService;
import com.msg_t_c.model.Msg_t_cVO;

/**
 * Servlet implementation class Msg_t_cServlet
 */
@WebServlet("/Msg_t_cServlet")
public class Msg_t_cServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_t_cServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		Msg_t_cService msg_t_cSvc = new Msg_t_cService();
		//新增
		msg_t_cSvc.addMsg_t_c("1351453",2,1);
		//刪除
		//msg_t_cSvc.deleteMsg_t_c(2);
		//修改
		msg_t_cSvc.updateMsg_t_c("xxx",1);
		//查詢一筆
//		Msg_t_cVO msg_t_cVO=msg_t_cSvc.getOneMsg_t_c(1);
//		System.out.println(msg_t_cVO);
		//查詢全部
		List<Msg_t_cVO> msg_t_cVO=msg_t_cSvc.getAll();
		System.out.println(msg_t_cVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
