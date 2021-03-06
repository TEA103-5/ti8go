package com.msg_n.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msg_n.model.Msg_nService;
import com.msg_n.model.Msg_nVO;


/**
 * Servlet implementation class Msg_nServlet
 */
@WebServlet("/Msg_nServlet")
public class Msg_nServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Msg_nServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Msg_nService msg_nSvc = new Msg_nService();
		//新增
		msg_nSvc.addMsg_n("1351453",1,1);
		//刪除
		msg_nSvc.deleteMsg_n(2);
		//修改
		msg_nSvc.updateMsg_n("xxx",1);
		//查詢一筆
		Msg_nVO msg_nVO=msg_nSvc.getOneMsg_n(1);
		System.out.println(msg_nVO);
		//查詢全部
//		List<Msg_nVO> msg_nVO=msg_nSvc.getAll();
//		System.out.println(msg_nVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
