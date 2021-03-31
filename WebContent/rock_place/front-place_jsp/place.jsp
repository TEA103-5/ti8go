<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@page import="com.place_collect.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<%
	session.setAttribute("users_id", 1);  //  測試用
	
	if(session.getAttribute("users_id") != null){
		// 先把目前登入的users_id放進變數, 並產生一個服務 , 將服務放在pageContext供下面EL使用
		Integer login_users = (Integer)session.getAttribute("users_id");
		Place_collectService place_collectSvc = new Place_collectService();
		pageContext.setAttribute("place_collectSvc", place_collectSvc);
		
	}
	
	
	List<PlaceVO> list = null;	
	if(request.getAttribute("list") != null){
		list = (List<PlaceVO>)request.getAttribute("list");
	}else{
		PlaceService placeSvc = new PlaceService();
		list = placeSvc.getAll();
		pageContext.setAttribute("list", list);
	}
%>


<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Services - Tivago</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/mycss/place/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/mycss/place/card-container.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/mycss/place/googleMap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/rock_place/front-place_jsp/mycss/place/search-bar.css">
    <style>
        /* .form-group {
            display: inline-flex;
            
        }

        .form-control {
            display: inline-flex;
        }

        .form-inline {
            display: inline-flex;
        } */

        .city-selector {
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
                <a class="nav-link active" href="#">新增地點</a>
                <a class="nav-link active" href="#">我的地點</a>
                <a class="nav-link active" href="#">我的收藏</a>
            </div>

            <form class="form-inline" METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do">
                <div class="city-selector" role="tw-city-selector" data-bootstrap-style data-standard-words></div>

                <input class="form-control mr-sm-2" type="search" name="place_name" placeholder="請輸入地點名稱" aria-label="Search">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
                <input type="hidden" name="action" value="getCard"> 
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
            </form>

        </nav>

        <div id="card-container">
            <c:forEach var="placeVO" items="${list}"  varStatus="loop">
	            <div class="card" style="width: 18rem;" data-id="${loop.index}" data-longitude="${placeVO.place_longitude}" data-latitude="${placeVO.place_latitude}">
	                <img src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}" class="card-img-top" alt="...">
	                <div class="card-body">
	                    <h5 class="card-title">${placeVO.place_name}</h5>
	                    <p class="card-text">${placeVO.place_address}</p>
	                    <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
	                    <form class="card-form" action="<%=request.getContextPath()%>/place/place.do" method="post">
	                    	<input class="place_id_value" type="hidden" name="place_id" value="${placeVO.place_id}"> 
	                    	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
	                    	<input type="hidden" name="action" value="getOne_For_Display">
	                        <button type="submit" class="btn btn-secondary">地點詳情</button>
	                    </form>
<!-- 	                    <form class="card-form" action="https://www.google.com/" method="get"> -->
<%-- 	                    	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">  --%>
	                        <input class="users_id_value" type="hidden" name="users_id" value="${placeVO.users_id}"> 
	                        <c:if test="${place_collectSvc.getOnePlace_collect(placeVO.place_id,users_id) == null}">
	                       		<button type="button" class="collect_btn btn btn-secondary">加入收藏</button>
	                        </c:if>
	                        <c:if test="${place_collectSvc.getOnePlace_collect(placeVO.place_id,users_id) != null}">
	                       		<button type="button" class="collect_btn btn btn-secondary">取消收藏</button>
	                        </c:if>
<!-- 	                    </form> -->
	                </div>
	            </div>
            </c:forEach>

        </div>

        <div id="map"></div>
    </main>


    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/js/theme.js"></script>

    <!-- 以下是自己新增的js -->
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/myjs/place/googleMap.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/myjs/place/search-bar.js"></script>

<!-- 	載入的地址選單套件 -->
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/tw-city-selector-master/dist/tw-city-selector.js"></script> 
    <script>
        function init() {
            new TwCitySelector({
                // el: '.city-selector',
                // bootstrapStyle: true
            });
        }
        init();
    </script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/myjs/place/card_container.js"></script>

    <script src="https://maps.googleapis.com/maps/api/js?key=APIKEY&callback=initMap&libraries=&v=weekly" async></script>
	
	<script>
		
// 		要注意VO裡的users_id是建立者 , 判斷收藏要看的是session的users_id
		// 用jstl判斷是否為登入狀態 , 登入則用el把登入的id放進去初始化js變數 
		<c:if test="${not empty users_id}">
			let login_users = ${users_id} ;
		</c:if>
		<c:if test="${empty users_id}">
			let login_users = null ;
		</c:if>
	
			$(".collect_btn").on("click" , function(e){

				// 	login_users為null代表未登入, 不執行後續動作	
				if(login_users == null){
					alert("登入後才能加入收藏");
					return ;
				}
				let that = $(this);
				
				let place_id = $(this).parent(".card-body").find(".place_id_value").attr("value");
				
			  	let data = {
			  			"action": "ajax_insert_or_delete_PlaceCollect",
			            "place_id": place_id,
			            "users_id": login_users
			  	}		
	// console.log($(this).parent(".card-body").find(".place_id_value").attr("value"));
	// console.log($(this).parent(".card-body").find(".users_id_value").attr("value"));
				
				$.ajax({
			        url: "/Tivago_Git/place_collect/Place_collectAjaxHandler.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: data,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			          console.log(data);
			          if(data.result == "insert_succss"){
	// 		        	  // 新增成功則按鈕改成顯示取消收藏
			        	  that.html("取消收藏")
			          }else if(data.result == "delete_success"){
			        	  that.html("加入收藏")
			          }
			          
			        }
			    });
			})
		
		
	</script>
	
	
</body>

</html>