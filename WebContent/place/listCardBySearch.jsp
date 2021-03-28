<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
${not empty list}

<%

	session.setAttribute("users_id", 10);  //  測試用

	List<PlaceVO> list = null;	
	if(request.getAttribute("list") != null){
		list = (List<PlaceVO>)request.getAttribute("list");
		session.setAttribute("list", list);
	}else{
		list = (List<PlaceVO>)session.getAttribute("list");
	}
// 	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>取出所有選中的卡片資料 - listCardBySearch.jsp</title>

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
		 <h3>取出所有選中的卡片資料 - listCardBySearch.jsp</h3>
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
		<th>圖片1</th>
		<th>地點狀態</th>
		<th>建立者</th>
		<th>讚數</th>
		<th>地點詳情</th>
		<th>加入收藏</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="placeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${placeVO.place_id}</td>
			<td>${placeVO.place_name}</td>
			<td>${placeVO.place_address}</td>
			<td>${placeVO.place_longitude}</td>
			<td>${placeVO.place_latitude}</td>
			<td><img width="300" height="225" src="DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}"></td>
			<td><c:choose>
	            	<c:when test="${placeVO.place_state == 1}">
	                	上架中
	           		</c:when>
	          		<c:otherwise>
	                 	審核中
	            	</c:otherwise>
	        	</c:choose></td>
			<td>${placeVO.users_id}</td>
			<td>${placeVO.place_like}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" style="margin-bottom: 0px;">
			     <input type="submit" value="地點詳情">
			     <input type="hidden" name="place_id"  value="${placeVO.place_id}">
			     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place_collect/place_collect.do" style="margin-bottom: 0px;">
			     
<!-- 			  // 判斷是否已加入收藏 , 可用foreach 還有if 跟GetAllByPrimaryKey比對 -->
			     <input type="submit" value="加入收藏">  
			     <input type="hidden" name="place_id"  value="${placeVO.place_id}">
			     <input type="hidden" name="users_id"  value="${users_id}">
			     <input type="hidden" name="action" value="insert"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>