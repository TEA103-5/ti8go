<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.users.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.users.controller.*"%>

<%
  UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">
  <head>
<%-- 	<%@ include file="/sale-end/pages/link.html"%> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  </head>
<body>
<!-- 頭段 -->
<!-- 	<nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar" style="background: #85867F;color: var(--red);"> -->
<!--         <div class="container"> -->
<!--         <a class="navbar-brand logo" href="#">Tivago</a> -->
<!--         <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"> -->
<!--         <span class="sr-only">Toggle navigation</span> -->
<!--         <span class="navbar-toggler-icon"></span></button> -->
        
<!--             <div class="collapse navbar-collapse" id="navcol-1"> -->
<!--                 <ul class="navbar-nav ml-auto"> -->
<!-- 					<li class="nav-item"> -->
<!-- 		              <a class="nav-link active" href="index.html">首頁</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="service-page.html">景點</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="blog-post-list.html">行程</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="blog-post-list.html">活動</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="blog-post.html">遊記</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="product/listAllProduct.jsp">商城</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="shopping-cart.html">購物車</a> -->
<!-- 		            </li> -->
<!-- 		            <li class="nav-item"> -->
<!-- 		              <a class="nav-link" href="login.jsp">登入</a> -->
<!-- 		            </li>                    -->
<!--                     <li class="nav-item" style="height: 38px;"> -->
<!--                         <div class="nav-item dropdown" style="padding: 4px;width: 82.7083px;margin: 0px;height: 30px;"> -->
<!--                         <a class="dropdown-toggle" aria-expanded="false" data-toggle="dropdown" href="#" style="padding: 20px;width: 0px;height: -8px;margin: -3px;color: rgba(0,0,0,0.5);">user</a> -->
<!--                             <div class="dropdown-menu"> -->
<!--                             <a class="dropdown-item" href="#">First Item</a> -->
<!--                             <a class="dropdown-item" href="#">Second Item</a> -->
<!--                             <a class="dropdown-item" href="#">Third Item</a> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--         </div> -->
<!--     </nav> -->
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
					
<!-- 					<div class="form-group form-row"> -->
<!-- 						<div class="col-6 col-sm-6"> -->
<!-- 							<img id="showImg" class="img-fluid float-right img-thumbnail col-sm-6"  -->
<%-- 									src="<%=request.getContextPath()%>/UsersGetPic?id=${usersVO.users_id}"> --%>
<!-- 							</img>							 -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group form-row"> -->
<!-- 						<label for="users_mail" class="col-sm-2">會員編號</label> -->
<!-- 						<div class="col-sm-6"> -->
<!-- 							<input type="text" class="form-control" id="users_id" name="users_id"  -->
<!-- 								size="10" placeholder="For Test ID"  -->
<%-- 								value="${usersVO.users_id}"> --%>
<!-- 						</div> -->
<!-- 					</div>	 -->

<!-- 					<div class="form-group form-row"> -->
<!-- 						<label for="users_id" class="col-sm-2">會員編號</label> -->
<!-- 						<div class="col-sm-6"> -->
<%-- 							<P>${usersVO.users_id}</P> --%>
<!-- 						</div> -->
<!-- 					</div> -->
					
					<div class="form-group form-row">
						<label for="users_name" class="col-sm-2">會員姓名</label>
						<div class="col-sm-6">

							<input type="text" class="form-control" id="users_name" name="users_name" size="30"
								placeholder="請輸入你的姓名..." 
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
								size="10" placeholder="請輸入你的暱稱..." 
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
								value="${usersVO.users_birthday}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_id_number" class="col-sm-2">身分證號碼</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="users_id_number"
								placeholder="請輸入你的身分證號碼..." name="users_id_number" size="10"
								value="${usersVO.users_id_number}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_phone" class="col-sm-2">電話</label>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="users_phone"
								placeholder="請輸入你的電話號碼..." name="users_phone" size="10" 
								value="${usersVO.users_phone}">
						</div>
					</div>	
					<div class="form-group form-row">
						<label for="users_" class="col-sm-2">頭像</label>
						<div class="col-sm-6">
							<input class="form" type="file" name="users_users_pic"  id="users_users_pic"
								onchange="selectImgFile(this.files)">
							<img id="showImg" class="img-fluid float-right img-thumbnail" 
								src="<%=request.getContextPath()%>/UsersGetPic?id=${usersVO.users_id}">
							</img>
						</div>
					</div>
					<div>
					<div style="margin: 0 auto">
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="users_status" value="1" />
					<input type="hidden" name="users_id" value="${usersVO.users_id}" />
					<input type="submit" value="確認新增" >
					</div></div>	
				</form>
			</div>
		</div>
	</div>
	</main>
<!-- 	footer	 -->
	  <footer class="page-footer dark" style="background: #575D59;">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <h5>Get started</h5>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Sign up</a></li>
                        <li><a href="#">Downloads</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>About us</h5>
                    <ul>
                        <li><a href="#">Company Information</a></li>
                        <li><a href="#">Contact us</a></li>
                        <li><a href="#">Reviews</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Support</h5>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">Help desk</a></li>
                        <li><a href="#">Forums</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Legal</h5>
                    <ul>
                        <li><a href="#">Terms of Service</a></li>
                        <li><a href="#">Terms of Use</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <p>© 2021 Copyright Text</p>
        </div>
    </footer>
<%-- 	<%@ include file="/front-end/pages/footer.html" %> --%>
	<script>
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
</body>
</html>

 