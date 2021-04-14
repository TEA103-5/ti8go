<%@page import="java.util.*"%>
<%@page import="com.note.model.*"%>
<%@page import="com.notec.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%

session.setAttribute("users_id", 1);  //  測試用

	NoteService noteSvc = new NoteService();
	List<NoteVO> list = noteSvc.getAll();
	pageContext.setAttribute("list", list);
	
	NoteCService noteCSvc = new NoteCService();
	// 	List<NoteCVO> list = noteCSvc.getAll();
	// 	pageContext.setAttribute("list", list);
	pageContext.setAttribute("noteCSvc", noteCSvc);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>我的遊記 - Tivago</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/notefront/assets/fonts/simple-line-icons.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/notefront/assets/css/smoothproducts.css">

<!-- 以下是自己新增的css -->
<style>
.nav-link.active {
	display: inline-flex;
}

.card-form {
	display: inline-block;
}

#card-container{
    overflow:scroll;
    height:90%;
    width:19rem;
    display: inline-block;
}


</style>

</head>

<body>
	<nav
		class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar"
		style="background: #85867F; color: var(- -red); height: 60px;">
		<div class="container">
			<a class="navbar-brand logo" href="#">font-end</a>
			<button data-toggle="collapse" class="navbar-toggler"
				data-target="#navcol-1">
				<span class="sr-only">Toggle navigation</span><span
					class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navcol-1">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item" style="height: 11.6667px;">
						<div class="nav-item dropdown"
							style="padding: 5px; width: 70.7083px;">
							<a class="dropdown-toggle" aria-expanded="false"
								data-toggle="dropdown" href="#"
								style="padding: -71px; width: 0px; height: -8px; margin: -5px; color: rgba(0, 0, 0, 0.5);">place&nbsp;</a>
							<div class="dropdown-menu"
								style="margin: -5px 0px 0px; padding: 3px 0px; height: 99px; width: 144px;">
								<a class="dropdown-item" href="#">First Item</a><a
									class="dropdown-item" href="#">Second Item</a><a
									class="dropdown-item" href="#">Third Item</a>
							</div>
						</div>
					</li>
					<li class="nav-item">
						<div class="nav-item dropdown"
							style="padding: 5px; width: 70.7083px;">
							<a class="dropdown-toggle" aria-expanded="false"
								data-toggle="dropdown" href="#"
								style="padding: -54px; width: 0px; height: -8px; margin: -1px; color: rgba(0, 0, 0, 0.5);">trip&nbsp;</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">First Item</a><a
									class="dropdown-item" href="#">Second Item</a><a
									class="dropdown-item" href="#">Third Item</a>
							</div>
						</div>
					</li>
					<li class="nav-item">
						<div class="nav-item dropdown"
							style="padding: 5px; width: 70.7083px;">
							<a class="dropdown-toggle" aria-expanded="false"
								data-toggle="dropdown" href="#"
								style="padding: -64px; width: 0px; height: -8px; margin: -3px; color: rgba(0, 0, 0, 0.5);">note</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">First Item</a><a
									class="dropdown-item" href="#">Second Item</a><a
									class="dropdown-item" href="#">Third Item</a>
							</div>
						</div>
					</li>
					<li class="nav-item">
						<div class="nav-item dropdown"
							style="padding: 5px; width: 70.7083px;">
							<a class="dropdown-toggle" aria-expanded="false"
								data-toggle="dropdown" href="#"
								style="padding: -64px; width: 0px; height: -8px; margin: -3px; color: rgba(0, 0, 0, 0.5);">product</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">First Item</a><a
									class="dropdown-item" href="#">Second Item</a><a
									class="dropdown-item" href="#">Third Item</a>
							</div>
						</div>
					</li>
					<li class="nav-item" style="height: 38px;">
						<div class="nav-item dropdown"
							style="padding: 4px; width: 82.7083px; margin: 0px; height: 30px;">
							<a class="dropdown-toggle" aria-expanded="false"
								data-toggle="dropdown" href="#"
								style="padding: 20px; width: 0px; height: -8px; margin: -3px; color: rgba(0, 0, 0, 0.5);">user</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">First Item</a><a
									class="dropdown-item" href="#">Second Item</a><a
									class="dropdown-item" href="#">Third Item</a>
							</div>
						</div>
					</li>
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
				<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/notefront/noteEdit.jsp">新增遊記</a> <a
					class="nav-link active disabled" href="<%=request.getContextPath()%>/front-end/notefront/listMyNote.jsp" aria-disabled="true">我的遊記</a>
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
					<c:forEach var="noteVO" items="${list}" varStatus="loop">
						<c:if test="${sessionScope.users_id == noteVO.users_id}">
							<div class="col-md-6 col-lg-4">
								<div class="card" style="width:350px;height:400px;border:3px #cccccc;">
									<img class="card-img-top w-100 d-block"
										src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCSvc.togetoneNote(noteVO.note_id).note_c_id}"
										style="width: 200px; height: 200px;">
									<div class="card-body">
										<h5 class="card-title"><b>${noteVO.note_title}</b></h5>
										<p class="card-text">${noteVO.note_description}</p>
										<form class="card-form"	action="<%=request.getContextPath()%>/note/note.do"method="post">
											<input class="note_id_value" type="hidden" name="note_id" value="${noteVO.note_id}"> 
											<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>"> 
											<input type="hidden" name="action" value="getOne_For_Display">
											<button class="btn btn-outline-primary btn-sm" type="submit">更多內容</button>
										</form>
										<form class="card-form"	action="<%=request.getContextPath()%>/note/note.do" method="post">
											<input class="note_id_value" type="hidden" name="note_id" value="${noteVO.note_id}">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="getOne_For_Update">
											<button class="btn btn-outline-primary btn-sm" type="submit">編輯遊記</button>
										</form>
										<form class="card-form"	action="<%=request.getContextPath()%>/notec/notec.do" method="post">
											<input class="note_id_value" type="hidden" name="note_c_id" value="${noteCSvc.togetoneNote(noteVO.note_id).note_c_id}">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="action" value="getOne_For_Update">
											<button class="btn btn-outline-primary btn-sm" type="submit">編輯內容</button>
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
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
	<script src="assets/js/smoothproducts.min.js"></script>
	<script src="assets/js/theme.js"></script>
</body>

</html>