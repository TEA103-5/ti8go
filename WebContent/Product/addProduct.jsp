<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<!DOCTYPE html>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料新增</title>

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
		<tr>
			<td>
				<h3>商品資料新增</h3>
			</td>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/selectProduct.jsp"><img
						src="images/tomcat.png" width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<jsp:useBean id="empSvc" scope="page"
		class="com.product.model.ProductService" />
	<FORM METHOD="post" ACTION="Product.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="product_name" size="45"
					value="<%=(productVO == null) ? "登山杖" : productVO.getProduct_name()%>" /></td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="TEXT" name="product_status" size="45"
					value="<%=(productVO == null) ? "1" : productVO.getProduct_status()%>" /></td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td>上架時間:</td> -->
<%-- 				<td><input name="time" id="product_id" type="text">${productVO.product_update_time}</td> --%>
<!-- 			</tr> -->
			<tr>
				<td>商品內容:</td>
				<td><input type="TEXT" name="product_content" size="45"
					value="<%=(productVO == null) ? "是個產品" : productVO.getProduct_content()%>" /></td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td><input type="TEXT" name="product_description" size="45"
					value="<%=(productVO == null) ? "超棒不買嗎" : productVO.getProduct_description()%>" /></td>
			</tr>
			<tr>
				<td>商品分類:</td>
				<td><input type="TEXT" name="product_categories" size="45"
					value="<%=(productVO == null) ? "產品" : productVO.getProduct_categories()%>" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="product_price" size="45"
					value="<%=(productVO == null) ? "487" : productVO.getProduct_price()%>" /></td>
			</tr>
			<tr>
				<td>庫存:</td>
				<td><input type="TEXT" name="product_stock" size="45"
					value="<%=(productVO == null) ? "50" : productVO.getProduct_stock()%>" /></td>
			</tr>
			<tr>
				<td>商品圖片:</td>
				<td><input type="file" name="product_pic" size="45"
					value="<%=(productVO == null) ? "" : productVO.getProduct_pic()%>" />
<!-- 					<input type="file" name="upfile1"></td> -->
			</tr>

<!-- 				<tr> -->
<!-- 					<td>商品擁有者:</td> -->
<!-- 					<td><input type="TEXT" name="sale_id" size="45" -->
<%-- 						 value="<%= (productVO==null)? "擁有者" : productVO.getSale_id()%>" /></td> --%>
<!-- 				</tr> -->

			<jsp:useBean id="deptSvc" scope="page"
				class="com.orderDetail.model.OrderDetailService" />
			<tr>
				<td>明細:<font color=red><b>*</b></font></td>
				<td><select size="1" name="product_id">
						<c:forEach var="deptVO" items="${deptSvc.all}">
							<option value="${deptVO.order_detail_id}"
								${(productVO.product_id==deptVO.product_id)? 'selected':'' }>${deptVO.order_detail_id}
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>
</html>