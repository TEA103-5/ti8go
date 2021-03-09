package com.msg_p.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.msg_p.model.Msg_pService;
import com.msg_p.model.Msg_pVO;

/**
 * Servlet implementation class Msg_pServlet
 */
@WebServlet("/Msg_pServlet")
public class Msg_pServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_pServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Msg_pService msg_pSvc = new Msg_pService();
		//新增
		msg_pSvc.addMsg_p("1351453",1,1);
		//刪除
		//msg_pSvc.deleteMsg_p(2);
		//修改
		msg_pSvc.updateMsg_p("xxx",1);
		//查詢一筆
//		Msg_pVO msg_pVO=msg_pSvc.getOneMsg_p(1);
		//System.out.println(msg_pVO);
		//查詢全部
		List<Msg_pVO> msg_pVO=msg_pSvc.getAll();
		System.out.println(msg_pVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
