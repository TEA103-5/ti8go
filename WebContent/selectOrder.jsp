<%-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!-- <html> -->
<!-- <head> -->
<!-- <title>Order: Home</title> -->

<!-- <style> -->
/*   table#table-1 { */
/* 	width: 450px; */
/* 	background-color: #CCCCFF; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 10px; */
/*     border: 3px ridge Gray; */
/*     height: 80px; */
/*     text-align: center; */
/*   } */
/*   table#table-1 h4 { */
/*     color: red; */
/*     display: block; */
/*     margin-bottom: 1px; */
/*   } */
/*   h4 { */
/*     color: blue; */
/*     display: inline; */
/*   } */
<!-- </style> -->

<!-- </head> -->
<!-- <body bgcolor='white'> -->

<!-- <table id="table-1"> -->
<!--    <tr><td><h3>Order: Home</h3><h4>( MVC )</h4></td></tr> -->
<!-- </table> -->

<!-- <p>This is the Home page for Order: Home</p> -->

<!-- <h3>資料查詢:</h3> -->
	
<%-- <%-- 錯誤表列 --%> --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 	    <c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/order/listAllOrder.jsp'>Get All</a> all order.  <br><br></li> --%>
  
  
<!--   <li> -->
<%--     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" > --%>
<!--         <b>輸入訂單編號 (如1):</b> -->
<!--         <input type="text" name="ordid"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

<%--   <jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" /> --%>
<%--   <jsp:useBean id="odrSvc" scope="page" class="com.order.model.OrderService" /> --%>

   
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" > --%>
<!--        <b>選擇訂單編號:</b> -->
<!--        <select size="1" name="ordid"> -->
<%--          <c:forEach var="odrVO" items="${odrSvc.all}" >  --%>
<%--           <option value="${odrVO.order_id}">${odrVO.order_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" > --%>
<!--        <b>選擇訂單狀態:</b> -->
       
<!--        <select size="1" name="ordid"> -->
<%--          <c:forEach var="odrVO" items="${odrSvc.all}" >  --%>
<%--           <option value="${odrVO.order_id}">${odrVO.order_status} --%>
         
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" > --%>
<!--        <b>選擇使用者訂單:</b> -->
       
<!--        <select size="1" name="usersId"> -->
<%--          <c:forEach var="uVO" items="${usersSvc.all}" >  --%>
<%--           <option value="${uVO.users_id}">${uVO.users_id} --%>
         
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="listOrderByUsersId_A"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->


<!-- <h3>員工管理</h3> -->

<!-- <ul> -->
<%--   <li><a href='<%=request.getContextPath()%>/order/addOrder.jsp'>Add</a> a new Order.</li> --%>
<!-- </ul> -->

<!-- </body> -->
<!-- </html> -->