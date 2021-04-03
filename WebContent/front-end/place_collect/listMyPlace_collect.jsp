<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place_collect.model.*"%>

<%


	Integer login_users = (Integer)session.getAttribute("users_id");

	Place_collectService place_collectSvc = new Place_collectService();
	List<Place_collectVO> list = place_collectSvc.getAllByPrimaryKey(login_users);
	
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("place_collectSvc", place_collectSvc);
%>
<jsp:useBean id="placeSvc" scope="page" class="com.place.model.PlaceService" />


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>我的收藏 - Tivago</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <style>
        .nav-link.active {
            display: inline-flex;

        }
    </style>

</head>

<body>
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar">
        <div class="container"><a class="navbar-brand logo" href="#">Tivago</a><button data-toggle="collapse"
                class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                    class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="index.html">首頁</a></li>
                    <li class="nav-item"><a class="nav-link active" href="service-page.html">景點</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">行程</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">活動</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post.html">遊記</a></li>
                    <li class="nav-item"><a class="nav-link" href="catalog-page.html">商城</a></li>
                    <li class="nav-item"><a class="nav-link" href="shopping-cart.html">購物車</a></li>
                    <li class="nav-item"><a class="nav-link" href="login.html">登入</a></li>
                    <li class="nav-item"><button class="btn btn-primary" type="button"
                            style="color: var(--gray);border-color: white;font-size: 14px;">訊息<span
                                class="badge badge-light">5</span></button></li>
                </ul>
            </div>
        </div>
    </nav>
    <main class="page service-page">

        <nav id="search-bar" class="navbar navbar-light bg-light">
            <!-- <a class="navbar-brand">Navbar</a> -->
            <!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
                首个元素放置于起点，末尾元素放置于终点  -->
            <div>
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/addPlace.jsp">新增地點</a>
                <a class="nav-link active" href="#">我的地點</a>
                <a class="nav-link active disabled" href="#" aria-disabled="true">我的收藏</a>
            </div>

            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active " aria-current="page">地點收藏</li>
                    <li class="breadcrumb-item"><a href="#">行程收藏</a></li>
                    <li class="breadcrumb-item"><a href="#">遊記收藏</a></li>
                    <li class="breadcrumb-item"><a href="#">商品收藏</a></li>
                </ol>
            </nav>
        </nav>


        <section class="clean-block clean-services dark">
            <div class="container">
                <div class="row">
                    <c:forEach var="place_collectVO" items="${list}"  varStatus="loop">
	                    <div class="col-md-6 col-lg-4">
	                        <div class="card"><img class="card-img-top w-100 d-block" src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic1&place_id=${place_collectVO.place_id}">
	                            <div class="card-body">
	                                <h4 class="card-title">${placeSvc.getOnePlace(place_collectVO.place_id).place_name}</h4>
	                                <p class="card-text">${placeSvc.getOnePlace(place_collectVO.place_id).place_address}</p>
		                            <form class="card-form" action="<%=request.getContextPath()%>/place/place.do" method="post">
				                    	<input class="place_id_value" type="hidden" name="place_id" value="${(place_collectVO.place_id)}"> 
				                    	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
				                    	<input type="hidden" name="action" value="getOne_For_Display">
				                        <button class="btn btn-outline-primary btn-sm" type="submit">地點詳情</button>
		                           		 <c:if test="${place_collectSvc.getOnePlace_collect(place_collectVO.place_id,sessionScope.users_id) == null}">
				                       		<button class="collect_btn btn btn-outline-primary btn-sm" type="button">加入收藏</button>
				                        </c:if>
				                        <c:if test="${place_collectSvc.getOnePlace_collect(place_collectVO.place_id,sessionScope.users_id) != null}">
				                       		<button class="collect_btn btn btn-outline-primary btn-sm" type="button">取消收藏</button>
				                        </c:if>
				                        
				                    </form>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </main>
<!--     <footer class="page-footer dark"> -->
<!--         <div class="container"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>Get started</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">Home</a></li> -->
<!--                         <li><a href="#">註冊</a></li> -->
<!--                         <li><a href="#">登入</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>About us</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">Company Information</a></li> -->
<!--                         <li><a href="#">Contact us</a></li> -->
<!--                         <li><a href="#">Reviews</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>Support</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">FAQ</a></li> -->
<!--                         <li><a href="#">Help desk</a></li> -->
<!--                         <li><a href="#">Forums</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>Legal</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">Terms of Service</a></li> -->
<!--                         <li><a href="#">Terms of Use</a></li> -->
<!--                         <li><a href="#">Privacy Policy</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="footer-copyright"> -->
<!--             <p>© 2021 Tivago</p> -->
<!--         </div> -->
<!--     </footer> -->
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/theme.js"></script>


	<script>
		
	$(function () {
// 		要注意VO裡的users_id是建立者 , 判斷收藏要看的是session的users_id
		// 用jstl判斷是否為登入狀態 , 登入則用el把登入的id放進去初始化js變數 
		<c:if test="${not empty sessionScope.users_id}">
			let login_users = ${sessionScope.users_id} ;
		</c:if>
		<c:if test="${empty sessionScope.users_id}">
			let login_users = null ;
		</c:if>
	
			$(".collect_btn").on("click" , function(e){

				// 	login_users為null代表未登入, 不執行後續動作	
				if(login_users == null){
					alert("登入後才能加入收藏");
					return ;
				}
				let that = $(this);
				
				// parents是會一直往上找,可能回傳一個陣列 , parent只會找到父元素且只找第一個
				let place_id = that.parents(".card-body").find(".place_id_value").attr("value");
				
			  	let data = {
			  			"action": "ajax_insert_or_delete_PlaceCollect",
			            "place_id": place_id,
			            "users_id": login_users
			  	}		
			  	console.log(data)
	// console.log($(this).parent(".card-body").find(".place_id_value").attr("value"));
	// console.log($(this).parent(".card-body").find(".users_id_value").attr("value"));
				
				$.ajax({
			        url: "<%=request.getContextPath()%>/place_collect/Place_collectAjaxHandler.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: data,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			          console.log(data);
			          if(data.result == "insert_succss"){
	// 		        	  // 新增成功則按鈕改成顯示取消收藏
			        	  that.html("取消收藏")
			          }else if(data.result == "delete_success"){
			        	  // 在此頁刪除成功則從畫面上移除
			        	  that.closest(".col-md-6.col-lg-4").fadeOut(300, function(){ $(this).remove();});
			        	  that.html("加入收藏")

			          }
			          
			        }
			    });
			})
	});
		
	</script>
</body>

</html>