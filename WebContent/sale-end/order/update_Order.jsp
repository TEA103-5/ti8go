<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.users.model.*"%>

<%
OrderVO orderVO = (OrderVO) request.getAttribute("OrderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>

<head>
    <title>Blank Page - Brand</title>
 <%@ include file="/sale-end/pages/link.html" %> 
</head>

<body id="page-top">
    <div id="wrapper">
 <%@ include file="/sale-end/pages/nav.jsp" %> 
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
  <%@ include file="/sale-end/pages/fnav.html" %>
                <div class="container-fluid">
                    <h3 class="text-dark mb-1">Blank Page</h3>
                    
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

<FORM METHOD="post" ACTION="Order.do" name="form1">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=orderVO.getOrder_id()%></td>
	</tr>
	
	<tr>
		<td>訂單狀態:</td>
		<td><input type="TEXT" name="Order_status" size="45" value="<%=orderVO.getOrder_status()%>" /></td>
	</tr>
	
	
	<tr>
		<td>信用卡號:</td>
		<td><input type="TEXT" name="Card_number" size="45"	value="<%=orderVO.getCard_number()%>" /></td>
	</tr>
	
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="order_address" size="45"	value="<%=orderVO.getOrder_address()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>使用者編號:</td> -->
<%-- 		<td><input type="TEXT" name="Users_id" size="45" value="<%=orderVO.getUsers_id()%>" /></td> --%>
<!-- 	</tr> -->

	<jsp:useBean id="depSvc" scope="page" class="com.users.model.UsersService" />
	
	<tr>
		<td>使用者:<font color=red><b>*</b></font></td>
		<td><select size="1" name="users_id">
			<c:forEach var="dptVO" items="${depSvc.all}">
				<option value="${dptVO.users_id}"${(orderVO.users_id==deptVO.users_id)? 'selected':'' }>${dptVO.users_name}
			</c:forEach>
		</select></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=orderVO.getOrder_id()%>">
<input type="hidden" name="URL"value="<%=request.getParameter("URL")%>">
<input type="submit" value="送出修改"></FORM>       
                    
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © Brand 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
 <%@ include file="/sale-end/pages/script.html" %> 
</body>

</html>