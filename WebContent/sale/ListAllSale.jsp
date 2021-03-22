<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.sale.model.*"%>

<%
    SaleService empSvc = new SaleService();
    List<SaleVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
 %>

	
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>所有販售者資料 - ListAllSale.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
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
   .img{
		width: 50px;
		height: 50px;
			}
</style>
</head>
<body>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有販售者資料 - ListAllSale.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page_product.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>販售者編號</th>
		<th>帳號</th>
		<th>販售者姓名</th>
		<th>販售者暱稱</th>
		<th>販售者連絡電話</th>
		<th>販售者帳號狀態</th>
		<th>販售者資格審核照片</th>
		<th>販售者資格審核狀態</th>
		<th>販售者評分</th>
		<th>販售者帳號創辦時間</th>
		<th>修改</th>
		<th>刪除</th>
		<th>查詢販售商品</th>
	</tr>
<%@ include file="pages/page1.file" %> 
<c:forEach var="saleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${saleVO.sale_id}</td>
			<td>${saleVO.sale_email}</td>
			<td>${saleVO.sale_name}</td>
			<td>${saleVO.sale_nickname}</td>
			<td>${saleVO.sale_phone}</td>
			<td>${saleVO.sale_status}</td>
			<td>
		<img class="img" src="<%=request.getContextPath()%>/DBGifReader4_1?id=${saleVO.sale_id}"/>
		</td>
			<td>${saleVO.sale_audit_status}</td>
			<td>${saleVO.sale_rate}</td> 
			<td><fmt:formatDate value="${saleVO.sale_time_create}" pattern="yyyy-MM-dd"/></td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sale/sale.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empno"  value="${saleVO.sale_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sale/sale.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="empno"  value="${saleVO.sale_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sale/sale.do" style="margin-bottom: 0px;">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="deptno" value="${saleVO.sale_id}">
			    <input type="hidden" name="action" value="listEmps_ByDeptno_B"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
<%if (request.getAttribute("listEmps_ByDeptno")!=null){%>
       <jsp:include page="listProds_BySaleid.jsp" />
<%} %>




</body>
</html>