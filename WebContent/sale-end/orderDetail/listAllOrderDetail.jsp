<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.orderDetail.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	OrderDetailService ordSvc = new OrderDetailService(); //不需要錯誤驗證,直接呼叫Service
	List<OrderDetailVO> list = ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="deptSvc" scope="page"
	class="com.orderDetail.model.OrderDetailService" />
<!DOCTYPE html>
<html>

<head>
    <title>Blank Page - Brand</title>
 <%@ include file="/sale-end/pages/link.html" %> 
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
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
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
		<th>訂單明細ID</th>
		<th>訂單明細數量</th>
		<th>商品ID</th>
		<th>訂單ID</th>
		<th>明細狀態</th>
		
		<th>修改</th>
		<th>刪除</th>
		</tr>
		<%@ include file="pages/page1.file"%>
		<c:forEach var="orderDetailVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
		<td>${orderDetailVO.order_detail_id}</td>
		<td>${orderDetailVO.order_detail_count}</td>
		<td>${orderDetailVO.product_id}</td>
		<td>${orderDetailVO.order_id}</td>
		<td>${orderDetailVO.order_detail_status}</td>
				<td>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="empno" value="${orderDetailVO.order_detail_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="empno" value="${orderDetailVO.order_detail_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="pages/page2.file"%>
                    
                    
                    
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