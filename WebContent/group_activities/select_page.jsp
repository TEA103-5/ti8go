<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Tivago Group_activities: Home</title>

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
   <tr><td><h3>Tivago Group_activities: Home</h3><h4><a href="<%=request.getContextPath()%>/select_page.jsp">�^����</a></h4></td></tr>
</table>

<p>This is the Home page for Tivago Group_activities: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/group_activities/listAllEmp.jsp'>List</a> all Group_activities.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" >
        <b>��J���ʽs�� (�p1,2,3...):</b>
        <input type="text" name="activities_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="group_activitiesSvc" scope="page" class="com.group_activities.model.Group_activitiesService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" >
       <b>��ܬ��ʽs��:</b>
       <select size="1" name="activities_id">
         <c:forEach var="group_activitiesVO" items="${group_activitiesSvc.all}" > 
          <option value="${group_activitiesVO.activities_id}">${group_activitiesVO.activities_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group_activities/group_activities.do" >
       <b>��ܬ��ʦW��:</b>
       <select size="1" name="activities_id">
         <c:forEach var="group_activitiesVO" items="${group_activitiesSvc.all}" > 
          <option value="${group_activitiesVO.activities_id}">${group_activitiesVO.activities_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���ʺ޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/group_activities/addEmp.jsp'>Add</a> a new Group_activitie.</li>
</ul>

</body>
</html>