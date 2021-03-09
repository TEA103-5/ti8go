package com.reminder_u.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder_u.model.Reminder_uService;
import com.reminder_u.model.Reminder_uVO;



/**
 * Servlet implementation class Reminder_uServlet
 */
@WebServlet("/Reminder_uServlet")
public class Reminder_uServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reminder_uServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Reminder_uService reminder_uSvc = new Reminder_uService();
		//新增
		reminder_uSvc.addReminder_u("1351453",1);
		//刪除
		//reminder_uSvc.deleteReminder_u(2);
		//修改
		reminder_uSvc.updateReminder_u(1,1);
		//查詢一筆
		Reminder_uVO reminder_uVO=reminder_uSvc.getOneReminder_u(1);
		System.out.println(reminder_uVO);
		//查詢全部
//		List<Reminder_uVO> reminder_uVO=reminder_uSvc.getAll();
//		System.out.println(reminder_uVO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
