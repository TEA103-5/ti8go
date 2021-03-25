<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="com.users.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%TripVO tripVO = (TripVO) request.getAttribute("tripVO");%>
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />


<html>
<head>
<title>行程資料 - listOneTrip.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>行程資料 - listOneTrip.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/trip/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>行程編號</th>
		<th>發佈者</th>
		<th>創建時間</th>
		<th>最新編輯時間</th>
		<th>最後編輯人</th>
		<th>行程狀態</th>
		<th>按讚數</th>
		<th>瀏覽次數</th>
		<th>閱覽權限</th>
		<th>編輯權限</th>
		<th>主要地區</th>
		<th>行程開始日期</th>
		<th>行程結束日期</th>
		<th>行程名稱</th>
		<th>行程描述</th>
		<th>類型</th>
		<th>費用總額</th>
		<th>天氣</th>
<!-- 		<th>複製</th> -->
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
			<td>${tripVO.trip_id}</td>
			<td>${tripVO.users_id}【${usersSvc.getOneusers(tripVO.users_id).users_name}】</td>
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
			     <input type="submit" value="修改">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="trip_id"  value="${tripVO.trip_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>