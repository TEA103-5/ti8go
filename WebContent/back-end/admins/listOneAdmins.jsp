<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.admins.model.*"%>
<%@ page import="java.util.*"%>


<% 


	AdminsVO adminsVO = (AdminsVO) request.getAttribute("adminsVO");
	
	pageContext.setAttribute("adminsVO" , adminsVO);
	
	
// 	用來判斷權限
if( !adminsVO.getAdmins_authority().equals("root")){
	
	if(   adminsVO.getAdmins_id() != ((AdminsVO)session.getAttribute("adminsVO")).getAdmins_id() ){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			errorMsgs.add("一般管理員只能修改自己的資料");
			String url = "/back-end/place/listAllPlace.jsp";
	//			getServletContext().getRequestDispatcher(url).forward(request, response);
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		//System.out.println("有進來這邊");
			return;
	}	
}
	
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
                    <h3 class="text-dark mb-1">管理者詳情</h3>
<!--                     <p class="text-primary">.text-primary</p> -->

	
	                <!-- 表單驗證參考這一項 利用加上特定class來標註錯誤或正確訊息 https://getbootstrap.com/docs/4.6/components/forms/#server-side -->
<!-- 	                管理者編號這邊純顯示, 值由底下的input=hidden 發送 -->
	                <form METHOD="post" ACTION="<%=request.getContextPath()%>/admins/admins.do" id="insert_form" name="form1" > 
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer00">管理者編號</label>
	                            <input type="text" class="form-control" id="validationServer00"  value="<%= (adminsVO==null)? "" : adminsVO.getAdmins_id()%>"   readonly>
							</div>
	                    </div>
	                    
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
								  <input class="form-check-input" type="radio" name="admins_sex" id="inlineRadio1" value="1" <%= (adminsVO !=null &&  adminsVO.getAdmins_sex()== 1 )? "checked" : "" %> >
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
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer07">創建日期</label>
	                            <input type="text" class="form-control " id="validationServer07"   value="<%= (adminsVO==null)? "" : adminsVO.getAdmins_create_time()%>"   readonly>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer08">最後創建日期</label>
	                            <input type="text" class="form-control " id="validationServer08"   value="<%= (adminsVO==null)? "" : adminsVO.getAdmins_edit_time()%>"   readonly>
	                        </div>
	                    </div>
	                    
	                    
	                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	                    <input type="hidden" name="action" value="update">
	                    <input type="hidden" name="admins_id" value="${adminsVO.getAdmins_id()}">
	                    <button type="submit" id="submit_button" class="btn btn-secondary">送出修改</button>
	                    <button type="button" id="update_button" class="btn btn-secondary">開始修改</button>
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




//             // <input> class有is-valid 就會顯示標籤div class="valid-feedback"內設定的文字 ,is-invalid則顯示class=invalid-feedback的內容
//             let el_place_name = $("#validationServer01");
//             const place_nameReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$/; // 只能是中、英文字母、數字和_ , 且長度必需在2到10之間
//             let el_place_address = $("#validationServer02")
//             const place_addressReg = /^[(\u4e00-\u9fa5)(0-9)]{5,50}$/; // 只能是中文字母、數字,且長度必需在5到50之間

//             let el_place_longitude = $("#validationServer03");
//             let el_place_latitude = $("#validationServer04");

//             let el_place_tel = $("#validationServer05");
//             const place_telReg = /^[(0-9-)]{10,20}$/;

//             let el_place_type = $("#validationServer06");
//             const place_typeReg = /^[(\u4e00-\u9fa5)]{1,50}$/;

//             let el_place_index = $("#validationServer07");
//             const place_indexReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$/; // 只能是中、英文字母、數字和_ , 且長度必需在2到200之間

//             let el_form = $("#insert_form");
			
			
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
				$(".form-check-input").attr("disabled" , "disabled")
				$(".form-control").attr("disabled" , "disabled")

				$("#submit_button").attr("disabled" , "disabled")
				
				$("#update_button").on("click", function(e){
					$("*").removeAttr("disabled") ;
				})
				
				
				
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
				
				

//             el_place_index.on("input", function (e) {

//                 let val_place_index = el_place_index.val();

//                 // 電話可以不輸入, 所以空字串時將錯誤驗證跟正確驗證都移除
//                 if (val_place_index == "") {
//                     el_place_index.removeClass("is-invalid");
//                     el_place_index.removeClass("is-valid");
//                     return;
//                 } else if (place_indexReg.test(val_place_index)) {

//                     el_place_index.removeClass("is-invalid");
//                     el_place_index.addClass("is-valid");
//                 } else {

//                     el_place_index.removeClass("is-valid");
//                     el_place_index.addClass("is-invalid");
//                 }


//             })

//             el_place_type.on("input", function (e) {

//                 let val_place_type = el_place_type.val();

//                 // 電話可以不輸入, 所以空字串時將錯誤驗證跟正確驗證都移除
//                 if (val_place_type == "") {
//                     el_place_type.removeClass("is-invalid");
//                     el_place_type.removeClass("is-valid");
//                     return;
//                 } else if (place_typeReg.test(val_place_type)) {
//                     // console.log("驗證通過")
//                     // console.log(val_place_type);
//                     el_place_type.removeClass("is-invalid");
//                     el_place_type.addClass("is-valid");
//                 } else {
//                     // console.log("驗證不通過")
//                     // console.log(val_place_type);
//                     el_place_type.removeClass("is-valid");
//                     el_place_type.addClass("is-invalid");
//                 }


//             })

//             el_place_tel.on("input", function (e) {

//                 let val_place_tel = el_place_tel.val();

//                 // 電話可以不輸入, 所以空字串時將錯誤驗證移除
//                 if (val_place_tel == "") {
//                     el_place_tel.removeClass("is-invalid");
//                     el_place_tel.removeClass("is-valid");
//                     return;
//                 } else if (place_telReg.test(val_place_tel)) {
//                     console.log("驗證通過")
//                     el_place_tel.removeClass("is-invalid");
//                     el_place_tel.addClass("is-valid");
//                 } else {
//                     el_place_tel.removeClass("is-valid");
//                     el_place_tel.addClass("is-invalid");
//                 }


//             })


//             el_place_name.on("input", function (e) {
//                 let val_place_name = el_place_name.val();
//                 if (place_nameReg.test(val_place_name)) {
//                     console.log("驗證通過")
//                     el_place_name.removeClass("is-invalid");
//                     el_place_name.addClass("is-valid");
//                 } else {
//                     el_place_name.removeClass("is-valid");
//                     el_place_name.addClass("is-invalid");
//                 }

//                 console.log("地點名稱input事件觸發")
//             })

//             // 同時把縣市/區域兩個選單都綁定上事件
//             $("select").on("change", function (e) {


//                 let select_group = $('.city-selector').find(":selected");


//                 // // 將select 選項存到sessionStorage , 以方便錯誤處理時讀回
//                 // sessionStorage.county = select_group.eq(0).text();
//                 // sessionStorage.district = select_group.eq(1).text();

//                 console.log(select_group)
//                 if (select_group.eq(0).text() != "選擇縣市" && select_group.eq(1).text() != "選擇區域") {

//                     $(".city-selector").removeClass("is-invalid");
//                     $(".city-selector").addClass("is-valid");

//                     get_lat_lng();
//                 } else {
//                     $(".city-selector").removeClass("is-valid");
//                     $(".city-selector").addClass("is-invalid")
//                 }
//             })


//             el_place_address.on("input", function (e) {

//                 let select_group = $('.city-selector').find(":selected");
//                 if (select_group.eq(0).text() != "選擇縣市" && select_group.eq(1).text() != "選擇區域") {
//                     // alert(777)
//                     $(".city-selector").removeClass("is-invalid");
//                     $(".city-selector").addClass("is-valid");
//                 } else {
//                     $(".city-selector").removeClass("is-valid");
//                     $(".city-selector").addClass("is-invalid")
//                 }

//                 let val_place_address = el_place_address.val();
//                 if (place_addressReg.test(val_place_address)) {
//                     console.log("驗證通過")
//                     el_place_address.removeClass("is-invalid");
//                     el_place_address.addClass("is-valid");

//                     get_lat_lng();

//                 } else {
//                     el_place_address.removeClass("is-valid");
//                     el_place_address.addClass("is-invalid");
//                 }
//             })



// 			const google_api_key = "AIzaSyCQspd49Wmywh3L5LAOftK_jV4qA2i89VQ";
//             // let target_address = "台灣台北市萬華區康定路190號"

//             function get_lat_lng() {
//                 // alert("777")
//                 // $("#getGeo_button").on("click", function (e) {
//                 let target_address;

//                 // 當兩個都驗證通過時才觸發,否則結束
//                 if (el_place_address.hasClass("is-valid") == true && $('.city-selector').hasClass("is-valid") == true) {
//                     target_address = el_place_address.val();
//                 } else {
//                     return;
//                 }

//                 let google_geocode_api = "https://maps.googleapis.com/maps/api/geocode/json?key=" + google_api_key + "&address=" + target_address;

//                 $.ajax({
//                     url: google_geocode_api,           // 資料請求的網址
//                     type: "POST",                  // GET | POST | PUT | DELETE | PATCH
//                     // data: data,               // 傳送資料到指定的 url
//                     dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
//                     success: function (data) {      // request 成功取得回應後執行
//                         console.log(data);
//                         // console.log(data.results[0].geometry.location.lat);
//                         let lat = data.results[0].geometry.location.lat;
//                         let lng = data.results[0].geometry.location.lng;
//                         console.log(lat);
//                         console.log(lng);
//                         let google_embed_src = "https://www.google.com/maps/embed/v1/search?key=" + google_api_key + "&q=" + target_address + "&zoom=20&center=" + lat + "," + lng;
//                         // let src = google_api_url + "&zoom=20&center=" + lat + "," + lng ;
//                         let src = google_embed_src;
//                         console.log(src)
//                         $("#map-iframe").attr("src", src);

//                         if (-180 <= lng && lng <= 180) {
//                             el_place_longitude.val(lng);
//                             el_place_longitude.addClass("is-valid");
//                         }
//                         if (-90 <= lat && lat <= 90) {
//                             el_place_latitude.val(lat)
//                             el_place_latitude.addClass("is-valid");
//                         }


//                     }
//                 });
//                 // })
//             }


        });

    </script>
</body>

</html>