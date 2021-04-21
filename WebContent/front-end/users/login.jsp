<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<%@ include file="/front-end/pages/links.html"%>

</head>
<body id="page-top">
	<div id="wrapper">
<!-- 選單頭 -->
<!-- include 不用選單 -->
<%@ include file="/front-end/pages/headNav.html" %>

	<div class="container mx-auto mt-5" id="app">
		<div class="row mt-5">
        	<div class="col-md-5 col-xl-5 mt-5 mb-5 mx-auto">
				<div class="block-heading">
					<h2 class="text-info">登 入</h2>
				</div>
				<div >
					<form METHOD="post" ACTION="<%=request.getContextPath()%>/login.do" id="form1">
						<div class="form-group">
							<h10 id="errormessage" style="color:red">${message}</h10>								
							<input class="form-control form-control-user" type="email" name="email"
								id="email" placeholder="請輸入 Email..." v-model="account" required >
						</div>
						
						<div class="form-group">
						<input class="form-control" type="password" name="pwd" id="password" 
							placeholder="請輸入密碼..." v-model="password">
						</div>
						
<!-- 						<div class="form-group"> -->
<!-- 							<div class="form-check"> -->
<!-- 								<input class="form-check-input" type="checkbox" id="checkbox"> -->
<!-- 									<label class="form-check-label" -->
<!-- 										for="checkbox">Remember me</label> -->
<!-- 							</div> -->
<!-- 						</div> -->
							<input class="btn btn-info btn-block text-white" @click="send"
								type="button" value="登入"> 
							<input type="hidden" name="action" value="login"> 
							<input type="hidden" name="requestUrl" 
								value="/front-end/users">
					<div class="text-center">
						<a class="text-center" id="forgotPassword" href="#">忘記密碼</a>
					</div>
					</form>
					
					
				</div>
			</div>
				
			<div class="col-md-5 col-xl-5 mb-5  mt-5 mx-auto">
				<div class="text-center block-heading">
					<h4 class="text-success mb-4">註冊新帳戶</h4>
				</div>
				<c:if test="${not empty errorMsgs}">
				
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				
				</c:if>
								
				<form class="user" METHOD="post"
					ACTION="<%=request.getContextPath()%>/users/users.do" id="form2">

				<div class="form-group">
					<input class="form-control-user form-control " type="email" id="exampleInputEmail"
						aria-describedby="emailHelp" placeholder="請輸入Email..." name="users_mail" 
						v-model="account1" required >
				</div>
				
				<div class="form-group row">
					<div class="col-sm-6 mb-3 mb-sm-0">
						<input class="form-control form-control-user" type="password" id="examplePasswordInput" 
							placeholder="請輸入密碼..." name="users_pwd" v-model="password1">
					</div>
					
					<div class="col-sm-6">
						<input class="form-control form-control-user" type="password"
							id="exampleRepeatPasswordInput" placeholder="請再輸入一次密碼..." 
							name="password_repeat" v-model="repassword1">
					</div>
				</div>
					<input class="btn btn-info btn-block text-white" @click="send2" type="button" 
						value="註冊">
					<input type="hidden" name="requestUrl" 
						value="/front-end/users">
					<input type="hidden" name="action" value="insertUsers">
					<img src="<%=request.getContextPath()%>/VerifyPic" id="verify-pic" class="mg">
				</form>									 
			</div>
		</div>
	</div>
</div>

	<a class="border rounded d-inline scroll-to-top" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>
	
	<%@ include file="/front-end/pages/script.html" %>

<!-- 	腳腳 -->
<%-- 	<%@ include file="/front-end/pages/footer.html" %>  --%>

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
 <!-- 以下是自己新增的js -->
    <script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>
	<script>
		var app = new Vue({
			el : '#app',
			data : {
				account : '',
				password : '',
				account1 : '',
				password1 : '',
				repassword1 : '',
			},
			methods : {
				send : function() {

					if (this.account !== '' && this.password !== '') {
						$("#errormessage").show();
						$("#errormessage").text("");
						let uid=this.account;
						let pwd=this.password;
 						console.log(uid);
					  	let data = {
				  			"action": "userlogin",
				  			"pwd": pwd,
	 			            "u_id":uid 
//	 			            "users_id": login_users
		 				}		
		// console.log($(this).parent(".card-body").find(".place_id_value").attr("value"));
		// console.log($(this).parent(".card-body").find(".users_id_value").attr("value"));
					
					$.ajax({
				        url: "<%=request.getContextPath()%>/login.do",           // 資料請求的網址
				        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
				        data: data,               // 傳送資料到指定的 url
				        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
				        success: function (data) {      // request 成功取得回應後執行
				      console.log(data);
				          
				        	$("#errormessage").text(data.errormsg);
				        	$("#errormessage").fadeOut(1300);
				          if(data.hasVO ==false){
// 								document.getElementById('password').value=data.hasV;
														$("#form1").submit();
				          }
// 				          else if(data.result == "delete_success"){
//	 			        	  that.html("加入收藏")
// 				          }    
				        }
				    });
					} else {
						$("#errormessage").show();
						$("#errormessage").text('資料尚未輸入完全');
						$("#errormessage").fadeOut(1300);
					}
				},
			
			send2 : function() {
				if (this.account1 !== '' && this.password1 !== '') {
					if (this.password1 == this.repassword1) {
						$("#form2").submit();
					} else {
						alert('請確認密碼')
					}
				} else {
					alert('資料尚未輸入完全')
				}
			},
		  }
		})
// 	忘記密碼
			$("#forgotPassword").on("click", function(e){
//             	alert("777") ;
				e.preventDefault();
            	swal_forgotPassword();
            	
            })
            
            function swal_forgotPassword(){
            	swal("忘記密碼","請輸入電子信箱,如電子信箱無誤,則會寄出暫時密碼", {
           		  content: "input",
           		})
           		.then((users_email) => {
           			console.log(users_email);
           			ajax_forgotPassword(users_email);
           		 	swal( "待會請至信箱確認," + users_email );
           		});
            }
            
            function ajax_forgotPassword(users_email){

                let data = {
                        "action": "forgotPasswordUsers",
                        "users_email": users_email
                }
            	
            	$.ajax({
			        url: "<%=request.getContextPath()%>/admins/adminsAjaxHandler.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: data,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			        	console.log(data);
// 			        	swal( "請至信箱確認," + admins_email );
			          
			        }
			    });
            }
		
	</script>
</body>
</html>