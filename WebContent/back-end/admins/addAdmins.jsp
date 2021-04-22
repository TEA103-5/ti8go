<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admins.model.*"%>


<% 
// if( ((AdminsVO)session.getAttribute("adminsVO")).getAdmins_authority() != "root"){
// 	List<String> errorMsgs = new LinkedList<String>();
// 	request.setAttribute("errorMsgs", errorMsgs);
// 	errorMsgs.add("新增管理員只有最高管理員可使用");
// 	String url = "/back-end/place/listAllPlace.jsp";
// //		getServletContext().getRequestDispatcher(url).forward(request, response);
// 	RequestDispatcher successView = request.getRequestDispatcher(url);
// 	successView.forward(request, response);
// //System.out.println("有進來這邊");
// 	return;
// }


	AdminsVO adminsVO = (AdminsVO) request.getAttribute("adminsVO");
	
	pageContext.setAttribute("adminsVO" , adminsVO);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blank Page - Brand</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/fonts/fontawesome5-overrides.min.css">
    
        <!-- 以下是自己新增的css -->
    <style>


        /* 在所有class="xrequired"的label後面加上紅色星號 */
        label.xrequired:after {
            content: '* ';
            color: red;
        }
    </style>
</head>

<body id="page-top">
    <div id="wrapper">
    
    	<%@include file="/back-end/pages/side_nav.html" %>


        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
            
         	   <%@include file="/back-end/pages/top_nav.html" %>

                <div class="container-fluid">
                    <h3 class="text-dark mb-1">新增管理員</h3>
<!--                     <p class="text-primary">.text-primary</p> -->

	
	                <!-- 表單驗證參考這一項 利用加上特定class來標註錯誤或正確訊息 https://getbootstrap.com/docs/4.6/components/forms/#server-side -->
<!-- 	                管理者編號這邊純顯示, 值由底下的input=hidden 發送 -->
	                <form METHOD="post" ACTION="<%=request.getContextPath()%>/admins/admins.do" id="insert_form" name="form1" > 

	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired" for="validationServer01">管理者姓名</label>
	                            <input type="text" class="form-control " id="validationServer01" name="admins_name" value="<%= (adminsVO==null)? "" : adminsVO.getAdmins_name()%>"
	                                required >
	                            <div class="valid-feedback">驗證通過</div>
								<div id="validationServer01Feedback" class="invalid-feedback">
									姓名只能輸入中文及英文,且長度必需在2到10之間</div>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                        	<label class="xrequired" for="validationServer02">性別</label>
	                        	<div class="form-check form-check-inline">
								  <input class="form-check-input" type="radio" name="admins_sex" id="inlineRadio1" value="1" <%= (adminsVO !=null &&  adminsVO.getAdmins_sex()== 1 )? "checked" : "" %>  required>
								  <label class="form-check-label" for="inlineRadio1">男</label>
								</div>
								<div class="form-check form-check-inline">
								  <input class="form-check-input" type="radio" name="admins_sex" id="inlineRadio2" value="2" <%= (adminsVO !=null &&  adminsVO.getAdmins_sex()== 2 )? "checked" : "" %>  >
								  <label class="form-check-label" for="inlineRadio2">女</label>
								</div>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired" for="validationServer03">信箱</label>
	                            <input type="text" class="form-control " id="validationServer03" name="admins_email"  value="<%= (adminsVO==null)? "aa@us.com.us" : adminsVO.getAdmins_email()%>"   required >
	                        	<div class="valid-feedback">驗證通過</div>
								<div id="validationServer03Feedback" class="invalid-feedback">
									不是有效的電子信箱格式</div>
								<div id="validationServer035Feedback" class="invalid-feedback">
									</div>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired" for="validationServer04">密碼</label>
	                            <input type="password" class="form-control " id="validationServer04" name="admins_password"  value="<%= (adminsVO==null)? "" : adminsVO.getAdmins_password()%>"   required>
	                        	<div class="valid-feedback">驗證通過</div>
								<div id="validationServer03Feedback" class="invalid-feedback">
									密碼最少8個字,可輸入小寫字母、大寫字母、數字,需包含小寫字母、大寫字母</div>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired" for="validationServer045">密碼確認</label>
	                            <input type="password" class="form-control " id="validationServer045"  value=""   required>
	                        	<div class="valid-feedback">驗證通過</div>
								<div id="validationServer03Feedback" class="invalid-feedback">
									密碼確認與密碼內容不相同</div>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired" for="validationServer05">權限</label>
	                                <select class="form-control" id="validationServer05"name="admins_authority">
								      	<option value="normal" ${(adminsVO.admins_authority == 'normal')? 'selected' : '' }  >一般管理員</option>
								      	<option value="root"   ${(adminsVO.admins_authority == 'root')? 'selected' : '' }>最高管理員</option>
								    </select>
<%-- 	                            <input type="text" class="form-control " id="validationServer05" name="admins_authority"  value="<%= (adminsVO==null)? "root" : adminsVO.getAdmins_authority()%>"   required> --%>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer06">職位</label>
	                            <input type="text" class="form-control " id="validationServer06" name="admins_position"  value="<%= (adminsVO==null)? "派遣碼農" : adminsVO.getAdmins_position()%>"   >
	                        	<div class="valid-feedback">驗證通過</div>
								<div id="validationServer01Feedback" class="invalid-feedback">
									姓名只能輸入中文及英文,且長度必需在2到10之間</div>
	                        </div>
	                    </div>
	                    
	                    
	                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	                    <input type="hidden" name="action" value="insert">
	                    <button type="submit" id="submit_button" class="btn btn-secondary">送出</button>
	                </form>
	                <!-- 表單結束 -->
                    
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © Brand 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
    
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/theme.js"></script>
    
    

	<script>


        $(function () {

			
			
				let el_form = $("#insert_form");
			
				let el_admins_name = $("#validationServer01");
				const admins_nameReg = /^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$/; // 只能是中、英文字母 , 且長度必需在2到10之間
				let el_admins_email = $("#validationServer03");
				const admins_emailReg = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4})*$/
				let el_admins_password = $("#validationServer04");
				const admins_passwordReg = /^(?=^.{8,}$)((?=.*[A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z]))^.*$/ ;
				let el_check_password = $("#validationServer045");
				
				let el_admins_position = $("#validationServer06");
				const admins_positionReg = /^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$/; // 只能是中、英文字母 , 且長度必需在2到10之間
			
            
//             這邊將所有欄位先變成不可輸入,並定義開始修改按鈕事件(經度跟緯度原本就有設定readonly , 所以移除全部的元素的disabled時, 經緯度仍有readonly而不能修改)
// 				$(".form-check-input").attr("disabled" , "disabled")
// 				$(".form-control").attr("disabled" , "disabled")

// 				$("#submit_button").attr("disabled" , "disabled")
				
// 				$("#update_button").on("click", function(e){
// 					$("*").removeAttr("disabled") ;
// 				})
				
				
				
				el_form.on("submit", function (e) {
					if ($(".is-invalid").length != 0) {
						alert("資料尚未填寫完成")
	                    return false;
	                } 
            	})
				
				
// 				此為密碼欄輸入事件
				el_admins_password.on("input", function (e) {

	                let val_admins_password = el_admins_password.val();
	                let val_check_password = el_check_password.val();
					
	                if(admins_passwordReg.test(val_admins_password)){
	
	                	el_admins_password.removeClass("is-invalid");
	                	el_admins_password.addClass("is-valid");
	                } else {
	
	                	el_admins_password.removeClass("is-valid");
	                	el_admins_password.addClass("is-invalid");
	                }
	                
	                if(val_check_password == val_admins_password){
	                	
	                	el_check_password.removeClass("is-invalid");
	                	el_check_password.addClass("is-valid");
	                } else {
	
	                	el_check_password.removeClass("is-valid");
	                	el_check_password.addClass("is-invalid");
	                }
            	})
            	
// 				此為密碼確認欄輸入事件
            	el_check_password.on("input", function (e) {

	                let val_check_password = el_check_password.val();
	                let val_admins_password = el_admins_password.val();
	                
	                if(val_check_password == val_admins_password){
	
	                	el_check_password.removeClass("is-invalid");
	                	el_check_password.addClass("is-valid");
	                } else {
	
	                	el_check_password.removeClass("is-valid");
	                	el_check_password.addClass("is-invalid");
	                }
            	})
            	
//             	此為信箱欄輸入事件
            	el_admins_email.on("input", function (e) {

	                let val_admins_email = el_admins_email.val();
	                
	                if(admins_emailReg.test(val_admins_email)){
	
	                	el_admins_email.removeClass("is-invalid");
	                	el_admins_email.addClass("is-valid");
	                	
	                	$("#validationServer03Feedback").text("");
	                	
	                	if_Admins_email_exist();
	                } else {
	                	el_admins_email.removeClass("is-valid");
	                	el_admins_email.addClass("is-invalid");
	                	
	                	$("#validationServer03Feedback").text("不是有效的電子信箱格式");
	                }
            	})
				
//             	此為管理者姓名欄輸入事件
            	el_admins_name.on("input", function (e) {

	                let val_admins_name = el_admins_name.val();
	                
	                if(admins_nameReg.test(val_admins_name)){
	
	                	el_admins_name.removeClass("is-invalid");
	                	el_admins_name.addClass("is-valid");
	                } else {
	                	el_admins_name.removeClass("is-valid");
	                	el_admins_name.addClass("is-invalid");
	                }
            	})

//             	此為職位欄輸入事件
            	el_admins_position.on("input", function (e) {

	                let val_admins_position = el_admins_position.val();
	                
	                if(admins_nameReg.test(val_admins_position)){
	
	                	el_admins_position.removeClass("is-invalid");
	                	el_admins_position.addClass("is-valid");
	                } else {
	                	el_admins_position.removeClass("is-valid");
	                	el_admins_position.addClass("is-invalid");
	                }
            	})
	

				function if_Admins_email_exist(){
					
					let admins_email = $("#validationServer03").val() ;
					
console.log(admins_email)
					
					let data = {
				  			"action": "if_Admins_email_exist",
				            "admins_email": admins_email
				  	}	
					
					$.ajax({
					  url: "<%=request.getContextPath()%>/admins/adminsAjaxHandler.do",           // 資料請求的網址
					  type: "POST",                  // GET | POST | PUT | DELETE | PATCH
					  data: data,               // 傳送資料到指定的 url
					  dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
					  success: function(data){      // request 成功取得回應後執行
					    console.log(data);
					    if(data.result == "exist"){
// 					    	validationServer035Feedback
					    	el_admins_email.removeClass("is-valid");
		                	el_admins_email.addClass("is-invalid");
		                	
		                	$("#validationServer035Feedback").text("此電子信箱已被使用")
		                	
					    }else if(data.result == "not_exist"){
					    	el_admins_email.removeClass("is-invalid");
		                	el_admins_email.addClass("is-valid");
		                	
		                	$("#validationServer035Feedback").text("")
					    	
					    }
					    
					  }
					});
				}
				
				




        });

    </script>
</body>

</html>