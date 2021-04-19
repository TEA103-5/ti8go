<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listOrder_ByCompositeQuery" scope="request" type="java.util.List<OrderVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />

<!DOCTYPE html>
<html>

<head>
    <title>listOrder_ByCompositeQuery.jsp</title>
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
                    <table>
	<tr>
		    <th>訂單編號</th>
			<th>訂單成立時間</th>
			<th>訂單狀態</th>
			<th>信用卡號</th>
			<th>購買者編號</th>
			<th>地址</th>
			
			<th>修改</th>
			<th>刪除</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="empVO" items="${listOrder_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(empVO.order_id==param.order_id) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${empVO.order_id}</td>
			<td>${empVO.order_date}</td>
			<td>${empVO.order_status}</td>
			<td>${empVO.card_number}</td>
			<td>${empVO.users_id}</td>
			<td>${empVO.order_address}</td>		
				
			<td><c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.order_id==deptVO.order_id}">
	                    ${deptVO.order_id}【${deptVO.order_detail_count} - ${deptVO.order_detail_status} - ${deptVO.product_id}- ${deptVO.order_detail_id}】
                    </c:if>
                </c:forEach>
			</td>
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="empno"      value="${empVO.order_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="empno"      value="${empVO.order_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>
                    
                    
                    
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