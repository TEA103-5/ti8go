<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>

<!-- 於EL此行可省略 -->
<jsp:useBean id="deptSvc" scope="page"
	class="com.users.model.UsersService" />
<!DOCTYPE html>
<html>
<head>
<title>listOrderByUsersId.jsp</title>
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--     <meta http-equiv="X-UA-Compatible" content="ie=edge"> -->

<!--     BootStrap -->
<!--     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" -->
<!--         crossorigin="anonymous"> -->

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
<body>
	<!-- <div class="jumbotron jumbotron-fluid"> -->
	<!-- <div class="container text-center"> -->
	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-2">
		<tr>
			<td>
				<h3>listOrderByUsersId</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/selectOrder.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

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
			<th>訂單編號</th>
			<th>訂單成立時間</th>
			<th>訂單狀態</th>
			<th>評價分數</th>
			<th>信用卡號</th>
			<th>購買者</th>

			<!-- 		<th>修改</th> -->
			<!-- 		<th>刪除</th> -->
		</tr>

		<c:forEach var="empVO" items="${listOrderByUsersId}">
			<%-- 		<tr ${(empVO.product_id==param.product_id) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色--> --%>
			<tr>
				<td>${empVO.order_id}</td>
				<td>${empVO.order_date}</td>
				<td>${empVO.order_status}</td>


				<td>${empVO.order_rate}</td>
				<td>${empVO.card_number}</td>

				<td><c:forEach var="deptVO" items="${deptSvc.all}">
						<c:if test="${empVO.users_id==deptVO.users_id}">
	                    ${deptVO.users_id}【${deptVO.users_name}】
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
	<!--   </div>  -->




	<%-- <%@ include file="pages/page3.file" %> --%>
</body>
</html>