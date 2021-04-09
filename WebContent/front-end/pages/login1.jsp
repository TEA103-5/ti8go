<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.users.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.users.controller.*"%>

<%
  UsersVO usersVO = (UsersVO) request.getAttribute("usersVO");
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">
  <head>
	<%@ include file="/sale-end/pages/link.html"%>
 	<style media="screen">

	</style>
  </head>
<body>
	<div class="mx-auto col-8">

		<div class="card bg-light">
			<div class="card-header">
				<h2 class="card-title pt-3">
					個人資料
				</h2>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="card-body">
				<form style="margin: 20px" METHOD="post" ACTION="<%=request.getContextPath()%>/users/users.do"
					 name="form1" enctype="multipart/form-data">
					
					<div class="form-group form-row">
						<label for="users_id" class="col-sm-2">會員編號</label>
						<div class="col-sm-6">
							<P>${usersVO.users_id}</P>
						</div>
					</div>
					
					<div class="form-group form-row">
						<label for="users_name" class="col-sm-2">會員姓名</label>
						<div class="col-sm-6">

							<input type="text" class="form-control" id="users_name" name="users_name" size="30"
								placeholder="請輸入你的姓名..." 
								value="<%= (usersVO==null)?"":usersVO.getUsers_name()%>">
								
						</div>
						<div class="col-sm-4">
							<label>性別 : </label>
							<div class="form-check-inline">
								<input type="radio" name="users_sex" id="sexF" value="0">
								<label for="sexF"> 女</label>
							</div>
							<div class="form-check-inline">
								<input type="radio" name="users_sex" id="sexM" value="1">
								<label for="sexM"> 男</label>
							</div>
						</div>
					</div>
					<div class="form-group form-row">
						<label for="users_nickname" class="col-sm-2">會員暱稱</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="users_nickname" name="users_nickname" 
								size="10" placeholder="請輸入你的暱稱..." 
								value="<%= (usersVO==null)?"":usersVO.getUsers_nickname()%>">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_mail" class="col-sm-2">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="users_mail" name="users_mail" 
								size="30" placeholder="請輸入你的電子郵件信箱..." 
								value="${usersVO.users_mail}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_pwd" class="col-sm-2">密碼</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="users_pwd" name="users_pwd" 
								size="30" placeholder="請輸入你的新密碼..." 
								value="">
						</div>
					</div>
					<div class="form-group form-row">
						<label for="users_re_pwd" class="col-sm-2">確認密碼</label>
						<div class="col-sm-6">
							<input class="form-control" type="password" id="users_re_pwd"
								placeholder="請再輸入一次你的密碼..." name="users_pwd_repeat" >
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_birthday" class="col-sm-2">生日</label>
						<div class="col-sm-6">
							<input class="form-control" type="date" id="users_birthday" name="users_birthday" 
								value="<%= (usersVO==null)?"":usersVO.getUsers_birthday()%>">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_id_number" class="col-sm-2">身分證號碼</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="users_id_number"
								placeholder="請輸入你的身分證號碼..." name="users_id_number" size="10"
								value="<%= (usersVO==null)?"":usersVO.getUsers_id_number()%>">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_phone" class="col-sm-2">電話</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="users_phone"
								placeholder="請輸入你的電話號碼..." name="users_phone" size="10" 
								value="<%= (usersVO==null)?"":usersVO.getUsers_id_number()%>">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_" class="col-sm-2">頭像</label>
						<div class="col-sm-6">
							<input class="form" type="file" name="users_users_pic"  id="users_users_pic">
						</div>
					</div>	
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="users_status" value="1" />
					<input type="hidden" name="users_id" value="${usersVO.users_id}" />
					<input type="submit" value="確認新增">
				</form>
			</div>
		</div>

	</div>
<!-- 	footer	 -->
	<%@ include file="/front-end/pages/footer.html" %>
	<script>
	
	</script>
</body>
</html>

 