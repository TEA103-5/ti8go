<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sale.model.*"%>

<!DOCTYPE html>
<html>

<head>
<title>Profile - Brand</title>
<%@ include file="/sale-end/pages/link.html"%>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/sale-end/pages/nav.html"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="/sale-end/pages/fnav.html"%>
				<div class="container-fluid">
					<h3 class="text-dark mb-4">Profile</h3>
					<div class="row mb-3">
						<div class="col-lg-4">
							<div class="card mb-3">
								<div class="card-body text-center shadow">
									<img class="rounded-circle mb-3 mt-4" id="preimg"
										src="<%=request.getContextPath()%>/DBGifReader4_1?id=${saleVO.sale_id}"
										width="160" height="160">

									<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/sale/sale.do" id="form1">
<!-- 										enctype="multipart/form-data"> -->
										<div class="mb-3">
											<button class="btn btn-primary btn-sm" type="button"
												onclick="$('input[id=file1]').click();">Change
												Photo</button>
											<input type="file" id="file1" accept="image/*"
												style="display: none" name="upfile1"> <input
												type="hidden" id="pic" name="picc" value="T"> <input
												type="hidden" name="action" value="updatep"> <input
												type="hidden" name="empno" value="${saleVO.sale_id}">
											<input type="hidden" name="requestURL"
												value="<%=request.getParameter("requestURL")%>">
										</div>
									</form>

								</div>
							</div>
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="text-primary font-weight-bold m-0">Products</h6>
								</div>
								<div class="card-body">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/sale/sale.do" >
											<h4 class="small font-weight-bold">
													<input type="submit" value="查詢擁有商品"> <input
														type="hidden" name="deptno" value="${saleVO.sale_id}">
													<input type="hidden" name="action"
														value="listEmps_ByDeptno_B">
											</h4>
										</FORM>
									<!-- 							                                    <div class="progress progress-sm mb-3"> -->
									<!-- 							                                        <div class="progress-bar bg-danger" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%;"><span class="sr-only">20%</span></div> -->
									<!-- 							                                    </div> -->
									<!-- 							                                    <h4 class="small font-weight-bold">Sales tracking<span class="float-right">40%</span></h4> -->
									<!-- 							                                    <div class="progress progress-sm mb-3"> -->
									<!-- 							                                        <div class="progress-bar bg-warning" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;"><span class="sr-only">40%</span></div> -->
									<!-- 							                                    </div> -->
									<!-- 							                                    <h4 class="small font-weight-bold">Customer Database<span class="float-right">60%</span></h4> -->
									<!-- 							                                    <div class="progress progress-sm mb-3"> -->
									<!-- 							                                        <div class="progress-bar bg-primary" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"><span class="sr-only">60%</span></div> -->
									<!-- 							                                    </div> -->
									<!-- 							                                    <h4 class="small font-weight-bold">Payout Details<span class="float-right">80%</span></h4> -->
									<!-- 							                                    <div class="progress progress-sm mb-3"> -->
									<!-- 							                                        <div class="progress-bar bg-info" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%;"><span class="sr-only">80%</span></div> -->
									<!-- 							                                    </div> -->
									<!-- 							                                    <h4 class="small font-weight-bold">Account setup<span class="float-right">Complete!</span></h4> -->
									<!-- 							                                    <div class="progress progress-sm mb-3"> -->
									<!-- 							                                        <div class="progress-bar bg-success" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;"><span class="sr-only">100%</span></div> -->
									<!-- 							                                    </div> -->
								</div>
							</div>

						</div>
						<div class="col-lg-8">

							<div class="row">
								<div class="col">
									<div class="card shadow mb-3">
										<div class="card-header py-3">
											<p class="text-primary m-0 font-weight-bold">Sale
												Settings</p>
										</div>
										<div class="card-body">
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/sale/sale.do"
												id="form2" enctype="multipart/form-data">
												<div class="form-row">

													<div class="col">
														<div class="form-group">
															<label for="username"><strong>姓名</strong></label> <input
																class="form-control" type="text" id="name"
																placeholder="必填" name="name"
																value="${(saleVO==null)? '' : saleVO.sale_name}">
														</div>
													</div>
													<div class="col">
														<div class="form-group">
															<label for="email"><strong>暱稱</strong></label> <input
																class="form-control" id="nickname" placeholder="必填"
																name="nickname"
																value="${(saleVO==null)? '' : saleVO.sale_nickname}">
														</div>
													</div>
												</div>
												<div class="form-row">
													<div class="col">
														<div class="form-group">
															<label for="first_name"><strong>連絡電話</strong></label> <input
																class="form-control" type="text" id="phone"
																placeholder="必填" name="phone"
																value="${(saleVO==null)? '' : saleVO.sale_phone}">
														</div>
													</div>
													<div class="col">
														<c:if test="${not empty errorMsgs}">
															<ul>
																<c:forEach var="message" items="${errorMsgs}">
																	<li style="color: red">${message}</li>
																</c:forEach>
															</ul>
														</c:if>
														<!--                                                         <div class="form-group"><label for="last_name"><strong>Last Name</strong> -->
														<!--                                                         </label><input class="form-control" type="text" id="last_name" placeholder="Doe" name="last_name"> -->
														<!--                                                         </div> -->
														<input type="hidden" name="action" value="updatejsp">
														<input type="hidden" name="empno"
															value="${saleVO.sale_id}"> <input type="hidden"
															name="requestURL"
															value="<%=request.getParameter("requestURL")%>">
													</div>
												</div>
												<div class="form-group">
													<button class="btn btn-primary btn-sm" type="submit">Save
														Settings</button>
												</div>
											</form>
										</div>
									</div>
									<div class="card shadow">
										<div class="card-header py-3">
											<p class="text-primary m-0 font-weight-bold">Contact
												Settings</p>
										</div>
										<div class="card-body">
											<form>
												<div class="form-group">
													<label for="address"><strong>Address</strong></label><input
														class="form-control" type="text" id="address"
														placeholder="Sunset Blvd, 38" name="address">
												</div>
												<div class="form-row">
													<div class="col">
														<div class="form-group">
															<label for="city"><strong>City</strong></label><input
																class="form-control" type="text" id="city"
																placeholder="Los Angeles" name="city">
														</div>
													</div>
													<div class="col">
														<div class="form-group">
															<label for="country"><strong>Country</strong></label><input
																class="form-control" type="text" id="country"
																placeholder="USA" name="country">
														</div>
													</div>
												</div>
												<div class="form-group">
													<button class="btn btn-primary btn-sm" type="submit">Save&nbsp;Settings</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card shadow mb-5">
						<div class="card-header py-3">
							<p class="text-primary m-0 font-weight-bold">Forum Settings</p>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<form>
										<div class="form-group">
											<label for="signature"><strong>Signature</strong><br></label>
											<textarea class="form-control" id="signature" rows="4"
												name="signature"></textarea>
										</div>
										<div class="form-group">
											<div class="custom-control custom-switch">
												<input class="custom-control-input" type="checkbox"
													id="formCheck-1"><label
													class="custom-control-label" for="formCheck-1"><strong>Notify
														me about new replies</strong></label>
											</div>
										</div>
										<div class="form-group">
											<button class="btn btn-primary btn-sm" type="submit">Save
												Settings</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<footer class="bg-white sticky-footer">
				<div class="container my-auto">
					<div class="text-center my-auto copyright">
						<span>Copyright © Brand 2021</span>
					</div>
				</div>
			</footer>
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
			class="fas fa-angle-up"></i></a>
	</div>
	<%@ include file="/sale-end/pages/script.html"%>
	<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
	<script>
		$("#file1").on('change', function(e) {
			const file = this.files[0];
			if(file.type.match(/^image\/(png|jpeg)$/)){
				console.log("gfgdf");
			}
			const fr = new FileReader();
			fr.readAsDataURL(file);
			let formData = new FormData($("#form1")[0]);
			fr.onload = function(e) {
				        $("#preimg").attr('src', e.target.result);
				        $("#preimg1").attr('src', e.target.result);
					  	let data = {
					  			"upfile1":file,
					  			"action": "upload",
//					            "users_id": login_users
					  	}	
				console.log(formData);
				//alert("");
				//         $("#pic").val("F");
				//$("#form1").submit();
// 			  	let data = {
// 			  			"action": "ajax_insert_or_delete_PlaceCollect",
// 			            "place_id": place_id,
// 			            "users_id": login_users
// 			  	}		
	// console.log($(this).parent(".card-body").find(".place_id_value").attr("value"));
	// console.log($(this).parent(".card-body").find(".users_id_value").attr("value"));
				
				$.ajax({
			        url: "/TEA103G5/sale/sale.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: formData,               // 傳送資料到指定的 url
			       
			        cache:false, // 不需要cache
			        processData: false, // jQuery預設會把data轉為query String, 所以要停用
			        contentType: false, 
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			          console.log(data);
			          if(data.result == "insert_succss"){
			        	  // 新增成功則按鈕改成顯示取消收藏
// 			        	  that.html("取消收藏")
			          }else if(data.result == "delete_success"){
// 			        	  that.html("加入收藏")
			          }
			          
			        }
			    });
			};

			// 使用 readAsDataURL 將圖片轉成 Base64
// 			fr.readAsDataURL(file);
		});
	</script>
</body>

</html>