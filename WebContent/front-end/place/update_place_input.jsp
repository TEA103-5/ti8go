<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@page import="com.place_collect.model.*"%>
<%@page import="util.Google_key"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<% 
	pageContext.setAttribute("Google_key", Google_key.key);   // 將util.Google_key的金鑰字串放進pageContext
	
	PlaceVO placeVO = null ;
	if(request.getAttribute("placeVO") != null){
		placeVO = (PlaceVO)request.getAttribute("placeVO");
	}else{
		String url = request.getContextPath() + "/front-end/place/listMyPlace.jsp" ;
		response.sendRedirect(url) ;
		return;
	}
// 	PlaceVO placeVO = (PlaceVO)request.getAttribute("placeVO");

	// 以下將place_address字串做處理以便設定地址選單套件的選項
	String address = placeVO.getPlace_address(); // 
	
	Integer index = null;
	String county = null;
	if( ( index = address.indexOf("市")) != -1  || ( index = address.indexOf("縣")) != -1){
		county = address.substring(0 , index+1).replaceAll("\\d+", "").trim(); // 取出縣市之前的字串 , 去掉數字以及空格
	}
	
	String district = placeVO.getPlace_region();
	pageContext.setAttribute("district" , district);
	pageContext.setAttribute("county" , county);
%>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>修改地點 - Tivago</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/fonts/simple-line-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/place/assets/css/smoothproducts.css">

    <!-- 以下是自己新增的css -->
    <style>
        .nav-link.active {
            display: inline-flex;
        }

        /* 在所有class="xrequired"的label後面加上紅色星號 */
        label.xrequired:after {
            content: '* ';
            color: red;
        }
    </style>
</head>

<body>
	<%@ include file="/front-end/pages/headNav.html" %>
<!--     <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar"> -->
<!--         <div class="container"><a class="navbar-brand logo" href="#">Tivago</a><button data-toggle="collapse" -->
<!--                 class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span -->
<!--                     class="navbar-toggler-icon"></span></button> -->
<!--             <div class="collapse navbar-collapse" id="navcol-1"> -->
<!--                 <ul class="navbar-nav ml-auto"> -->
<!--                     <li class="nav-item"><a class="nav-link" href="index.html">首頁</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link active" href="service-page.html">景點</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link" href="blog-post-list.html">行程</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link" href="blog-post-list.html">活動</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link" href="blog-post.html">遊記</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link" href="catalog-page.html">商城</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link" href="shopping-cart.html">購物車</a></li> -->
<!--                     <li class="nav-item"><a class="nav-link" href="login.html">登入</a></li> -->
<!--                     <li class="nav-item"><button class="btn btn-primary" type="button" -->
<!--                             style="color: var(--gray);border-color: white;font-size: 14px;">訊息<span -->
<!--                                 class="badge badge-light">5</span></button></li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--         </div> -->
<!--     </nav> -->
    <main class="page service-page">

        <nav id="search-bar" class="navbar navbar-light bg-light">
            <!-- <a class="navbar-brand">Navbar</a> -->
            <!-- id="search-bar" 的屬性justify-content: space-between;  均匀排列每个元素
                首个元素放置于起点，末尾元素放置于终点  -->
            <div>
                <a class="nav-link active disabled" href="<%=request.getContextPath()%>/front-end/place/addPlace.jsp" aria-disabled="true">新增地點</a>
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place/listMyPlace.jsp">我的地點</a>
                <a class="nav-link active" href="<%=request.getContextPath()%>/front-end/place_collect/listMyPlace_collect.jsp">我的收藏</a>
            </div>

        </nav>

        <section class="clean-block clean-services dark">
            <div class="container">
                <!-- 使用Google Maps Embed API , q放的是搜尋目標 , 如有明確對象(地址或名稱)marker會標註在此位置 ,  -->
                <div class="block-heading"><iframe id="map-iframe" allowfullscreen="" frameborder="0"
                        src="https://www.google.com/maps/embed/v1/search?key=${Google_key}&q=<%= (placeVO==null)? "" : placeVO.getPlace_address()%>&zoom=20&center=${placeVO.getPlace_latitude()},${placeVO.getPlace_longitude()}"
                        width="100%" height="400"></iframe>
                    <h2 class="text-info">修改地點</h2>
                    <p> </p>
                </div>

                <!-- 表單驗證參考這一項 利用加上特定class來標註錯誤或正確訊息 https://getbootstrap.com/docs/4.6/components/forms/#server-side -->
                <form id="insert_form" METHOD="post" ACTION="<%=request.getContextPath()%>/place/place.do" name="form1" enctype="multipart/form-data"> 
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label class="xrequired" for="validationServer01">地點名稱</label>
                            <input type="text" class="form-control " id="validationServer01" name="place_name" value="<%= (placeVO==null)? "" : placeVO.getPlace_name()%>"
                                required>
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
                            <input type="text" class="form-control" id="validationServer03" name="place_longitude"
                                value="<%= (placeVO==null)? "" : placeVO.getPlace_longitude()%>" required readonly>
                            <div class="valid-feedback">
                                驗證通過
                            </div>
                            <div id="validationServer01Feedback" class="invalid-feedback">
                                不是有效的經緯度,地址不正確
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="xrequired" for="validationServer04">緯度</label>
                            <input type="text" class="form-control" id="validationServer04" name="place_latitude"
                                value="<%= (placeVO==null)? "" : placeVO.getPlace_latitude()%>" required readonly>
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
                    <input type="hidden" name="place_state" value="<%= (placeVO==null)? "" : placeVO.getPlace_state()%>">
                    <!-- 使用者新增的地點的place_state一律為0 -> 未審核 -->
                    <input type="hidden" name="users_id" value="${sessionScope.users_id}">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <input type="hidden" name="action" value="update">
                    <!-- <button id="getGeo_button" type="button" class="btn btn-secondary">取得經緯度及定位</button> -->
                    <input type="hidden" name="place_id" value="<%=placeVO.getPlace_id()%>">
                    <button type="submit" class="btn btn-secondary">送出</button>
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
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/smoothproducts.min.js"></script>
    <script src="<%=request.getContextPath()%>/front-end/place/assets/js/theme.js"></script>

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


        $(function () {



            // 因為VO沒有對應欄位,所以錯誤處理時從sessionStorage讀回(還需要處理如何正確選回原本的option)
            // $('.city-selector').find(":selected").eq(0).val(sessionStorage.county)
            // $('.city-selector').find(":selected").eq(1).val(sessionStorage.district)

            // <input> class有is-valid 就會顯示標籤div class="valid-feedback"內設定的文字 ,is-invalid則顯示class=invalid-feedback的內容
            let el_place_name = $("#validationServer01");
            const place_nameReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$/; // 只能是中、英文字母、數字和_ , 且長度必需在2到10之間
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


            el_form.on("submit", function (e) {
                // alert("777")
                if ($(".is-invalid").length != 0) {
                	alert("尚有欄位未輸入正確");
                    return false;
                } else {
                    let select_group = $('.city-selector').find(":selected");
                    let county = select_group.eq(0).text();
                    let district = select_group.eq(1).text();
                    let address = el_place_address.val();
                    el_place_address.val(county + district + address);


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



			const google_api_key = "${Google_key}";
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