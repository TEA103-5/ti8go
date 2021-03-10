<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>IBM Emp: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Emp: Home</p>

	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='<%=request.getContextPath()%>/sale/ListAllSale.jsp'>List</a>
			all Sales. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/sale/sale.do">
				<%
					/*這個在web.xml裡可以看到emp/emp.do 指的是com.emp.controller.EmpServlet
					    下面有個hidden叫action 以此字串來決定後面EmpServlet的行為
					    */
				%>
				<b>輸入販賣者編號 (如1):</b> <input type="text" name="empno"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>
		<jsp:useBean id="empSvc" scope="page"
			class="com.sale.model.SaleService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/sale/sale.do">
				<b>選擇員工編號:</b> <select size="1" name="empno">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.sale_id}">${empVO.sale_id}</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/sale/sale.do">
				<b>選擇員工姓名:</b> <select size="1" name="empno">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.sale_id}">${empVO.sale_name}</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>

	<h3>員工管理</h3>

	<ul>
		<li><a href='<%=request.getContextPath()%>/sale/addSale.jsp'>Add</a>
			a new Emp.</li>
	</ul>


</body>
</html>