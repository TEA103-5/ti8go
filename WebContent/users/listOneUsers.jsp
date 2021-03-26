<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.users.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件 --%>

<% UsersVO usersVO = (UsersVO) request.getAttribute("usersVO"); %>
 

<%-- 取出 對應的DeptVO物件--%>
<%--
//  cardService cardSvc = new cardService();
//  cardVO cardVO = cardSvc.getOnecard(cardVO.getusers());
--%>

<html>
<head>
<title>會員資料 - listOneUsers.jsp</title>

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
		 <h3>會員資料 - listOneUsers.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/users/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>使用者編號</th>
		<th>使用者信箱</th>
		<th>密碼</th>
		<th>認證信確認狀態</th>
		<th>使用者暱稱</th>
		<th>名字</th>
		<th>性別</th>
		<th>出生年月日</th>
		<th>身分證字號</th>
		<th>頭像照片</th>
		<th>連絡電話</th>
		<th>創建日期</th>
		<th>最後修改日期</th>

	</tr>
	<tr>
			<td>${usersVO.users_id}</td>
			<td>${usersVO.users_mail}</td>
			<td>${usersVO.users_pwd}</td>
			<td>${usersVO.users_status}</td>
			<td>${usersVO.users_nickname}</td>
			<td>${usersVO.users_name}</td>
			<td>${usersVO.users_sex}</td>
			<td>${usersVO.users_birthday}</td>
			<td>${usersVO.users_id_number}</td>
			<td><img src="../UsersGetPic?id=${usersVO.users_id}" height="64" width="64"></td>
			<td>${usersVO.users_phone}</td>   
			<td>${usersVO.create_time}</td>
			<td>${usersVO.update_time}</td>  	
	</tr>
<!-- 	<tr> -->
<%-- 		<td><%=empVO.getEmpno()%></td> --%>
<%-- 		<td><%=empVO.getEname()%></td> --%>
<%-- 		<td><%=empVO.getJob()%></td> --%>
<%-- 		<td><%=empVO.getHiredate()%></td> --%>
<%-- 		<td><%=empVO.getSal()%></td> --%>
<%-- 		<td><%=empVO.getComm()%></td> --%>
<%-- 		<td><%=empVO.getDeptno()%>【<%=deptVO.getDname()%> - <%=deptVO.getLoc()%>】</td> --%>
<!-- 	</tr> -->
		</table>
	</body>
</html>