package com.msg_pro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msg_pro.model.Msg_proService;
import com.msg_pro.model.Msg_proVO;

/**
 * Servlet implementation class Msg_proServlet
 */
@WebServlet("/Msg_proServlet")
public class Msg_proServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_proServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Msg_proService msg_proSvc = new Msg_proService();
		//新增
		msg_proSvc.addMsg_pro("1351453",1,1);
		//刪除
		//msg_pSvc.deleteMsg_p(2);
		//修改
		msg_proSvc.updateMsg_pro("xxx",1);
		//查詢一筆
		Msg_proVO msg_proVO=msg_proSvc.getOneMsg_pro(1);
		System.out.println(msg_proVO);
		//查詢全部
//		List<Msg_proVO> msg_proVO=msg_proSvc.getAll();
//		System.out.println(msg_proVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
