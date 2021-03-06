<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.place.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	PlaceVO placeVO = (PlaceVO)request.getAttribute("placeVO");//PlaceServlet.java(Concroller), 存入req的placeVO物件
%>

<%= placeVO==null %> -- ${placeVO.place_id}

<html>
<head>
<meta charset="UTF-8">
<title>地點資料 - listOnePlace.jsp</title>

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
	width: 600px;
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
		 <h3>地點資料 - listOnePlace.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
<!-- 		<th>營業時間</th> -->
		<th>讚數</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
		<tr>
			<td><%=placeVO.getPlace_id()%></td>
			<td><%=placeVO.getPlace_name()%></td>
			<td><%=placeVO.getPlace_address()%></td>
			<td><%=placeVO.getPlace_longitude()%></td>
			<td><%=placeVO.getPlace_latitude()%></td>
			<td><%=placeVO.getPlace_tel()%></td>
			<td><%=placeVO.getPlace_region()%></td>
			<td><%=placeVO.getPlace_type()%></td>
			<td><%=placeVO.getPlace_index()%></td>
			<td><img width="300" height="225" src="DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}"></td>
			<td><img width="300" height="225" src="DBGifReader4.do?place_pic=place_pic2&place_id=${placeVO.place_id}"></td>
			<td><img width="300" height="225" src="DBGifReader4.do?place_pic=place_pic3&place_id=${placeVO.place_id}"></td>
			<td><c:choose>
	            	<c:when test="${placeVO.place_state == 1}">
	                	上架中
	           		</c:when>
	          		<c:otherwise>
	                 	審核中
	            	</c:otherwise>
	        	</c:choose></td>
			<td><%=placeVO.getUsers_id()%></td>
<%-- 			<td><%=placeVO.getBusiness_time()%></td> --%>
			<td><%=placeVO.getPlace_like()%></td>
		</tr>
</table>

</body>
</html>