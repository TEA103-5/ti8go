<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Tivago: Home</title>

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
   <tr><td><h3>Tivago: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Tivago: Home</p>

<h3>¡i0201¡j¸ê®Æ¬d¸ß:</h3>
	


<ul>
  <li><a href="<%=request.getContextPath()%>/trip/select_page.jsp">Trip</a></li>
  <li><a href='<%=request.getContextPath()%>/trip_detail/select_page.jsp'>Trip_detail</a></li>
  <li><a href='<%=request.getContextPath()%>/team/select_page.jsp'>Team</a></li>
  <li><a href='<%=request.getContextPath()%>/group_activities/select_page.jsp'>Group_activities</a></li>
  <li><a href='<%=request.getContextPath()%>/applicant/select_page.jsp'>Applicant</a></li>
  
  

   

</ul>



</body>
</html>