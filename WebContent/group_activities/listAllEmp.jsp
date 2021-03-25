<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_activities.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Group_activitiesService group_activitiesSvc = new Group_activitiesService();
    List<Group_activitiesVO> list = group_activitiesSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
<html>
<head>
<title>所有活動資料 - listAllGroup_activities.jsp</title>

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
		 <h3>所有活動資料 - listAllGroup_activities.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/group_activities/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>活動編號</th>
		<th>行程編號</th>
		<th>發佈者</th>
		<th>創建時間</th>
		<th>活動狀態</th>
		<th>報名截止日</th>
		<th>報名人數上限</th>
		<th>報名人數</th>
		<th>活動開始日期</th>
		<th>活動結束日期</th>
		<th>活動名稱</th>
		<th>活動描述</th>
<!-- 		<th>複製</th> -->
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="group_activitiesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${group_activitiesVO.activities_id}</td>
			<td>${group_activitiesVO.trip_id}【${tripSvc.getOneEmp(group_activitiesVO.trip_id).trip_name}】</td>
			<td>${group_activitiesVO.users_id}【${usersSvc.getOneusers(group_activitiesVO.users_id).users_name}】</td>
			<td>${group_activitiesVO.activities_create_time}</td>
			<td>${group_activitiesVO.activities_state}</td>
			<td>${group_activitiesVO.activities_deadline}</td>
			<td>${group_activitiesVO.activities_max_users}</td>
			<td>${group_activitiesVO.activities_users_count}</td>
			<td>${group_activitiesVO.activities_start}</td>
			<td>${group_activitiesVO.activities_end}</td>
			<td>${group_activitiesVO.activities_name}</td>
			<td>${group_activitiesVO.activities_description}</td>
			 
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
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="修改">
			     <input type="hidden" name="activities_id"  value="${group_activitiesVO.activities_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="activities_id"  value="${group_activitiesVO.activities_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>