<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	ProductService proSvc = new ProductService(); //���ݭn���~����,�����I�sService
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="deptSvc" scope="page"
	class="com.product.model.ProductService" />

<html>
<head>
<title>�Ҧ��ӫ~��� - listAllEmp.jsp</title>

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
.my_img{
width:130px;
height:80px;
}
</style>

</head>
<body bgcolor='white'>

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ����u��� - listAllEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/selectProduct.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>�ӫ~�s��</th>
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
		<%@ include file="pages/page1.file"%>
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${productVO.product_id}</td>
				<td>${productVO.product_name}</td>
				<td>${productVO.product_status}</td>
				<td>${productVO.product_content}</td>
				<td>${productVO.product_description}</td>
				<td>${productVO.product_update_time}</td>
				<td>${productVO.product_categories}</td>
				<td>${productVO.product_price}</td>
				<td>${productVO.product_stock}</td>
<%-- 				<td>${productVO.product_pic}</td> --%>
				<td>${productVO.sale_id}</td>
				<td>

			
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Product/Product.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="empno" value="${productVO.product_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Product/Product.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="empno" value="${productVO.product_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			<td>
			<img class="my_img" src="<%=request.getContextPath()%>/BLOB_Reader?id=${productVO.product_id}">
			</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>

</body>
</html>