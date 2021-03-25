package com.users.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class UsersGetPic extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		try {
			Statement stmt = con.createStatement();
//			System.out.println("測試用");
			String id = req.getParameter("id").trim();  //trim
			ResultSet rs = stmt.executeQuery("SELECT users_pic FROM users where users_id =" + id);
			
//				"SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
				
			if (rs.next()) {
				
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("users_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		DataSource ds = null;
	
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
				con = ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		//		try {
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
//			System.out.println(e);
		}
	}

}