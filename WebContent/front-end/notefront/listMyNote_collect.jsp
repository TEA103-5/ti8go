<%@page import="java.util.*"%>
<%@page import="com.note.model.*"%>
<%@page import="com.notec.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.note_collect.model.*"%>

<%

// session.setAttribute("users_id", 1);  //  測試用
	Integer login_users = (Integer)session.getAttribute("users_id");

	Note_collectService note_collectSvc = new Note_collectService();
	List<Note_collectVO> list = note_collectSvc.getAllByPrimaryKey(login_users);
	
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("note_collectSvc", note_collectSvc);

%>
<jsp:useBean id="noteSvc" scope="page" class="com.note.model.NoteService" />
<jsp:useBean id="noteCSvc" scope="page" class="com.notec.model.NoteCService" />


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>我的收藏 - Tivago</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/notefront/assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <style>
        .nav-link.active {
            display: inline-flex;

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
        
    </style>

</head>

<body>
<%@ include file="/front-end/pages/headNav.html" %>
<!--     <nav -->
<!-- 		class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar" -->
<!-- 		style="background: #85867F; color: var(- -red); height: 60px;"> -->
<!-- 		<div class="container"> -->
<!-- 			<a class="navbar-brand logo" href="#">font-end</a> -->
<!-- 			<button data-toggle="collapse" class="navbar-toggler" -->
<!-- 				data-target="#navcol-1"> -->
<!-- 				<span class="sr-only">Toggle navigation</span><span -->
<!-- 					class="navbar-toggler-icon"></span> -->
<!-- 			</button> -->
<!-- 			<div class="collapse navbar-collapse" id="navcol-1"> -->
<!-- 				<ul class="navbar-nav ml-auto"> -->
<!-- 					<li class="nav-item" style="height: 11.6667px;"> -->
<!-- 						<div class="nav-item dropdown" -->
<!-- 							style="padding: 5px; width: 70.7083px;"> -->
<!-- 							<a class="dropdown-toggle" aria-expanded="false" -->
<!-- 								data-toggle="dropdown" href="#" -->
<!-- 								style="padding: -71px; width: 0px; height: -8px; margin: -5px; color: rgba(0, 0, 0, 0.5);">place&nbsp;</a> -->
<!-- 							<div class="dropdown-menu" -->
<!-- 								style="margin: -5px 0px 0px; padding: 3px 0px; height: 99px; width: 144px;"> -->
<!-- 								<a class="dropdown-item" href="#">First Item</a><a -->
<!-- 									class="dropdown-item" href="#">Second Item</a><a -->
<!-- 									class="dropdown-item" href="#">Third Item</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 					<li class="nav-item"> -->
<!-- 						<div class="nav-item dropdown" -->
<!-- 							style="padding: 5px; width: 70.7083px;"> -->
<!-- 							<a class="dropdown-toggle" aria-expanded="false" -->
<!-- 								data-toggle="dropdown" href="#" -->
<!-- 								style="padding: -54px; width: 0px; height: -8px; margin: -1px; color: rgba(0, 0, 0, 0.5);">trip&nbsp;</a> -->
<!-- 							<div class="dropdown-menu"> -->
<!-- 								<a class="dropdown-item" href="#">First Item</a><a -->
<!-- 									class="dropdown-item" href="#">Second Item</a><a -->
<!-- 									class="dropdown-item" href="#">Third Item</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 					<li class="nav-item"> -->
<!-- 						<div class="nav-item dropdown" -->
<!-- 							style="padding: 5px; width: 70.7083px;"> -->
<!-- 							<a class="dropdown-toggle" aria-expanded="false" -->
<!-- 								data-toggle="dropdown" href="#" -->
<!-- 								style="padding: -64px; width: 0px; height: -8px; margin: -3px; color: rgba(0, 0, 0, 0.5);">note</a> -->
<!-- 							<div class="dropdown-menu"> -->
<!-- 								<a class="dropdown-item" href="#">First Item</a><a -->
<!-- 									class="dropdown-item" href="#">Second Item</a><a -->
<!-- 									class="dropdown-item" href="#">Third Item</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 					<li class="nav-item"> -->
<!-- 						<div class="nav-item dropdown" -->
<!-- 							style="padding: 5px; width: 70.7083px;"> -->
<!-- 							<a class="dropdown-toggle" aria-expanded="false" -->
<!-- 								data-toggle="dropdown" href="#" -->
<!-- 								style="padding: -64px; width: 0px; height: -8px; margin: -3px; color: rgba(0, 0, 0, 0.5);">product</a> -->
<!-- 							<div class="dropdown-menu"> -->
<!-- 								<a class="dropdown-item" href="#">First Item</a><a -->
<!-- 									class="dropdown-item" href="#">Second Item</a><a -->
<!-- 									class="dropdown-item" href="#">Third Item</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 					<li class="nav-item" style="height: 38px;"> -->
<!-- 						<div class="nav-item dropdown" -->
<!-- 							style="padding: 4px; width: 82.7083px; margin: 0px; height: 30px;"> -->
<!-- 							<a class="dropdown-toggle" aria-expanded="false" -->
<!-- 								data-toggle="dropdown" href="#" -->
<!-- 								style="padding: 20px; width: 0px; height: -8px; margin: -3px; color: rgba(0, 0, 0, 0.5);">user</a> -->
<!-- 							<div class="dropdown-menu"> -->
<!-- 								<a class="dropdown-item" href="#">First Item</a><a -->
<!-- 									class="dropdown-item" href="#">Second Item</a><a -->
<!-- 									class="dropdown-item" href="#">Third Item</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</nav> -->
    <main class="page service-page">

        <nav id="search-bar" class="navbar navbar-light bg-light">
            <!-- <a class="navbar-brand">Navbar</a> -->
            <!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
                首个元素放置于起点，末尾元素放置于终点  -->
            <div style="margin-bottom:50px;">
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
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/notefront/listMyNote.jsp">我的遊記</a>
                <a class="nav-link active disabled" href="<%=request.getContextPath()%>/front-end/notefront/listMyNote_collect.jsp" aria-disabled="true">我的收藏</a>
            	</div>
            </div>

<!--             <nav aria-label="breadcrumb"> -->
<!--                 <ol class="breadcrumb"> -->
<!--                     <li class="breadcrumb-item" ><a href="#">地點收藏</a></li> -->
<!--                     <li class="breadcrumb-item"><a href="#">行程收藏</a></li> -->
<!--                     <li class="breadcrumb-item active"><aria-current="page">遊記收藏</li> -->
<!--                     <li class="breadcrumb-item"><a href="#">商品收藏</a></li> -->
<!--                 </ol> -->
<!--             </nav> -->
        </nav>


        <section class="clean-block clean-services dark">
            <div class="container">
                <div class="row">
                    <c:forEach var="note_collectVO" items="${list}"  varStatus="loop">
	                    <div class="col-md-6 col-lg-4">
	                        <div class="card" style="width:350px;height:400px;border:3px #cccccc;">
	                        <img class="card-img-top w-100 d-block" src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCSvc.togetoneNote(note_collectVO.note_id).note_c_id}" 
	                        style="width: 200px; height: 200px;">
	                            <div class="card-body">
	                                <h5 class="card-title">${noteSvc.getOneNote(note_collectVO.note_id).note_title}</h5>
	                                <p class="card-text">${noteSvc.getOneNote(note_collectVO.note_id).note_description.substring(0,15)}</p>
		                            <form class="card-form" action="<%=request.getContextPath()%>/note/note.do" method="post">
				                    	<input class="note_id_value" type="hidden" name="note_id" value="${(note_collectVO.note_id)}"> 
				                    	<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
				                    	<input type="hidden" name="action" value="getOne_For_Display">
				                        <button class="btn btn-outline-primary btn-sm" type="submit">更多內容</button>
		                           		 <c:if test="${note_collectSvc.getOneNote_collect(note_collectVO.note_id,sessionScope.users_id) == null}">
				                       		<button class="collect_btn btn btn-outline-primary btn-sm" type="button">加入收藏</button>
				                        </c:if>
				                        <c:if test="${note_collectSvc.getOneNote_collect(note_collectVO.note_id,sessionScope.users_id) != null}">
				                       		<button class="collect_btn btn btn-outline-primary btn-sm" type="button">取消收藏</button>
				                        </c:if>
				                        
				                    </form>
	                            </div>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </main>
<!--     <footer class="page-footer dark"> -->
<!--         <div class="container"> -->
<!--             <div class="row"> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>Get started</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">Home</a></li> -->
<!--                         <li><a href="#">註冊</a></li> -->
<!--                         <li><a href="#">登入</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>About us</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">Company Information</a></li> -->
<!--                         <li><a href="#">Contact us</a></li> -->
<!--                         <li><a href="#">Reviews</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>Support</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">FAQ</a></li> -->
<!--                         <li><a href="#">Help desk</a></li> -->
<!--                         <li><a href="#">Forums</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--                 <div class="col-sm-3"> -->
<!--                     <h5>Legal</h5> -->
<!--                     <ul> -->
<!--                         <li><a href="#">Terms of Service</a></li> -->
<!--                         <li><a href="#">Terms of Use</a></li> -->
<!--                         <li><a href="#">Privacy Policy</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="footer-copyright"> -->
<!--             <p>© 2021 Tivago</p> -->
<!--         </div> -->
<!--     </footer> -->
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/notefront/assets/js/theme.js"></script>
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
					alert("登入後才能加入收藏");
					return ;
				}
				let that = $(this);
				
				// parents是會一直往上找,可能回傳一個陣列 , parent只會找到父元素且只找第一個
				let note_id = that.parents(".card-body").find(".note_id_value").attr("value");
				
			  	let data = {
			  			"action": "ajax_insert_or_delete_NoteCollect",
			            "note_id": note_id,
			            "users_id": login_users
			  	}		
			  	console.log(data)
	// console.log($(this).parent(".card-body").find(".place_id_value").attr("value"));
	// console.log($(this).parent(".card-body").find(".users_id_value").attr("value"));
				
				$.ajax({
			        url: "<%=request.getContextPath()%>/note_collect/Note_collectAjaxHandler.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: data,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			          console.log(data);
			          if(data.result == "insert_succss"){
	// 		        	  // 新增成功則按鈕改成顯示取消收藏
			        	  that.html("取消收藏")
			        	  swal("加入收藏", "成功加入收藏", "success")
			          }else if(data.result == "delete_success"){
			        	  // 在此頁刪除成功則從畫面上移除
			        	  that.closest(".col-md-6.col-lg-4").fadeOut(300, function(){ $(this).remove();});
			        	  that.html("加入收藏")
			        	  swal("取消收藏", "成功取消收藏", "success")
			        	  

			          }
			          
			        }
			    });
			})
	});
		
	</script>
</body>

</html>