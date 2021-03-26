<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.users.model.*"%>

<%
  UsersVO usersVO = (UsersVO) request.getAttribute("usersVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_users_input.jsp</title>

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
		 <h3>會員資料修改 - update_users_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/users/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="users.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>使用者姓名:</td>
		<td><input type="TEXT" name="users_name" size="15" 
			 value="<%= (usersVO==null)? "會員假" : usersVO.getUsers_id()%>" /></td>
	</tr>
	<tr>
		<td>暱稱:</td>
		<td><input type="TEXT" name="users_nickname" size="20"
			 value="<%= (usersVO==null)? "假甲" : usersVO.getUsers_nickname()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="users_sex" size="2"
			 value="<%= (usersVO==null)? "1" : usersVO.getUsers_sex()%>" /></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input name="users_mail" type="text" size="20"
			 value="<%= (usersVO==null)? "aa@us.com.us" : usersVO.getUsers_mail()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="password" name="users_pwd" size="20"
			 value="<%= (usersVO==null)? "444" : usersVO.getUsers_pwd()%>" /></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input type="TEXT" name="users_birthday" size="10"
			 value="<%= (usersVO==null)? "20200101" : usersVO.getUsers_birthday()%>" /></td>
	</tr>
	<tr>
		<td>身分證號碼:</td>
		<td><input type="TEXT" name="users_id_number" size="10"
			 value="<%= (usersVO==null)? "A123456789" : usersVO.getUsers_id_number()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="users_phone" size="15"
			 value="<%= (usersVO==null)? "8825252" : usersVO.getUsers_phone()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><input type="TEXT" name="users_sex" size="2"
			 value="<%= (usersVO==null)? "1" : usersVO.getUsers_sex()%>" /></td>
	</tr>
	<tr>
		<td>頭像:</td>
		<td><input type="file" name="users_users_pic"></td>
	</tr>
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="users_id" value="<%=usersVO.getUsers_id()%>">
<input type="hidden" name="users_status" 
	 value="<%= (usersVO==null)? "1" : usersVO.getUsers_status()%>" />
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>