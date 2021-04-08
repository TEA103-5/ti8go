<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
    />
    <title>Home - Tivago</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/bootstrap/css/bootstrap.min.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i"
    />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/fonts/simple-line-icons.min.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css"
    />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/smoothproducts.css" />

</head>
 <body>
 
    <nav
      class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar"
    >
      <div class="container">
        <a class="navbar-brand logo" href="#">Tivago</a
        ><button
          data-toggle="collapse"
          class="navbar-toggler"
          data-target="#navcol-1"
        >
          <span class="sr-only">Toggle navigation</span
          ><span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navcol-1">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link active" href="index.html">首頁</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="service-page.html">景點</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="blog-post-list.html">行程</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="blog-post-list.html">活動</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="blog-post.html">遊記</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="product/listAllProduct.jsp">商城</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="shopping-cart.html">購物車</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="login.html">登入</a>
            </li>
            <li class="nav-item">
              <button
                class="btn btn-primary"
                type="button"
                style="color: var(--gray); border-color: white; font-size: 14px"
              >
                訊息<span class="badge badge-light">5</span>
              </button>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <main class="page landing-page">
<!--     中段開始 		-->
<%@ include file="pages/login.jsp" %>
<!--       中段結束		 -->
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="assets/js/smoothproducts.min.js"></script>
    <script src="assets/js/theme.js"></script>
  
  </body>
</html>