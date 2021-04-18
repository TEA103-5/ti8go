<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

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
		 <h3>�ӫ~��ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectProduct.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="Product.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=productVO.getProduct_id()%></td>
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="product_name" size="45" value="<%=productVO.getProduct_name()%>" /></td>
	</tr>
	<tr>
		<td>���A:</td>
		<td><input type="TEXT" name=product_status size="45"	value="<%=productVO.getProduct_status()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>�W�[�ɶ�:</td> -->
<!-- 		<td><input name="hiredate" id="f_date1" type="text" ></td> -->
<!-- 	</tr> -->
	<tr>
		<td>�ӫ~���e:</td>
		<td><input type="TEXT" name="product_content" size="45"	value="<%=productVO.getProduct_content()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�y�z:</td>
		<td><input type="TEXT" name="product_description" size="45" value="<%=productVO.getProduct_description()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="product_categories" size="45" value="<%=productVO.getProduct_categories()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="product_price" size="45" value="<%=productVO.getProduct_price()%>" /></td>
	</tr>
	<tr>
		<td>�w�s:</td>
		<td><input type="TEXT" name="product_stock" size="45" value="<%=productVO.getProduct_stock()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�Ϥ�:</td>
		<td><input type="file" name="product_pic" size="45" value="<%=productVO.getProduct_pic()%>"/></td>
	</tr>
<%-- 	<%=productVO.getProduct_pic()%> --%>
<!-- 	<tr> -->
<!-- 		<td>�ӫ~�֦���:</td> -->
<%-- 		<td><input type="TEXT" name="sname" size="45" value="<%=productVO.getSale_id()%>" /></td> --%>
<!-- 	</tr> -->

	<jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
	<tr>
		<td>����:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.order_detail_id}" ${(productVO.product_id==deptVO.product_id)? 'selected':'' } >${deptVO.order_detail_id}
			</c:forEach>
		</select></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=productVO.getProduct_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

