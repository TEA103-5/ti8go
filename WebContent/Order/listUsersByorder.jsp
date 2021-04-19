<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>員工資料 - listOneOrder.jsp</title>

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
		 <h3>員工資料 - listOneOrder.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectOrder.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>訂單成立時間</th>
		<th>訂單狀態</th>
		<th>評價分數</th>
		<th>信用卡號</th>
		<th>購買者</th>
	</tr>
	<tr>
		<td>${ordVO.order_id}</td>
		<td>${ordVO.order_date}</td>
		<td>${ordVO.order_status}</td>
		<td>${ordVO.order_rate}</td>
		<td>${ordVO.card_number}</td>
		<td>${ordVO.users_id}</td>
	</tr> 
</table>

</body>
</html>