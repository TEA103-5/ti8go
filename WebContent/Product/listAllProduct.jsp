<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	ProductService proSvc = new ProductService(); //不需要錯誤驗證,直接呼叫Service
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="deptSvc" scope="page"
	class="com.product.model.ProductService" />

<html>
<head>
<title>所有商品資料 - listAllEmp.jsp</title>

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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工資料 - listAllEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/selectProduct.jsp"><img
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
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>上下架狀態</th>
			<th>商品內容</th>
			<th>商品描述</th>
			<th>上架時間</th>
			<th>商品分類</th>
			<th>商品價格</th>
			<th>庫存</th>
			<th>商品圖片</th>
			<th>商品擁有者</th>
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
						<input type="submit" value="修改"> <input type="hidden"
							name="empno" value="${productVO.product_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/Product/Product.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
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