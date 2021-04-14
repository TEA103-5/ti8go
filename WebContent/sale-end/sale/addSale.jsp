<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sale.model.*"%>
<%
	SaleVO empVO = (SaleVO) request.getAttribute("empVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>sale register here</title>

<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/fonts/fontawesome5-overrides.min.css">


</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/sale-end/pages/nav.html"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="/sale-end/pages/fnav.html"%>
				
								<div class="container-fluid">
					<div class="card rounded shadow border-0">
						<div class="card-body p-5 bg-white rounded">
				
				
				<div class="container">

					<div class="row">

						<div class="col-lg-12">
							<div class="p-5" id="app">
								<div class="text-center">
									<h4 class="text-dark mb-4">Create an Account!</h4>

								</div>
								<c:if test="${not empty errorMsgs}">
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<form class="user" METHOD="post"
									ACTION="<%=request.getContextPath()%>/sale/sale.do" id="form1">

									<div class="form-group">
										<input class="form-control form-control-user" type="email"
											id="exampleInputEmail" aria-describedby="emailHelp"
											placeholder="Email Address" name="semail" v-model="account">
									</div>
									<div class="form-group row">
										<div class="col-sm-6 mb-3 mb-sm-0">
											<input class="form-control form-control-user" type="password"
												id="examplePasswordInput" placeholder="Password" name="spwd"
												v-model="password">
										</div>
										<div class="col-sm-6">
											<input class="form-control form-control-user" type="password"
												id="exampleRepeatPasswordInput"
												placeholder="Repeat Password" name="password_repeat"
												v-model="repassword">
										</div>
									</div>
									<input class="btn btn-primary btn-block text-white btn-user"
										@click="send" type="button" value="Register Account">
									<input type="hidden" name="action" value="insertjsp">
								</form>

							</div>
						</div>

					</div>

				</div>
				
				
				</div>
				</div>
				</div>
			</div>

		</div>

	</div>


	<footer class="bg-white sticky-footer">
		<div class="container my-auto">
			<div class="text-center my-auto copyright">
				<span>Copyright © Brand 2021</span>
			</div>
		</div>
	</footer>
	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<script src="/TEA103G5/back-end/sale/assets/js/jquery.min.js"></script>
	<script
		src="/TEA103G5/back-end/sale/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/chart.min.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/bs-init.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/theme.js"></script>

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>
		var app = new Vue({
			el : '#app',
			data : {
				account : '',
				password : '',
				repassword : '',
			},
			methods : {
				send : function() {

					if (this.account !== '' && this.password !== '') {
						if (this.password == this.repassword) {
							$("#form1").submit();
						} else {
							alert('請確認密碼')
						}
					} else {
						alert('資料尚未輸入完全')
					}

				}
			}
		})
	</script>
</body>
</html>