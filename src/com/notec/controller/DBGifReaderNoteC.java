package com.notec.controller;

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

@WebServlet("/DBGifReaderNoteC")
public class DBGifReaderNoteC extends HttpServlet {


	Connection con;

	 public void doGet(HttpServletRequest req, HttpServletResponse res)
			   throws ServletException, IOException {
			  doPost(req, res);
			 }
			 
			 
			 public void doPost(HttpServletRequest req, HttpServletResponse res)
			   throws ServletException, IOException {
			 
			  res.setContentType("image/gif");
			  ServletOutputStream out = res.getOutputStream();

			  try {
			   Statement stmt = con.createStatement();
			   String note_c_id = req.getParameter("note_c_id").trim();
			   ResultSet rs = stmt.executeQuery("SELECT note_c_img FROM note_c where note_c_id=" + note_c_id);           
			   if (rs.next()) {
			    BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("note_c_img"));
			    byte[] buf = new byte[4 * 1024]; // 4K buffer
			    int len;
			    while ((len = in.read(buf)) != -1) {
			     out.write(buf, 0, len);
			     
			    }
			    
			    in.close();
			   
			   } else {
				   
			    InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			    byte[] b = new byte[in.available()];
			    in.read(b);
			    out.write(b);
			    in.close();
			   }
			    rs.close();
			    stmt.close();
			   } catch (Exception e) {

//			      InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");

			      InputStream in = getServletContext().getResourceAsStream("/NoData/3.0.png");

			    byte[] b = new byte[in.available()];
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
			    e.printStackTrace();
			   } catch (SQLException e) {
			    e.printStackTrace();
			   }
			  }
			 
			  public void destroy() {
			   try {
			    if (con != null) con.close();
			   } catch (SQLException e) {
//			    System.out.println(e);
			   }
			  }


}