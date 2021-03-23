<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.card.model.*"%>

<%
  CardVO cardVO = (CardVO) request.getAttribute("cardVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>卡片資料修改 - update_emp_input.jsp</title>

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
		 <h3>卡片資料修改 - update_card_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/card/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="card.do" name="form1">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=cardVO.getUsers_id()%></td>
	</tr>
	<tr>
		<td>卡片編號:</td>
		<td><%=cardVO.getCard_id()%></td>
	</tr>
	<tr>
		<td>卡號:</td>
		<td><input type="TEXT" name="card_number" size="16"	value="<%=cardVO.getCard_number()%>" /></td>
	</tr>
	<tr>
		<td>到期日:</td>
		<td><input name="card_date" size="4"  type="text" value="<%=cardVO.getCard_date()%>" /></td>
	</tr>
	<tr>
		<td>末三碼:</td>
		<td><input type="TEXT" name="card_last" size="3"	value="<%=cardVO.getCard_last()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="card_default" size="1" value="<%=cardVO.getCard_default()%>" /></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="card_id" value="<%=cardVO.getCard_id()%>">
<input type="hidden" name="users_id" value="<%=cardVO.getUsers_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>




</html>