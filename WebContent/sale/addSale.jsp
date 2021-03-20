<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sale.model.*"%>
<%
  SaleVO empVO = (SaleVO) request.getAttribute("empVO");
%>

<%= empVO==null %>--${empVO.sale_name==null}--

<!DOCTYPE html>
<html>
<head>
<title>販售者資料新增 - addSale.jsp</title>

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
<body>
<table id="table-1">
	<tr><td>
		 <h3>新增販售者 - addSale.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/select_page_sale.jsp">
		 <img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="sale.do" name="form1">
<table>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="semail" size="45"

			 value="${(empVO.sale_email==null)? '':empVO.sale_email}" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input name="spwd" size="45" type="text"
		 value="${(empVO.sale_pwd==null)? '':empVO.sale_pwd}" /></td>
	</tr>
	<tr>
		<td>販售者姓名:</td>
		<td><input type="TEXT" name="sname" size="45" 
			 value="${(empVO==null)? '' : empVO.sale_name}" /></td>
	</tr>
	<tr>
		<td>販售者暱稱:</td>
		<td><input type="TEXT" name="snickname" size="45"
			 value="${(empVO==null)? '' : empVO.sale_nickname}" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="sphone" size="45"
			 value="${(empVO==null)? '' : empVO.sale_phone}" /></td>
	</tr>			
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>


</body>
</html>