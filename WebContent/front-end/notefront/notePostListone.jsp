<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.notec.model.*"%>
<%@ page import="com.note.model.*"%>

<%
	NoteService noteSvc = new NoteService();
	List<NoteVO> list = noteSvc.getAll();
	pageContext.setAttribute("list", list);

	NoteCService noteCSvc = new NoteCService();
// 	List<NoteCVO> list = noteCSvc.getAll();
// 	pageContext.setAttribute("list", list);
	pageContext.setAttribute("noteCSvc", noteCSvc);
%>

<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Blog - font-end</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/fonts/simple-line-icons.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/css/smoothproducts.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/css/blog-home.css">
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

			
	<main class="page blog-post-list">
		<section class="clean-block clean-blog-list dark">
			<div class="container">
				<div class="block-heading"
					style="background: rgba(133, 134, 127, 0.15); height: 300px;">
					<h2 class="text-info">遊記列表</h2>
					<p class="p1 pb-3">更新成功</p>
				
					<!-- Search Widget -->
						<div class="card my-4">
							<h5 class="card-header">Search</h5>
							<div class="card-body">
								<div class="input-group">
									<input type="text" class="form-control"
										placeholder="Search for..."> <span
										class="input-group-append">
										<button class="btn btn-secondary" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div>
				</div>

				<div id="togohere" class="block-content">

						<div class="clean-blog-post">
							<div class="row">
								<div class="col-lg-5">
									<img
										src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_id=${noteCSvc.togetoneNote(noteVO.note_id).note_c_id}"
										style="max-width: 100%; height: 200px;">
								</div>
								<div class="col-lg-7">
									<h3>${noteCSvc.togetoneNote(noteVO.note_id).note_title}</h3>
									<div class="info">
										<span class="text-muted"><fmt:formatDate
												pattern="yyyy-MM-dd" value="${noteVO.travel_start}" />by&nbsp;<a
											href="#">${noteVO.users_id}</a></span>
									</div>
									<p>${noteCSvc.togetoneNote(noteVO.note_id).note_description}</p>
									<button class="btn btn-outline-primary btn-sm" type="button">Read
										More</button>
								</div>
							</div>
						</div>

					</div>
				</div>
		</section>
	</main>

	<footer class="page-footer dark" style="background: #575D59;">
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<h5>Get started</h5>
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">Sign up</a></li>
						<li><a href="#">Downloads</a></li>
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
			<p>© 2021 Copyright Text</p>
		</div>
	</footer>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/smoothproducts.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/theme.js"></script>
</body>

</html>