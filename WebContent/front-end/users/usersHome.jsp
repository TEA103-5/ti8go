<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Login - font-end</title>
     <%@ include file="/front-end/pages/links.html" %> 
</head>

<body>

    <%@ include file="/front-end/pages/headNav.html" %>
    
    <main class="page login-page">
        <section class="clean-block clean-form dark">
                <div class="block-heading">
                    <h2 class="text-info">Log In</h2>
					</div>
            <div class="container">
            <div class="row">
            	
						 <div class="col-md-7 col-xl-7 mb-7 conn">
						    <h2 class="text-info">查看/編輯與自己有關的資料</h2>
						 </div>
						 <ul>
						 		<li>
						 			編輯會員資料
						 		</li>
						 		<li>
						 			查看地點收藏
						 		</li>
						 		<li>
						 			查看/編輯 自己的行程
						 		</li>
						 </ul>
						 </div>
            </div>
        </section>
    </main>

 <%@ include file="/front-end/pages/footer.html" %>
 <%@ include file="/front-end/pages/script.html" %>


</body>

</html>