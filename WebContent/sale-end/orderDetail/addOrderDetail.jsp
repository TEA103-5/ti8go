<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderDetail.model.*"%>
<!DOCTYPE html>
<%
OrderDetailVO orderDetailVO = (OrderDetailVO) request.getAttribute("OrderDetailVO");
%>
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
              <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" name="form1">
<table>
	<tr>
		<td>明細數量:</td>
		<td><input type="TEXT" name="detail_count" size="45" 
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_detail_count()%>" /></td>
	</tr>
	<tr>
		<td>明細ID:</td>
		<td><input type="TEXT" name="detail_id" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_detail_id()%>" /></td>
	</tr>
	<tr>
		<td>訂單ID:</td>
		<td><input type="TEXT" name="order_id" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_id()%>" /></td>
	</tr>
	<tr>
		<td>商品ID:</td>
		<td><input type="TEXT" name="product_id" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getProduct_id()%>" /></td>
	</tr>
	<tr>
		<td>明細狀態:</td>
		<td><input type="TEXT" name="detail_status" size="45"
			 value="<%= (orderDetailVO==null)? "" : orderDetailVO.getOrder_detail_status()%>" /></td>
	</tr>

	<jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />
	<tr>
		<td>明細:<font color=red><b>*</b></font></td>
		<td><select size="1" name="detail_id">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.order_detail_id}" ${(orderVO.order_id==deptVO.order_id)? 'selected':'' } >${deptVO.order_detail_id}
			</c:forEach>
		</select></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>      
                    
                    
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