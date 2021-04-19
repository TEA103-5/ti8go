package com.login.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class mailServlet
 */
//@WebServlet(
//	    urlPatterns={"/mail"},
//	    initParams={
//	        @WebInitParam(name = "host", value = "smtp.gmail.com"),
//	        @WebInitParam(name = "port", value = "587"),
//	        @WebInitParam(name = "username", value = "peter20433@gmail.com"),
//	        @WebInitParam(name = "password", value = "~!@#EDCXZAQWS")
//	    }
//	)
@WebServlet("/mail")
public class mailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private String host;
	    private int port;
	    private String username= "peter20433@gmail.com";
	    private String password;
	    private Properties props= new Properties();
	    
	    
		@Override
	    public void init() throws ServletException {
		    host = "smtp.gmail.com";
		    port = 465;
		    username = "peter20433@gmail.com";
		    password = "~!@#EDCXZAQWS";
		    
	        props = new Properties();
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.port", port);
	        
//	        Session.getInstance(props, new Authenticator() {  
//	            protected PasswordAuthentication getPasswordAuthentication() {  
//	                return new PasswordAuthentication(username, password);  
//	            }} 
//	        );  
//	        
//	        try {
//
//	        	Session  session = Session.getInstance(props,
//	                    new javax.mail.Authenticator() {
//	                      protected PasswordAuthentication getPasswordAuthentication() {
//	                          //returning user name and password to connect to email server
//	                          return new PasswordAuthentication("Username", "password");
//	                      }
//	                    });
//	           } catch(RuntimeException e) {
//	               e.printStackTrace(); // for example
//	           }
//	        Session session = Session.getInstance(props, 
//	        	      new javax.mail.Authenticator() { 
//	        	      protected PasswordAuthentication getPasswordAuthentication() { 
//	        	       return new PasswordAuthentication(username, password); 
//	        	      } 
//	        	      }); 
	        
	        
	    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        request.getParameter("from");
        request.getParameter("to");
        request.getParameter("subject");
        request.getParameter("text");
        
//        try {
//            Message message = createMessage(from, to, subject, text);
//            Transport.send(message);  
//            response.getWriter().println("郵件傳送成功");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
	}

//	private Message createMessage(
//            String from, String to, String subject, String text)
//                              throws Exception {
//        Session session = Session.getInstance(props, new Authenticator() {  
//            protected PasswordAuthentication getPasswordAuthentication() {  
//                return new PasswordAuthentication(username, password);  
//            }} 
//        );  
//        
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(from));
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//        message.setSubject(subject);
//        message.setSentDate(new Date());
//        message.setText(text);
//        
//        return message;
//    }
	
	
}
