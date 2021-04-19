<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	OrderDetailService ordSvc = new OrderDetailService(); //���ݭn���~����,�����I�sService
	List<OrderDetailVO> list = ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="deptSvc" scope="page"
	class="com.orderDetail.model.OrderDetailService" />

<html>
<head>
<title>�Ҧ��ӫ~��� - listAllDetail.jsp</title>

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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ����u��� - listAllDetail.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/selectOrderDetail.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~���C --%>
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
		<th>�q�����ID</th>
		<th>�q����Ӽ�</th>
		<th>�ӫ~ID</th>
		<th>�q��ID</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="orderDetailVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
		<td>${orderDetailVO.order_detail_id}</td>
		<td>${orderDetailVO.order_detail_count}</td>
		<td>${orderDetailVO.product_id}</td>
		<td>${orderDetailVO.order_id}</td>
				<td>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="empno" value="${orderDetailVO.order_detail_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="empno" value="${orderDetailVO.order_detail_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>

</body>
</html>