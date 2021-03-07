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
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i"
    />
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css"
    />
    <link rel="stylesheet" href="assets/css/smoothproducts.css" />
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
              <a class="nav-link" href="catalog-page.html">商城</a>
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
      <div class="container">
        <div class="block-heading"></div>
        <div class="carousel slide" data-ride="carousel" id="carousel-1">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                class="w-100 d-block"
                src="assets/img/scenery/image1.jpg"
                alt="Slide Image"
              />
              <div>
                <h1 id="H1" class="h1-modal" style="text-align: center">
                  超值行程-OOO
                </h1>
              </div>
            </div>
            <div class="carousel-item">
              <img
                class="w-100 d-block"
                src="http://localhost:8081/T8go/assets/img/scenery/image4.jpg"
                alt="Slide Image"
              />
              <div>
                <h1 id="H1" class="h1-modal" style="text-align: center">
                  超值行程-XXX
                </h1>
              </div>
            </div>
            <div class="carousel-item">
              <img
                class="w-100 d-block"
                src="assets/img/scenery/image6.jpg"
                alt="Slide Image"
              />
              <div>
                <h1 id="H-1" class="h1-modal" style="text-align: center">
                  超值行程-YYY
                </h1>
              </div>
            </div>
          </div>
          <div>
            <a
              class="carousel-control-prev"
              href="#carousel-1"
              role="button"
              data-slide="prev"
              ><span class="carousel-control-prev-icon"></span
              ><span class="sr-only">Previous</span></a
            ><a
              class="carousel-control-next"
              href="#carousel-1"
              role="button"
              data-slide="next"
              ><span class="carousel-control-next-icon"></span
              ><span class="sr-only">Next</span></a
            >
          </div>
          <ol class="carousel-indicators">
            <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-1" data-slide-to="1"></li>
            <li data-target="#carousel-1" data-slide-to="2"></li>
          </ol>
        </div>
      </div>

      <section class="clean-block clean-info dark">
        <div class="container">
          <div class="block-heading">
            <h2 class="text-info">Info</h2>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam
              urna, dignissim nec auctor in, mattis vitae leo.
            </p>
          </div>
          <div class="row align-items-center">
            <div class="col-md-6">
              <img class="img-thumbnail" src="assets/img/scenery/image5.jpg" />
            </div>
            <div class="col-md-6">
              <h3>Lorem impsum dolor sit amet</h3>
              <div class="getting-started-info">
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                </p>
              </div>
              <button class="btn btn-outline-primary btn-lg" type="button">
                Join Now
              </button>
            </div>
          </div>
        </div>
      </section>
      <section class="clean-block about-us">
        <div class="container">
          <div class="block-heading">
            <h2 class="text-info">About Us</h2>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam
              urna, dignissim nec auctor in, mattis vitae leo.
            </p>
          </div>
          <div class="row justify-content-center">
            <div class="col-sm-6 col-lg-4">
              <div class="card text-center clean-card">
                <img
                  class="card-img-top w-100 d-block"
                  src="assets/img/avatars/avatar1.jpg"
                />
                <div class="card-body info">
                  <h4 class="card-title">John Smith</h4>
                  <p class="card-text">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                  </p>
                  <div class="icons">
                    <a href="#"><i class="icon-social-facebook"></i></a
                    ><a href="#"><i class="icon-social-instagram"></i></a
                    ><a href="#"><i class="icon-social-twitter"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-sm-6 col-lg-4">
              <div class="card text-center clean-card">
                <img
                  class="card-img-top w-100 d-block"
                  src="assets/img/avatars/avatar2.jpg"
                />
                <div class="card-body info">
                  <h4 class="card-title">Robert Downturn</h4>
                  <p class="card-text">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                  </p>
                  <div class="icons">
                    <a href="#"><i class="icon-social-facebook"></i></a
                    ><a href="#"><i class="icon-social-instagram"></i></a
                    ><a href="#"><i class="icon-social-twitter"></i></a>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-sm-6 col-lg-4">
              <div class="card text-center clean-card">
                <img
                  class="card-img-top w-100 d-block"
                  src="assets/img/avatars/avatar3.jpg"
                />
                <div class="card-body info">
                  <h4 class="card-title">Ally Sanders</h4>
                  <p class="card-text">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                  </p>
                  <div class="icons">
                    <a href="#"><i class="icon-social-facebook"></i></a
                    ><a href="#"><i class="icon-social-instagram"></i></a
                    ><a href="#"><i class="icon-social-twitter"></i></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
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
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="assets/js/smoothproducts.min.js"></script>
    <script src="assets/js/theme.js"></script>
  </body>
</html>