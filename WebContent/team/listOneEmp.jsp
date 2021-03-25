<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.team.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%TeamVO teamVO = (TeamVO) request.getAttribute("TeamVO");%>

<%-- ���X ������DeptVO����--%>

<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />

<html>
<head>
<title>��{�ѻP�H��� - listOneTeam.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>��{�ѻP�H��� - listOneTeam.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/team/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>��{�s��</th>
		<th>�ϥΪ̽s��</th>
<!-- 		<th>�ק�</th> -->
		<th>�R��</th>
	</tr>
	<tr>
			<td>${teamVO.trip_id}�i${tripSvc.getOneEmp(teamVO.trip_id).trip_name}�j</td>
			<td>${teamVO.users_id}�i${usersSvc.getOneusers(teamVO.users_id).users_name}�j</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/applicant/applicant.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�ק�"> -->
<%-- 			     <input type="hidden" name="activities_id"  value="${applicantVO.activities_id}"> --%>
<%-- 			     <input type="hidden" name="users_id"  value="${applicantVO.users_id}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/team/team.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="trip_id"  value="${teamVO.trip_id}">
			     <input type="hidden" name="users_id"  value="${teamVO.users_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
</table>

</body>
</html>