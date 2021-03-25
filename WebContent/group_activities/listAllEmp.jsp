<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_activities.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Group_activitiesService group_activitiesSvc = new Group_activitiesService();
    List<Group_activitiesVO> list = group_activitiesSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
<html>
<head>
<title>�Ҧ����ʸ�� - listAllGroup_activities.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����ʸ�� - listAllGroup_activities.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/group_activities/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
		<th>��{�s��</th>
		<th>�o�G��</th>
		<th>�Ыخɶ�</th>
		<th>���ʪ��A</th>
		<th>���W�I���</th>
		<th>���W�H�ƤW��</th>
		<th>���W�H��</th>
		<th>���ʶ}�l���</th>
		<th>���ʵ������</th>
		<th>���ʦW��</th>
		<th>���ʴy�z</th>
<!-- 		<th>�ƻs</th> -->
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="group_activitiesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${group_activitiesVO.activities_id}</td>
			<td>${group_activitiesVO.trip_id}�i${tripSvc.getOneEmp(group_activitiesVO.trip_id).trip_name}�j</td>
			<td>${group_activitiesVO.users_id}�i${usersSvc.getOneusers(group_activitiesVO.users_id).users_name}�j</td>
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
<%--	                    ${deptVO.deptno}�i${deptVO.dname} - ${deptVO.loc}�j --%>
 <%--                   </c:if> --%>
<%--                </c:forEach> --%>
<%--			</td> --%>

<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" style="margin-bottom: 0px;">  --%>
<!-- 			     <input type="submit" value="�ƻs"> -->
<%-- 			     <input type="hidden" name="activities_id"  value="${group_activitiesVO.activities_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="activities_id"  value="${group_activitiesVO.activities_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="activities_id"  value="${group_activitiesVO.activities_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>