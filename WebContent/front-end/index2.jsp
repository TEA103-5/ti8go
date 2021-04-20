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
    <%@ include file="/front-end/pages/links.html" %>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/tripstyles2.css">
</head>
 <body>
 		
<!--  		這行是header的nav區塊 -->
 		<%@ include file="/front-end/pages/headNav.html" %>

    <main class="page landing-page">

<section>
		<img src="<%=request.getContextPath()%>/front-end/assets/img/star.png" id="bg">
		<img src="<%=request.getContextPath()%>/front-end/assets/img/moon.png" id="moon">
		<img src="<%=request.getContextPath()%>/front-end/assets/img/taipei.png" id="mountain">
		<img src="<%=request.getContextPath()%>/front-end/assets/img/road.png" id="road">
		<h2 id="text">Tivago</h2>
</section>

          </div>
          <ol class="carousel-indicators">
            <li data-target="#carousel-1" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-1" data-slide-to="1"></li>
            <li data-target="#carousel-1" data-slide-to="2"></li>
          </ol>
        </div>
      </div>


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
   

    </main>
    
<!--     	這行是footer區塊 -->
    <%@ include file="/front-end/pages/footer.html" %>

    <script src="<%=request.getContextPath()%>/front-end/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/assets/js/theme.js"></script>
  
  <script type="text/javascript">
  let bg=document.getElementById("bg");
  let moon=document.getElementById("moon");
  let mountain=document.getElementById("mountain");
  let road=document.getElementById("road");
  let text=document.getElementById("text");
  
  window.addEventListener('scroll',function(){
	  var value = window.scrollY;
	  bg.style.top= value*0.5 + 'px';
	  moon.style.left= -value*0.5 + 'px';
	  mountain.style.top= -value*0.15 + 'px';
	  road.style.top= value*0.15 + 'px';
	  text.style.top= value*1 + 'px';
  })
  </script>
  </body>
</html>