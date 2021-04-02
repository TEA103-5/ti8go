<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@page import="com.place_collect.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Services - Tivago</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <style>
        .nav-link.active {
            display: inline-flex;

        }
    </style>
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar">
        <div class="container"><a class="navbar-brand logo" href="#">Tivago</a><button data-toggle="collapse"
                class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                    class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="index.html">首頁</a></li>
                    <li class="nav-item"><a class="nav-link active" href="service-page.html">景點</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">行程</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post-list.html">活動</a></li>
                    <li class="nav-item"><a class="nav-link" href="blog-post.html">遊記</a></li>
                    <li class="nav-item"><a class="nav-link" href="catalog-page.html">商城</a></li>
                    <li class="nav-item"><a class="nav-link" href="shopping-cart.html">購物車</a></li>
                    <li class="nav-item"><a class="nav-link" href="login.html">登入</a></li>
                    <li class="nav-item"><button class="btn btn-primary" type="button"
                            style="color: var(--gray);border-color: white;font-size: 14px;">訊息<span
                                class="badge badge-light">5</span></button></li>
                </ul>
            </div>
        </div>
    </nav>
    <main class="page service-page">

        <nav id="search-bar" class="navbar navbar-light bg-light">
            <!-- <a class="navbar-brand">Navbar</a> -->
            <!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
                首个元素放置于起点，末尾元素放置于终点  -->
            <div>
                <a class="nav-link active disabled" href="#" aria-disabled="true">新增地點</a>
                <a class="nav-link active" href="#">我的地點</a>
                <a class="nav-link active" href="#">我的收藏</a>
            </div>

        </nav>

        <section class="clean-block clean-services dark">
            <div class="container">
                <!-- 使用Google Maps Embed API , q放的是搜尋目標 , 如有明確對象(地址或名稱)marker會標註在此位置 ,  -->
                <div class="block-heading"><iframe id="map-iframe" allowfullscreen="" frameborder="0"
                        src="https://www.google.com/maps/embed/v1/search?key=AIzaSyBt62TZeRNMLW9m5vw1DfsxwUE_xdmK7rQ&q=111台北市信義區信義路五段7號&zoom=20&center=25.033952,121.564360"
                        width="100%" height="400"></iframe>
                    <h2 class="text-info">新增地點</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in,
                        mattis vitae leo.</p>
                </div>

                <!-- 表單驗證參考這一項 利用加上特定class來標註錯誤或正確訊息 https://getbootstrap.com/docs/4.6/components/forms/#server-side -->
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" name="form1" enctype="multipart/form-data">
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationServer01">地點名稱</label>
                            <input type="text" class="form-control is-valid" id="validationServer01" name="place_name"
                                value="" required>
                            <div class="valid-feedback">
                                class有is-valid 就會顯示div class="valid-feedback"驗證通過的設定文字
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationServer02">地址</label>
                            <input type="text" class="form-control " id="validationServer02" name="place_address"
                                value="" required>
                            <div class="valid-feedback">
                                class有is-valid 就會顯示div class="valid-feedback"驗證通過的設定文字
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="validationServer03">經度</label>
                            <input type="text" class="form-control is-valid" id="validationServer03"
                                name="place_longitude" value="" required >
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="validationServer04">緯度</label>
                            <input type="text" class="form-control is-valid" id="validationServer04"
                                name="place_latitude" value="" required >
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="validationServer05">電話</label>
                            <input type="text" class="form-control is-valid" id="validationServer05" name="place_tel"
                                value="">
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="validationServer06">地點類型</label>
                            <input type="text" class="form-control is-valid" id="validationServer06" name="place_type"
                                value="">
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationServer07">地點簡介</label>
                            <textarea class="form-control is-invalid" id="validationServer07" name="place_index"
                                rows="6" placeholder="Required example textarea"></textarea>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationServer08">圖片1</label>
                            <input type="file" class="form-control-file " id="validationServer08" name="place_pic1">
                            <!-- <img
                                src="https://images.pexels.com/photos/6757343/pexels-photo-6757343.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=200&w=1260" /> -->
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationServer09">圖片2</label>
                            <input type="file" class="form-control-file " id="validationServer09" name="place_pic2">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="validationServer10">圖片3</label>
                            <input type="file" class="form-control-file " id="validationServer10" name="place_pic3">
                        </div>
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
                    <div class="form-group">
                        <div class="form-check">
                            <input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3"
                                aria-describedby="invalidCheck3Feedback" required>
                            <label class="form-check-label" for="invalidCheck3">
                                Agree to terms and conditions
                            </label>
                            <div id="invalidCheck3Feedback" class="invalid-feedback">
                                You must agree before submitting.
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="place_state" value="0">
                    <!-- 使用者新增的地點的place_state一律為0 -> 未審核 -->
                    <input type="hidden" name="users_id" value="1">
                    <!-- users_id 為當前當入的id -->
                    <input type="hidden" name="action" value="insert">
                    <button type="submit" class="btn btn-secondary">submit</button>
                </form>
                <!-- 表單結束 -->

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