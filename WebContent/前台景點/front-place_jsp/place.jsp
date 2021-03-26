<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<%
	PlaceService placeSvc = new PlaceService();
	List<PlaceVO> list = placeSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Services - Tivago</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <link rel="stylesheet" href="mycss/place/main.css">
    <link rel="stylesheet" href="mycss/place/card-container.css">
    <link rel="stylesheet" href="mycss/place/googleMap.css">
    <link rel="stylesheet" href="mycss/place/search-bar.css">
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
                <a class="nav-link active" href="#">前往地點收藏</a>
            </div>

            <form class="form-inline">
                <div class="city-selector" role="tw-city-selector" data-bootstrap-style data-standard-words></div>

                <input class="form-control mr-sm-2" type="search" placeholder="請輸入地點名稱" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜尋</button>
            </form>

        </nav>


        <!-- <div class="topnav">
            <a id=placeList_toggle class="" href="#">顯示地點清單</a>
            <a href="#">新增地點</a>
            <input type="text" placeholder="請輸入地點名稱">
        </div> -->


        <div id="card-container">
            <c:forEach var="placeVO" items="${list}"  varStatus="loop">
	            <div class="card" style="width: 18rem;" data-id="${loop.index}" data-longitude="${placeVO.place_longitude}" data-latitude="${placeVO.place_latitude}">
	                <img src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}" class="card-img-top" alt="...">
	                <div class="card-body">
	                    <h5 class="card-title">${placeVO.place_name}</h5>
	                    <p class="card-text">${placeVO.place_address}</p>
	                    <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
	                    <form class="card-form" action="https://www.google.com/" method="get">
	                        <button type="submit" class="btn btn-secondary">地點詳情</button>
	                    </form>
	                    <form class="card-form" action="https://www.google.com/" method="get">
	                        <button type="submit" class="btn btn-secondary">加入收藏</button>
	                    </form>
	                </div>
	            </div>
            </c:forEach>

        </div>

        <div id="map"></div>


    </main>
    <!-- <footer class="page-footer dark">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <h5>Get started</h5>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">註冊</a></li>
                        <li><a href="#">登入</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>About us</h5>
                    <ul>
                        <li><a href="#">Company Information</a></li>
                        <li><a href="#">Contact us</a></li>
                        <li><a href="#">Reviews</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Support</h5>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">Help desk</a></li>
                        <li><a href="#">Forums</a></li>
                    </ul>
                </div>
                <div class="col-sm-3">
                    <h5>Legal</h5>
                    <ul>
                        <li><a href="#">Terms of Service</a></li>
                        <li><a href="#">Terms of Use</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <p>© 2021 Tivago</p>
        </div>
    </footer> -->

    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="assets/js/smoothproducts.min.js"></script>
    <script src="assets/js/theme.js"></script>

    <!-- 以下是自己新增的js -->
    <script src="myjs/place/googleMap.js"></script>
    <script src="myjs/place/search-bar.js"></script>

    <script src="./tw-city-selector-master/dist/tw-city-selector.js"></script> // 載入的地址選單套件
    <script>
        function init() {
            new TwCitySelector({
                // el: '.city-selector',
                // bootstrapStyle: true
            });
        }
        init();
    </script>
    <script src="myjs/place/card_container.js"></script>

    <script src="https://maps.googleapis.com/maps/api/js?key=你的金鑰&callback=initMap&libraries=&v=weekly" async></script>

</body>

</html>