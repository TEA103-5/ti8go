<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

if (session.getAttribute("adminsVO") != null) {
	String url = request.getContextPath() + "/back-end/admins/listAllAdmins.jsp" ;
	response.sendRedirect(url);
	return;
}

%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Login - Brand</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/assets/fonts/fontawesome5-overrides.min.css">
</head>

<body class="bg-gradient-primary">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-9 col-lg-12 col-xl-10">
                <div class="card shadow-lg o-hidden border-0 my-5">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-flex">
                                <div class="flex-grow-1 bg-login-image"
                                    style="background-image: url(&quot;assets/img/dogs/image3.jpeg&quot;);"></div>
                            </div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h4 class="text-dark mb-4">歡迎回來!</h4>
                                    </div>
                                    <form class="user" id="login_form">
                                        <div class="form-group"><input class="form-control form-control-user"
                                                type="email" id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="請輸入信箱" name="email">
                                            <div class="valid-feedback">
                                                信箱格式正確
                                            </div>
                                            <div class="invalid-feedback">
                                                不是有效的電子信箱格式
                                            </div>

                                        </div>
                                        <div class="form-group"><input class="form-control form-control-user"
                                                type="password" id="exampleInputPassword" placeholder="請輸入密碼"
                                                name="password"></div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
<!--                                                 <div class="form-check"><input -->
<!--                                                         class="form-check-input custom-control-input" type="checkbox" -->
<!--                                                         id="formCheck-1"><label -->
<!--                                                         class="form-check-label custom-control-label" -->
<!--                                                         for="formCheck-1">Remember Me</label></div> -->
<!--                                             </div> -->
                                        </div><button class="btn btn-primary btn-block text-white btn-user"
                                            id="login_button" type="button">登入</button>
                                        <!-- <hr><a class="btn btn-primary btn-block text-white btn-google btn-user"
                                            role="button"><i class="fab fa-google"></i>&nbsp; Login with Google</a><a
                                            class="btn btn-primary btn-block text-white btn-facebook btn-user"
                                            role="button"><i class="fab fa-facebook-f"></i>&nbsp; Login with
                                            Facebook</a>
                                        <hr> -->
                                    </form>
                                    <div class="text-center"><a class="small" id="forgotPassword" href="#">忘記密碼</a></div>
<!--                                     <div class="text-center"><a class="small" href="register.html">Create an -->
<!--                                             Account!</a></div> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/assets/js/theme.js"></script>

    <!-- 以下是自己新增的js -->
    <script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>

    <script>
        $(function () {

            let el_admins_email = $("#exampleInputEmail");
            const admins_emailReg = /^([\w\.\-]){1,64}\@([\w\.\-]){1,64}$/
            let el_login_form = $("#login_form");
            let el_login_button = $("#login_button");


            el_admins_email.on("input", function (e) {

                let val_admins_email = el_admins_email.val();

                if (admins_emailReg.test(val_admins_email)) {

                    el_admins_email.removeClass("is-invalid");
                    el_admins_email.addClass("is-valid");

                } else {
                    el_admins_email.removeClass("is-valid");
                    el_admins_email.addClass("is-invalid");

                }
            })

            el_login_button.on("click", function (e) {

                let admins_email = $("#exampleInputEmail").val();
                let admins_password = $("#exampleInputPassword").val();

                let data = {
                    "action": "admins_login",
                    "admins_email": admins_email,
                    "admins_password": admins_password
                }

                if ($(".is-invalid").length != 0 || admins_email == "" || admins_password == "") {
                    alert("尚有資料未填寫完成")
                    return false;

                } else {
                	
                    $.ajax({
                        url: "<%=request.getContextPath()%>/admins/adminsAjaxHandler.do",           // 資料請求的網址
                        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                        data: data,               // 傳送資料到指定的 url
                        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
                        success: function (data) {      // request 成功取得回應後執行
                            console.log(data);
                            let redirect_target;
                            // 如果Session裡有loction並回傳回來, 則導向至該處 , 如沒有則導向到listAllAdmins.jsp
                            if(data.location){
                            	redirect_target = data.location ;
                            }else{
                        		redirect_target = "<%=request.getContextPath()%>/back-end/admins/listAllAdmins.jsp" ;
                            }
                            if (data.result == "login_success") {

                                let timer = 3, // timer in seconds
                                    isTimerStarted = false;
								
                                // 這是一個IIFE函式 , swal使用promise, 當點擊確認按鈕promise會收到true , 點擊範圍外則得到null
                                (function customSwal() {
                                    swal({
                                        title: "登入成功 !",
                                        text: timer + "秒後即將跳轉",
                                        timer: !isTimerStarted ? timer * 1000 : undefined,
//                                      showCancelButton: true,
//                                 		confirmButtonClass: "btn-danger",
//                                 		confirmButtonText: "Yes, delete it!",
                                    })
                                    .then( (isConfirm) => {
                                    	if(isConfirm){
//                                     		window.location.href = redirect_target;
                                    		window.location.replace(redirect_target);
//                                     		return;
                                    	}
                                    });
                                    isTimerStarted = true;
                                    if (timer) {
                                        timer--;
                                        setTimeout(customSwal, 1000);
                                    }else{
//                                     	window.location.href = redirect_target;
                                    	window.location.replace(redirect_target);
                                    }
                                })();

//                                 swal("登入成功", "3秒後跳轉", "success")
//                                 setTimeout(function () {
//                                     window.location.href = "/Tivago_Git_Master_0417/back-end/admins/listAllAdmins.jsp";
//                                 }, 3000)

                            } else if (data.result == "login_failure") {
                                swal("登入失敗", "帳號或密碼錯誤", "error");
                            }

                        }
                    });
                }
            })

            
//             -------------
            $("#forgotPassword").on("click", function(e){
//             	alert("777") ;
				e.preventDefault();
            	swal_forgotPassword();
            	
            })
            
            
            function swal_forgotPassword(){
            	swal("忘記密碼","請輸入電子信箱,如電子信箱無誤,則會寄出暫時密碼", {
           		  content: "input",
           		})
           		.then((admins_email) => {
           			console.log(admins_email);
           			ajax_forgotPassword(admins_email);
           		 	swal( "待會請至信箱確認," + admins_email );
           		});
            }
            
            function ajax_forgotPassword(admins_email){

                let data = {
                        "action": "forgotPassword",
                        "admins_email": admins_email
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



        })

    </script>
</body>

</html>