<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admins.model.*"%>

<%
  AdminsVO adminsVO = (AdminsVO) request.getAttribute("adminsVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增管理者 - addAdmins.jsp</title>

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
	width: 450px;
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
		 <h3>管理者資料新增 - addAdmins.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/admins/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="admins.do" name="form1" >
<table>
	<tr>
		<td>管理者姓名:</td>
		<td><input type="TEXT" name="admins_name" size="15" 
			 value="<%= (adminsVO==null)? "會員假" : adminsVO.getAdmins_name()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="admins_sex" size="2"
			 value="<%= (adminsVO==null)? "1" : adminsVO.getAdmins_sex()%>" /></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input name="admins_email" type="text" size="20"
			 value="<%= (adminsVO==null)? "aa@us.com.us" : adminsVO.getAdmins_email()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="password" name="admins_password" size="20"
			 value="<%= (adminsVO==null)? "444" : adminsVO.getAdmins_password()%>" /></td>
	</tr>
	<tr>
		<td>權限:</td>
		<td><input type="TEXT" name="admins_authority" size="20"
			 value="<%= (adminsVO==null)? "root" : adminsVO.getAdmins_authority()%>" /></td>
	</tr>
	<tr>
		<td>職位:</td>
		<td><input type="TEXT" name="admins_position" size="10"
			 value="<%= (adminsVO==null)? "派遣碼農" : adminsVO.getAdmins_position()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>