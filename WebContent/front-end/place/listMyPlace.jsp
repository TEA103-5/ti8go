<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@page import="com.place_collect.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PlaceService placeSvc = new PlaceService();
	List<PlaceVO> list = placeSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>我的地點 - Tivago</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/place/assets/fonts/simple-line-icons.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/place/assets/css/smoothproducts.css">

<!-- 以下是自己新增的css -->
<style>
.nav-link.active {
	display: inline-flex;
}

.card-form {
	display: inline-flex;
}
</style>

</head>

<body>
	<nav
		class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar">
		<div class="container">
			<a class="navbar-brand logo" href="#">Tivago</a>
			<button data-toggle="collapse" class="navbar-toggler"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="index.html">首頁</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="service-page.html">景點</a></li>
					<li class="nav-item"><a class="nav-link"
						href="blog-post-list.html">行程</a></li>
					<li class="nav-item"><a class="nav-link"
						href="blog-post-list.html">活動</a></li>
					<li class="nav-item"><a class="nav-link" href="blog-post.html">遊記</a></li>
					<li class="nav-item"><a class="nav-link"
						href="catalog-page.html">商城</a></li>
					<li class="nav-item"><a class="nav-link"
						href="shopping-cart.html">購物車</a></li>
					<li class="nav-item"><a class="nav-link" href="login.html">登入</a></li>
					<li class="nav-item"><button class="btn btn-primary"
							type="button"
							style="color: var(- -gray); border-color: white; font-size: 14px;">
							訊息<span class="badge badge-light">5</span>
						</button></li>
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
				<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/addPlace.jsp">新增地點</a> <a
					class="nav-link active disabled" href="<%=request.getContextPath()%>/front-end/place/listMyPlace.jsp" aria-disabled="true">我的地點</a>
				<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place_collect/listMyPlace_collect.jsp">我的收藏</a>
			</div>


		</nav>









		<section class="clean-block clean-services dark">
			<div class="container">
				<!-- <div class="block-heading"><iframe allowfullscreen="" frameborder="0"
                        src="https://cdn.bootstrapstudio.io/placeholders/map.html" width="100%" height="400"></iframe>
                    <h2 class="text-info">Service Page</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in,
                        mattis vitae leo.</p>
                </div> -->

				<div class="row">
					<c:forEach var="placeVO" items="${list}" varStatus="loop">
						<c:if test="${sessionScope.users_id == placeVO.users_id}">
							<div class="col-md-6 col-lg-4">
								<div class="card">
									<img class="card-img-top w-100 d-block"
										src="<%=request.getContextPath()%>/place/DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}">
									<div class="card-body">
										<h4 class="card-title">${placeVO.place_name}</h4>
										<p class="card-text">${placeVO.place_address}</p>
										<form class="card-form"	action="<%=request.getContextPath()%>/place/place.do"method="post">
											<input class="place_id_value" type="hidden" name="place_id" value="${placeVO.place_id}"> 
											<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>"> 
											<input type="hidden" name="action" value="getOne_For_Display">
											<button class="btn btn-outline-primary btn-sm" type="submit">地點詳情</button>
										</form>
										<form class="card-form"	action="<%=request.getContextPath()%>/place/place.do" method="post">
											<input class="place_id_value" type="hidden" name="place_id" value="${placeVO.place_id}">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="getOne_For_Update">
											<button class="btn btn-outline-primary btn-sm" type="submit">地點修改</button>
										</form>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
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
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/theme.js"></script>
	
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