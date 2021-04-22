<%@page import="com.users.model.UsersService"%>
<%@page import="com.admins.model.AdminsService"%>
<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	PlaceService placeSvc = new PlaceService();
	UsersService usersSvc = new UsersService();

	List<PlaceVO> list = null;	
	if(request.getAttribute("list") != null){
		list = (List<PlaceVO>)request.getAttribute("list");
	}else{
		list = placeSvc.getAll();
	}

	
// 	List<PlaceVO> list = placeSvc.getAll();
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("placeSvc", placeSvc);  // 用來取代底下的jsp:bean
	pageContext.setAttribute("usersSvc", usersSvc);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Table - Brand</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/fonts/fontawesome5-overrides.min.css">

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">

</head>

<body id="page-top">
    <div id="wrapper">
    	
    	<%@include file="/back-end/pages/side_nav.html" %>
<%--     	<jsp:include page="/back-end/html_component/side_nav.html"></jsp:include> --%>

        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
           		
           		<%@include file="/back-end/pages/top_nav.html" %>
<%--            		<jsp:include page="/back-end/html_component/top_nav.html"></jsp:include> --%>
         	

                <div class="container-fluid">
                    <!-- 這邊開始是主要內容區塊 -->
                    <h3 class="text-dark mb-4">地點管理</h3>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <!-- <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p> -->

                            <ul>

                                <!-- 		<li><a href="/Tivago_Git/place_collect/select_page.jsp">進入地點收藏.</a>  <br> <br></li> -->

                                <!-- <li><a href="listAllPlace.jsp">List</a> all Place. <br> <br></li> -->


<!--                                 <li> -->
<!--                                     <FORM METHOD="post" ACTION="place.do"> -->
<!--                                         <b>輸入地點編號 (如1):</b> <input type="text" name="place_id"> -->
<!--                                         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--                                         <input type="submit" value="送出"> -->
<!--                                     </FORM> -->
<!--                                 </li> -->

<%-- <%--                                 <jsp:useBean id="placeSvc" scope="page" class="com.place.model.PlaceService" /> --%> 

<!--                                 <li> -->
<!--                                     <FORM METHOD="post" ACTION="place.do"> -->
<!--                                         <b>選擇地點編號 :</b> -->
<!--                                         <select size="1" name="place_id"> -->
<%--                                             <c:forEach var="placeVO" items="${placeSvc.all}"> --%>
<%--                                                 <option value="${placeVO.place_id}">${placeVO.place_id} --%>
<%--                                             </c:forEach> --%>
<!--                                         </select> -->
<%--                                         <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!--                                         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--                                         <input type="submit" value="送出"> -->
<!--                                     </FORM> -->
<!--                                 </li> -->

                                <li>
                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do">
                                        <b>選擇地點名稱:</b>
                                        <select size="1" name="place_id">
                                            <c:forEach var="placeVO" items="${placeSvc.all}">
                                                <option value="${placeVO.place_id}">${placeVO.place_name}
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                                        <input type="hidden" name="action" value="getOne_For_Display">
                                        <input type="submit" value="送出">
                                    </FORM>
                                </li>

                                <li>
                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do">
                                        <b>輸入地址跟地點名稱進行模糊查詢 :</b>
                                        <input type="text" name="county" placeholder="請輸入縣市名稱">
                                        <input type="text" name="district" placeholder="請輸入區域名稱">
                                        <input type="text" name="place_name" placeholder="請輸入地點名稱">
                                        <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                                        <input type="hidden" name="action" value="getCard">
                                        <input type="submit" value="送出">
                                    </FORM>
                                </li>
<!--                                 <li><a href="addPlace.jsp">新增</a> 一個新地點</li> -->
                            </ul>

                            <!-- <h3>地點管理</h3> -->
                            <!-- <ul>
                                <li><a href="addPlace.jsp">新增</a> 一個新地點</li>
                            </ul> -->
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <!-- <div class="col-md-6 text-nowrap">
                                    <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable">
                                        <label>Show&nbsp;<select
                                                class="form-control form-control-sm custom-select custom-select-sm">
                                                <option value="10" selected="">10</option>
                                                <option value="25">25</option>
                                                <option value="50">50</option>
                                                <option value="100">100</option>
                                            </select>&nbsp;</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="text-md-right dataTables_filter" id="dataTable_filter"><label><input
                                                type="search" class="form-control form-control-sm"
                                                aria-controls="dataTable" placeholder="Search"></label></div>
                                </div> -->
                            </div>
                            <div class="table-responsive table mt-2" id="dataTable" role="grid"
                                aria-describedby="dataTable_info">
                                <!--                                 動態表格:https://datatables.net/ -->
                                <table class="table my-0" id="table_id">
                                    <thead>
                                        <tr>
                                            <th>地點編號</th>
                                            <th>地點名稱</th>
                                            <!-- <th>地址</th>
                                            <th>經度</th>
                                            <th>緯度</th>
                                            <th>電話</th>
                                            <th>區域</th>
                                            <th>地點類型</th>
                                            <th>地點簡介</th>
                                            <th>圖片1</th>
                                            <th>圖片2</th>
                                            <th>圖片3</th> -->
                                            <th>地點狀態</th>
                                            <th>建立者</th>
                                            <!-- 		<th>營業時間</th> -->
<!--                                             <th>讚數</th> -->
											<th>詳情</th>
<!--                                             <th>修改</th> -->
                                            <th>上架/下架</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="placeVO" items="${list}" >
                                        <tr>
                                            <td>${placeVO.place_id}</td>
											<td>${placeVO.place_name}</td>
											<td class="place_state_text">
												<c:choose>
									            	<c:when test="${placeVO.place_state == 1}">
									                	上架中
									           		</c:when>
									          		<c:otherwise>
									                 	審核中
									            	</c:otherwise>
									        	</c:choose></td>
									        <td>
									        	<c:choose>
									            	<c:when test="${placeVO.users_id == 0}">
									                	管理員
									           		</c:when>
									          		<c:otherwise>
									          			${usersSvc.getOneusers(placeVO.users_id).users_mail}
									            	</c:otherwise>
									        	</c:choose></td>
									        <td>
											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" style="margin-bottom: 0px;">
											     <input type="submit" value="詳情">
											     <input type="hidden" name="place_id"  value="${placeVO.place_id}">
											     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											     <input type="hidden" name="action"	value="getOne_For_Display"></FORM>
											</td>
<!-- 	                                        <td> -->
<%-- 											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" style="margin-bottom: 0px;"> --%>
<!-- 											     <input type="submit" value="修改"> -->
<%-- 											     <input type="hidden" name="place_id"  value="${placeVO.place_id}"> --%>
<%-- 											     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 											     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 											</td> -->
											<td>
											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" style="margin-bottom: 0px;">
											     <c:if test="${placeVO.place_state == 1}">
						                       		 <input class="change_place_state" type="button" value="下架">
						                         </c:if>
						                         <c:if test="${placeVO.place_state == 0}">
						                       		 <input class="change_place_state" type="button" value="上架">
						                         </c:if>
											     
<!-- 											     <input class="change_place_state" type="button" value="上架/下架(未寫好)"> -->
											     <input class="place_id_value" type="hidden" name="place_id"  value="${placeVO.place_id}"></FORM>
											</td>
                                        </tr>
									</c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>地點編號</th>
                                            <th>地點名稱</th>
                                            <!-- <th>地址</th>
                                            <th>經度</th>
                                            <th>緯度</th>
                                            <th>電話</th>
                                            <th>區域</th>
                                            <th>地點類型</th>
                                            <th>地點簡介</th>
                                            <th>圖片1</th>
                                            <th>圖片2</th>
                                            <th>圖片3</th> -->
                                            <th>地點狀態</th>
                                            <th>建立者</th>
                                            <!-- 		<th>營業時間</th> -->
<!--                                             <th>讚數</th> -->
											<th>詳情</th>
<!--                                             <th>修改</th> -->
                                            <th>上架/下架</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="row">
                                <!-- <div class="col-md-6 align-self-center">
                                    <p id="dataTable_info" class="dataTables_info" role="status" aria-live="polite">
                                        Showing 1 to 10 of 27</p>
                                </div>
                                <div class="col-md-6">
                                    <nav
                                        class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                        <ul class="pagination">
                                            <li class="page-item disabled"><a class="page-link" href="#"
                                                    aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span
                                                        aria-hidden="true">»</span></a></li>
                                        </ul>
                                    </nav>
                                </div> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © Brand 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
    <script src="<%=request.getContextPath()%>/back-end/place/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/place/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/place/assets/js/chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/place/assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/place/assets/js/theme.js"></script>

    <script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>
	
	<script src="https://unpkg.com/sweetalert@2.1.2/dist/sweetalert.min.js"></script>
	
    <script>$(document).ready(function () {
            $('#table_id').DataTable();
        });</script>
    
    <script>
    
    	$(".change_place_state").on("click",function(e){
    		let that = $(this);
    		let place_id_value = that.closest("form").find(".place_id_value").attr("value")
    		
		  	let data = {
		  			"action": "change_place_state",
		            "place_id": place_id_value,
		  	}		
    		
			$.ajax({
		        url: "<%=request.getContextPath()%>/place/PlaceAjaxHandler.do",           // 資料請求的網址
		        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		        data: data,               // 傳送資料到指定的 url
		        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		        success: function (data) {      // request 成功取得回應後執行
//console.log(data);
		          if(data.result == "state_off"){
		        	  that.closest("tr").find(".place_state_text").text("審核中")
		        	  that.val("上架")
		          }else if(data.result == "state_on"){
		        	  that.closest("tr").find(".place_state_text").text("上架中")
		        	  that.val("下架")
		          }
		          
		        }
		    });
    	})
    </script>
	
	
<!-- 	這個script用來放錯誤處理訊息 -->
	<script>
		window.addEventListener("load", function(event) {
			<c:if test="${not empty errorMsgs}">
					<c:forEach var="message" items="${errorMsgs}">
// 						alert("${message}");
						swal("操作失敗", "${message}", "error");
					</c:forEach>
			</c:if>
		});
	</script>
	

</body>

</html>