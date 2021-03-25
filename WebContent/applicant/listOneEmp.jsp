<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.applicant.model.*"%>
<%@ page import="com.group_activities.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%ApplicantVO applicantVO = (ApplicantVO) request.getAttribute("applicantVO");%>

<%-- 取出 對應的DeptVO物件--%>

<jsp:useBean id="group_activitiesSvc" scope="page" class="com.group_activities.model.Group_activitiesService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />

<html>
<head>
<title>活動報名人資料 - listOneApplicant.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>活動報名人資料 - listOneApplicant.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/applicant/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動編號</th>
		<th>使用者編號</th>
		<th>報名時間</th>
<!-- 		<th>修改</th> -->
		<th>刪除</th>
	</tr>
	<tr>
	<td>${applicantVO.activities_id}【${group_activitiesSvc.getOneEmp(applicantVO.activities_id).activities_name}】</td>
			<td>${applicantVO.users_id}【${usersSvc.getOneusers(applicantVO.users_id).users_name}】</td>
			<td>${applicantVO.registration_time}</td> 
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/applicant/applicant.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="activities_id"  value="${applicantVO.activities_id}"> --%>
<%-- 			     <input type="hidden" name="users_id"  value="${applicantVO.users_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/applicant/applicant.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="activities_id"  value="${applicantVO.activities_id}">
			     <input type="hidden" name="users_id"  value="${applicantVO.users_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
</table>

</body>
</html>