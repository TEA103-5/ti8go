<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<!--  取出 Concroller EmpServlet.java已存入request的EmpVO物件 -->
<%--  <%ProductVO empVO = (ProductVO) request.getAttribute("productVO");%>  --%>

<%-- <%-- 取出 對應的DeptVO物件 --%> --%>
<%-- <%-- <% --%> --%>
<%-- <% --%>
//     ProductService deptSvc = new ProductService();
//     ProductVO deptVO = deptSvc.getOneProduct(empVO.getProduct_id());
<%--     %> --%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
  .my_img{
width:130px;
height:80px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/selectProduct.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
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
	<tr>
		<td>${empVO.product_name}</td>
		<td>${empVO.product_status}</td>
		<td>${empVO.product_content}</td>
		<td>${empVO.product_description}</td>
		<td>${empVO.product_update_time}</td>
		<td>${empVO.product_categories}</td>
		<td>${empVO.product_price}</td>
		<td>${empVO.product_stock}</td>
<%--  		<td>${empVO.product_pic}</td>  --%>
<%-- 		<td>${empVO.sale_id}</td> --%>
		        <td>
			<img class="my_img" src="<%=request.getContextPath()%>/BLOB_Reader?id=${empVO.product_id}">
			</td>  
	</tr> 
</table>

</body>
</html>