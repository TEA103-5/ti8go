<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="deptSvc" scope="page"
	class="com.order.model.OrderService" />
<!DOCTYPE html>
<html>

<head>
    <title>Blank Page - Brand</title>
 <%@ include file="/sale-end/pages/link.html" %> 
 <style>
table#table-2 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-2 h4 {
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

.img {
	width: 50px;
	height: 50px;
}
</style>

<style>
body {
	font-family: Microsoft JhengHei;
}

.btn-circle {
	width: 30px;
	height: 30px;
	padding: 6px 0px;
	border-radius: 15px;
	text-align: center;
	font-size: 12px;
	line-height: 1.42857;
}
</style>
</head>

<body id="page-top">
    <div id="wrapper">
 <%@ include file="/sale-end/pages/nav.jsp" %> 
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
  <%@ include file="/sale-end/pages/fnav.html" %>
                <div class="container-fluid">
                    <h3 class="text-dark mb-1">Blank Page</h3>
                 <%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
		<th>訂單明細ID</th>
		<th>訂單明細數</th>
		<th>商品ID</th>
		<th>訂單ID</th>
	</tr>

		<c:forEach var="empVO" items="${listAllOrderDetail}">
			<%-- 		<tr ${(empVO.product_id==param.product_id) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色--> --%>
			<tr>
<%-- 		<td>${ordtVO.order_date}</td> --%>
		<td>${empVO.order_detail_id}</td>
		<td>${empVO.order_detail_count}</td>
		<td>${empVO.product_id}</td>
		<td>${empVO.order_id}</td>

				<td><c:forEach var="deptVO" items="${deptSvc.all}">
						<c:if test="${empVO.order_id==deptVO.order_id}">
	                    ${deptVO.order_id}【${deptVO.order_id}】
                    </c:if>
					</c:forEach></td>

			</tr>
		</c:forEach>

	</table>
	<!-- </div> -->
	<br>本網頁的路徑:
	<br>
	<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
		<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%>
	</b>   
                    
                    
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © Brand 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
 <%@ include file="/sale-end/pages/script.html" %> 
</body>

</html>