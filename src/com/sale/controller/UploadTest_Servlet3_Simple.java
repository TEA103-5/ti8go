package com.sale.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.sale.model.SaleService;
import com.sale.model.SaleVO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

//@WebServlet("/uploadServlet3_simple.do")
@MultipartConfig
public class UploadTest_Servlet3_Simple extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		req.getPart("upfile1").write(getServletContext().getRealPath("/images_uploaded")+"/file.gif");
		Integer sale_id = new Integer(req.getParameter("empno").trim());
		String name=req.getParameter("sname");
		System.out.println(name);
		Part part=req.getPart("upfile1");
		InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		SaleService saleSvc = new SaleService();
//		System.out.println(sale_status);
		saleSvc.updateSaleP(sale_id,buf);
		//這裡有個如何判斷是不是圖片的問題
		SaleService empVO1=new SaleService();
		SaleVO saleVO=empVO1.getOneSale(sale_id);
		/***************************3.修改完成.準備轉交(Send the Success view)*************/
		req.setAttribute("empVO", saleVO); // 資料庫update成功後,正確的empVO物件,存入req
		String url = "/sale/update_sale.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後.轉交listOneEmp.jsp
		successView.forward(req, res);
	}
}