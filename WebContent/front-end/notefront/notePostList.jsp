<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.notec.model.*"%>
<%@ page import="com.note.model.*"%>
<%@page import="com.users.model.*"%>
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />

<%

if(session.getAttribute("usersVO") != null){
	session.setAttribute("users_id" , (  ( (UsersVO)session.getAttribute("usersVO")).getUsers_id() ));
}

    List<NoteVO> list = null;
	if (request.getAttribute("list") != null) {
		list = (List<NoteVO>)request.getAttribute("list");
	} else {
	NoteService noteSvc = new NoteService();
	list = noteSvc.getAll();
	}
	pageContext.setAttribute("list",list);
	
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
<%@ include file="/front-end/pages/headNav.html" %>


			
	<main class="page blog-post-list">
		<section class="clean-block clean-blog-list dark">
			<div class="container">
				<div id="div1" class="block-heading"
					style="background-image:url(&quot;<%=request.getContextPath()%>/front-end/notefront/assets/img/scenery/image1.jpg&quot;);">
					<h2 class="text-info">遊記列表</h2>
					<p class="p1 pb-3">熱門遊記 精選 Top</p>
					<button class="btn btn-outline-primary btn-sm" type="button">
						<a class="a1" href="#togohere">Read More Note</a>
					</button>
<!-- 					<button class="btn btn-outline-primary btn-sm" type="button"> -->
<!-- 						<a class="a1" href="noteEdit.jsp">Create New Note</a> -->
<!-- 					</button> -->
					<!-- Search Widget -->
					
                <div class="city-selector" role="tw-city-selector" data-bootstrap-style data-standard-words></div>
        	
            	<form METHOD="post" ACTION="<%=request.getContextPath()%>/note/note.do">
				  <div class="card my-4">
					<h5 class="card-header">Search</h5>
					  <div class="card-body">
						<div class="input-group">
						  <input type="search" class="form-control" name="note_title" placeholder="Search for...">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
                			<input type="hidden" name="action" value="getMyGo">
							<button class="btn btn-secondary" type="submit">Go!</button>
								</div>
							</div>
						</div>
				</form>		
				</div>

				<div id="togohere" class="block-content">
					<%@ include file="page1.file"%>

					<c:forEach var="noteVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<div class="clean-blog-post">
							<div class="row">
								<div class="col-lg-5">
									<img
										src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCSvc.togetoneNote(noteVO.note_id).note_c_id}"
										style="max-width: 300px; height: 200px;">
								</div>
								<div class="col-lg-7">
									<h3>${noteVO.note_title}</h3>
									<div class="info">
										<span class="text-muted"><fmt:formatDate
												pattern="yyyy-MM-dd" value="${noteVO.note_date}" /> by&nbsp;<a
											href="#">${usersSvc.getOneusers(noteVO.users_id).users_name}</a></span>
									</div>
									<p>${noteVO.note_description.substring(0,20)}</p>
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/note/note.do" style="margin-bottom: 0px;">
									<input type="hidden" name="note_id"  value="${noteVO.note_id}">
									<input type="hidden" name="requestURL"  value="<%=request.getServletPath()%>">
									<input type="hidden" name="action"  value="getOne_For_Display2">
									<button class="btn btn-outline-primary btn-sm" type="submit">Read More</button>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>

					<%-- 					<c:forEach var="noteCVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
					<!-- 						<div class="clean-blog-post"> -->
					<!-- 							<div class="row"> -->
					<!-- 								<div class="col-lg-5"> -->
					<%-- 								  <img src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}" style="max-width: 100%; height:200px;"> --%>
					<!-- 								</div> -->
					<!-- 								<div class="col-lg-7"> -->
					<%-- 									<h3>${noteCVO.note_c_title}</h3> --%>
					<!-- 									<div class="info"> -->
					<!-- 										<span class="text-muted">Jan 16, 2018 by&nbsp;<a -->
					<!-- 											href="#">John Smith</a></span> -->
					<!-- 									</div> -->
					<%-- 									<p>${noteCVO.note_c_content}</p> --%>
					<!-- 									<button class="btn btn-outline-primary btn-sm" type="button">Read -->
					<!-- 										More</button> -->
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<%-- 					</c:forEach> --%>


					<div style="text-align: center;">
						<%@ include file="page2.file"%>
					</div>
					</div>
				</div>
		</section>
	</main>

	<%@ include file="/front-end/pages/footer.html" %>
	
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/smoothproducts.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/theme.js"></script>
	
	<script>
	$(document).ready(function(){
	    $("#div1").mouseover(function(){
	    $("#togohere").slideDown(1000);
	         });
	  
	});
	</script>
</body>

</html>