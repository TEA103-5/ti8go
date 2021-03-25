<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Tivago Team: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Tivago Team: Home</h3><h4><a href="<%=request.getContextPath()%>/select_page.jsp">�^����</a></h4></td></tr>
</table>

<p>This is the Home page for Tivago Team: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/team/listAllEmp.jsp'>List</a> all Teams.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/team/team.do" >
        <b>��J��{�s���P�ϥΪ̽s�� (�p1,2,3...):</b>
        <input type="text" name="trip_id" size="5">
        <input type="text" name="users_id" size="5">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="teamSvc" scope="page" class="com.team.model.TeamService" />
   <jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
   <jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/team/team.do" >
       <b>��ܦ�{�s���s��:</b>
       <select size="1" name="trip_id">
         <c:forEach var="teamVO" items="${teamSvc.all}" > 
          <option value="${teamVO.trip_id}">${teamVO.trip_id}
         </c:forEach>   
       </select><br>
       <b>��ܨϥΪ̽s���s��:</b>
       <select size="1" name="users_id">
         <c:forEach var="teamVO" items="${teamSvc.all}" > 
          <option value="${teamVO.users_id}">${teamVO.users_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/team/team.do" >
       <b>��ܦ�{�s��:</b>
       <select size="1" name="trip_id">
         <c:forEach var="teamVO" items="${teamSvc.all}" > 
          <option value="${teamVO.trip_id}">${tripSvc.getOneEmp(teamVO.trip_id).trip_name}
         </c:forEach>   
       </select><br>
       <b>��ܨϥΪ̽s��:</b>
       <select size="1" name="users_id">
         <c:forEach var="teamVO" items="${teamSvc.all}" > 
          <option value="${teamVO.users_id}">${usersSvc.getOneusers(teamVO.users_id).users_name}

         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/applicant/applicant.do" > --%>
<!--        <b>��ܬ��ʽs���s���P�ϥΪ̽s��:</b> -->
<!--        <select size="1" name="activities_id"> -->
<%--          <c:forEach var="applicantVO" items="${applicantSvc.all}" >  --%>
<%--           <option value="${applicantVO.activities_id}}">${applicantVO.activities_id},${applicantVO.users_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<%--        <input type="hidden" name="users_id" value="${applicantVO.users_id}"> --%>
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
  
</ul>


<h3>��{�ѻP�H�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/team/addEmp.jsp'>Add</a> a new Applicant.</li>
</ul>

</body>
</html>