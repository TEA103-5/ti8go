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
 		
<!--  		這行是header的nav區塊 -->
 		<%@ include file="/front-end/pages/headNav.html" %>

    <main class="page landing-page">
      <div class="container">
        <div class="block-heading"></div>
        <div class="carousel slide" data-ride="carousel" id="carousel-1">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img
                class="w-100 d-block"
                src="<%=request.getContextPath()%>/front-end/assets/img/scenery/image1.jpg"
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
                src="<%=request.getContextPath()%>/front-end/assets/img/scenery/image4.jpg"
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
                src="<%=request.getContextPath()%>/front-end/assets/img/scenery/image6.jpg"
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
          </div>
          <div class="row align-items-center">
            <div class="col-md-6">
              <img class="img-thumbnail" src="<%=request.getContextPath()%>/front-end/assets/img/scenery/image5.jpg" />
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
                  src="<%=request.getContextPath()%>/front-end/assets/img/avatars/avatar3.jpg"
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
                  src="<%=request.getContextPath()%>/DBGifReader4_1?id=2"
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
                  src="<%=request.getContextPath()%>/DBGifReader4_1?id=18"
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
    
<!--     	這行是footer區塊 -->
    <%@ include file="/front-end/pages/footer.html" %>

    <script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/js/theme.js"></script>
  </body>
</html>