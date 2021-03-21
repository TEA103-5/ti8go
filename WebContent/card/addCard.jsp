<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.card.model.*"%>

<%
  CardVO cardVO = (CardVO) request.getAttribute("cardVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>卡片新增 - addCard.jsp</title>

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
		 <h3>卡片資料新增 - addCard.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/card/select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="card.do" name="form1">
<table>
	<tr>
		<td>使用者編號:</td>
		<td><input type="TEXT" name="users_id" size="5" 
			 value="<%= (cardVO==null)? "1" : cardVO.getUsers_id()%>" /></td>
	</tr>
	<tr>
		<td>信用卡號:</td>
		<td><input type="TEXT" name="card_number" size="16"
			 value="<%= (cardVO==null)? "1234567824681357" : cardVO.getCard_number()%>" /></td>
	</tr>
	<tr>
		<td>到期日:</td>
		<td><input name="card_date" id="f_date1" type="text" size="5"
			 value="<%= (cardVO==null)? "0112" : cardVO.getCard_date()%>" /></td>
	</tr>
	<tr>
		<td>末三碼:</td>
		<td><input type="TEXT" name="card_last" size="3"
			 value="<%= (cardVO==null)? "444" : cardVO.getCard_last()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="card_default" size="1"
			 value="<%= (cardVO==null)? "1" : cardVO.getCard_default()%>" /></td>
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
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


</html>