<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tivago Product_collect: Home</title>

<style>
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
<body bgcolor="white">

<table id="table-1">
	<tr><td><h3>Tivago Product_collect: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Tivago Product_collect: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllProduct_collect.jsp'>List</a> all Product_collect.  <br><br></li>
	
	<li>
	    <FORM METHOD="post" ACTION="product_collect.do" >
	        <b>查詢某使用者編號的全部收藏 (如1)(查看收藏時會取session裡的users_id, 所以不需做users_id輸入的錯誤處理):</b>
	        <input type="text" name="users_id">
	        <input type="hidden" name="action" value="getAllByPrimaryKey">
	        <input type="submit" value="送出">
    	</FORM>
	</li>
	
	<li>
	    <FORM METHOD="post" ACTION="product_collect.do" >
	        <b>查詢某筆特定的收藏:</b>
	        <input type="text" name="product_id" placeholder="請輸入商品編號">
	        <input type="text" name="users_id" placeholder="請輸入使用者編號">
	        <input type="hidden" name="action" value="getOne_For_Display">
	        <input type="submit" value="送出">
    	</FORM>
	</li>
	
	<jsp:useBean id="product_collectSvc" scope="page" class="com.product_collect.model.Product_collectService" />

<!-- 	<li> -->
<!-- 		<FORM METHOD="post" ACTION="product_collect.do"> -->
<!-- 			<b>選擇使用者編號:</b> <select size="1" name="users_id"> -->
<%-- 				<c:forEach var="product_collectVO" items="${product_collectSvc.all}"> --%>
<%-- 					<option value="${product_collectVO.users_id}">${product_collectVO.users_id} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> <input type="hidden" name="action" value="getAllByPrimaryKey"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 		</FORM> -->
<!-- 	</li> -->
	
<!-- 	<li> -->
<!-- 		<FORM METHOD="post" ACTION="product_collect.do"> -->
<!-- 			<b>選擇使用者姓名:</b> <select size="1" name="empno"> -->
<%-- 				<c:forEach var="empVO" items="${empSvc.all}"> --%>
<%-- 					<option value="${empVO.empno}">${empVO.ename} --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 			<input type="submit" value="送出"> -->
<!-- 		</FORM> -->
<!-- 	</li> -->
</ul>

<h3>商品收藏管理</h3>

<ul>
  <li><a href='addProduct_collect.jsp'>Add</a> a new Product_collect.</li>
</ul>

</body>
</html>

