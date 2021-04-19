<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<!DOCTYPE html>
<%
	ProductService empSvc = new ProductService();
	List<ProductVO> list = empSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="deptSvc" scope="page" class="com.sale.model.SaleService" />

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Catalog - Tivago</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/smoothproducts.css">
</head>
<style>

       .img{ 
	width: 100%; 
		height: 100%; 
 			} 
</style>

<body>
<body bgcolor='white'>
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar">
        <div class="container"><a class="navbar-brand logo" href="#">Tivago</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/">首頁</a></li>
                    <li class="nav-item"><a class="nav-link" href="service-page.html">景點</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">行程</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">活動</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post.html">遊記</a></li>
                    <li class="nav-item"><a class="nav-link active" href="catalog-page.html">商城</a></li>
                    <li class="nav-item"><a class="nav-link" href="shopping-cart.html">購物車</a></li>
                    <li class="nav-item"><a class="nav-link" href="login.html">登入</a></li>
                    <li class="nav-item"><button class="btn btn-primary" type="button" style="color: var(--gray);border-color: white;font-size: 14px;">訊息<span class="badge badge-light">5</span></button></li>
                </ul>
            </div>
        </div>
    </nav>
    <main class="page catalog-page">
        <section class="clean-block clean-catalog dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">listAllProduct</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo.</p>
                </div>
                <div class="content">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="d-none d-md-block">
                                <div class="filters">
                                    <div class="filter-item">
                                        <h3>Categories</h3>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"><label class="form-check-label" for="formCheck-1">Phones</label></div>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-2"><label class="form-check-label" for="formCheck-2">Laptops</label></div>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-3"><label class="form-check-label" for="formCheck-3">PC</label></div>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-4"><label class="form-check-label" for="formCheck-4">Tablets</label></div>
                                    </div>
                                    <div class="filter-item">
                                        <h3>Brands</h3>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-5"><label class="form-check-label" for="formCheck-5">Samsung</label></div>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-6"><label class="form-check-label" for="formCheck-6">Apple</label></div>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-7"><label class="form-check-label" for="formCheck-7">HTC</label></div>
                                    </div>
                                    <div class="filter-item">
                                        <h3>OS</h3>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-8"><label class="form-check-label" for="formCheck-8">Android</label></div>
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-9"><label class="form-check-label" for="formCheck-9">iOS</label></div>
                                    </div>
                                </div>
                            </div>
                            <div class="d-md-none"><a class="btn btn-link d-md-none filter-collapse" data-toggle="collapse" aria-expanded="false" aria-controls="filters" href="#filters" role="button">Filters<i class="icon-arrow-down filter-caret"></i></a>
                                <div class="collapse" id="filters">
                                    <div class="filters">
                                        <div class="filter-item">
                                            <h3>Categories</h3>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"><label class="form-check-label" for="formCheck-1">Phones</label></div>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-2"><label class="form-check-label" for="formCheck-2">Laptops</label></div>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-3"><label class="form-check-label" for="formCheck-3">PC</label></div>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-4"><label class="form-check-label" for="formCheck-4">Tablets</label></div>
                                        </div>
                                        <div class="filter-item">
                                            <h3>Brands</h3>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-5"><label class="form-check-label" for="formCheck-5">Samsung</label></div>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-6"><label class="form-check-label" for="formCheck-6">Apple</label></div>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-7"><label class="form-check-label" for="formCheck-7">HTC</label></div>
                                        </div>
                                        <div class="filter-item">
                                            <h3>OS</h3>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-8"><label class="form-check-label" for="formCheck-8">Android</label></div>
                                            <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-9"><label class="form-check-label" for="formCheck-9">iOS</label></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        	<%@ include file="/pages/page1.file" %> 
					<div class="row">
                        <div class="col-md-12">
                            <div class="products">
                                <div class="row no-gutters">
	<c:forEach var="empVO" items="${list}"
begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		<div class="col-12 col-md-6 col-lg-4">
            <div class="clean-product-item">
            <div class="image"><a href="#">
             <div class="rating"><img src="<%=request.getContextPath()%>/front-end/assets/img/star.svg"><img src="<%=request.getContextPath()%>/front-end/assets/img/star.svg"><img src="<%=request.getContextPath()%>/front-end/assets/img/star.svg"><img src="<%=request.getContextPath()%>/front-end/assets/img/star-half-empty.svg"><img src="<%=request.getContextPath()%>/front-end/assets/img/star-empty.svg"></div> --> -->
            <div class="product-name"><a href="#">${empVO.product_name}</a></div>
            <img class="img-fluid d-block mx-auto img" src="<%=request.getContextPath()%>/BLOB_Reader?id=${empVO.product_id}"></a></div>
                                            <div class="about">
                                                <div class="price">
                                                    <h3>${empVO.product_price}</h3>
													<h4>${empVO.product_content}</h4>
													<h5>${empVO.product_description}</h5>
													<h6>${empVO.product_categories}</h6>
													數量:<h7>${empVO.product_stock}</h7>
													<br>
													上架日期:<h8><fmt:formatDate value="${empVO.product_update_time}" pattern="yyyy-MM-dd"/></h8>
													<br>
													<h9><c:forEach var="deptVO" items="${deptSvc.all}">
                    								<c:if test="${empVO.sale_id==deptVO.sale_id}">
											                    販售者:【${deptVO.sale_name} - ${deptVO.sale_phone}】
										                    </c:if>
										                </c:forEach>
													</h9>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                               
<%-- 			<td>${empVO.product_id} </td> --%>
<%-- 			<td>${empVO.product_name}</td> --%>
			
<%-- 			<td>${empVO.product_price}</td> --%>
	
<%-- 			<td><img class="img" id="preimg" src="<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p"/></td> --%>


		</tr>
	</c:forEach>
<!--                         <div class="col-md-9"> -->
<!--                             <div class="products"> -->
<!--                                 <div class="row no-gutters"> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                     <div class="col-12 col-md-6 col-lg-4"> -->
<!--                                         <div class="clean-product-item"> -->
<!--                                             <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a><a href="#"></a></div> -->
<!--                                             <div class="product-name"><a href="#">Lorem ipsum dolor sit amet</a></div> -->
<!--                                             <div class="about"> -->
<!--                                                 <div class="rating"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star.svg"><img src="assets/img/star-half-empty.svg"><img src="assets/img/star-empty.svg"></div> -->
<!--                                                 <div class="price"> -->
<!--                                                     <h3>$100</h3> -->
<!--                                                 </div> -->
<!--                                             </div> -->
<!--                                         </div> -->
<!--                                     </div> -->
<!--                                 </div> -->
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
              <ul class="pagination"> <li class="page-item disabled"> <%@ include file="/pages/page2.file" %></li></ul>
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
    <script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/js/theme.js"></script>
</body>

</html>