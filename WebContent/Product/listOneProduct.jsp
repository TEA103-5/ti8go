<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<!--  ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO���� -->
<%--  <%ProductVO empVO = (ProductVO) request.getAttribute("productVO");%>  --%>

<%-- <%-- ���X ������DeptVO���� --%> --%>
<%-- <%-- <% --%> --%>
<%-- <% --%>
//     ProductService deptSvc = new ProductService();
//     ProductVO deptVO = deptSvc.getOneProduct(empVO.getProduct_id());
<%--     %> --%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
  .my_img{
width:130px;
height:80px;
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
		 <h3>���u��� - listOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectProduct.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ӫ~�W��</th>
		<th>�W�U�[���A</th>
		<th>�ӫ~���e</th>
		<th>�ӫ~�y�z</th>
		<th>�W�[�ɶ�</th>
		<th>�ӫ~����</th>
		<th>�ӫ~����</th>
		<th>�w�s</th>
		<th>�ӫ~�Ϥ�</th>
		<th>�ӫ~�֦���</th>
	</tr>
	<tr>
		<td>${empVO.product_name}</td>
		<td>${empVO.product_status}</td>
		<td>${empVO.product_content}</td>
		<td>${empVO.product_description}</td>
		<td>${empVO.product_update_time}</td>
		<td>${empVO.product_categories}</td>
		<td>${empVO.product_price}</td>
		<td>${empVO.product_stock}</td>
<%--  		<td>${empVO.product_pic}</td>  --%>
<%-- 		<td>${empVO.sale_id}</td> --%>
		        <td>
			<img class="my_img" src="<%=request.getContextPath()%>/BLOB_Reader?id=${empVO.product_id}">
			</td>  
	</tr> 
</table>

</body>
</html>