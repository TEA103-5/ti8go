package com.sale.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DBGifReader4_1
 */
//@WebServlet("/DBGifReader4_1")
public class DBGifReader4_1 extends HttpServlet {
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		String id2=null;
		
		try{
		id2=req.getParameter("id2").trim();
		}catch(Exception e) {}

		try {
			Statement stmt = con.createStatement();
			String id=req.getParameter("id").trim();
			ResultSet rs=null;
			String wpic=null;
			if("p".equals(id2)) {
				rs = stmt.executeQuery("SELECT product_pic FROM product where product_id="+id);
				wpic="product_pic";
			}else {
				rs = stmt.executeQuery("SELECT sale_audit_pic FROM sale where sale_id="+id);
				wpic="sale_audit_pic";
			}

			if (rs.next()) {
				
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(wpic));
				
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);//404
				InputStream in=getServletContext().getResourceAsStream("/NoData/none.jpg");
				byte[] b=new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			
			
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in=getServletContext().getResourceAsStream("/NoData/null3.png");
			byte[] b=new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//surround with try catch
	//暴力貓

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
		//	System.out.println(e);
		}
	}

}
