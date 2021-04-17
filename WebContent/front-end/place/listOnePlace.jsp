<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.place.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PlaceVO placeVO = (PlaceVO)request.getAttribute("placeVO");//PlaceServlet.java(Concroller), 存入req的placeVO物件
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blog Post - Tivago</title>
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
                    <li class="nav-item"><a class="nav-link" href="service-page.html">景點</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">行程</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">活動</a></li>
                    <li class="nav-item"><a class="nav-link active" href="blog-post.html">遊記</a></li>
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
    <main class="page blog-post">

		<nav id="search-bar" class="navbar navbar-light bg-light">
			<!-- <a class="navbar-brand">Navbar</a> -->
			<!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
             	   首个元素放置于起点，末尾元素放置于终点  -->
			<div>
				<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/addPlace.jsp">新增地點</a> <a
					class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/listMyPlace.jsp">我的地點</a> <a
					class="nav-link active" href="<%=request.getContextPath()%>/front-end/place_collect/listMyPlace_collect.jsp">我的收藏</a>
			</div>

		</nav>

		<div class="block-content">

            <section class="clean-block clean-post dark">
                <div class="container">

                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item">
                                <img src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic2&place_id=${placeVO.place_id}" class="d-block w-100" alt="...">
                            </div>
                            <div class="carousel-item">
                                <img src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic3&place_id=${placeVO.place_id}" class="d-block w-100" alt="...">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                            data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                            data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <div class="post-body">
                        <h3>地點名稱</h3>
                        <!-- <div class="post-info"><span>By John Smith</span><span>Jan 27, 2018</span></div> -->
                        <h5>${placeVO.place_name}</h5>
                        <!-- <p>地點名稱</p> -->
                        <figure class="figure"><img class="rounded img-fluid figure-img"
                                src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic3&place_id=${placeVO.place_id}"
                                alt="A generic square placeholder image with rounded corners in a figure.">
<!--                             <figcaption class="figure-caption">Lorem ipsum dolor</figcaption> -->
                        </figure>
                        <h4>地址</h4>
                        <h6>${placeVO.place_address}</h6>
                        <h4>電話</h4>
                        <h6>${placeVO.place_tel}</h6>
                        <br>
                        <h4>地點簡介</h4>
                        <div class="row">
                            <div class="col-md-6">
                                <figure class="figure"><img class="rounded img-fluid figure-img"
                                        src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic2&place_id=${placeVO.place_id}"
                                        alt="A generic square placeholder image with rounded corners in a figure.">
<!--                                     <figcaption class="figure-caption">Lorem Ipsum dolor</figcaption> -->
                                </figure>
                            </div>
                            <div class="col">
                                <p>${placeVO.place_index}&nbsp;</p>
                            </div>
                        </div>
                        <!-- <h4>Lorem Ipsum dolor</h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor
                            in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam
                            urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur
                            adipiscing elit.</p>
                        <div class="row">
                            <div class="col">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim
                                    nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing
                                    elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor
                                    sit amet, consectetur adipiscing elit.</p>
                            </div>
                            <div class="col-md-6">
                                <figure class="figure"><img class="rounded img-fluid figure-img"
                                        src="assets/img/scenery/image5.jpg"
                                        alt="A generic square placeholder image with rounded corners in a figure.">
                                    <figcaption class="figure-caption">Lorem Ipsum dolor</figcaption>
                                </figure>
                            </div>
                        </div> -->
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
    
    <!-- 	以下是自己加入的js -->
	<script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>
    
    <!-- 	此script是將成功訊息印出來 -->
	<script>
		<c:if test="${not empty successMsgs}">
				<c:forEach var="message" items="${successMsgs}" >
					swal("成功","${message}", "success")
				</c:forEach>
		</c:if>
	</script>
	
</body>

</html>