<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<!DOCTYPE html>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("OrderVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單資料新增</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單資料新增 </h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/selectOrder.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="Order.do" name="form1">
<table>
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="order_status" size="45" 
			 value="<%= (orderVO==null)? "" : orderVO.getOrder_status()%>" /></td>
	</tr>
	<tr>
		<td>評價分數:</td>
		<td><input type="TEXT" name="order_rate" size="45"
			 value="<%= (orderVO==null)? "" : orderVO.getOrder_rate()%>" /></td>
	</tr>
	<tr>
		<td>信用卡號:</td>
		<td><input type="TEXT" name="card_number" size="45"
			 value="<%= (orderVO==null)? "" : orderVO.getCard_number()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>使用者ID:</td> -->
<!-- 		<td><input type="TEXT" name="user_id" size="45" -->
<%-- 			 value="<%= (orderVO==null)? "" : orderVO.getUser_id()%>" /></td> --%>
<!-- 	</tr> -->

	<jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
	<tr>
		<td>明細:<font color=red><b>*</b></font></td>
		<td><select size="1" name="order_id">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.order_detail_id}" ${(orderVO.order_id==deptVO.order_id)? 'selected':'' } >${deptVO.order_detail_id}
			</c:forEach>
		</select></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>