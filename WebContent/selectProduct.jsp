<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM pro: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: lightSkyBlue;
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

img {
	/* 				position: absolute; */
	/* 				left: 70px; */
	width: 400px;
	height: 300px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>IBM Product: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for pro: Home</p>

	<h3>��Ƭd��:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/Product/listAllProduct.jsp'>Get
				All</a> all pro. <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/Product/Product.do">
				<b>��J�ӫ~�s�� (�p1):</b> <input type="text" name="empno"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="�e�X">
			</FORM>
		</li>

		<jsp:useBean id="empSvc" scope="page"
			class="com.product.model.ProductService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/Product/Product.do">
				<b>��ܰӫ~�s��:</b> <select size="1" name="empno">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.product_id}">${empVO.product_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/Product/Product.do">
				<b>��ܰӫ~�W��:</b> <select size="1" name="empno">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.product_id}">${empVO.product_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>
<!-- 		</li> -->
<!-- 		    <li> -->
<%-- 		    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Product/Product.do" > --%>
<!-- 		        <b>�Ϥ�:</b> -->
<!-- 		        <img src ="BLOB_Reader?id=4"> -->
<!-- 		        <input type="text" name="pic"> -->
<!-- 		        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		        <input type="submit" value="�e�X"> -->
<!-- 		    </FORM> -->
<!-- 		  </li> -->
		<jsp:useBean id="deptSvc" scope="page"
 			class="com.product.model.ProductService" /> 
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/Product/Product.do">
				<img src ="BLOB_Reader?id=4">
				   <b>��ܹϤ��ӫ~:</b><select size="1" name="empno">
					<c:forEach var="productVO" items="${deptSvc.all}">
						<option value="${productVO.product_id}">${productVO.product_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="�e�X">
			</FORM>

		</li>




	</ul>


	<h3>�ӫ~�޲z</h3>

	<ul>
		<li><a
			href='<%=request.getContextPath()%>/Product/addProduct.jsp'>Add</a> a
			new pro.</li>
	</ul>

</body>
</html>