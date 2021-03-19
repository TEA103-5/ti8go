<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.place.model.*" %>

<% 
	PlaceVO placeVO = (PlaceVO)request.getAttribute("placeVO");
%>

<html>
<head>
<meta charset="UTF-8">
<title>地點資料新增 - addPlace.jsp</title>

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
		 <h3>地點資料新增 - addPlace.jsp</h3></td><td>
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

<FORM METHOD="post" ACTION="place.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>地點名稱:</td>
		<td><input type="TEXT" name="place_name" size="45" 
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_name()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="place_address" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_address()%>" /></td>
	</tr>
	<tr>
		<td>經度:</td>
		<td><input type="TEXT" name="place_longitude" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_longitude()%>" /></td>
	</tr>
	<tr>
		<td>緯度:</td>
		<td><input type="TEXT" name="place_latitude" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_latitude()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="place_tel" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_tel()%>" /></td>
	</tr>
	<tr>
		<td>縣市:</td>
		<td><input type="TEXT" name="place_region" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_region()%>" /></td>
	</tr>
	<tr>
		<td>地點類型:</td>
		<td><input type="TEXT" name="place_type" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_type()%>" /></td>
	</tr>
	<tr>
		<td>地點簡介:</td>
		<td><input type="TEXT" name="place_index" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_index()%>" /></td>
	</tr>
	<tr>
		<td>圖片1:</td>
		<td><input type="file" name="place_pic1" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_pic1()%>" /></td>
	</tr>
	<tr>
		<td>圖片2:</td>
		<td><input type="file" name="place_pic2" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_pic2()%>" /></td>
	</tr>
	<tr>
		<td>圖片3:</td>
		<td><input type="file" name="place_pic3" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_pic3()%>" /></td>
	</tr>
	<tr>
		<td>地點狀態:</td>
		<td><input type="TEXT" name="place_state" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_state()%>" /></td>
	</tr>
	<tr>
		<td>建立者:</td>
		<td><input type="TEXT" name="users_id" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getUsers_id()%>" /></td>
	</tr>
	<tr>
		<td>營業時間:</td>
		<td><input type="TEXT" name="business_time" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getBusiness_time()%>" /></td>
	</tr>

<!-- 	未更改,這邊之後應該是用來放建立者的位置 -->
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
</html>