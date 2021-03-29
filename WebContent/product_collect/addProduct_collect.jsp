<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_collect.model.*" %>

<%
	Product_collectVO product_collectVO = (Product_collectVO)request.getAttribute("product_collectVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品資料新增 - addProduct_collect.jsp</title>

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
	<tr><td>
		 <h3>商品資料新增 - addProduct_collect.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="product_collect.do" name="form1">
<table>
	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="product_id" size="45" 
			 value="<%= (product_collectVO==null)? "" : product_collectVO.getProduct_id()%>" /></td>
	</tr>
	<tr>
		<td>使用者:</td>
		<td><input type="TEXT" name="users_id" size="45" 
			 value="<%= (product_collectVO==null)? "" : product_collectVO.getUsers_id()%>" /></td>
	</tr>
	
	<jsp:useBean id="product_collectSvc" scope="page" class="com.product_collect.model.Product_collectService" />
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>