<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_activities.model.*"%>

<%Group_activitiesVO group_activitiesVO = (Group_activitiesVO) request.getAttribute("group_activitiesVO");%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʸ�ƭק� - update_activities_input.jsp</title>

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
		 <h3>���ʸ�ƭק� - update_activities_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/group_activities/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="group_activities.do" name="form1">
<table>
	<tr>
		<td>���ʽs��:</td>
		<td><%=group_activitiesVO.getActivities_id()%></td>
	</tr>
	<tr>
		<td>�o�G��:</td>
		<td><%=group_activitiesVO.getUsers_id()%></td>
	</tr>
	<tr>
		<td>���W�H��:</td>
		<td><%=group_activitiesVO.getActivities_users_count()%></td>
	</tr>
	<tr>
		<td>�Ыخɶ�:</td>
		<td><%=group_activitiesVO.getActivities_create_time()%></td>
	</tr>
	<tr>
		<td>���ʦW��:</td>
		<td><input type="TEXT" name="activities_name" size="45"  value="<%=group_activitiesVO.getActivities_name()%>" /></td>
	</tr>
	<tr>
		<td>���ʴy�z:</td>
		<td><input type="TEXT" name="activities_description" size="45"	value="<%=group_activitiesVO.getActivities_description()%>" /></td>
	</tr>
	<tr>
		<td>���W�I���:</td>
		<td><input name="activities_deadline" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td>���ʶ}�l���:</td>
		<td><input name="activities_start" id="f_date2" type="text"></td>
	</tr>
	<tr>
		<td>���ʵ������:</td>
		<td><input name="activities_end" id="f_date3" type="text"></td>
	</tr>
	<tr>
		<td>���W�H�ƤW��:</td>
		<td><input type="TEXT" name="activities_max_users" size="45"  value="<%=group_activitiesVO.getActivities_max_users()%>" /></td>
	</tr>
	<tr>
		<td>���ʪ��A:</td>
		<td><input type="TEXT" name="activities_state" size="45" value="<%=group_activitiesVO.getActivities_state()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>���W�H��:</td> -->
<%-- 		<td><input type="TEXT" name="activities_users_count" size="45" value="<%=group_activitiesVO.getActivities_users_count()%>" /></td> --%>
<!-- 	</tr> -->
	

	<jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
	<tr>
		<td>��{�s��:<font color=red><b>*</b></font></td>
		<td><select size="1" name="trip_id">
			<c:forEach var="tripVO" items="${tripSvc.all}">
				<option value="${tripVO.trip_id}" ${(group_activitiesVO.trip_id==tripVO.trip_id)? 'selected':'' } >${tripVO.trip_id}-${tripVO.trip_name}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="activities_id" value="<%=group_activitiesVO.getActivities_id()%>">
<input type="hidden" name="users_id" value="<%=group_activitiesVO.getUsers_id()%>">
<input type="hidden" name="activities_users_count" value="<%=group_activitiesVO.getActivities_users_count()%>">
<input type="hidden" name="activities_create_time" value="<%=group_activitiesVO.getActivities_create_time()%>">
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
 		   value: '<%=group_activitiesVO.getActivities_deadline()%>', // value:   new Date(),
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
 		   value: '<%=group_activitiesVO.getActivities_start()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        }); $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=group_activitiesVO.getActivities_end()%>', // value:   new Date(),
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