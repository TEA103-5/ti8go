<%@page import="com.note_collect.model.Note_collectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.note_collect.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	System.out.println("測試");
// 因page1.file & page2.file 會重新連結到此頁 , 故需做此處理
	Integer users_id = null;
	if(  request.getParameter("users_id") != null){
		users_id = new Integer(request.getParameter("users_id"));
		session.setAttribute("users_id", users_id);
	}else{
		users_id = (Integer)(session.getAttribute("users_id"));
	}
	Note_collectService note_collectSvc = new Note_collectService();
	List<Note_collectVO> list = note_collectSvc.getAllByPrimaryKey(users_id);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>找出特定使用者id的所有收藏 - getAllByPrimaryKey.jsp</title>

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
		 <h3>找出特定使用者id的所有收藏 - getAllByPrimaryKey.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${errorMsgs}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>遊記編號</th>
		<th>使用者</th>
		<th>收藏時間</th>
	</tr>
	<%@ include file="page1.file"  %>
	<c:forEach var="note_collectVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${note_collectVO.note_id}</td>
			<td>${note_collectVO.users_id}</td>
			<td>${note_collectVO.note_collect_time}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/note_collect/note_collect.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="users_id"  value="${note_collectVO.users_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/note_collect/note_collect.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="users_id"  value="${note_collectVO.users_id}">
			     <input type="hidden" name="note_id"  value="${note_collectVO.note_id}">
			     <input type="hidden" name="action" value="delete">
			     <input type="hidden" name="source" value="fromGetAllByPrimaryKey"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>




</body>
</html>