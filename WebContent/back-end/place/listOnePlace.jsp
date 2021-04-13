<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@page import="com.place_collect.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<% 
	PlaceVO placeVO = (PlaceVO)request.getAttribute("placeVO");
// System.out.println( "listOne");	
// System.out.println( (placeVO == null));	
	// 以下將place_address字串做處理以便設定地址選單套件的選項
	String address = placeVO.getPlace_address(); // 
// System.out.println("address=" + address);	
	Integer index = null;
	String county = null;
	if( ( index = address.indexOf("市")) != -1  || ( index = address.indexOf("縣")) != -1){
		county = address.substring(0 , index+1).replaceAll("\\d+", "").trim(); // 取出縣市之前的字串 , 去掉數字以及空格
	}
// System.out.println("county=" +  county);
	String district = placeVO.getPlace_region();
	pageContext.setAttribute("district" , district);
// System.out.println("district=" +  district);
// String handle_address = address.split(district)[1];
// System.out.println("handle_address" +  handle_address);
	pageContext.setAttribute("county" , county);
	
	pageContext.setAttribute("placeVO" , placeVO);
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Blank Page - Brand</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/place/assets/fonts/fontawesome5-overrides.min.css">
    
        <!-- 以下是自己新增的css -->
    <style>


        /* 在所有class="xrequired"的label後面加上紅色星號 */
        label.xrequired:after {
            content: '* ';
            color: red;
        }
    </style>
</head>

<body id="page-top">
    <div id="wrapper">
        <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
            <div class="container-fluid d-flex flex-column p-0"><a
                    class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                    <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
                    <div class="sidebar-brand-text mx-3"><span>Brand</span></div>
                </a>
                <hr class="sidebar-divider my-0">
                <ul class="navbar-nav text-light" id="accordionSidebar">
                    <li class="nav-item"><a class="nav-link" href="index.html"><i
                                class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="profile.html"><i
                                class="fas fa-user"></i><span>Profile</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="table.html"><i
                                class="fas fa-table"></i><span>Table</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="login.html"><i
                                class="far fa-user-circle"></i><span>Login</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="register.html"><i
                                class="fas fa-user-circle"></i><span>Register</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="forgot-password.html"><i
                                class="fas fa-key"></i><span>Forgotten Password</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="404.html"><i
                                class="fas fa-exclamation-circle"></i><span>Page Not Found</span></a></li>
                    <li class="nav-item"><a class="nav-link active" href="blank.html"><i
                                class="fas fa-window-maximize"></i><span>Blank Page</span></a></li>
                </ul>
                <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0"
                        id="sidebarToggle" type="button"></button></div>
            </div>
        </nav>
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3"
                            id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <form
                            class="form-inline d-none d-sm-inline-block mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                            <div class="input-group"><input class="bg-light form-control border-0 small" type="text"
                                    placeholder="Search for ...">
                                <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i
                                            class="fas fa-search"></i></button></div>
                            </div>
                        </form>
                        <ul class="navbar-nav flex-nowrap ml-auto">
                            <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link"
                                    aria-expanded="false" data-toggle="dropdown" href="#"><i
                                        class="fas fa-search"></i></a>
                                <div class="dropdown-menu dropdown-menu-right p-3 animated--grow-in"
                                    aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto navbar-search w-100">
                                        <div class="input-group"><input class="bg-light form-control border-0 small"
                                                type="text" placeholder="Search for ...">
                                            <div class="input-group-append"><button class="btn btn-primary py-0"
                                                    type="button"><i class="fas fa-search"></i></button></div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link"
                                        aria-expanded="false" data-toggle="dropdown" href="#"><span
                                            class="badge badge-danger badge-counter">3+</span><i
                                            class="fas fa-bell fa-fw"></i></a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-list animated--grow-in">
                                        <h6 class="dropdown-header">alerts center</h6><a
                                            class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="mr-3">
                                                <div class="bg-primary icon-circle"><i
                                                        class="fas fa-file-alt text-white"></i></div>
                                            </div>
                                            <div><span class="small text-gray-500">December 12, 2019</span>
                                                <p>A new monthly report is ready to download!</p>
                                            </div>
                                        </a><a class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="mr-3">
                                                <div class="bg-success icon-circle"><i
                                                        class="fas fa-donate text-white"></i></div>
                                            </div>
                                            <div><span class="small text-gray-500">December 7, 2019</span>
                                                <p>$290.29 has been deposited into your account!</p>
                                            </div>
                                        </a><a class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="mr-3">
                                                <div class="bg-warning icon-circle"><i
                                                        class="fas fa-exclamation-triangle text-white"></i></div>
                                            </div>
                                            <div><span class="small text-gray-500">December 2, 2019</span>
                                                <p>Spending Alert: We've noticed unusually high spending for your
                                                    account.</p>
                                            </div>
                                        </a><a class="dropdown-item text-center small text-gray-500" href="#">Show All
                                            Alerts</a>
                                    </div>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link"
                                        aria-expanded="false" data-toggle="dropdown" href="#"><span
                                            class="badge badge-danger badge-counter">7</span><i
                                            class="fas fa-envelope fa-fw"></i></a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-list animated--grow-in">
                                        <h6 class="dropdown-header">alerts center</h6><a
                                            class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle"
                                                    src="assets/img/avatars/avatar4.jpeg">
                                                <div class="bg-success status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>Hi there! I am wondering if you can
                                                        help me with a problem I've been having.</span></div>
                                                <p class="small text-gray-500 mb-0">Emily Fowler - 58m</p>
                                            </div>
                                        </a><a class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle"
                                                    src="assets/img/avatars/avatar2.jpeg">
                                                <div class="status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>I have the photos that you ordered last
                                                        month!</span></div>
                                                <p class="small text-gray-500 mb-0">Jae Chun - 1d</p>
                                            </div>
                                        </a><a class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle"
                                                    src="assets/img/avatars/avatar3.jpeg">
                                                <div class="bg-warning status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>Last month's report looks great, I am
                                                        very happy with the progress so far, keep up the good
                                                        work!</span></div>
                                                <p class="small text-gray-500 mb-0">Morgan Alvarez - 2d</p>
                                            </div>
                                        </a><a class="dropdown-item d-flex align-items-center" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle"
                                                    src="assets/img/avatars/avatar5.jpeg">
                                                <div class="bg-success status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>Am I a good boy? The reason I ask is
                                                        because someone told me that people say this to all dogs, even
                                                        if they aren't good...</span></div>
                                                <p class="small text-gray-500 mb-0">Chicken the Dog · 2w</p>
                                            </div>
                                        </a><a class="dropdown-item text-center small text-gray-500" href="#">Show All
                                            Alerts</a>
                                    </div>
                                </div>
                                <div class="shadow dropdown-list dropdown-menu dropdown-menu-right"
                                    aria-labelledby="alertsDropdown"></div>
                            </li>
                            <div class="d-none d-sm-block topbar-divider"></div>
                            <li class="nav-item dropdown no-arrow">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link"
                                        aria-expanded="false" data-toggle="dropdown" href="#"><span
                                            class="d-none d-lg-inline mr-2 text-gray-600 small">Valerie Luna</span><img
                                            class="border rounded-circle img-profile"
                                            src="assets/img/avatars/avatar1.jpeg"></a>
                                    <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in"><a
                                            class="dropdown-item" href="#"><i
                                                class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a><a
                                            class="dropdown-item" href="#"><i
                                                class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Settings</a><a
                                            class="dropdown-item" href="#"><i
                                                class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Activity
                                            log</a>
                                        <div class="dropdown-divider"></div><a class="dropdown-item" href="#"><i
                                                class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="container-fluid">
                    <h3 class="text-dark mb-1">Blank Page</h3>
	                     <!-- 使用Google Maps Embed API , q放的是搜尋目標 , 如有明確對象(地址或名稱)marker會標註在此位置 ,  -->
	                <div class="block-heading"><iframe id="map-iframe" allowfullscreen="" frameborder="0"
	                        src="https://www.google.com/maps/embed/v1/search?key=AIzaSyCQspd49Wmywh3L5LAOftK_jV4qA2i89VQ&q=<%= (placeVO==null)? "" : placeVO.getPlace_address()%>&zoom=20&center=${placeVO.getPlace_latitude()},${placeVO.getPlace_longitude()}"
	                        width="100%" height="400"></iframe>
	                    <h2 class="text-info">修改地點</h2>
	                    <p> </p>
	                </div>
	
	                <!-- 表單驗證參考這一項 利用加上特定class來標註錯誤或正確訊息 https://getbootstrap.com/docs/4.6/components/forms/#server-side -->
<!-- 	                地點編號這邊純顯示, 值由底下的input=hidden 發送 -->
	                <form METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" name="form1" enctype="multipart/form-data"> 
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer00">地點編號</label>
	                            <input type="text" class="form-control " id="validationServer00"  value="<%= (placeVO==null)? "" : placeVO.getPlace_id()%>"   >
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired" for="validationServer01">地點名稱</label>
	                            <input type="text" class="form-control " id="validationServer01" name="place_name" value="<%= (placeVO==null)? "" : placeVO.getPlace_name()%> "
	                                required >
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                地點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間
	                            </div>
	                        </div>
	                    </div>
	
	                    <!-- class="city-selector" 在後面加上is-valid一樣有效 -->
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label class="xrequired">縣市/區域</label>
	                            <div class="city-selector" role="tw-city-selector" data-bootstrap-style data-standard-words>
	                            </div>
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div class="invalid-feedback">
	                                請選擇縣市以及區域
	                            </div>
	                        </div>
	
	                    </div>
	
	
	                    <div class="form-row">
	
	                        <div class="col-md-12 mb-3">
	
	                            <label class="xrequired" for="validationServer02">地址</label>
	                            <input type="text" class="form-control " id="validationServer02" name="place_address"
	                                value="<%= (placeVO==null)? "" : placeVO.getPlace_address().split(district)[1]%>" required>
	<!--                                 	用place_region把地址的地區之後擷取顯示出來 -->
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                地址:只能是中文字母、數字,且長度必需在5到50之間
	                            </div>
	                        </div>
	                    </div>
	
	                    <div class="form-row">
	                        <div class="col-md-6 mb-3">
	                            <label class="xrequired" for="validationServer03">經度</label>
	                            <fmt:formatNumber value="${placeVO.place_longitude}" maxFractionDigits="6" var="format_longitude" />
	                            <input type="text" class="form-control" id="validationServer03" name="place_longitude"
	                                value="${format_longitude}" required readonly>
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                不是有效的經緯度,地址不正確
	                            </div>
	                        </div>
	                        <div class="col-md-6 mb-3">
	                            <label class="xrequired" for="validationServer04">緯度</label>
	                            <fmt:formatNumber value="${placeVO.place_latitude}" maxFractionDigits="6" var="format_latitude" />
	                            <input type="text" class="form-control" id="validationServer04" name="place_latitude"
	                                value="${format_latitude}" required readonly>
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                不是有效的經緯度,地址不正確
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-row">
	                        <div class="col-md-6 mb-3">
	                            <label for="validationServer05">電話</label>
	                            <input type="text" class="form-control" id="validationServer05" name="place_tel" value="<%= (placeVO==null)? "" : placeVO.getPlace_tel()%>">
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                電話只能輸入數字或-
	                            </div>
	                        </div>
	                        <div class="col-md-6 mb-3">
	                            <label for="validationServer06">地點類型</label>
	                            <input type="text" class="form-control" id="validationServer06" name="place_type" value="<%= (placeVO==null)? "" : placeVO.getPlace_type()%>">
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                地點類型只能輸入中文,且長度必需在1到50之間
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer07">地點簡介</label>
	                            <textarea class="form-control " id="validationServer07" name="place_index" rows="6"
	                                placeholder=""><%= (placeVO==null)? "" : placeVO.getPlace_index()%></textarea>
	                            <div class="valid-feedback">
	                                驗證通過
	                            </div>
	                            <div id="validationServer01Feedback" class="invalid-feedback">
	                                地點簡介: 只能是中、英文字母、數字和_ , 且長度必需在2到200之間
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer08">圖片1</label>
	                            <input type="file" class="form-control-file " id="validationServer08" name="place_pic1">
								<img id="pic1" width="300" height="225" src="DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}">
	                        </div>
	                    </div>
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer09">圖片2</label>
	                            <input type="file" class="form-control-file " id="validationServer09" name="place_pic2">
	                        	<img id="pic2" width="300" height="225" src="DBGifReader4.do?place_pic=place_pic2&place_id=${placeVO.place_id}">
	                        </div>
	                    </div>
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                            <label for="validationServer10">圖片3</label>
	                            <input type="file" class="form-control-file " id="validationServer10" name="place_pic3">
	                            <img id="pic3" width="300" height="225" src="DBGifReader4.do?place_pic=place_pic3&place_id=${placeVO.place_id}">
	                        </div>
	                    </div>
	                    <div class="form-row">
	                        <div class="col-md-12 mb-3">
	                        	<label for="validationServer11">地點狀態</label>
		                        <select class="form-control" id="validationServer11" name="place_state">
								  <c:choose>
						            	<c:when test="${placeVO.place_state == 1}">
						                	<option selected="selected" value="1">上架中
											<option value="0">審核中
						           		</c:when>
						          		<c:otherwise>
						                 	<option value="1">上架中
											<option selected="selected" value="0">審核中
						            	</c:otherwise>
						          </c:choose>
								</select>
	                        </div>
	                    </div>
	                    
	                    <div class="form-row">
	                    <!-- 	                建立者這邊純顯示, 值由底下的input=hidden 發送 -->
<!-- 	                        <div class="col-md-12 mb-3"> -->
<!-- 	                            <label  for="validationServer12">建立者</label> -->
<%-- 	                            <input type="text" class="form-control " id="validationServer12" name="" value="<%= (placeVO==null)? "" : placeVO.getUsers_id()%>" > --%>
<!-- 	                        </div> -->
	                    </div>
	                    
			        	
			        	
	                    <!-- <div class="form-row">
	                        <div class="col-md-6 mb-3">
	                            <label for="validationServer03">City</label>
	                            <input type="text" class="form-control is-invalid" id="validationServer03"
	                                aria-describedby="validationServer03Feedback" required>
	                            <div id="validationServer03Feedback" class="invalid-feedback">
	                                Please provide a valid city.
	                            </div>
	                        </div>
	                        <div class="col-md-3 mb-3">
	                            <label for="validationServer04">State</label>
	                            <select class="custom-select is-invalid" id="validationServer04"
	                                aria-describedby="validationServer04Feedback" required>
	                                <option selected disabled value="">Choose...</option>
	                                <option>...</option>
	                            </select>
	                            <div id="validationServer04Feedback" class="invalid-feedback">
	                                Please select a valid state.
	                            </div>
	                        </div>
	                        <div class="col-md-3 mb-3">
	                            <label for="validationServer05">Zip</label>
	                            <input type="text" class="form-control is-invalid" id="validationServer05"
	                                aria-describedby="validationServer05Feedback" required>
	                            <div id="validationServer05Feedback" class="invalid-feedback">
	                                Please provide a valid zip.
	                            </div>
	                        </div>
	                    </div> -->
	<!--                     <div class="form-group"> -->
	<!--                         <div class="form-check"> -->
	<!--                             <input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3" -->
	<!--                                 aria-describedby="invalidCheck3Feedback" required>  -->
	<!--                             <label class="form-check-label" for="invalidCheck3"> -->
	<!--                                 Agree to terms and conditions -->
	<!--                             </label> -->
	<!--                             <div id="invalidCheck3Feedback" class="invalid-feedback"> -->
	<!--                                 You must agree before submitting. -->
	<!--                             </div> -->
	<!--                         </div> -->
	<!--                     </div> -->
	                    <input type="hidden" name="users_id" value="${placeVO.getUsers_id()}">
						<!-- 	                    在管理者頁面做修改時不會更動地點的建立者 -->
	                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	                    <input type="hidden" name="action" value="update">
	                    <!-- <button id="getGeo_button" type="button" class="btn btn-secondary">取得經緯度及定位</button> -->
	                    <input type="hidden" name="place_id" value="<%=placeVO.getPlace_id()%>">
	                    <button type="submit" id="submit_button" class="btn btn-secondary">送出修改</button>
	                    <button type="button" id="update_button" class="btn btn-secondary">開始修改</button>
	                </form>
	                <!-- 表單結束 -->
                    
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
    
     <!-- 以下是自己新增的js -->
    <script src="<%=request.getContextPath()%>/front-end/place/tw-city-selector-master/dist/tw-city-selector.js"></script> <!-- // 載入的地址選單套件 -->
<!--     			匯入變數google_api_key   -->
<%--     <script src="<%=request.getContextPath()%>/front-end/place/google_key/google_key.js"></script> --%>
    <script>
	    // 這行如果放在下一個script內 , 會導致地址選單還沒初始化就執行了跟地址選單有關的綁定而沒綁定到
	    let tcs;
	    function init() {
	        tcs = new TwCitySelector({
	            el: '.city-selector',
	            bootstrapStyle: true,
	            standardWords: true,
// 	            countyValue: 新北市
	            countyValue: "${county}", // 此處需用正體字的臺 , 這一項可以設定預設的縣市 , 用EL指定值
	            districtValue: "${district}" // 用EL指定值
	        });
	    }
	    init();

    </script>
	<script>
	
// 	這個script負責上傳圖片的預覽
	
		// 	為何變數跟id同名時, 沒有getElementById , 監聽事件就可以直接使用元素id ???

		let pic1_el = document.getElementById("pic1");
		let p_file1_el = document.getElementById("validationServer08");

		let pic2_el = document.getElementById("pic2");
		let p_file2_el = document.getElementById("validationServer09");

		let pic3_el = document.getElementById("pic3");
		let p_file3_el = document.getElementById("validationServer10");

		// 先將圖片修改前內容存起來
		let pic1_origin = pic1_el.getAttribute("src");
		let pic2_origin = pic2_el.getAttribute("src");
		let pic3_origin = pic3_el.getAttribute("src");

		// target是代表對應的圖片標籤節點
		var preview_img = function(file, target) {
			var reader = new FileReader(); // 用來讀取檔案
			reader.addEventListener("load", function() {
				target.setAttribute("src", reader.result);
			});
			reader.readAsDataURL(file); // 讀取檔案
		};

		p_file1_el.addEventListener("change", function(e) {

			// 可接受的附檔名 , 如上傳例外檔案類型 , 則不讀取 , 預覽圖仍保持原樣
			var validExts = new Array(".jpg", ".png");

			var fileExt = this.value;
			fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
			if (validExts.indexOf(fileExt) < 0) {
				alert("檔案類型錯誤，可接受的副檔名有：" + validExts.toString());
				this.value = null;
				// 將修改前的圖片內容讀回去
				pic1_el.setAttribute("src", pic1_origin);
				return;
			}

			if (this.files.length > 0) {
				preview_img(this.files[0], pic1_el);
			} else {
			}
		});

		p_file2_el.addEventListener("change", function(e) {

			// 可接受的附檔名 , 如上傳例外檔案類型 , 則不讀取 , 預覽圖仍保持原樣
			var validExts = new Array(".jpg", ".png");

			var fileExt = this.value;
			fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
			if (validExts.indexOf(fileExt) < 0) {
				alert("檔案類型錯誤，可接受的副檔名有：" + validExts.toString());
				this.value = null;
				// 將修改前的圖片內容讀回去
				pic2_el.setAttribute("src", pic2_origin);
				return;
			}

			if (this.files.length > 0) {
				preview_img(this.files[0], pic2_el);
			} else {
			}
		});

		p_file3_el.addEventListener("change", function(e) {

			// 可接受的附檔名 , 如上傳例外檔案類型 , 則不讀取 , 預覽圖仍保持原樣
			var validExts = new Array(".jpg", ".png");

			var fileExt = this.value;
			fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
			if (validExts.indexOf(fileExt) < 0) {
				alert("檔案類型錯誤，可接受的副檔名有：" + validExts.toString());
				this.value = null;
				// 將修改前的圖片內容讀回去
				pic3_el.setAttribute("src", pic3_origin);
				return;
			}

			if (this.files.length > 0) {
				preview_img(this.files[0], pic3_el);
			} else {
			}
		});
	</script>
	<script>

//         const google_api_key = "AIzaSyAjDUYEdEloKExVbhYLVsCg4EqL0KQLSDs";

        $(function () {



            // 因為VO沒有對應欄位,所以錯誤處理時從sessionStorage讀回(還需要處理如何正確選回原本的option)
            // $('.city-selector').find(":selected").eq(0).val(sessionStorage.county)
            // $('.city-selector').find(":selected").eq(1).val(sessionStorage.district)

            // <input> class有is-valid 就會顯示標籤div class="valid-feedback"內設定的文字 ,is-invalid則顯示class=invalid-feedback的內容
            let el_place_name = $("#validationServer01");
            const place_nameReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$/; // 只能是中、英文字母、數字和_ , 且長度必需在2到10之間
            let el_place_address = $("#validationServer02")
            const place_addressReg = /^[(\u4e00-\u9fa5)(0-9)]{5,50}$/; // 只能是中文字母、數字,且長度必需在5到50之間

            let el_place_longitude = $("#validationServer03");
            let el_place_latitude = $("#validationServer04");

            let el_place_tel = $("#validationServer05");
            const place_telReg = /^[(0-9-)]{10,20}$/;

            let el_place_type = $("#validationServer06");
            const place_typeReg = /^[(\u4e00-\u9fa5)]{1,50}$/;

            let el_place_index = $("#validationServer07");
            const place_indexReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,200}$/; // 只能是中、英文字母、數字和_ , 且長度必需在2到200之間

            let el_form = $("#insert_form");
			
            
//             這邊將所有欄位先變成不可輸入,並定義開始修改按鈕事件(經度跟緯度原本就有設定readonly , 所以移除全部的元素的disabled時, 經緯度仍有readonly而不能修改)
				$(".form-control").attr("disabled" , "disabled")
				$("#validationServer08").attr("disabled" , "disabled")
				$("#validationServer09").attr("disabled" , "disabled")
				$("#validationServer10").attr("disabled" , "disabled")
				$("#submit_button").attr("disabled" , "disabled")
				
				$("#update_button").on("click", function(e){
					$("*").removeAttr("disabled") ;
				})
				
				
// 			$(".form-control").removeAttr("disabled");
            

            el_form.on("submit", function (e) {
                // alert("777")
                if ($(".is-invalid").length != 0) {
                    return false;
                } else {
//                     let select_group = $('.city-selector').find(":selected");
//                     let county = select_group.eq(0).text();
//                     let district = select_group.eq(1).text();
//                     let address = el_place_address.val();
//                     el_place_address.val(county + district + address);


                }



            })


            el_place_index.on("input", function (e) {

                let val_place_index = el_place_index.val();

                // 電話可以不輸入, 所以空字串時將錯誤驗證跟正確驗證都移除
                if (val_place_index == "") {
                    el_place_index.removeClass("is-invalid");
                    el_place_index.removeClass("is-valid");
                    return;
                } else if (place_indexReg.test(val_place_index)) {

                    el_place_index.removeClass("is-invalid");
                    el_place_index.addClass("is-valid");
                } else {

                    el_place_index.removeClass("is-valid");
                    el_place_index.addClass("is-invalid");
                }


            })

            el_place_type.on("input", function (e) {

                let val_place_type = el_place_type.val();

                // 電話可以不輸入, 所以空字串時將錯誤驗證跟正確驗證都移除
                if (val_place_type == "") {
                    el_place_type.removeClass("is-invalid");
                    el_place_type.removeClass("is-valid");
                    return;
                } else if (place_typeReg.test(val_place_type)) {
                    // console.log("驗證通過")
                    // console.log(val_place_type);
                    el_place_type.removeClass("is-invalid");
                    el_place_type.addClass("is-valid");
                } else {
                    // console.log("驗證不通過")
                    // console.log(val_place_type);
                    el_place_type.removeClass("is-valid");
                    el_place_type.addClass("is-invalid");
                }


            })

            el_place_tel.on("input", function (e) {

                let val_place_tel = el_place_tel.val();

                // 電話可以不輸入, 所以空字串時將錯誤驗證移除
                if (val_place_tel == "") {
                    el_place_tel.removeClass("is-invalid");
                    el_place_tel.removeClass("is-valid");
                    return;
                } else if (place_telReg.test(val_place_tel)) {
                    console.log("驗證通過")
                    el_place_tel.removeClass("is-invalid");
                    el_place_tel.addClass("is-valid");
                } else {
                    el_place_tel.removeClass("is-valid");
                    el_place_tel.addClass("is-invalid");
                }


            })


            el_place_name.on("input", function (e) {
                let val_place_name = el_place_name.val();
                if (place_nameReg.test(val_place_name)) {
                    console.log("驗證通過")
                    el_place_name.removeClass("is-invalid");
                    el_place_name.addClass("is-valid");
                } else {
                    el_place_name.removeClass("is-valid");
                    el_place_name.addClass("is-invalid");
                }

                console.log("地點名稱input事件觸發")
            })

            // 同時把縣市/區域兩個選單都綁定上事件
            $("select").on("change", function (e) {


                let select_group = $('.city-selector').find(":selected");


                // // 將select 選項存到sessionStorage , 以方便錯誤處理時讀回
                // sessionStorage.county = select_group.eq(0).text();
                // sessionStorage.district = select_group.eq(1).text();

                console.log(select_group)
                if (select_group.eq(0).text() != "選擇縣市" && select_group.eq(1).text() != "選擇區域") {

                    $(".city-selector").removeClass("is-invalid");
                    $(".city-selector").addClass("is-valid");

                    get_lat_lng();
                } else {
                    $(".city-selector").removeClass("is-valid");
                    $(".city-selector").addClass("is-invalid")
                }
            })


            el_place_address.on("input", function (e) {

                let select_group = $('.city-selector').find(":selected");
                if (select_group.eq(0).text() != "選擇縣市" && select_group.eq(1).text() != "選擇區域") {
                    // alert(777)
                    $(".city-selector").removeClass("is-invalid");
                    $(".city-selector").addClass("is-valid");
                } else {
                    $(".city-selector").removeClass("is-valid");
                    $(".city-selector").addClass("is-invalid")
                }

                let val_place_address = el_place_address.val();
                if (place_addressReg.test(val_place_address)) {
                    console.log("驗證通過")
                    el_place_address.removeClass("is-invalid");
                    el_place_address.addClass("is-valid");

                    get_lat_lng();

                } else {
                    el_place_address.removeClass("is-valid");
                    el_place_address.addClass("is-invalid");
                }
            })



			const google_api_key = "AIzaSyCQspd49Wmywh3L5LAOftK_jV4qA2i89VQ";
            // let target_address = "台灣台北市萬華區康定路190號"

            function get_lat_lng() {
                // alert("777")
                // $("#getGeo_button").on("click", function (e) {
                let target_address;

                // 當兩個都驗證通過時才觸發,否則結束
                if (el_place_address.hasClass("is-valid") == true && $('.city-selector').hasClass("is-valid") == true) {
                    target_address = el_place_address.val();
                } else {
                    return;
                }

                let google_geocode_api = "https://maps.googleapis.com/maps/api/geocode/json?key=" + google_api_key + "&address=" + target_address;

                $.ajax({
                    url: google_geocode_api,           // 資料請求的網址
                    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                    // data: data,               // 傳送資料到指定的 url
                    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
                    success: function (data) {      // request 成功取得回應後執行
                        console.log(data);
                        // console.log(data.results[0].geometry.location.lat);
                        let lat = data.results[0].geometry.location.lat;
                        let lng = data.results[0].geometry.location.lng;
                        console.log(lat);
                        console.log(lng);
                        let google_embed_src = "https://www.google.com/maps/embed/v1/search?key=" + google_api_key + "&q=" + target_address + "&zoom=20&center=" + lat + "," + lng;
                        // let src = google_api_url + "&zoom=20&center=" + lat + "," + lng ;
                        let src = google_embed_src;
                        console.log(src)
                        $("#map-iframe").attr("src", src);

                        if (-180 <= lng && lng <= 180) {
                            el_place_longitude.val(lng);
                            el_place_longitude.addClass("is-valid");
                        }
                        if (-90 <= lat && lat <= 90) {
                            el_place_latitude.val(lat)
                            el_place_latitude.addClass("is-valid");
                        }


                    }
                });
                // })
            }


        });

    </script>
</body>

</html>