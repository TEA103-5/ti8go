<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/emp/listAllEmp.jsp'>List</a> all Emps. <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="empno">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="empno">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.empno}">${empVO.empno}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="empno">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.empno}">${empVO.ename}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
  
  
   <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dept/dept.do" >
       <b><font color=orange>選擇部門:</font></b>
       <select size="1" name="deptno">
         <c:forEach var="deptVO" items="${deptSvc.all}" > 
          <option value="${deptVO.deptno}">${deptVO.dname}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listEmps_ByDeptno_A">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/emp/addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

<h3><font color=orange>部門管理</font></h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a> all Depts. </li>
</ul>

</body>
</html>