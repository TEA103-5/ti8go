<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip_detail.model.*"%>
<%@ page import="com.trip.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%Trip_detailVO trip_detailVO = (Trip_detailVO) request.getAttribute("trip_detailVO");%>
<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />


<html>
<head>
<title>��{���Ӹ�� - listOneTrip_detail.jsp</title>

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
		 <h3>��{���Ӹ�� - listOneTrip_detail.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/trip_detail/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>��{���ӽs��</th>
		<th>��{�s��</th>
		<th>�Ѽ�</th>
		<th>����</th>
		<th>��{��������</th>
		<th>��{���e</th>
		<th>�a�I�s��</th>
		<th>�}�l�ɶ�</th>
		<th>�����ɶ�</th>
		<th>�Ƶ�</th>
		<th>��O</th>
<!-- 		<th>�ƻs</th> -->
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<tr>
		<td>${trip_detailVO.trip_detail_id}</td>
			<td>${trip_detailVO.trip_id}�i${tripSvc.getOneEmp(trip_detailVO.trip_id).trip_name}�j</td>
			<td>${trip_detailVO.trip_day}</td>
			<td>${trip_detailVO.trip_sort}</td>
			<td>${trip_detailVO.trip_detail_type}</td>
			<td>${trip_detailVO.trip_content}</td>
			<td>${trip_detailVO.place_id}</td>
			<td>${trip_detailVO.trip_start_time}</td>
			<td>${trip_detailVO.trip_end_time}</td>
			<td>${trip_detailVO.trip_remarks}</td>
			<td>${trip_detailVO.trip_cost}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" style="margin-bottom: 0px;"> 
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="trip_detail_id"  value="${trip_detailVO.trip_detail_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="trip_detail_id"  value="${trip_detailVO.trip_detail_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>