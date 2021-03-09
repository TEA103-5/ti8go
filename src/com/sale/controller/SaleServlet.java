package com.sale.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sale.model.SaleService;
import com.sale.model.SaleVO;




/**
 * Servlet implementation class SaleServlet
 */
@WebServlet("/SaleServlet")
public class SaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		SaleService saleSvc = new SaleService();
		
		//新增
//		saleSvc.addSale(sale_email, sale_pwd, sale_name, sale_audit_pic, sale_phone, sale_nickname, sale_rate);
		saleSvc.addSale("sale_email", "sale_pwd", "sale_name", "sale_phone", "sale_nickname", 5f);
		//刪除
		//saleSvc.deleteSale(2);
		//修改
//		saleSvc.updateSale(sale_pwd, sale_audit_status, *sale_audit_pic*, sale_name, sale_status, sale_phone, sale_nickname, sale_rate, sale_id);
		saleSvc.updateSale("sale_pwd1",1,"sale_name1",1,"sale_phone2","sale_nickname2",4f,1);
		//查詢一筆
		SaleVO saleVO=saleSvc.getOneSale(1);
		System.out.println(saleVO);
		//查詢全部
//		List<SaleVO> saleVO=saleSvc.getAll();
//		System.out.println(saleVO);
	}

	/**			    
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
