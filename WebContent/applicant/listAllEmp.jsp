<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.applicant.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
ApplicantService applicantSvc = new ApplicantService();
    List<ApplicantVO> list = applicantSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="group_activitiesSvc" scope="page" class="com.group_activities.model.Group_activitiesService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
<html>
<head>
<title>�Ҧ����ʳ��W�H��� - listAllApplicant.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����ʳ��W�H��� - listAllApplicant.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/applicant/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���ʽs��</th>
		<th>�ϥΪ̽s��</th>
		<th>���W�ɶ�</th>
<!-- 		<th>�ק�</th> -->
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="applicantVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${applicantVO.activities_id}�i${group_activitiesSvc.getOneEmp(applicantVO.activities_id).activities_name}�j</td>
			<td>${applicantVO.users_id}�i${usersSvc.getOneusers(applicantVO.users_id).users_name}�j</td>
			<td>${applicantVO.registration_time}</td> 
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/applicant/applicant.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="activities_id"  value="${applicantVO.activities_id}"> --%>
<%-- 			     <input type="hidden" name="users_id"  value="${applicantVO.users_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/applicant/applicant.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="activities_id"  value="${applicantVO.activities_id}">
			     <input type="hidden" name="users_id"  value="${applicantVO.users_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>