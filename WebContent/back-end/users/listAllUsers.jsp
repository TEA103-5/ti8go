<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.users.model.*"%>
<%@ page import="com.users.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	UsersService UsersSvc = new UsersService();
    List<UsersVO> list = UsersSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="cardSvc" scope="page" class="com.card.model.cardService" /> --%>

<html>
<head>
<title>所有會員資料 - listAllUsers.jsp</title>

<!-- 底下是另外匯入的css -->

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllUsers.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/users/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<table id="table_id">
<%-- <%@ include file="pages/page1.file" %>  --%>

	<c:forEach var="usersVO" items="${list}" >
	
<thead>
	<tr>
		<th>使用者編號</th>
		<th>信箱</th>
		<th>密碼</th>
		<th>狀態</th>
		<th>暱稱</th>
		<th>姓名</th>
		<th>性別</th>
		<th>出日</th>
		<th>身分證</th>
		<th>頭像</th>
		<th>電話</th>
		<th>創建日期</th>
		<th>修改日期</th>
		<th>功能</th>
		
		
	</tr>
</thead>	
<tbody>		
		<tr>
			<td>${usersVO.users_id}</td>
			<td>${usersVO.users_mail}</td>
			<td>${usersVO.users_pwd}</td>
			<td>
			<c:if test="${usersVO.users_status==0}">停用</c:if>
			<c:if test="${usersVO.users_status==1}">正常</c:if>
			</td>
			<td>${usersVO.users_nickname}</td>
			<td>${usersVO.users_name}</td>
			<td>
			<c:if test="${usersVO.users_sex==0}">女</c:if>
			<c:if test="${usersVO.users_sex==1}">男</c:if>
			</td>
			<td>${usersVO.users_birthday}</td>
			<td>${usersVO.users_id_number}</td>
			<td><img src="<%=request.getContextPath()%>/UsersGetPic?id=${usersVO.users_id}" height="64" width="64"></td>
			<td>${usersVO.users_phone}</td>   
			<td>${usersVO.create_time}</td>
			<td>${usersVO.update_time}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/users/users.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="users_id"  value="${usersVO.users_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
<!-- 			</td> -->
<BR>
<!-- 			<td> -->
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/users/users.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="users_id"  value="${usersVO.users_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
</tbody>
	</c:forEach>
</table>
<%-- <%@ include file="pages/page2.file" %> --%>

 <!-- 底下是另外匯入的js -->
    <script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>

    <script>$(document).ready(function () {
            $('#table_id').DataTable();
        });</script>



</body>
</html>