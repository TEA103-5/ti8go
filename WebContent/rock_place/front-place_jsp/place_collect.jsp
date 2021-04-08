<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
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
                <a class="nav-link active" href="#">新增地點</a>
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
                    <div class="col-md-6 col-lg-4">
                        <div class="card"><img class="card-img-top w-100 d-block" src="assets/img/scenery/image5.jpg">
                            <div class="card-body">
                                <h4 class="card-title">地點名稱</h4>
                                <p class="card-text">地點地址</p>
                            </div>
                            <div><button class="btn btn-outline-primary btn-sm" type="button">Learn More</button></div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </section>
    </main>
    <footer class="page-footer dark">
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
    </footer>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/rock_place/front-place_jsp/assets/js/theme.js"></script>

</body>

</html>