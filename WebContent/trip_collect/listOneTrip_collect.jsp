<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.trip_collect.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Trip_collectVO trip_collectVO = (Trip_collectVO)request.getAttribute("trip_collectVO");
%>

<%= trip_collectVO==null %> 

<html>
<head>
<meta charset="UTF-8">
<title>行程收藏資料 - listOneTrip_collect.jsp</title>

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
		 <h3>行程收藏資料 - listOneTrip_collect.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>行程編號</th>
		<th>使用者</th>
		<th>收藏時間</th>
	</tr>
		<tr>
			<td>${trip_collectVO.trip_id}</td>
			<td>${trip_collectVO.users_id}</td>
			<td>${trip_collectVO.trip_collect_time}</td>
		</tr>
</table>

</body>
</html>