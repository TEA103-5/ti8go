<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.note_collect.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Note_collectVO note_collectVO = (Note_collectVO)request.getAttribute("note_collectVO");
%>

<%= note_collectVO==null %> 

<html>
<head>
<meta charset="UTF-8">
<title>遊記收藏資料 - listOneNote_collect.jsp</title>

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
		 <h3>遊記收藏資料 - listOneNote_collect.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>遊記編號</th>
		<th>使用者</th>
		<th>收藏時間</th>
	</tr>
		<tr>
			<td>${note_collectVO.note_id}</td>
			<td>${note_collectVO.users_id}</td>
			<td>${note_collectVO.note_collect_time}</td>
		</tr>
</table>

</body>
</html>