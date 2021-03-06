<%@page import="java.util.*"%>
<%@page import="com.note.model.*"%>
<%@page import="com.notec.model.*"%>
<%@page import="com.users.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
session.setAttribute("users_id" , (  ( (UsersVO)session.getAttribute("usersVO")).getUsers_id() ));

// session.setAttribute("users_id", 1);  //  測試用

	NoteService noteSvc = new NoteService();
	List<NoteVO> list = noteSvc.getAll();
	pageContext.setAttribute("list", list);
	
	NoteCService noteCSvc = new NoteCService();
// 		List<NoteCVO> list = noteCSvc.getAllNoteC();
// 		pageContext.setAttribute("list", list);
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
.card-title{
overflow:hidden;
white-space: nowrap;
text-overflow: ellipsis;
}

.card-text{
overflow:hidden;
white-space: nowrap;
text-overflow: ellipsis;
}

.clean-block{
/* height:600px; */
background-color: #f8f9fa!important;
}

</style>

</head>

<body>
<%@ include file="/front-end/pages/headNav.html" %>
	
	<main class="page service-page">

		<nav id="search-bar" class="navbar navbar-light bg-light">
			<!-- <a class="navbar-brand">Navbar</a> -->
			<!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
                首个元素放置于起点，末尾元素放置于终点  -->
			<div style="margin-bottom:50px;">
<%-- 				<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/notefront/noteEdit.jsp">新增遊記</a> --%>
 						<div class="nav-item dropdown" style="padding: 5px; width: 70.7083px;">
							<a class="dropdown-toggle" aria-expanded="false"
								data-toggle="dropdown" href="#"
								style="padding: -64px; width: 0px; height: -8px; margin: -3px; color: #007bff; font-size: 1rem;">新增</a>
							<div class="dropdown-menu">
								<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/notefront/noteEdit.jsp">新增遊記</a>
								<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/notefront/notecEdit.jsp">新增內容</a>
							</div>
 						</div>
 				<div  style="margin-top:-38px; padding-left:80px;">	
				<a class="nav-link active disabled" href="<%=request.getContextPath()%>/front-end/notefront/listMyNote.jsp" aria-disabled="true">我的遊記</a>
				<a class="nav-link active" href="<%=request.getContextPath()%>/front-end/notefront/listMyNote_collect.jsp">我的收藏</a>
			    </div>
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
										<span class="card-text"><p>${noteVO.note_description.substring(0,20)}</p></span>
									</div>
									<div class="card-form" style="margin-top:20px">	
										<form class="card-form"	action="<%=request.getContextPath()%>/note/note.do"method="post">
											<input class="note_id_value" type="hidden" name="note_id" value="${noteVO.note_id}"> 
											<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>"> 
											<input type="hidden" name="action" value="getOne_For_Display2">
											<button class="btn btn-outline-primary btn-sm" type="submit">更多內容</button>
										</form>
<%-- 										<form class="card-form"	action="<%=request.getContextPath()%>/note/note.do" method="post"> --%>
<%-- 											<input class="note_id_value" type="hidden" name="note_id" value="${noteVO.note_id}"> --%>
<%-- 											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 											<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 											<button class="btn btn-outline-primary btn-sm" type="submit">編輯遊記</button> -->
<!-- 										</form> -->
<%-- 										<form class="card-form"	action="<%=request.getContextPath()%>/notec/notec.do" method="post"> --%>
<%-- 											<input class="note_id_value" type="hidden" name="note_c_id" value="${noteCSvc.togetoneNote(noteVO.note_id).note_c_id}"> --%>
<%-- 											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 											<input type="hidden" name="action" value="getOne_For_Update"> -->
<%-- 											<input type="hidden" name="note_id" value="${noteVO.note_id}"> --%>
<!-- 											<button class="btn btn-outline-primary btn-sm" type="submit">編輯內容</button> -->
<!-- 										</form> -->
										 <form class="card-form" action="<%=request.getContextPath()%>/note/note.do"method="post">
											<input class="note_id_value" type="hidden" name="note_id" value="${noteVO.note_id}"> 
											<input type="hidden" name="requestURL"value="<%=request.getServletPath()%>"> 
											<input type="hidden" name="action" value="getOne_For_Display">
											<input type="hidden" name="action2" value="UpdateAll">
											<button class="btn btn-outline-primary btn-sm" type="submit">編輯內文</button>
										</form>
										</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
		</section>
	</main>
	<%@ include file="/front-end/pages/footer.html" %>

	<script>
		
	</script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/smoothproducts.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/theme.js"></script>
</body>

</html>