package com.users.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.VerifyUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet("/VerifyPic")
public class VerifyPic extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> data = VerifyUtil.generateVerify();
        //獲取驗證碼
        String verifyCode = (String) data.get("verifyCode");

        //將驗證碼存入session
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode", verifyCode);
        //獲取圖片
        BufferedImage verifyPic = (BufferedImage) data.get("verifyPic");
        //輸出圖片給前端，使用工具類ImageIO
        ImageIO.write(verifyPic, "jpg", response.getOutputStream());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}