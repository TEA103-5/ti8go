<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip_detail.model.*"%>

<%Trip_detailVO trip_detailVO = (Trip_detailVO) request.getAttribute("trip_detailVO");%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>行程明細資料修改 - update_trip_detail_input.jsp</title>

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
		 <h3>行程明細資料修改 - update_trip_detail_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/trip_detail/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="trip_detail.do" name="form1">
<table>
	<tr>
		<td>行程明細編號:</td>
		<td><%=trip_detailVO.getTrip_detail_id()%></td>
	</tr>
	
	<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
	<tr>
		<td>行程編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="trip_id">
			<c:forEach var="tripVO" items="${tripSvc.all}">
				<option value="${tripVO.trip_id}" ${(group_activitiesVO.trip_id==tripVO.trip_id)? 'selected':'' } >${tripVO.trip_id}-${tripVO.trip_name}
			</c:forEach>
		</select></td>
	</tr>

	<tr>
		<td>天數:</td>
		<td><input type="TEXT" name="trip_day" size="45"  value="<%=trip_detailVO.getTrip_day()%>" /></td>
	</tr>
	<tr>
		<td>順序:</td>
		<td><input type="TEXT" name="trip_sort" size="45"  value="<%=trip_detailVO.getTrip_sort()%>" /></td>
	</tr>
	<tr>
		<td>行程明細類型:</td>
<%-- 		<td><input type="TEXT" name="trip_detail_type" size="45"  value="<%=trip_detailVO.getTrip_detail_type()%>" /></td> --%>
		<td><select size="1" name="trip_detail_type">
		<option value="${trip_detailVO.trip_detail_type}" selected>${trip_detailVO.trip_detail_type}
		<option value="景點">景點
		<option value="餐廳">餐廳
		<option value="飯店">飯店
		<option value="交通">交通
		<option value="其他">其他
			
		</select></td>
	</tr>
	<tr>
		<td>行程內容:</td>
		<td><input type="TEXT" name="trip_content" size="45"	value="<%=trip_detailVO.getTrip_content()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>地點編號:</td> -->
<%-- 		<td><input type="TEXT" name="place_id" size="45"  value="<%=trip_detailVO.getPlace_id()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>開始時間:</td>
		<td><input name="trip_start_time" id="f_date1" type="text" value="<%=trip_detailVO.getTrip_start_time()%>" /></td>
	</tr>
<%-- 		<td><input type="time" name="trip_start_time" size="45"  value="<%=trip_detailVO.getTrip_start_time()%>" /></td> --%>
	
<%--  	</tr> --%>
	<tr>
		<td>結束時間:</td>
		<td><input name="trip_end_time" id="f_date2" type="text" value="<%=trip_detailVO.getTrip_end_time()%>"></td>
	</tr>
<%-- 		<td><input type="time" name="trip_end_time" size="45"  value="<%=trip_detailVO.getTrip_end_time()%>" /></td> --%>
	
<%--  	</tr> --%>

	<tr>
		<td>備註:</td>
		<td><input type="TEXT" name="trip_remarks" size="45"  value="<%=trip_detailVO.getTrip_remarks()%>" /></td>
	</tr>

	<tr>
		<td>花費:</td>
		<td><input type="TEXT" name="trip_cost" size="45" value="<%=trip_detailVO.getTrip_cost()%>" /></td>
	</tr>
	


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="trip_detail_id" value="<%=trip_detailVO.getTrip_detail_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=group_activitiesVO.getActivities_deadline()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
//         $.datetimepicker.setLocale('zh');
//         $('#f_date2').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=group_activitiesVO.getActivities_start()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         }); $.datetimepicker.setLocale('zh');

        
   
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