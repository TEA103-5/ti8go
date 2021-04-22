<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admins.model.*"%>
<%@ page import="com.admins.controller.*"%>

<%
if( !((AdminsVO)session.getAttribute("adminsVO")).getAdmins_authority().equals("root")){
System.out.println(((AdminsVO)session.getAttribute("adminsVO")).getAdmins_authority());
System.out.println( !((AdminsVO)session.getAttribute("adminsVO")).getAdmins_authority().equals("root") );
	List<String> errorMsgs = new LinkedList<String>();
	request.setAttribute("errorMsgs", errorMsgs);
	errorMsgs.add("管理者列表只有最高管理員可瀏覽");
	String url = "/back-end/place/listAllPlace.jsp";
//		getServletContext().getRequestDispatcher(url).forward(request, response);
	RequestDispatcher successView = request.getRequestDispatcher(url);
	successView.forward(request, response);
//System.out.println("有進來這邊");
	return;
}

	AdminsService adminsSvc = new AdminsService();
    List<AdminsVO> list = adminsSvc.getAll();
    pageContext.setAttribute("list",list);
    
    pageContext.setAttribute("adminsSvc", adminsSvc);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Table - Brand</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/admins/assets/fonts/fontawesome5-overrides.min.css">

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
                    <h3 class="text-dark mb-4">管理者列表</h3>
                    <div class="card shadow">
                        <div class="card-header py-3">
                            <!-- <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p>
                            <p class="text-primary m-0 font-weight-bold">Employee Info</p> -->

                            <ul>

<!-- 								  <li> -->
<!-- 								    <FORM METHOD="post" ACTION="admins.do" > -->
<!-- 								        <b>輸入管理者編號 (如1):</b> -->
<!-- 								        <input type="text" name="admins_id"> -->
<!-- 								        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 								        <input type="submit" value="送出"> -->
<!-- 								    </FORM> -->
<!-- 								  </li> -->
								
<%-- 								  <jsp:useBean id="adminsSvc" scope="page" class="com.admins.model.AdminsService" /> --%>
								   <li><a href='<%=request.getContextPath()%>/back-end/admins/addAdmins.jsp'>新增</a> 管理員.  <br><br></li>
								   
								  <li>
								     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admins/admins.do" >
								       <b>選擇管理者編號:</b>
								       <select size="1" name="admins_id">
								         <c:forEach var="adminsVO" items="${adminsSvc.all}" > 
								          <option value="${adminsVO.admins_id}">${adminsVO.admins_id}
								         </c:forEach>   
								       </select>
								       <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								       <input type="hidden" name="action" value="getOne_For_Display">
								       <input type="submit" value="送出">
								    </FORM>
								  </li>
								  
								  <li>
								     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admins/admins.do" >
								       <b>選擇管理者姓名:</b>
								       <select size="1" name="admins_id">
								         <c:forEach var="adminsVO" items="${adminsSvc.all}" > 
								          <option value="${adminsVO.admins_id}">${adminsVO.admins_name}
								         </c:forEach>   
								       </select>
								       <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								       <input type="hidden" name="action" value="getOne_For_Display">
								       <input type="submit" value="送出">
								     </FORM>
								  </li>
<!--                                 <li><a href="addPlace.jsp">新增</a> 一個新地點</li> -->
                            </ul>

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
                                            <th>管理者編號</th>
											<th>信箱</th>
											<th>姓名</th>
											<th>權限</th>
											<th>職位</th>
											<th>創建日期</th>
											<th>最後修改日期</th>
											<th>詳情</th>
<!--                                             <th>修改</th> -->
                                            <th>移除</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="adminsVO" items="${list}" >
                                        <tr>
                                            <td>${adminsVO.admins_id}</td>
											<td>${adminsVO.admins_email}</td>
											<td>${adminsVO.admins_name}</td>
											<td><c:choose>
									            	<c:when test="${adminsVO.admins_authority == 'root'}">
									                	最高管理員
									           		</c:when>
									          		<c:otherwise>
									                 	一般管理員
									            	</c:otherwise>
									        	</c:choose></td>
									        <td>${adminsVO.admins_position}</td>
									        <td>${adminsVO.admins_create_time}</td>
											<td>${adminsVO.admins_edit_time}</td>  
									        <td>
											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admins/admins.do" style="margin-bottom: 0px;">
											     <input type="submit" value="詳情">
											     <input type="hidden" name="admins_id"  value="${adminsVO.admins_id}">
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
											  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/admins/admins.do" style="margin-bottom: 0px;">
											     <input type="submit" value="移除管理員">
											     <input type="hidden" name="admins_id"  value="${adminsVO.admins_id}">
											     <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											     <input type="hidden" name="action" value="delete"></FORM>
											</td>
                                        </tr>
									</c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>管理者編號</th>
											<th>信箱</th>
											<th>姓名</th>
											<th>權限</th>
											<th>職位</th>
											<th>創建日期</th>
											<th>最後修改日期</th>
											<th>詳情</th>
<!--                                             <th>修改</th> -->
                                            <th>移除</th>
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
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/chart.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/bs-init.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=request.getContextPath()%>/back-end/admins/assets/js/theme.js"></script>

    <script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.js"></script>

    <script>$(document).ready(function () {
            $('#table_id').DataTable();
        });</script>


</body>

</html>