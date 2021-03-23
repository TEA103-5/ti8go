<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.card.model.*"%>
<%@ page import="com.users.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件 --%>

<% CardVO cardVO = (CardVO) request.getAttribute("cardVO"); %>
 

<%-- 取出 對應的DeptVO物件--%>
<%--
//  cardService cardSvc = new cardService();
//  cardVO cardVO = cardSvc.getOnecard(cardVO.getusers());
--%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/card/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>信用卡編號</th>
		<th>會員編號</th>
		<th>信用卡編號</th>
		<th>信用卡到期日</th>
		<th>信用卡末三碼</th>
		<th>信用卡狀態</th>
	</tr>
	<tr>
		<td>${cardVO.card_id}</td>
		<td>${cardVO.users_id}</td>
		<td>${cardVO.card_number}</td>
		<td>${cardVO.card_date}</td>
		<td>${cardVO.card_last}</td>
		<td>${cardVO.card_default}</td>
		
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