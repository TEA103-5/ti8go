<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@page import="com.place_collect.model.*"%>
<%@page import="util.Google_key"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<%
	pageContext.setAttribute("Google_key", Google_key.key);   // 將util.Google_key的金鑰字串放進pageContext
	pageContext.setAttribute("weather_key", Google_key.weather_key);

	session.setAttribute("users_id", 1);  //  測試用
	
	if(session.getAttribute("users_id") != null){
		// 先把目前登入的users_id放進變數, 並產生一個地點收藏service , 將service放在pageContext供下面EL使用
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


<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>景點首頁 - Tivago</title>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/mycss/place/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/mycss/place/card-container.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/mycss/place/googleMap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/mycss/place/search-bar.css">
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


	<%@ include file="/front-end/pages/headNav.html" %>

<!-- 	在原本的主題外額外加上了class=fixed-top , 用來固定主區塊避免垂直卷軸出現 -->
    <main class="page service-page ">

        <nav id="search-bar" class="navbar navbar-light bg-light">
            <!-- <a class="navbar-brand">Navbar</a> -->
            <!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
                首个元素放置于起点，末尾元素放置于终点  -->
            <div>
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/addPlace.jsp">新增地點</a>
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/listMyPlace.jsp">我的地點</a>
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place_collect/listMyPlace_collect.jsp">我的收藏</a>
            </div>
            
            <form class="form-inline">
                <label class="my-1 mr-2" for="inlineFormCustomSelectPref">點擊地點後選擇交通方式</label>
                <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                    <option value="TRANSIT" selected>大眾運輸</option>
                    <option value="WALKING">走路</option>
                    <option value="BICYCLING">腳踏車</option>
                    <option value="DRIVING">開車</option>
                </select>

                <!-- <input class="form-control mr-sm-2" type="search" name="place_name" placeholder="請輸入地點名稱"
                    aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋路線</button> -->
            </form>

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
	            <c:if test="${(placeVO.place_state == 1 ) ||  (sessionScope.users_id == placeVO.users_id) }">
		            <div class="card" style="width: 100%;" data-id="${loop.index}" data-longitude="${placeVO.place_longitude}" data-latitude="${placeVO.place_latitude}">
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
		                        <c:if test="${place_collectSvc.getOnePlace_collect(placeVO.place_id,users_id) == null}">
		                       		<button type="button" class="collect_btn btn btn-secondary">加入收藏</button>
		                        </c:if>
		                        <c:if test="${place_collectSvc.getOnePlace_collect(placeVO.place_id,users_id) != null}">
		                       		<button type="button" class="collect_btn btn btn-secondary">取消收藏</button>
		                        </c:if>
		                    </form>
	<!-- 	                    <form class="card-form" action="https://www.google.com/" method="get"> -->
	<%-- 	                    	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">  --%>
	<%-- 	                        <input class="users_id_value" type="hidden" name="users_id" value="${placeVO.users_id}">  --%>
	<!-- 	                    </form> -->
		                </div>
		            </div>
	            </c:if>
            </c:forEach>

        </div>

        <div id="map"></div>
    </main>
	
<%-- 	 <%@ include file="/front-end/pages/script.html" %> --%>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/theme.js"></script>

    <!-- 以下是自己新增的js -->
    <script>
    
    	let x ;
    	let y ;
    	let latlng;
    
    	let position = [];
		    <c:forEach var="placeVO" items="${list}"  varStatus="loop">
			    <c:if test="${(placeVO.place_state == 1 ) ||  (sessionScope.users_id == placeVO.users_id) }">
			     	x = ${placeVO.place_latitude}
			     	y = ${placeVO.place_longitude}
			     	latlng = {lat : x , lng: y}
			     	position.push(latlng);
// 			    	position[${loop.index}] = { lat: ${placeVO.place_latitude}, lng: ${placeVO.place_longitude} } ;
			    </c:if>
			</c:forEach>
		
		
		
			
			
			
// 	    let position = [
// 	        { label: 'A', lat: 25.0336962, lng: 121.5643673 },
// 	        { label: 'B', lat: 25.0333698, lng: 121.5641564 },
// 	        { label: 'C', lat: 25.033899, lng: 121.564329 },
// 	        { label: 'D', lat: 25.0338407, lng: 121.5645269 },
// 	        { label: 'E', lat: 25.03361, lng: 121.56083 }
// 	    ];
    </script>
    
    
    <script src="<%=request.getContextPath()%>/front-end/place/myjs/place/googleMap.js"></script>
    
    <!-- 	載入的地址選單套件 -->
    <script src="<%=request.getContextPath()%>/front-end/place/tw-city-selector-master/dist/tw-city-selector.js"></script> 
    <script src="<%=request.getContextPath()%>/front-end/place/myjs/place/search-bar.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/myjs/place/card_container.js"></script>

	<script>
        let weather_api_key = "${weather_key}";
    </script>
	
	<script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>

    <script src="https://maps.googleapis.com/maps/api/js?key=${Google_key}&callback=initMap&libraries=&v=weekly" async></script>
	
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
				
				let place_id = $(this).closest(".card-body").find(".place_id_value").attr("value");
				
			  	let data = {
			  			"action": "ajax_insert_or_delete_PlaceCollect",
			            "place_id": place_id,
			            "users_id": login_users
			  	}		
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
			        	  swal("加入收藏成功", "已加入地點收藏", "success")
			          }else if(data.result == "delete_success"){
			        	  that.html("加入收藏")
			        	  swal("移除收藏成功", "已移除地點收藏", "success")
			          }
			          
			        }
			    });
			})
	});
		
	</script>
	
	
</body>

</html>