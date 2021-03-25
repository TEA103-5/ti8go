<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Tivago Trip: Home</title>

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
   <tr><td><h3>Tivago Trip: Home</h3><h4><a href="<%=request.getContextPath()%>/select_page.jsp">回首頁</a></h4></td></tr>
</table>

<p>This is the Home page for Tivago Trip: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/trip/listAllEmp.jsp'>List</a> all Trips.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" >
        <b>輸入行程編號 (如1,2,3...):</b>
        <input type="text" name="trip_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" >
       <b>選擇行程編號:</b>
       <select size="1" name="trip_id">
         <c:forEach var="tripVO" items="${tripSvc.all}" > 
          <option value="${tripVO.trip_id}">${tripVO.trip_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" >
       <b>選擇行程名稱:</b>
       <select size="1" name="trip_id">
         <c:forEach var="tripVO" items="${tripSvc.all}" > 
          <option value="${tripVO.trip_id}">${tripVO.trip_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>行程管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/trip/addEmp.jsp'>Add</a> a new Trip.</li>
</ul>

</body>
</html>