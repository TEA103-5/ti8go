<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.users.model.*"%>
<%@ page import="com.admins.model.*"%>
<%@ page import="com.sale.controller.*"%>
<%@ page import="com.sale.model.*"%>
<%@ page import="com.users.controller.*"%>
<%@ page import="com.admins.controller.*"%>



<% 
  AdminsVO adminsVO = (AdminsVO) session.getAttribute("adminsVO");
  UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");

// 	未登入過，連進此頁，轉去登入頁，避免錯誤	
	if (adminsVO == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/front-end/login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
	    return;
	}
	UsersService UsersSvc = new UsersService();
    List<UsersVO> list = UsersSvc.getAll();
    pageContext.setAttribute("list",list);

  
%>
<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">
<head>
<title>所有會員資料  - AllUsers.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" ></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<%-- 	<%@ include file="/sale-end/pages/link.html"%> --%>
 	<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome5-overrides.min.css">

</head>
<body id="page-top">
    <div id="wrapper">
		<%@include file="/back-end/pages/side_nav.html" %>
			<div class="d-flex flex-column" id="content-wrapper">
            	<div id="content">
				
					<%@include file="/back-end/pages/top_nav.html" %>
					
				</div>
						<div class="container-fluid">


							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							
							<div class="card col-11 mt-5 mb-5" style="text-align:center;margin: 0 auto;">
								<div class="card-header ">
									<h2>所有使用者列表</h2>
								</div>
								<div class="card-body">
							
									
										<%@ include file="pages/page1.file" %> 
										
										<c:forEach var="usersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<c:if test="${usersVO.users_status==0}"><div class="card mb-5 bg-warning" ></c:if>
										<c:if test="${usersVO.users_status==1}"><div class="card mb-5" style="border: 1px solid rgb(0,0,0);"></c:if>
											
										<table class="table rows" id="table_id">
											<tr>
												<th>狀態</th>
												<th>編號 </th>
												<th>圖示</th>
												<th>暱稱</th>
												<th>姓名</th>
												<th>信箱</th>
											</tr>		
											<tr>
												 
													<td>
													<c:if test="${usersVO.users_status==0}">停用</c:if>
													<c:if test="${usersVO.users_status==1}">正常</c:if>
													</td>
													<td>${usersVO.users_id}</td>
													<td><img src="<%=request.getContextPath()%>/UsersGetPic?id=${usersVO.users_id}" height="48" width="48">
													</td>
													<td>${usersVO.users_nickname}</td>
													<td>${usersVO.users_name}</td>
													<td>${usersVO.users_mail}</td>
												</tr>
											<tr>
												
													<th>性別</th>
													<th>電話</th>
													<th>創建日期</th>
													<th>修改日期</th>
													<th>生日</th>
													<th>身分證</th>
													
												</tr>
											<tr>
													<td>
													<c:if test="${usersVO.users_sex==0}">女</c:if>
													<c:if test="${usersVO.users_sex==1}">男</c:if>
													</td>
													
													<td>${usersVO.users_phone}</td>  
											<fmt:parseDate var="parseDate" value="${usersVO.create_time}" 
												pattern="yyyy-MM-dd HH:mm:ss" parseLocale="Asia/Taipei" />	
								 
													<td><fmt:formatDate pattern="yyyy-MM-dd" 
            												value="${parseDate}" /></td>
											<fmt:parseDate var="parseDate" value="${usersVO.update_time}" 
												pattern="yyyy-MM-dd HH:mm:ss" parseLocale="Asia/Taipei" />	
													<td><fmt:formatDate pattern="yyyy-MM-dd" 
            												value="${parseDate}" /></td>
													<td>${usersVO.users_birthday}</td>
													<td>${usersVO.users_id_number}</td>				
												</tr>	
										</table>
											<c:if test="${usersVO.users_status==1}">
											<hr>
											<div class="form-group form-inline" style="text-align:center;margin: 0 auto;">
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/users/users.do" >
												     <input type="submit" class="btn btn-primary form-control m-3" value="修改">
												     <input type="hidden" name="users_id"  value="${usersVO.users_id}">
												     <input type="hidden" name="requestUrl" value="/back-end/users">
												     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
																	
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/users/users.do" >
												     <input type="submit" class="btn btn-primary form-control m-3" value="停用">
												     <input type="hidden" name="users_id"  value="${usersVO.users_id}">
												     <input type="hidden" name="users_status"  value="0">
												     <input type="hidden" name="requestUrl" value="/back-end/users">
												     <input type="hidden" name="action" value="deactivate"></FORM>
											</div>	  
											</c:if>
											<c:if test="${usersVO.users_status==0}">
											<hr>
											<div class="form-group form-inline" style="text-align:center;margin: 0 auto;">
																		
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/users/users.do" >
												     <input type="submit" class="btn btn-primary form-control m-3" value="啟用">
												     <input type="hidden" name="users_id"  value="${usersVO.users_id}">
												     <input type="hidden" name="users_status"  value="1">
												     <input type="hidden" name="requestUrl" value="/back-end/users">
												     <input type="hidden" name="action" value="deactivate"></FORM>
											</div>	  
											</c:if>
										</div> <!-- status /div -->
										</c:forEach>
										<%@ include file="pages/page2.file" %>	
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>		
		
				
 

</body>
</html>