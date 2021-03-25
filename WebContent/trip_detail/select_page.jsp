<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Tivago Trip_detail: Home</title>

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
   <tr><td><h3>Tivago Trip_detail: Home</h3><h4><a href="<%=request.getContextPath()%>/select_page.jsp">�^����</a></h4></td></tr>
</table>

<p>This is the Home page for Tivago Trip_detail: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/trip_detail/listAllEmp.jsp'>List</a> all Trip_details.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" >
        <b>��J��{���ӽs�� (�p1,2,3...):</b>
        <input type="text" name="trip_detail_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="trip_detailSvc" scope="page" class="com.trip_detail.model.Trip_detailService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" >
       <b>��ܦ�{���ӽs��:</b>
       <select size="1" name="trip_detail_id">
         <c:forEach var="trip_detailVO" items="${trip_detailSvc.all}" > 
          <option value="${trip_detailVO.trip_detail_id}">${trip_detailVO.trip_detail_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/trip_detail/trip_detail.do" >
       <b>��ܦ�{���Ӥ��e:</b>
       <select size="1" name="trip_detail_id">
         <c:forEach var="trip_detailVO" items="${trip_detailSvc.all}" > 
          <option value="${trip_detailVO.trip_detail_id}">${trip_detailVO.trip_content}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>��{���Ӻ޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/trip_detail/addEmp.jsp'>Add</a> a new Trip_detail.</li>
</ul>

</body>
</html>