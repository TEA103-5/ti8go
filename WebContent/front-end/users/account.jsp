<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.users.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.users.controller.*"%>


<%
  UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
// 	未登入過，連進此頁，轉去登入頁，避免錯誤	
	if (usersVO == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/front-end/users/login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
	    return;
	}
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">
  <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" ></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
	<%@ include file="/sale-end/pages/link.html"%>
  </head>
<body>
<!-- 頭段 -->
	<%@ include file="/front-end/pages/headNav.html" %>  
<!-- 中段     -->
	<main class="page landing-page">
	<div class="mx-auto col-8" style="margin: 100px">
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
						<label for="users_name" class="col-sm-2">會員姓名</label>
						<div class="col-sm-6">

							<input type="text" class="form-control" id="users_name" name="users_name" size="30"
								placeholder="請輸入你的姓名..." maxlength="30"
								value="${usersVO.users_name}">
								
						</div>
						<div class="col-sm-4">
							<label>性別 : </label>
							<div class="form-check-inline">
								<input type="radio" name="users_sex" id="sexF" value="0"
									<%= usersVO.getUsers_sex()==0 ? "checked" : ""%> >
								<label for="sexF"> 女</label>
							</div>
							<div class="form-check-inline">
								<input type="radio" name="users_sex" id="sexM" value="1"
									<%= usersVO.getUsers_sex()==1 ? "checked" : ""%>>
								<label for="sexM"> 男</label>
							</div>
						</div>
					</div>
					<div class="form-group form-row">
						<label for="users_nickname" class="col-sm-2">會員暱稱</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="users_nickname" name="users_nickname" 
								size="10" placeholder="請輸入你的暱稱..." maxlength="10"
								value="${usersVO.users_nickname}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_mail" class="col-sm-2">Email</label>
						<div class="col-sm-6">
						
							<input type="hidden" class="form-control" id="users_mail" name="users_mail" 
								size="30" placeholder="請輸入你的電子郵件信箱..." 
								value="${usersVO.users_mail}">
								
							<p>${usersVO.users_mail}</p>
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_pwd" class="col-sm-2">密碼</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="users_pwd" name="users_pwd" 
								size="30" placeholder="請輸入你的新密碼..." 
								value="${usersVO.users_pwd}">
						</div>
					</div>
					<div class="form-group form-row">
						<label for="users_re_pwd" class="col-sm-2">確認密碼</label>
						<div class="col-sm-6">
							<input class="form-control" type="password" id="users_re_pwd"
								placeholder="請再輸入一次你的密碼..." name="users_pwd_repeat"
								value="${usersVO.users_pwd}" >
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_birthday" class="col-sm-2">生日</label>
						<div class="col-sm-6">
							<input class="form-control" type="date" id="users_birthday" name="users_birthday" 
								value="${usersVO.users_birthday}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_id_number" class="col-sm-2">身分證號碼</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="users_id_number"
								placeholder="請輸入你的身分證號碼..." name="users_id_number" size="10"
								value="${usersVO.users_id_number}" maxlength="10">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_phone" class="col-sm-2">電話</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="users_phone"
								placeholder="請輸入你的電話號碼..." name="users_phone" maxlength="10" 
								value="${usersVO.users_phone}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_" class="col-sm-2">頭像</label>
						<div class="col-sm-6">
							<input class="form" type="file" name="users_users_pic"  id="users_users_pic"
								onchange="selectImgFile(this.files)">
							<img id="showImg" class="img-fluid float-right img-thumbnail" 
								src="<%=request.getContextPath()%>/UsersGetPic?id=${usersVO.users_id}" />
						</div>
					</div>
					<div>
					<div style="margin: 0 auto">
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="requestUrl" value="/front-end/users">
					<input type="hidden" name="users_status" value="1" />
					<input type="hidden" name="users_id" value="${usersVO.users_id}" />
					<input type="submit" value="確認新增" >
					</div></div>	
				</form>
			</div>
			<div class="card-footer">
			<input type ="button" onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/card/card.jsp'" value="信用卡列表"></input>
			</div>
		</div>
	</div>
	</main>
<!-- 	footer	 -->
	 
<%-- 	<%@ include file="/front-end/pages/footer.html" %> --%>

	<script>
// 	圖片顯示控制
	function selectImgFile(files) {
	    if (!files.length) {
	        return false;
	    }
	    
	    let file   = files[0];
	    let reader = new FileReader();
	    reader.onload = function () {
	        document.getElementById('showImg').src = this.result;
	    };

	    reader.readAsDataURL(file);
	}
	</script>
	<!-- 	以下是自己加入的js -->
	<script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>
	
<!-- 	此script是將成功訊息跟錯誤訊息印出來 -->
	<script>
		<c:if test="${not empty successMsgs}">
				<c:forEach var="message" items="${successMsgs}" >
					swal("成功","${message}", "success")
				</c:forEach>
		</c:if>
		
		<c:if test="${not empty errorMsgs}">
			<c:forEach var="message" items="${errorMsgs}" >
				swal("失敗","${message}", "error")
			</c:forEach>
		</c:if>	
	</script>
</body>
</html>

 