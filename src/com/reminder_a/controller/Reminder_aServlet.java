package com.reminder_a.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder_a.model.Reminder_aService;
import com.reminder_a.model.Reminder_aVO;


/**
 * Servlet implementation class Reminder_aServlet
 */
@WebServlet("/Reminder_aServlet")
public class Reminder_aServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reminder_aServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		Reminder_aService reminder_aSvc = new Reminder_aService();
		//新增
		reminder_aSvc.addReminder_a("1351453",1);
		//刪除
		//reminder_aSvc.deleteReminder_a(2);
		//修改
		reminder_aSvc.updateReminder_a(1,1);
		//查詢一筆
		Reminder_aVO reminder_aVO=reminder_aSvc.getOneReminder_a(1);
		System.out.println(reminder_aVO);
		//查詢全部
//		List<Reminder_aVO> reminder_aVO=reminder_aSvc.getAll();
//		System.out.println(reminder_aVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
