<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.users.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%TripVO tripVO = (TripVO) request.getAttribute("tripVO");%>
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />


<html>
<head>
<title>��{��� - listOneTrip.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>��{��� - listOneTrip.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/trip/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>��{�s��</th>
		<th>�o�G��</th>
		<th>�Ыخɶ�</th>
		<th>�̷s�s��ɶ�</th>
		<th>�̫�s��H</th>
		<th>��{���A</th>
		<th>���g��</th>
		<th>�s������</th>
		<th>�\���v��</th>
		<th>�s���v��</th>
		<th>�D�n�a��</th>
		<th>��{�}�l���</th>
		<th>��{�������</th>
		<th>��{�W��</th>
		<th>��{�y�z</th>
		<th>����</th>
		<th>�O���`�B</th>
		<th>�Ѯ�</th>
<!-- 		<th>�ƻs</th> -->
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<tr>
			<td>${tripVO.trip_id}</td>
			<td>${tripVO.users_id}�i${usersSvc.getOneusers(tripVO.users_id).users_name}�j</td>
			<td>${tripVO.trip_create_time}</td>
			<td>${tripVO.last_edit_time}</td>
			<td>${tripVO.last_editor}</td>
			<td>${tripVO.trip_state}</td>
			<td>${tripVO.trip_like}</td>
			<td>${tripVO.trip_look}</td>
			<td>${tripVO.read_authority}</td>
			<td>${tripVO.edit_authority}</td>
			<td>${tripVO.trip_area}</td>
			<td>${tripVO.trip_start}</td>
			<td>${tripVO.trip_end}</td>
			<td>${tripVO.trip_name}</td>
			<td>${tripVO.trip_description}</td>
			<td>${tripVO.trip_type}</td>
			<td>${tripVO.trip_tot_cost}</td>
			<td>${tripVO.place_weather}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>