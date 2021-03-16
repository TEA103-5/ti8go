<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	PlaceService placeSvc = new PlaceService();
	List<PlaceVO> list = placeSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有地點資料 - listAllPlace.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用EL的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有地點資料 - listAllPlace.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}" >
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>地點編號</th>
		<th>地點名稱</th>
		<th>地址</th>
		<th>經度</th>
		<th>緯度</th>
		<th>電話</th>
		<th>縣市</th>
		<th>地點類型</th>
		<th>地點簡介</th>
		<th>圖片1</th>
		<th>圖片2</th>
		<th>圖片3</th>
		<th>地點狀態</th>
		<th>建立者</th>
		<th>營業時間</th>
		<th>讚數</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="placeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${placeVO.place_id}</td>
			<td>${placeVO.place_name}</td>
			<td>${placeVO.place_address}</td>
			<td>${placeVO.place_longitude}</td>
			<td>${placeVO.place_latitude}</td>
			<td>${placeVO.place_tel}</td>
			<td>${placeVO.place_region}</td>
			<td>${placeVO.place_type}</td>
			<td>${placeVO.place_index}</td>
			<td>${placeVO.place_pic1}</td>
			<td>${placeVO.place_pic2}</td>
			<td>${placeVO.place_pic3}</td>
			<td>${placeVO.place_state}</td>
			<td>${placeVO.users_id}</td>
			<td>${placeVO.business_time}</td>
			<td>${placeVO.place_like}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="place_id"  value="${placeVO.place_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="place_id"  value="${placeVO.place_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>