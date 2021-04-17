<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.admins.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件 --%>

<% AdminsVO adminsVO = (AdminsVO) request.getAttribute("adminsVO"); %>
 

<%-- 取出 對應的DeptVO物件--%>
<%--
//  cardService cardSvc = new cardService();
//  cardVO cardVO = cardSvc.getOnecard(cardVO.getadmins());
--%>

<html>
<head>
<title>會員資料 - listOneAdmins.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員資料 - listOneAdmins.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/admins/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>管理者編號</th>
		<th>信箱</th>
		<th>姓名</th>
		<th>密碼</th>
		<th>性別</th>
		<th>權限</th>
		<th>職位</th>
		<th>創建日期</th>
		<th>最後修改日期</th>
	</tr>
	<tr>
			<td>${adminsVO.admins_id}</td>
			<td>${adminsVO.admins_email}</td>
			<td>${adminsVO.admins_name}</td>
			<td>${adminsVO.admins_password}</td>
			<td>${adminsVO.admins_sex}</td>
			<td>${adminsVO.admins_authority}</td>
			<td>${adminsVO.admins_position}</td>
			<td>${adminsVO.admins_create_time}</td>
			<td>${adminsVO.admins_edit_time}</td>  	
	</tr>

		</table>
	</body>
</html>