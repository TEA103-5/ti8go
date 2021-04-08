<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Detail: Home</title>

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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Detail: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>資料查詢:</h3>
	
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
  <li><a href='<%=request.getContextPath()%>/OrderDetail/listAllOrderDetail.jsp'>Get All</a> all order.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" >
        <b>輸入訂單編號 (如1):</b>
        <input type="text" name="ordtid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="odrSvc" scope="page" class="com.orderDetail.model.OrderDetailService" />

   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" >
       <b>選擇訂單明細編號:</b>
       <select size="1" name="ordtid">
         <c:forEach var="odrVO" items="${odrSvc.all}" > 
          <option value="${odrVO.order_detail_id}">${odrVO.order_detail_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetail/OrderDetail.do" >
       <b>選擇訂單:</b>
       
       <select size="1" name="ordtid">
         <c:forEach var="odrVO" items="${odrSvc.all}" > 
          <option value="${odrVO.order_detail_id}">${odrVO.product_id}
         
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>明細管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/OrderDetail/addOrderDetail.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>