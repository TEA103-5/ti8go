<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sale.model.*"%>
<%
  SaleVO empVO = (SaleVO) request.getAttribute("empVO");
%>
<!DOCTYPE html>
<html>
<head>
<title>販售者資料修改 - update_emp_input.jsp</title>

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
    .img{
		width: 50px;
		height: 50px;
			}
</style>

</head>
<body>
<table id="table-1">
	<tr><td>
		 <h3>販售者資料修改 - update_sale.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/select_page_sale.jsp">
		 <img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<img class="img" src="<%=request.getContextPath()%>/DBGifReader4_1?id=<%=empVO.getSale_id()%>"/>
  <FORM action="uploadServlet3_simple.do" method=post enctype="multipart/form-data">
        <input type="file" name="upfile1">
        <input type="hidden" name="empno" value="<%=empVO.getSale_id()%>">
        <input type="submit" value="上傳">
  </FORM>

<FORM METHOD="post" ACTION="sale.do" name="form1" >
<table>
	<tr>
		<td>帳號:</td>
		<td><%=empVO.getSale_email()%></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input name="spwd" size="45" type="text" value="<%=empVO.getSale_pwd()%>"></td>
	</tr>
	<tr>
		<td>販售者姓名:</td>
		<td><input type="TEXT" name="sname" size="45" 
			 value="<%= empVO.getSale_name()%>" /></td>
	</tr>
	<tr>
		<td>販售者暱稱:</td>
		<td><input type="TEXT" name="snickname" size="45"
			 value="<%= empVO.getSale_nickname()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="sphone" size="45"
			 value="<%= empVO.getSale_phone()%>" /></td>
	</tr>
	<tr>
		<td>販售者帳號狀態:</td>
		<td><input type="TEXT" name="sstatus" size="45"
			 value="<%= empVO.getSale_status()%>" /></td>
	</tr>
	<tr>
		<td>販售者資格照片審核狀態:</td>
		<td><input type="TEXT" name="sastatus" size="45"
			 value="<%= empVO.getSale_audit_status()%>" /></td>
	</tr>
	<tr>
		<td>販售者評分:</td>
		<td><input type="TEXT" name="srate" size="45"
			 value="<%= empVO.getSale_rate()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=empVO.getSale_id()%>">
<input type="submit" value="送出修改"></FORM>


</body>
</html>