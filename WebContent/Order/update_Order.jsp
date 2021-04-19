<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.users.model.*"%>

<%
OrderVO orderVO = (OrderVO) request.getAttribute("OrderVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�q���ƭק� - update_order_input.jsp</title>

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
		 <h3>�q���ƭק� - update_Order_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectOrder.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="Order.do" name="form1">
<table>
	<tr>
		<td>�q��s��:<font color=red><b>*</b></font></td>
		<td><%=orderVO.getOrder_id()%></td>
	</tr>
	
	<tr>
		<td>�q�檬�A:</td>
		<td><input type="TEXT" name="Order_status" size="45" value="<%=orderVO.getOrder_status()%>" /></td>
	</tr>
	
	<tr>
		<td>������:</td>
		<td><input type="TEXT" name=Order_rate size="45"	value="<%=orderVO.getOrder_rate()%>" /></td>
	</tr>
	
	<tr>
		<td>�H�Υd��:</td>
		<td><input type="TEXT" name="Card_number" size="45"	value="<%=orderVO.getCard_number()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>�ϥΪ̽s��:</td> -->
<%-- 		<td><input type="TEXT" name="Users_id" size="45" value="<%=orderVO.getUsers_id()%>" /></td> --%>
<!-- 	</tr> -->

	<jsp:useBean id="depSvc" scope="page" class="com.users.model.UsersService" />
	
	<tr>
		<td>�ϥΪ�ID:<font color=red><b>*</b></font></td>
		<td><select size="1" name="users_id">
			<c:forEach var="dptVO" items="${depSvc.all}">
				<option value="${dptVO.users_id}"${(orderVO.users_id==deptVO.users_id)? 'selected':'' }>${dptVO.users_id}
			</c:forEach>
		</select></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=orderVO.getOrder_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

