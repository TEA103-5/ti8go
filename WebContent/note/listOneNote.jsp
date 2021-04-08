<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.note.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  NoteVO noteVO = (NoteVO) request.getAttribute("noteVO");
%>

<html>
<head>
<title>遊記資料 - listOneNote.jsp</title>

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
	width: 600px;
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
		 <h3>遊記資料 - ListOneNote.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>遊記序號</th>
		<th>分類編號</th>
		<th>創建時間</th>
		<th>旅遊日期</th>
		<th>遊記標題</th>
		<th>遊記大綱</th>
		<th>更新時間</th>
		<th>讚數</th>
	</tr>
	<tr>
		<td><%=noteVO.getNote_id()%></td>
		<td><%=noteVO.getNote_classid()%></td>
		<td><%=noteVO.getNote_date()%></td>
		<td><%=noteVO.getTravel_start()%></td>
		<td><%=noteVO.getNote_title()%></td>
		<td><%=noteVO.getNote_description()%></td>
		<td><%=noteVO.getNote_update()%></td>
		<td><%=noteVO.getNote_like()%></td>
	</tr>
</table>

</body>
</html>