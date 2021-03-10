package com.sale.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.sale.model.SaleService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/uploadServlet3_simple.do")
@MultipartConfig
public class UploadTest_Servlet3_Simple extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
//		req.getPart("upfile1").write(getServletContext().getRealPath("/images_uploaded")+"/file.gif");
		Part part=req.getPart("upfile1");
		InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		SaleService saleSvc = new SaleService();
		
		saleSvc.addSale("sale_email354", "sal54e_pwd", "sale_n5435ame", "sale_ph45one", "sale545_nickname", 5f,buf);
	}
}