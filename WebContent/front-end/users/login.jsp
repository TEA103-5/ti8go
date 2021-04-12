<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <%@ include file="/front-end/pages/links.html" %> 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/productstyles.css">
<title>LOGIN</title>
</head>
<body>
			<div id="app">
			
				<div id="fade" class="black_overlay" style="display:block;"></div>
						<div id="lightadd" class="white_content glass" style="display:block;">
<!-- 							帳號：<input type="text" v-model="name"> <br />  -->
<!-- 							密碼：<input type="text" v-model="age"><br /> -->
<!-- 							<button v-on:click="login()">登入</button> -->
<!-- 							<button -->
<!-- 								onclick="document.getElementById('lightadd').style.display='none';document.getElementById('fade').style.display='none'">取消</button> -->
						
						
										<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/login.do" id="form1">
										<div class="form-group">
							
														<h10 id="errormessage" style="color:red">${message}</h10>
								
											<input class="form-control form-control-user" type="email" name="u_id"
												id="email" placeholder="Email Address" v-model="account">
										</div>
										<div class="form-group">
										<input
												class="form-control" type="password" name="pwd"
												id="password" placeholder="Password" v-model="password">
										</div>
										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													id="checkbox"><label class="form-check-label"
													for="checkbox">Remember me</label>
											</div>
										</div>
										<input class="btnl btn-cancel"  
										@click="send"
											type="button" value="Log In"> 
											<input type="hidden"
											name="action" value="userlogin">
<!-- 											<input type="hidden" -->
<!-- 											name="arraysize" v-model="updatelist.arraysize"> -->
											
<!-- 										<input type="hidden" name="array" value="1" id="checkedIdArray"> -->


											
											 <input type="hidden"
											name="requestURL" value="<%=request.getServletPath()%>">
									</form>
									<input type="button" class="btnl btn-cancel" value="取消" onclick="location.href='<%=request.getContextPath()%>/front-end/order/addOrder.jsp'">
						
						</div>
			</div>	
	 <%@ include file="/front-end/pages/script.html" %>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>
	var vm = new Vue({
	    el: '#app',
	    data:{
	    	account:'',
	    	password:'',
	    },
	    methods: {
	    	send(){
				$("#form1").submit();
	    	}
	    	
	    }
	})
	</script>						
</body>
</html>