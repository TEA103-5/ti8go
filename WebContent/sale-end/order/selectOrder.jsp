<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>Blank Page - Brand</title>
 <%@ include file="/sale-end/pages/link.html" %> 
 <style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

<ul>
  <li><a href='<%=request.getContextPath()%>/sale-end/order/listAllOrder.jsp'>Get All</a> all order.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" >
        <b>輸入訂單編號 (如1):</b>
        <input type="text" name="ordid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
  <jsp:useBean id="odrSvc" scope="page" class="com.order.model.OrderService" />
   <jsp:useBean id="deptSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />

   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="ordid">
         <c:forEach var="odrVO" items="${odrSvc.all}" > 
          <option value="${odrVO.order_id}">${odrVO.order_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" >
       <b>選擇訂單狀態:</b>
       
       <select size="1" name="ordid">
         <c:forEach var="odrVO" items="${odrSvc.all}" > 
          <option value="${odrVO.order_id}">${odrVO.order_status}
         
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" >
       <b>選擇使用者訂單:</b>
       
       <select size="1" name="usersId">
         <c:forEach var="uVO" items="${usersSvc.all}" > 
          <option value="${uVO.users_id}">${uVO.users_id}
         
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="listOrderByUsersId_A">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Order/Order.do" name="form1">
        <b><font color=blue>複合查詢:</font></b> <br>
       
        <b>訂單編號:</b>
        <input type="text" name="order_id" value="1"><br>
           
       <b>訂單狀態:</b>
       <input type="text" name="order_status" value="0"><br>
       
       <b>信用卡號:</b>
       <input type="text" name="card_number" value="1234567890123456"><br>
       
       <b>使用者編號:</b>
       <input type="text" name="users_id" value="1"><br>

       <b>地址:</b>
       <input type="text" name="order_address" value="台北市大安區"><br>
    
<!--        <b>選擇明細:</b> -->
<!--        <select size="1" name="order_detail_id" > -->
<!--           <option value=""> -->
<%--          <c:forEach var="deptVO" items="${deptSvc.all}" >  --%>
<%--           <option value="${deptVO.order_detail_id}">${deptVO.order_detail_id} --%>
<%--          </c:forEach>    --%>
<!--        </select><br> -->
           
<!--        <b>訂單日期:</b> -->
<!-- 	   <select size="1" name="ordid"> -->
<%--          <c:forEach var="odrVO" items="${odrSvc.all}" >  --%>
<%--           <option value="${odrVO.order_date}">${odrVO.order_id} --%>
<%-- 		    </c:forEach>    --%>
<!--        </select> -->
             
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listOrder_ByCompositeQuery">
     </FORM>
  </li>
</ul>




<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/order/addOrder.jsp'>Add</a> a new Order.</li>
</ul>   
                    
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