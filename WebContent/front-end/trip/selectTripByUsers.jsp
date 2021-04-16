<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>ListTripByUsersId - font-end</title>
     <%@ include file="/front-end/pages/links.html" %> 
</head>

<body>

    <%@ include file="/front-end/pages/headNav.html" %>
    
    <main class="page catalog-page">
     
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">行程資料庫</h2>
                    <p></p>
                </div>
				<div class="content">
					 <div class="row">
					 <div class="col-md-9">
                            <div class="products">
                                <div class="row no-gutters">
                                
                                
                                    <div class="col-12 col-md-6 col-lg-4">
                                        <div class="clean-product-item">
                                            <div class="image"><a href="#"><img class="img-fluid d-block mx-auto" src="assets/img/tech/image2.jpg"></a></div>
                                            <div class="product-name"><a href="#">行程名稱</a></div>
                                            <div class="product-name">行程日期/天數</div>
                                            <div class="product-name"><button>編輯</button><button>刪除</button></div>
                                            <div class="about">
                                                <div class="rating">瀏覽次數</div>
                                                <div class="price">
                                                    <h3>使用者名稱</h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                
                                
                                
                                </div>
                                </div>
                                </div>
					 </div>
				</div>
            </div>
  
    </main>

 <%@ include file="/front-end/pages/footer.html" %>
 <%@ include file="/front-end/pages/script.html" %>


</body>

</html>