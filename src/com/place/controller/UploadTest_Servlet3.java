package com.place.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// 當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
// 上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class UploadTest_Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// 為什麼再次getWriter()會亂碼???
//		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
//		res.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = res.getWriter();
		System.out.println("ContentType="+req.getContentType()); // 測試用

		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
//		out.write("<h2> Total parts : " + parts.size() + "</h2>");
		
		System.out.println( "在UploadTest_Servlet3裡的place_id" +(String)req.getParameter("place_id"));
		
		for (Part part : parts) {
			String filename = getFileNameFromPart(part);
//System.out.println("這裡是1");
			if (filename != null && part.getContentType() != null) {
				String name = part.getName();
				String ContentType = part.getContentType();
				long size = part.getSize();
//System.out.println("這裡是2");
				if( !ContentType.contains("image") ) {
					continue; // 如果該part不是圖片則跳過此次處理
				}

				System.out.println("name: " + name);
				System.out.println("filename: " + filename);
				System.out.println("ContentType: " + ContentType);
				System.out.println("size: " + size);
System.out.println("這裡是3");				
//System.out.println(("place_id = " + (String)req.getParameter("place_id")));

				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				in.close();
				System.out.println("buffer length: " + buf.length);
				
				req.setAttribute(name, buf);

				
//System.out.println("uploadServlet3的最後一行");
			}
		}
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
System.out.println("123filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}