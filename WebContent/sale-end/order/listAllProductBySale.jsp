<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.sale.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="deptSvc" scope="page"
	class="com.order.model.OrderService" />
 
<!DOCTYPE html>
<html>

<head>
    <title>所有商品資料 - listAllOrser.jsp</title>
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
 <%@ include file="/sale-end/pages/nav.html" %> 
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
			<th>訂單編號</th>
			<th>商品名稱</th>
			<th>商品金額</th>
			<th>訂單時間</th>
			<th>訂單數量</th>
			<th>確認</th>
			
		
		</tr>
		<c:forEach var="orderVO" items="${listProBySale_id}">
			<tr>
				<td>${orderVO.order_id}</td>
				<td>${orderVO.order_date}</td>
				<td>${orderVO.order_status}</td>
				<td>${orderVO.card_number}</td>
				<td>${orderVO.users_id}</td>
				<td>
					     <FORM id="from2" METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" > 							       
				<button >check</button>
									       <input type="hidden" name="detail_id" value="${orderVO.detail_id}">
									       <input type="hidden" name="sale_id" value="${saleVO.sale_id}">
									       <input type="hidden" name="action" value="updateS">
									     </FORM>
				
				</td>
			</tr>
		</c:forEach>
	</table>

                    
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