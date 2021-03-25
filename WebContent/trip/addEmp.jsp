<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>

<%TripVO tripVO = (TripVO) request.getAttribute("tripVO");%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程資料新增 - addTrip.jsp</title>

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
	width: 500px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
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
		 <h3>行程資料新增 - addTrip.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/trip/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
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

<FORM METHOD="post" ACTION="trip.do" name="form1">
<table>
	<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
	<tr>
		<td>發佈者:<font color=red><b>*</b></font></td>
		<td><select size="1" name="users_id">
			<c:forEach var="usersVO" items="${usersSvc.all}">
				<option value="${usersVO.users_id}" ${(tripVO.users_id==usersVO.users_id)? 'selected':'' } >${usersVO.users_id}-${usersVO.users_name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>最後編輯人:<font color=red><b>*</b></font></td>
		<td><select size="1" name="last_editor">
			<c:forEach var="usersVO" items="${usersSvc.all}">
				<option value="${usersVO.users_id}" ${(tripVO.last_editor==usersVO.users_id)? 'selected':'' } >${usersVO.users_id}-${usersVO.users_name}
			</c:forEach>
		</select></td>
	</tr>
	
	<tr>
		<td>行程狀態:</td>
		<td><input type="TEXT" name="trip_state" size="45"
			 value="<%= (tripVO==null)? "1" : tripVO.getTrip_state()%>" /></td>
	</tr>
	<tr>
		<td>閱覽權限:</td>
		<td><input type="TEXT" name="read_authority" size="45"
			 value="<%= (tripVO==null)? "1" : tripVO.getRead_authority()%>" /></td>
	</tr>
	<tr>
		<td>編輯權限:</td>
		<td><input type="TEXT" name="edit_authority" size="45"
			 value="<%= (tripVO==null)? "1" : tripVO.getEdit_authority()%>" /></td>
	</tr>
	<tr>
		<td>主要地區:</td>
		<td><input type="TEXT" name="trip_area" size="45"
			 value="<%= (tripVO==null)? "台灣" : tripVO.getTrip_area()%>" /></td>
	</tr>
	
	<tr>
		<td>行程開始日期:</td>
		<td><input name="trip_start" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>行程結束日期:</td>
		<td><input name="trip_end" id="f_date2" type="text"></td>
	</tr>
	
	<tr>
		<td>行程名稱:</td>
		<td><input type="TEXT" name="trip_name" size="45"
			 value="<%= (tripVO==null)? "Tivago" : tripVO.getTrip_name()%>" /></td>
	</tr>
	<tr>
		<td>行程描述:</td>
		<td><input type="TEXT" name="trip_description" size="45"
			 value="<%= (tripVO==null)? "描述.." : tripVO.getTrip_description()%>" /></td>
	</tr>
	<tr>
		<td>類型:</td>
		<td><input type="TEXT" name="trip_type" size="45"
			 value="<%= (tripVO==null)? "家庭旅遊" : tripVO.getTrip_type()%>" /></td>
	</tr>
	<tr>
		<td>費用總額:</td>
		<td><input type="TEXT" name="trip_tot_cost" size="45"
			 value="<%= (tripVO==null)? "0" : tripVO.getTrip_tot_cost()%>" /></td>
	</tr>
	<tr>
		<td>天氣:</td>
		<td><input type="TEXT" name="place_weather" size="45"
			 value="<%= (tripVO==null)? "晴天" : tripVO.getPlace_weather()%>" /></td>
	</tr>

	


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date start = null;
  try {
	  start = tripVO.getTrip_start();
   } catch (Exception e) {
	   start = new java.sql.Date(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Date end = null;
  try {
	  end = tripVO.getTrip_end();
   } catch (Exception e) {
	   end = new java.sql.Date(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=start%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=end%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>