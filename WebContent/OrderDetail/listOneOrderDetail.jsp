<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Detail - listOneEmp.jsp</title>



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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>Detail - listOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectOrderDetail.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�q�����ID</th>
		<th>�q����Ӽ�</th>
		<th>�ӫ~ID</th>
		<th>�q��ID</th>
	</tr>
	<tr>
<%-- 		<td>${ordtVO.order_date}</td> --%>
		<td>${ordtVO.order_detail_id}</td>
		<td>${ordtVO.order_detail_count}</td>
		<td>${ordtVO.product_id}</td>
		<td>${ordtVO.order_id}</td>
	</tr> 
</table>

</body>
</html>