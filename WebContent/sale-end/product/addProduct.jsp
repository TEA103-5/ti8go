<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<!DOCTYPE html>
<html>

<head>
<title>商品資料新增 - addProduct.jsp</title>
<%@ include file="/sale-end/pages/link.html"%>
<style>
.img {
	width: 50px;
	height: 50px;
}
</style>
</head>

<body id="page-top">
	<div id="wrapper">
		<%@ include file="/sale-end/pages/nav.html"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="/sale-end/pages/fnav.html"%>
				<div class="container-fluid">
					<h3 class="text-dark mb-1">商品資料新增:</h3>

					<div>

						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>



						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/product/prod.do"
							name="form1" enctype="multipart/form-data">
							<table>

								<tr>
									<td><img class="img" id="preimg"
										src="<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p" /></td>
									<td><input type="file" id="file1" accept="image/*"
										name="upfile1"></td>
								</tr>

								<tr>
									<td>商品名稱:</td>
									<td><input type="TEXT" name="name" size="45"
										value="${(empVO==null)? '俊偉同學':empVO.product_name}" /></td>
								</tr>
								<tr>
									<td>商品簡述:</td>
									<td><input type="TEXT" name="desc" size="45"
										value="${(empVO==null)? '耐操能幹':empVO.product_description}" /></td>
								</tr>
								<tr>
									<td>商品內容:</td>
									<td><input type="TEXT" name="cont" size="45"
										value="${(empVO==null)? '好用的俊瑋同學':empVO.product_content}" /></td>
								</tr>
								<tr>
									<td>商品分類:</td>
									<td><input type="TEXT" name="cate" size="45"
										value="${(empVO==null)? '活體生物':empVO.product_categories}" /></td>
								</tr>
								<tr>
									<td>商品價格:</td>
									<td><input type="TEXT" name="pric" size="45"
										value="${(empVO==null)? '499':empVO.product_price}" /></td>
								</tr>
								<tr>
									<td>商品庫存:</td>
									<td><input type="TEXT" name="stoc" size="45"
										value="${(empVO==null)? '487996':empVO.product_stock}" /></td>
								</tr>
								<jsp:useBean id="deptSvc" scope="page"
									class="com.sale.model.SaleService" />
								<tr>
									<td>販售者:<font color=red><b>*</b></font></td>
									<td><select size="1" name="deptno">
											<c:forEach var="deptVO" items="${deptSvc.all}">
												<option value="${deptVO.sale_id}"
													${(empVO.sale_id==deptVO.sale_id)? 'selected':'' }>${deptVO.sale_name}
											</c:forEach>
									</select></td>
								</tr>
							</table>
							<br> <input type="hidden" name="action" value="insert">
							<input type="submit" value="送出新增">
						</FORM>

					</div>






 <div id="app">
        <div style="width: 70%; float: left;">
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label">姓名</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" placeholder="姓" v-model="lastName">
                </div>
                <div class="col-sm-5">
                    <input type="email" class="form-control" placeholder="名" v-model="firstName">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label">帳號</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="帳號" v-model="account">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label">密碼</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" placeholder="輸入密碼" v-model="password">
                </div>
                <div class="col-sm-5">
                    <input type="password" class="form-control" placeholder="確認密碼" v-model="confirmPassword">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail3" class="col-sm-2 col-form-label">聯絡電話</label>
                <button type="submit" class="btn btn-sm btn-warning" @click="addTel" >新增電話</button>
            </div>
            <div class="form-group row" v-for="(item, index) in telList" :key="index">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <input type="text" class="form-control" v-model="item.tel">
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="btn btn-danger btn-sm" @click="removeTel(index)">刪除電話</button>
                </div>
            </div>
            <button type="submit" class="btn btn-primary" style="float: left;"  @click="send">送出</button>
        </div>
        <div style="width: 100%; float: left; margin-top: 50px" v-if="isShow">
            【申請資訊】
            <ul>
                <li>姓名：{{fullName}}</li>
                <li>帳號：{{account}}</li>
                <li>密碼：{{password}}</li>
                <li>
                    聯絡電話：
                    <ul>
                        <li v-for="(item, index) in telList" :key="index">{{item}}</li>
                    </ul>
                </li>
            </ul>
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
	    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
  <script>
    $("#file1").on('change', function(e){      
      const file = this.files[0];
      
      const fr = new FileReader();
      fr.onload = function (e) {
        $("#preimg").attr('src', e.target.result);
//         $("#pic").val("F");
      };
      
      // 使用 readAsDataURL 將圖片轉成 Base64
      fr.readAsDataURL(file);
    });
  </script>
  
  <script>
  
  var app = new Vue({
	    el: '#app',
	    data: {
	        lastName: '',
	        firstName: '',
	        account: '',
	        password: '',
	        confirmPassword: '',
	        telList: [
	            { tel: '' },
	        ],
	        isShow: false,
	        fullName: '',
	    },
	    methods: {
	        addTel: function() {
	            this.telList.push({ tel: '' });
	        },
	        removeTel: function(index) {
	            this.telList.splice(index, 1);
	        },
	        send: function() {
	     
	          	var vm=this
				$.ajax({
			        url: "/TEA103G5/login.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        data: this.$data,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // request 成功取得回應後執行
			          console.log(data);
						console.log(vm.telList)
						vm.telList=data.errormsg
// 					 data.errormsg;
			        	$("#errormessage").text(data.errormsg);
			        	$("#errormessage").fadeOut(1300);
			          if(data.hasVO ==false){
//								document.getElementById('password').value=data.hasV;
													$("#form1").submit();
			          }
//				          else if(data.result == "delete_success"){
// 			        	  that.html("加入收藏")
//				          }
			          
			        }
			    });
	            
				  this.isShow = true;
	            
	            if( this.firstName !== ''
	                && this.lastName !== ''
	                && this.account !== ''
	                && this.password !== ''
	                && this.confirmPassword !== ''
	            ) {
	                if(this.password == this.confirmPassword) {
	                    this.fullName = this.lastName + this.firstName;
	                    this.isShow = true;
	                } else {
	                   // alert('密碼確認錯誤');
	                }
	            } else {
	               // alert('資料尚未輸入完全')
	            }
	            
	        }
	    }
	})
  
  </script>
  
  
</body>

</html>