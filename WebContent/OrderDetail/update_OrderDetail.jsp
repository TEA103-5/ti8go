<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderDetail.model.*"%>

<%
OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("OrderDetailVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_Detail_input.jsp</title>

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
		 <h3>員工資料修改 - update_Detail_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectOrderDetail.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="OrderDetail.do" name="form1">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=orderDetailVO.getOrder_detail_id()%></td>
	</tr>
	
	<tr>
		<td>明細數量:</td>
		<td><input type="TEXT" name="detail_count" size="45" value="<%=orderDetailVO.getOrder_detail_count()%>" /></td>
	</tr>
	
	<tr>
		<td>明細ID:</td>
		<td><input type="TEXT" name=detail_id size="45"	value="<%=orderDetailVO.getOrder_detail_id()%>" /></td>
	</tr>
	
	<tr>
		<td>訂單ID:</td>
		<td><input type="TEXT" name="order_id" size="45"value="<%=orderDetailVO.getOrder_id()%>" /></td>
	</tr>
	<tr>
		<td>商品ID:</td>
		<td><input type="TEXT" name="product_id" size="45" value="<%=orderDetailVO.getProduct_id()%>" /></td>
	</tr>

	<jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
	<tr>
		<td>明細:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.order_detail_id}" ${(orderVO.order_id==deptVO.order_id)? 'selected':'' } >${deptVO.order_detail_id}
			</c:forEach>
		</select></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=orderDetailVO.getOrder_detail_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>

