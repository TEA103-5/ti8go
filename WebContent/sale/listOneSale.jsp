<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.sale.model.*"%> 

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%SaleVO empVO1 = (SaleVO) request.getAttribute("empVO");%>
<%SaleService SSV= new SaleService();%>
<%SaleVO empVO =SSV.getOneSale(empVO1.getSale_id());%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>販售者資料- listOneSale.jsp</title>
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
  .img{
		width: 50px;
		height: 50px;
			}
</style>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>販售者資料 - listOneSale.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page_sale.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>販售者編號</th>
		<th>帳號</th>
		
		<th>販售者姓名</th>
		<th>販售者暱稱</th>
		<th>販售者連絡電話</th>
		<th>販售者帳號狀態</th>
		<th>販售者資格審核照片</th>
		<th>販售者資格審核狀態</th>
		<th>販售者評分</th>
		<th>販售者帳號創辦時間</th>
	</tr>
	<tr>
		<td><%=empVO.getSale_id()%></td>
		<td><%=empVO.getSale_email()%></td>
		<td><%=empVO.getSale_name()%></td>
		<td><%=empVO.getSale_nickname()%></td>
		<td><%=empVO.getSale_phone()%></td>
		<td><%=empVO.getSale_status()%></td>
		<td>
		<img class="img" src="<%=request.getContextPath()%>/DBGifReader4_1?id=<%=empVO.getSale_id()%>"/>
		</td>
		<td><%=empVO.getSale_audit_status()%></td>
		<td><%=empVO.getSale_rate()%></td>
		<td><%=empVO.getSale_time_create()%></td>
	</tr>
</table>
</body>
</html>