<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.notec.model.*"%>
<%@ page import="com.note.model.*"%>
<%@page import="com.note_collect.model.*"%>

<%
	// 	NoteService noteSvc = new NoteService();
	//     NoteVO getOneNote = noteSvc.getOneNote(note_id)
	// 	pageContext.setAttribute("getOneNote", getOneNote);

	// 	NoteCService noteCSvc = new NoteCService();
	// 	List<NoteCVO> list = noteCSvc.getAll();
	// 	pageContext.setAttribute("list", list);
	session.setAttribute("users_id", 1); //  測試用

	if (session.getAttribute("users_id") != null) {
		// 先把目前登入的users_id放進變數, 並產生一個收藏service , 將service放在pageContext供下面EL使用
		Integer login_users = (Integer) session.getAttribute("users_id");
		Note_collectService note_collectSvc = new Note_collectService();
		pageContext.setAttribute("note_collectSvc", note_collectSvc);

	}

	NoteVO noteVO = (NoteVO) request.getAttribute("noteVO");

	NoteCService noteCSvc = new NoteCService();
	List<NoteCVO> Clist = noteCSvc.getAllNoteC(noteVO.getNote_id());
	pageContext.setAttribute("Clist", Clist);
%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Blog Post - font-end</title>
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

<style type="text/css">
 	.like_btn{ 
 	font-size:50px;
 	width:fit-content;  
 	color:#ccc; 
 	cursor:pointer;
 	} 
	.cs{color:#f00;}

</style>

</head>

<body>
	<nav
		class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar"
		style="background: #85867F; color: var(- -red);">
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
	<main class="page blog-post pd-3">
		<section class="clean-block clean-post dark">
			<div class="container">
				<div class="block-content">
					<div class="post-image"
						style="background-image:url(&quot;<%=request.getContextPath()%>/front-end/notefront/assets/img/scenery/image1.jpg&quot;);"></div>
					<div class="post-body">
					
					<p class="like_btn">&#10084;</p>
						<form class="card-form"
							action="<%=request.getContextPath()%>/note/note.do" method="post"
							style="padding-bottom: 30px;">
							<input class="note_id_value" type="hidden" name="note_id" value="${noteVO.note_id}"> 
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="getOne_For_Display">
							<c:if
								test="${note_collectSvc.getOneNote_collect(noteVO.note_id,users_id) == null}">
								<button type="button" class="collect_btn btn btn-secondary">加入收藏</button>
							</c:if>
							<c:if
								test="${note_collectSvc.getOneNote_collect(noteVO.note_id,users_id) != null}">
								<button type="button" class="collect_btn btn btn-secondary">取消收藏</button>
							</c:if>
						</form>
						<h3>
							<font size="7">${noteVO.note_title}</font>
						</h3>
						<div class="post-info">
							<span>By ${noteVO.users_id}</span><span><fmt:formatDate
									pattern="yyyy-MM-dd" value="${noteVO.note_date}" /></span>
						</div>
						<p>${noteVO.note_description}</p>


						<c:forEach var="noteCVO" items="${Clist}">
							<figure class="figure">
								<img class="rounded img-fluid figure-img"
									src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}"
									style="width: 400px; height: 300px;"
									alt="A generic square placeholder image with rounded corners in a figure.">
								<figcaption class="figure-caption"></figcaption>
							</figure>
							<div class="row">
								<!--                             <div class="col-md-6"> -->
								<!--                             </div> -->
								<div class="col">
									<h> <font size="4"><b>${noteCVO.note_c_title}</b></font></h>
									<p>${noteCVO.note_c_content}</p>
								</div>
								<!--                         </div> -->
								<!--                         <h4>Lorem Ipsum dolor</h4> -->
								<!--                         <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p> -->
								<!--                         <div class="row"> -->
								<!--                             <div class="col"> -->
								<!--                                 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p> -->
								<!--                             </div> -->
								<!--                             <div class="col-md-6"> -->
								<!--                                 <figure class="figure"><img class="rounded img-fluid figure-img" src="assets/img/scenery/image5.jpg" alt="A generic square placeholder image with rounded corners in a figure."> -->
								<!--                                     <figcaption class="figure-caption">Lorem Ipsum dolor</figcaption> -->
								<!--                                 </figure> -->
								<!--                             </div> -->
								<!--                         </div> -->
							</div>
						</c:forEach>
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
	<script
		src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/notefront/assets/js/smoothproducts.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/notefront/assets/js/theme.js"></script>
	<script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>
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
// 					alert("登入後才能加入收藏");
					swal("尚未登入會員!", "請前去登入會員", "error");
					return ;
				}
				let that = $(this);
				
				let note_id = $(this).closest(".post-body").find(".note_id_value").attr("value");
				
			  	let data = {
			  			"action": "ajax_insert_or_delete_NoteCollect",
			            "note_id": note_id,
			            "users_id": login_users
			  	}
				
				$.ajax({
			        url: "<%=request.getContextPath()%>/note_collect/Note_collectAjaxHandler.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: data,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			          console.log(data);
			          if(data.result == "insert_succss"){
// 		        	  // 新增成功則按鈕改成顯示取消收藏
			             that.html("取消收藏");
			        	 swal("加入收藏", "成功加入收藏", "success")
			          }else if(data.result == "delete_success"){
			        	 that.html("加入收藏");
			        	 swal("取消收藏", "成功取消收藏", "success")
			          }
			          
			        }
			    });
			})
	});
		
	</script>

	<script>
// 	<c:if test="${not empty sessionScope.users_id}">
// 	let login_users = ${sessionScope.users_id} ;
// 	</c:if>
// 	<c:if test="${empty sessionScope.users_id}">
// 	let login_users = null ;
// 	</c:if>
    
//     	$(".like_btn").on("click",function(e){
//     		alert('like');
//     		let that = $(this);
    		
//     		let note_id_value = that.closest(".post-body").find(".note_id_value").attr("value")
    		
// 		  	let data = {
// 		  			"action": "update_note_like",
// 		            "note_id": note_id_value

			
            $(function () {            
            $(".like_btn").on("click" , function(e) {
                $(this).toggleClass('cs');                
            })
        })

        

		  			
    		
// 			$.ajax({
<%-- 		        url: "<%=request.getContextPath()%>/note/NoteAjaxHandler.do",           // 資料請求的網址 --%>
// 		        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
// 		        data: data,               // 傳送資料到指定的 url
// 		        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
// 		        success: function (data) {      // request 成功取得回應後執行
// //console.log(data);
// 		          if(data.result == "like_yet"){
// 		        	  that.html("讚")
// 		          }else if(data.result == "like_add"){
// 		        	  that.html("收回")
// 		          }
		          
// 		        }
// 		    });
//     	})
    </script>

</body>

</html>