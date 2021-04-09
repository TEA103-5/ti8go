<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@ include file="/sale-end/pages/link.html"%>
</head>
<body id="page-top">
	<div id="wrapper">
<%-- 		<%@ include file="/sale-end/pages/nav.html"%> --%>
<!-- 		<div class="d-flex flex-column" id="content-wrapper"> -->
<!-- 			<div id="content"> -->
<%-- 				<%@ include file="/sale-end/pages/fnav.html"%> --%>
<!-- 				<div class="container-fluid"> -->
<!-- 					<div class="card rounded shadow border-0"> -->
<!-- 						<div class="card-body p-5 bg-white rounded"> -->





							<div class="container mx-auto mt-5" id="app">
							
								 <div class="row">
                        <div class="col-md-5 col-xl-5 mb-5 mx-auto">
								<div class="block-heading">
									<h2 class="text-info">登 入</h2>
								</div>

								<div >
									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/login.do" id="form1">
										<div class="form-group">
							
														<h10 id="errormessage" style="color:red">${message}</h10>
								
											<input class="form-control form-control-user" type="email" name="email"
												id="email" placeholder="Email Address" v-model="account"
												required="true" >
										</div>
										<div class="form-group">
										<input
												class="form-control" type="password" name="pwd"
												id="password" placeholder="Password" v-model="password">{{password}}
										</div>
										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													id="checkbox"><label class="form-check-label"
													for="checkbox">Remember me</label>
											</div>
										</div>
										<input class="btn btn-primary btn-block" @click="send"
											type="button" value="Log In"> <input type="hidden"
											name="action" value="login"> <input type="hidden"
											name="requestURL" value="<%=request.getServletPath()%>">
									</form>
								</div>
									</div>

									 <div class="col-md-5 col-xl-5 mb-5 mx-auto">
								<div class="text-center block-heading">
									<h4 class="text-dark mb-4">註冊新帳戶</h4>
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
										<input class="form-control-user form-control " type="email"
											id="exampleInputEmail"
											 aria-describedby="emailHelp"
											placeholder="Email Address" name="users_mail" v-model="account1"
											required="true" placeholder="Email">
									</div>
									<div class="form-group row">
										<div class="col-sm-6 mb-3 mb-sm-0">
											<input class="form-control form-control-user" type="password"
												id="examplePasswordInput" placeholder="Password" name="users_pwd"
												v-model="password1">
										</div>
										<div class="col-sm-6">
											<input class="form-control form-control-user" type="password"
												id="exampleRepeatPasswordInput"
												placeholder="Repeat Password" name="password_repeat"
												v-model="repassword1">
										</div>
									</div>
									<input class="btn btn-primary btn-block"
										@click="send2" type="button" value="Register Account">
									<input type="hidden" name="action" value="insertUsers">
								</form>

						
									 
									 
									 
									 
									 </div>


							
							</div>
							</div>





						</div>
					</div>
				</div>
			</div>
<!-- 			<footer class="bg-white sticky-footer"> -->
<!-- 				<div class="container my-auto"> -->
<!-- 					<div class="text-center my-auto copyright"> -->
<!-- 						<span>Copyright © Brand 2021</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</footer> -->
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
			class="fas fa-angle-up"></i></a>
	</div>
	<%@ include file="/sale-end/pages/script.html"%>

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>

	
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
				  			"action": "check",
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
	</script>
</body>
</html>