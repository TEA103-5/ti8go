<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderDetail.model.*"%>
<!DOCTYPE html>
<%
OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("OrderDetailVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���Ӹ�Ʒs�W</title>

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
		 <h3>�ӫ~��Ʒs�W </h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/selectOrderDetail.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="OrderDetail.do" name="form1">
<table>
	<tr>
		<td>���Ӽƶq:</td>
		<td><input type="TEXT" name="detail_count" size="45" 
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_detail_count()%>" /></td>
	</tr>
	<tr>
		<td>����ID:</td>
		<td><input type="TEXT" name="detail_id" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_detail_id()%>" /></td>
	</tr>
	<tr>
		<td>�q��ID:</td>
		<td><input type="TEXT" name="order_id" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_id()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~ID:</td>
		<td><input type="TEXT" name="product_id" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getProduct_id()%>" /></td>
	</tr>

	<jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
	<tr>
		<td>����:<font color=red><b>*</b></font></td>
		<td><select size="1" name="detail_id">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.order_detail_id}" ${(orderVO.order_id==deptVO.order_id)? 'selected':'' } >${deptVO.order_detail_id}
			</c:forEach>
		</select></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>
</html>