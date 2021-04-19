<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.users.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<%
	OrderVO orderVO = (OrderVO) request.getAttribute("OrderVO");
	// ProductService proSvc = new ProductService(); //不需要錯誤驗證,直接呼叫Service
	// List<ProductVO> list = proSvc.getAll();
	// pageContext.setAttribute("list", list);
%>
<html>

<head>
<title>Blank Page - Brand</title>
<%@ include file="/sale-end/pages/link.html"%>
</head>

<body id="page-top">
	<div id="wrapper">
		<%@ include file="/sale-end/pages/nav.jsp"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="/sale-end/pages/fnav.html"%>
				<div class="container-fluid">
					<h3 class="text-dark mb-1">新增訂單</h3>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" name="form1">
						<table>
							<tr>
								<td>狀態:</td>
								<td><input type="TEXT" name="order_status" size="45"
									value="<%=(orderVO == null) ? "" : orderVO.getOrder_status()%>" /></td>
							</tr>
							<tr>
								<td>信用卡號:</td>
								<td><input type="TEXT" name="card_number" size="45"
									value="<%=(orderVO == null) ? "" : orderVO.getCard_number()%>" /></td>
							</tr>
							
							<tr>
								<td>地址:</td>
								<td><input type="TEXT" name="order_address" size="45"
									value="<%=(orderVO == null) ? "" : orderVO.getOrder_address()%>" /></td>
							</tr>
							<!-- 	<tr> -->
							<!-- 		<td>使用者ID:</td> -->
							<!-- 		<td><input type="TEXT" name="users_id" size="45" -->
							<%-- 			 value="<%= (orderVO==null)? "" : orderVO.getUsers_id()%>" /></td> --%>
							<!-- 	</tr> -->

							<jsp:useBean id="dptSvc" scope="page"
								class="com.users.model.UsersService" />
							<%-- 	<c:forEach var="ProductVO" items="${list}"> --%>
							<tr>
								<td>使用者ID:<font color=red><b>*</b></font></td>
								<td><select size="1" name="users_id">
										<c:forEach var="dptVO" items="${dptSvc.all}">
											<option value="${dptVO.users_id}"
												${(orderVO.users_id==deptVO.users_id)? 'selected':'' }>${dptVO.users_name}
										</c:forEach>
								</select></td>
							</tr>
							<%-- 	</c:forEach> --%>


						</table>
						<br> <input type="hidden" name="action" value="insert">
						<input type="submit" value="送出新增">
					</FORM>

				</div>
			</div>
			<footer class="bg-white sticky-footer">
				<div class="container my-auto">
					<div class="text-center my-auto copyright">
						<span>Copyright © Brand 2021</span>
					</div>
				</div>
			</footer>
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
			class="fas fa-angle-up"></i></a>
	</div>
	<%@ include file="/sale-end/pages/script.html"%>
</body>

</html>