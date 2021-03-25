<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.team.model.*"%>

<%TeamVO teamVO = (TeamVO) request.getAttribute("teamVO");%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程參與人資料新增 - addTeam.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width: 500px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table#table-2 {
    width: 200px;
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
		 <h3>行程參與人資料新增 - addTeam.jsp</h3></td>
		 <td>
		 <h4><a href="<%=request.getContextPath()%>/team/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td>
	</tr>
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

<FORM METHOD="post" ACTION="team.do" name="form1">
<table id="#table-2">

	<jsp:useBean id="TripSvc" scope="page" class="com.trip.model.TripService" />
	<tr>
		<td>行程編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="trip_id">
			<c:forEach var="tripVO" items="${TripSvc.all}">
				<option value="${tripVO.trip_id}" ${(teamVO.trip_id==tripVO.trip_id)? 'selected':'' } >${tripVO.trip_id}-${tripVO.trip_name}
			</c:forEach>
		</select></td>
	</tr>
	
	<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
	<tr>
		<td>使用者編號:</td>
		<td><select size="1" name="users_id">
			<c:forEach var="usersVO" items="${usersSvc.all}">
				<option value="${usersVO.users_id}" ${(teamVO.users_id==usersVO.users_id)? 'selected':'' } >${usersVO.users_id}-${usersVO.users_name}
			</c:forEach>
		</select></td>
	</tr>
	
<%-- 	<jsp:useBean id="group_activitiesSvc" scope="page" class="com.group_activities.model.Group_activitiesService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>活動編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="activities_id"> -->
<%-- 			<c:forEach var="group_activitiesVO" items="${group_activitiesSvc.all}"> --%>
<%-- 				<option value="${group_activitiesVO.activities_id}" ${(applicantVO.activities_id==group_activitiesVO.activities_id)? 'selected':'' } >${group_activitiesVO.activities_id}-${group_activitiesVO.activities_name} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



</html>