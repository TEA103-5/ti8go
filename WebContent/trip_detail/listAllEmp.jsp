<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.trip_detail.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Trip_detailService trip_detailSvc = new Trip_detailService();
    List<Trip_detailVO> list = trip_detailSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />

<html>
<head>
<title>所有行程明細資料 - listAllTrip_detail.jsp</title>

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
	width: 1200px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有行程明細資料 - listAllTrip_detail.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/trip_detail/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>行程明細編號</th>
		<th>行程編號</th>
		<th>天數</th>
		<th>順序</th>
		<th>行程明細類型</th>
		<th>行程內容</th>
		<th>地點編號</th>
		<th>開始時間</th>
		<th>結束時間</th>
		<th>備註</th>
		<th>花費</th>
<!-- 		<th>複製</th> -->
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="trip_detailVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${trip_detailVO.trip_detail_id}</td>
			<td>${trip_detailVO.trip_id}【${tripSvc.getOneEmp(trip_detailVO.trip_id).trip_name}】</td>
			<td>${trip_detailVO.trip_day}</td>
			<td>${trip_detailVO.trip_sort}</td>
			<td>${trip_detailVO.trip_detail_type}</td>
			<td>${trip_detailVO.trip_content}</td>
			<td>${trip_detailVO.place_id}</td>
			<td>${trip_detailVO.trip_start_time}</td>
			<td>${trip_detailVO.trip_end_time}</td>
			<td>${trip_detailVO.trip_remarks}</td>
			<td>${trip_detailVO.trip_cost}</td>
			 
<!-- 			<td> -->
<%-- 				${tripSvc.getOneDept(group_activitiesVO.trip_id).trip_name} --%>
<!-- 			</td> -->
<%--			<td><c:forEach var="deptVO" items="${deptSvc.all}"> --%>
 <%--                   <c:if test="${empVO.deptno==deptVO.deptno}"> --%>
<%--	                    ${deptVO.deptno}【${deptVO.dname} - ${deptVO.loc}】 --%>
 <%--                   </c:if> --%>
<%--                </c:forEach> --%>
<%--			</td> --%>

<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" style="margin-bottom: 0px;">  --%>
<!-- 			     <input type="submit" value="複製"> -->
<%-- 			     <input type="hidden" name="activities_id"  value="${group_activitiesVO.activities_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="修改">
			     <input type="hidden" name="trip_detail_id"  value="${trip_detailVO.trip_detail_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="trip_detail_id"  value="${trip_detailVO.trip_detail_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>