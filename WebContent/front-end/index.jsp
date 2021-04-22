<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.users.model.*"%>
<%@ page import="com.users.controller.*"%>
<% 
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
%>
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
<%--  			<%@ include file="/front-end/pages/headNav.html" %> --%>
<!--  		這行是header的nav區塊 -->
 	

    <main class="page landing-page">

<section>
		<img src="<%=request.getContextPath()%>/front-end/assets/img/star.png" id="bg">
		<img src="<%=request.getContextPath()%>/front-end/assets/img/moon.png" id="moon">
		<img src="<%=request.getContextPath()%>/front-end/assets/img/taipei.png" id="mountain">
		<img src="<%=request.getContextPath()%>/front-end/assets/img/road.png" id="road">
		<b id="text">Tivago</b>
</section>



        <div class="container-fluid glass">
        
  <div class="containar row">
  
  
  
          <div class="cerd col-md-1 col-xl-1 mb-1 ">
          <div class="box">
          <div class="content">
    						<h2>01</h2>
    						<h3>會員</h3>
    						<p>成為會員,享受我們提供的專業服務</p>
    						<a href="<%=request.getContextPath()%>/front-end/users/login.jsp">
    						<%= (usersVO==null)? "成為會員" : "會員資料"%>
    						</a>
    				</div>	
    			</div>
          </div>
          <div class="cerd col-md-1 col-xl-1 mb-1 ">
          <div class="box">
          <div class="content">
    						<h2>02</h2>
    						<h3>地點</h3>
    						<p>探索周圍人氣景點</p>
    						<a href="<%=request.getContextPath()%>/front-end/place/selectPlace.jsp">搜索景點</a>
    				</div>	
    			</div>
          </div>
          <div class="cerd col-md-1 col-xl-1 mb-1 ">
          <div class="box">
          <div class="content">
    						<h2>03</h2>
    						<h3>行程</h3>
    						<p>尋找夢想中的行程</p>
    						<a href="<%=request.getContextPath()%>/front-end/trip/listTrip.jsp">瀏覽行程</a>
    				</div>	
    			</div>
          </div>
          <div class="cerd col-md-1 col-xl-1 mb-1 ">
          <div class="box">
          <div class="content">
    						<h2>04</h2>
    						<h3>商品</h3>
    						<p>出發前覺得少了點什麼?來這看看吧</p>
    						<a href="<%=request.getContextPath()%>/front-end/order/addOrder.jsp">瀏覽商品</a>
    				</div>	
    			</div>
          </div>
          <div class="cerd col-md-1 col-xl-1 mb-1 ">
          <div class="box">
          <div class="content">
    						<h2>05</h2>
    						<h3>遊記</h3>
    						<p>別忘了記錄你美好的回憶</p>
    						<a href="<%=request.getContextPath()%>/front-end/notefront/notePostList.jsp">瀏覽遊記</a>
    				</div>	
    			</div>
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
	  moon.style.left= -value*0.15 + 'px';
	  mountain.style.top= -value*0.15 + 'px';
	  road.style.top= value*0.17 + 'px';
	  text.style.top= value*0.7 + 'px';
	  text.style.left= -value*0.15 + 'px';
  })
  </script>
  </body>
</html>