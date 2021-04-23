<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.users.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<jsp:useBean id="deptSvc" scope="page"
	class="com.order.model.OrderService" />
 
<!DOCTYPE html>
<html>

<head>
    <title>所有訂單資料</title>
         <%@ include file="/front-end/pages/links.html" %> 
     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/productstyles.css">
     <style>
</style>
</head>
<body id="page-top">
 <%@ include file="/front-end/pages/headNav.html" %>
  <main class="page glass">
<!-- 						<div id="fade" class="black_overlay"></div> -->
<!-- 						<div id="fade1" class="black_overlay"></div> -->
<!--     	  <div class="circle1"></div> -->
<!--     	  <div class="circle2"></div> -->
<!-- 				<div class="container-fluid glass"> -->
		
	<div id="app" class="row" style="margin-right:309px; margin-left:340px;border:1px black solid;border-radius:20px;position:relative;top:20px; left:20px;text-align:CENTER;font-size:18px; background-color:
	#ffcce600">

<!-- 						 <div class="col-md-7 col-xl-7 mb-7 conn"style="width:300px;"> -->
    <div id="wrapper">
<%--  <%@ include file="/sale-end/pages/nav.html" %>  --%>
<!--         <div class="d-flex flex-column" id="content-wrapper"> -->
<!--             <div id="content"> -->
<%--   <%@ include file="/sale-end/pages/fnav.html" %> --%>
<!--                 <div class="container-fluid"> -->
<!--                     <h3 class="text-dark mb-1">Blank Page</h3> -->
          
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
			
			
		
		</tr>
		<c:forEach var="orderVO" items="${listOrdByUsers_id}">
			<tr>
				<td>${orderVO.order_id}</td > 
				<td>${orderVO.card_number}</td>
				<td>${orderVO.order_status}</td>
				<td>${orderVO.order_date}</td>
				<td>${orderVO.users_id}</td>
				
				
				
			</tr>
		</c:forEach>
	</table>
    </div>
                   </div>     
<!--                 </div> -->
<!--     </div> -->
    </main>
  <%@ include file="/front-end/pages/footer.html" %>
 <%@ include file="/front-end/pages/script.html" %>

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>


</body>

</html>