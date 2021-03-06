package com.reminder_s.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder_s.model.Reminder_sService;
import com.reminder_s.model.Reminder_sVO;



/**
 * Servlet implementation class Reminder_sServlet
 */
@WebServlet("/Reminder_sServlet")
public class Reminder_sServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reminder_sServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
	
		Reminder_sService reminder_sSvc = new Reminder_sService();
		//新增
		reminder_sSvc.addReminder_s("1351453",1);
		//刪除
		//reminder_sSvc.deleteReminder_s(2);
		//修改
		reminder_sSvc.updateReminder_s(1,1);
		//查詢一筆
		Reminder_sVO reminder_sVO=reminder_sSvc.getOneReminder_s(1);
		System.out.println(reminder_sVO);
		//查詢全部
//		List<Reminder_sVO> reminder_sVO=reminder_sSvc.getAll();
//		System.out.println(reminder_sVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
