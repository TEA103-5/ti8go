<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

 <!-- 於EL此行可省略 -->
<jsp:useBean id="deptSvc" scope="page" class="com.sale.model.SaleService" />
<!DOCTYPE html>
<html>
<head>
<title>販售者商品 - listProds_BySaleid.jsp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- BootStrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
        crossorigin="anonymous">

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
         .img{ 
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
<div class="jumbotron jumbotron-fluid">
<div class="container text-center">
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-2">
	<tr><td>
		 <h3>販售者商品 - listProds_BySaleid.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page_product.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
<tr>
		<th>商品編號</th>
		<th>商品圖片</th>
		<th>商品名稱</th>
		<th>商品狀態</th>
		<th>商品內容</th>
		<th>商品描述</th>
		<th>商品分類</th>
		<th>商品價格</th>
		<th>商品庫存</th>
		<th>商品創建日期</th>
		<th>販售者</th>
	
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
		<c:forEach var="empVO" items="${listEmps_ByDeptno}" >
		<tr ${(empVO.product_id==param.product_id) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色-->
			<td>${empVO.product_id}</td>
			<td><img class="img" id="preimg" src="<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p"/></td>
		
			<td>${empVO.product_name}</td>
			<td>${empVO.product_status}</td>
			<td>${empVO.product_content}</td>
			<td>${empVO.product_description}</td>
			<td>${empVO.product_categories}</td>
			<td>${empVO.product_price}</td>
			<td>${empVO.product_stock}</td>
			<td>${empVO.product_update_time}</td>
			<td><c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.sale_id==deptVO.sale_id}">
	                    ${deptVO.sale_id}【<font color=orange>${deptVO.sale_name}</font> - ${deptVO.sale_phone}】
                    </c:if>
                </c:forEach>
			</td>
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/prod.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改"> 
			    <input type="hidden" name="empno"      value="${empVO.product_id}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/prod.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="empno"      value="${empVO.product_id}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	
</table>
</div>
<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>
  </div> 
   

  


</body>
</html>