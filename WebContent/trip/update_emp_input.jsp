<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.trip.model.*"%>

<%TripVO tripVO = (TripVO) request.getAttribute("tripVO");%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>��{��ƭק� - update_trip_input.jsp</title>

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
		 <h3>��{��ƭק� - update_trip_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/trip/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
		<td>�o��{�s��:</td>
		<td><%=tripVO.getTrip_id()%></td>
	</tr>
	<tr>
		<td>�o�G��:</td>
		<td>${tripVO.users_id}-${usersSvc.getOneusers(tripVO.trip_id).users_name}</td>
	</tr>
	<tr>
		<td>�Ыخɶ�:</td>
		<td>${tripVO.trip_create_time}</td>
	</tr>
	<tr>
		<td>�̷s�s��ɶ�:</td>
		<td>${tripVO.last_edit_time}</td>
	</tr>
	<tr>
		<td>���g��:</td>
		<td>${tripVO.trip_like}</td>
	</tr>
	<tr>
		<td>�s������:</td>
		<td>${tripVO.trip_look}</td>
	</tr>

	<tr>
		<td>�̫�s��H:<font color=red><b>*</b></font></td>
		<td><select size="1" name="last_editor">
			<c:forEach var="usersVO" items="${usersSvc.all}">
				<option value="${tripVO.last_editor}" ${(tripVO.last_editor==usersVO.users_id)? 'selected':'' } >${usersVO.users_id}-${usersVO.users_name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>��{���A:</td>
		<td><input type="TEXT" name="trip_state" size="45"
			 value="<%= tripVO.getTrip_state()%>" /></td>
	</tr>
	<tr>
		<td>�\���v��:</td>
		<td><input type="TEXT" name="read_authority" size="45"
			 value="<%= tripVO.getRead_authority()%>" /></td>
	</tr>
	<tr>
		<td>�s���v��:</td>
		<td><input type="TEXT" name="edit_authority" size="45"
			 value="<%= tripVO.getEdit_authority()%>" /></td>
	</tr>
	<tr>
		<td>�D�n�a��:</td>
		<td><input type="TEXT" name="trip_area" size="45"
			 value="<%= tripVO.getTrip_area()%>" /></td>
	</tr>
	
	<tr>
		<td>��{�}�l���:</td>
		<td><input name="trip_start" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>��{�������:</td>
		<td><input name="trip_end" id="f_date2" type="text"></td>
	</tr>
	
	<tr>
		<td>��{�W��:</td>
		<td><input type="TEXT" name="trip_name" size="45"
			 value="<%= tripVO.getTrip_name()%>" /></td>
	</tr>
	<tr>
		<td>��{�y�z:</td>
		<td><input type="TEXT" name="trip_description" size="45"
			 value="<%= tripVO.getTrip_description()%>" /></td>
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="trip_type" size="45"
			 value="<%= tripVO.getTrip_type()%>" /></td>
	</tr>
	<tr>
		<td>�O���`�B:</td>
		<td><input type="TEXT" name="trip_tot_cost" size="45"
			 value="<%= tripVO.getTrip_tot_cost()%>" /></td>
	</tr>
	<tr>
		<td>�Ѯ�:</td>
		<td><input type="TEXT" name="place_weather" size="45"
			 value="<%= (tripVO==null)? "����" : tripVO.getPlace_weather()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="trip_id" value="${tripVO.trip_id}">
<input type="hidden" name="users_id" value="${tripVO.users_id}">
<input type="hidden" name="trip_create_time" value="${tripVO.trip_create_time}">
<input type="hidden" name="last_edit_time" value="${tripVO.last_edit_time}">
<input type="hidden" name="trip_like" value="${tripVO.trip_like}">
<input type="hidden" name="trip_look" value="${tripVO.trip_look}">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
   step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '${tripVO.trip_start}', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	            '2017/07/10',  // �_�l��
   //minDate:               '-1970-01-01', // �h������(���t)���e
   //maxDate:               '+1970-01-01'  // �h������(���t)����
});
$.datetimepicker.setLocale('zh');
$('#f_date2').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '${tripVO.trip_end}', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
   //startDate:	            '2017/07/10',  // �_�l��
   //minDate:               '-1970-01-01', // �h������(���t)���e
   //maxDate:               '+1970-01-01'  // �h������(���t)����
});

        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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