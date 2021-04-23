<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.product.model.*"%>
<jsp:useBean id="deptSvc" scope="page"
	class="com.sale.model.SaleService" />
<!DOCTYPE html>
<html>

<head>
<title>販售者商品明細 - listProductsBySaleid.jsp</title>
<%@ include file="/sale-end/pages/link.html"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sale-end/assets/css/styles.css">
</head>

<body id="page-top">
	
		<div id="wrapper">
			<%@ include file="/sale-end/pages/nav.html"%>
			<div class="d-flex flex-column" id="content-wrapper">
				<%@ include file="/sale-end/pages/fnav.html"%>



				<div class=" container">
				<div class="containerr">
					<header class="hdr">
						<h3>listProductsBySaleid</h3>
					</header>
					<div  id="app" class="table-wrapper table-responsive">
						<table class="table-users table table-hover">
						<thead>
							<tr>
								<th>商品編號</th>
								<th>商品圖片</th>
								<th>商品名稱</th>
								<th>商品狀態</th>
								<!-- 										<th>商品內容</th> -->
<!-- 								<th>商品描述</th> -->
								<th>商品分類</th>
								<th>商品價格</th>
								<th>商品庫存</th>
								<th>商品創建日期</th>
								<!-- 									<th>販售者</th> -->
								<th>編輯</th>
							</tr>
							</thead>
							<tbody>
							<tr v-for="(item, index) in list">
								<td>{{item.productId}}</td>
								<td><img class="img" id="preimg"
									v-bind:src="item.productSrc" /></td>
								<td>{{item.productName}}</td>
								<td>{{item.product_sstatus}}</td>
								
								
								
								
								<!-- 										<td>{{item.productContent}}</td> -->
<!-- 								<td>{{item.productDescription}}</td> -->
								<td>{{item.productCategories}}</td>
								<td>{{item.productPrice}}</td>
								<td>{{item.productStock}}</td>
								<td>{{item.productCreateTime| timeString }}</td>
								<td><button class="btnl btn-edit"
										v-on:click="select(index)">edit</button>
									<button class="btnl btn-delete" v-on:click="deletep(index)">delete</button></td>
							</tr>
							</tbody>
						</table>
						<button class="btnl btn-add" v-on:click="selectadd()">add</button>
						
						
						
								<div id="fade" class="black_overlay" 
			v-if="isshow">
			<div id="light" class="white_content modal-wrapper"
				 v-if="isshow">
				<div class="modall">
					<div class="modal-headerl">
					<header class="hdr" >
						<h3>{{action}}product</h3>
					</header>
					</div>
					<div class="modal-body">
					<div>
						<img class="img" id="preimg" onclick="$('input[id=file1]').click();" v-bind:src="updatelist.pSrc" />
						<input type="file" id="file1" style="display: none" ref="file" @change="uploadFile" name="file">
						</div>
						
						</br>
									<div class="custom-control custom-switch">
												<input class="custom-control-input" type="checkbox" v-model="updatelist.product_rate"
													id="formCheck-1"><label
													class="custom-control-label" for="formCheck-1"><strong>商品狀態</strong></label>
											</div>
											
						</br>
						<input placeholder="商品名稱 :" type="text" name="pname" v-model="updatelist.pName" value="123"> <br /> 
<!-- 						<input placeholder="商品狀態 :" type="text" v-model="updatelist.product_rate"><br /> -->
						
						
						
						
					    <input placeholder="商品內容 :" type="text" v-model="updatelist.pContent"><br /> 
					    <input placeholder="商品描述 :" type="text" v-model="updatelist.pDescription"><br />
						<input placeholder="商品分類 :" type="text" v-model="updatelist.pCategories"><br />
						<input placeholder="商品價格 :" type="number" v-model="updatelist.pPrice"><br />
						<input placeholder="商品庫存 :" type="number" v-model="updatelist.pStock"><br />
						<button class="btnl btn-modal" v-on:click="update()">{{action}}</button>
						<button class="btnl btn-cancel" v-on:click="cancel()">cancel</button>
					</div>
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
			<a class="border rounded d-inline scroll-to-top" href="#page-top">
				<i class="fas fa-angle-up"></i>
			</a>
		</div>



		






	<%@ include file="/sale-end/pages/script.html"%>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script>
	Vue.config.devtools = true;

window.onload=ShowHello();
function ShowHello(){
	document.getElementById('light').style.display='block';
	document.getElementById('fade').style.display='block';
	}
	
var vm = new Vue({

    el: '#app',
    data: {
    	action:'新增',
    	isshow:false,
    	dataU: new FormData(),
    	baseImg:'',
        updateindex:-1,
        ContextPath:'<%=request.getContextPath()%>/DBGifReader4_1?id=',
        updatelist: 
        	{
        	action:'',
        	index:'',
        	pName:'',
        	pId:'',
        	pSrc:'',
        	pStatus:'',
        	pContent:'',
        	pDescription:'',
        	pCategories:'',
        	product_rate:true,
        	pPrice:'',
        	pStock:'',
        	pSaleId:'${saleVO.sale_id}'
        	},
        list: [
        	<c:forEach var="empVO" items="${listEmps_ByDeptno}">
        	{
        		productName:'${empVO.product_name}',
        		productId:'${empVO.product_id}',
        		productSrc:'<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p',
        		productStatus:'${empVO.product_status}',
        		
        		productContent:'${empVO.product_content}',
				productDescription:'${empVO.product_description}',
				productCategories:'${empVO.product_categories}',
				productPrice:'${empVO.product_price}',
				productStock:'${empVO.product_stock}',
				product_rate:${(empVO.product_rate==0)? true : false},
				product_sstatus:"${(empVO.product_rate==0)? '上架中' : '下架'}",
				
				productCreateTime:'<fmt:formatDate value="${empVO.product_update_time}"
					pattern="yyyy-MM-dd" />',
				productSale:'<c:forEach var="deptVO" items="${deptSvc.all}"><c:if test="${empVO.sale_id==deptVO.sale_id}">${deptVO.sale_id}【${deptVO.sale_name} - ${deptVO.sale_phone}】</c:if></c:forEach>',
        		productSaleId:'${empVO.sale_id}'
        	},
        	</c:forEach>
        ]
    },
    methods: {
    	cancel(){
    		 this.isshow=false;
    	},
        remove(index) {
            this.list.splice(index, 1);
        },
        select(index){
        	this.action='edit';
        	this.updatelist.action='updateajax';
        	this.updatelist.pId=this.list[index].productId;
        	this.updatelist.pSrc=this.list[index].productSrc;
        	this.updatelist.pPrice=this.list[index].productPrice;
        	this.updatelist.pStock=this.list[index].productStock;
        	this.updatelist.pName=this.list[index].productName;
        	this.updatelist.pSaleId=this.list[index].productSaleId;
        	this.updatelist.pStatus=this.list[index].productStatus;
        	this.updatelist.pContent=this.list[index].productContent;
        	this.updatelist.pCategories=this.list[index].productCategories;
        	this.updatelist.pDescription=this.list[index].productDescription;
        	this.updatelist.product_rate=this.list[index].product_rate;
        	this.updatelist.index=index;
        	this.isshow=true;

        },
        update(){
        	 let self=this;
        	 if(this.updatelist.action=='updateajax'){
        		 console.log(this.updatelist.product_rate);
        		 if(this.updatelist.product_rate==true){
        			 this.updatelist.product_rate=0;
        		 }else{
        			 this.updatelist.product_rate=1;
        		 }
           		 console.log(this.updatelist.product_rate);
		if((this.updatelist.pSrc!=this.list[this.updatelist.index].productSrc)&&(this.dataU.get('upfile1')!='')){
			this.list[this.updatelist.index].productSrc=this.updatelist.pSrc;
		      axios.post("<%=request.getContextPath()%>/product/prod.do", self.dataU)
              .then( (response) => {
                 console.log(response);
                 self.dataU.set('upfile1','');
              })
		}
			$.ajax({
		        url: "<%=request.getContextPath()%>/product/prod.do",           // 資料請求的網址
		        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		        data:this.updatelist,               // 傳送資料到指定的 url
		        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		        success: function (data) {      // request 成功取得回應後執行
		          console.log(data);
		          if(data.result == "insert_succss"){
		        	  // 新增成功則按鈕改成顯示取消收藏
//			        	  that.html("取消收藏")
		          }else if(data.result == "delete_success"){
//			        	  that.html("加入收藏")
		          }
		          
		        }
		    });
			
			if(this.updatelist.product_rate==1){
				this.list[this.updatelist.index].product_sstatus='下架';
				this.list[this.updatelist.index].product_rate=false;
			}else{
				this.list[this.updatelist.index].product_sstatus='上架中';
				this.list[this.updatelist.index].product_rate=true;
			}
        	this.list[this.updatelist.index].productPrice=this.updatelist.pPrice;
        	this.list[this.updatelist.index].productStock=this.updatelist.pStock;
        	this.list[this.updatelist.index].productName=this.updatelist.pName;
        	this.list[this.updatelist.index].productStatus=this.updatelist.pStatus;
        	this.list[this.updatelist.index].productContent=this.updatelist.pContent;
        	this.list[this.updatelist.index].productCategories=this.updatelist.pCategories;
        	this.list[this.updatelist.index].productDescription=this.updatelist.pDescription;
        	this.list[this.updatelist.index].productDescription=this.updatelist.pDescription;
        	 }else{
              self.dataU.set('deptno',this.updatelist.pSaleId);
              self.dataU.set('action',this.updatelist.action);
              self.dataU.set('name',this.updatelist.pName);
              self.dataU.set('desc',this.updatelist.pDescription);
              self.dataU.set('cont',this.updatelist.pContent);
              self.dataU.set('cate',this.updatelist.pCategories);
              self.dataU.set('pric',this.updatelist.pPrice);
              self.dataU.set('stoc',this.updatelist.pStock);
   		      axios.post("<%=request.getContextPath()%>/product/prod.do", self.dataU)
              .then( (response) => {
                 let p=response.data.product;
                 console.log(p);
                 self.dataU.set('upfile1','');
                 
               self.list.push({
           		productName:p.product_name,
        		productId:p.product_id,
        		productSrc:self.ContextPath+p.product_id+'&id2=p',
        		productStatus:p.product_status,
        		productContent:p.product_content,
				productDescription:p.product_description,
				productCategories:p.product_categories,
				productPrice:p.product_price,
				productStock:p.product_stock,
				productCreateTime:p.product_update_time,
				productSale:p.sale_id,
        		productSaleId:p.sale_id
           });
                 
              })
        	 }
// 			this.list[this.updatelist.index].productSrc='';
			this.isshow=false;
        },
        selectadd(){
        	this.action='add';
        	this.updatelist.action='insertajax';
   			this.updatelist.pSrc=this.ContextPath;
   			this.updatelist.pPrice='';
        	this.updatelist.pStock='';
        	this.updatelist.pName='';
  			this.updatelist.pStatus='';
        	this.updatelist.pContent='';
        	this.updatelist.pCategories='';
        	this.updatelist.pDescription='';
        	this.isshow=true;
        	document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block';
        },
        uploadFile () {
            let self=this;
            if(this.$refs.file.files.length > 0) {
            const fr = new FileReader();
            const file =self.$refs.file.files[0];
            fr.readAsDataURL(file);
            fr.onload = function(e) {
            	self.updatelist.pSrc=e.target.result;
            	self.baseimg=e.target.result;
            }
            self.dataU.set('upfile1', self.$refs.file.files[0]);
            self.dataU.set('empno', self.updatelist.pId);
            self.dataU.set('action','updatep');
            }else{
            	self.dataU.set('upfile1','');
            	self.updatelist.pSrc=self.ContextPath;
            }
        },
        deletep(index){
        	let self=this;
        	let sindex=index;
        	var yes = confirm('你確定嗎？');
        	if (yes) {
        	    this.updatelist.action='deleteajax';
        	    this.updatelist.pId=this.list[index].productId;       	 
    			$.ajax({
    		        url: "<%=request.getContextPath()%>/product/prod.do",           // 資料請求的網址
    		        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    		        data:this.updatelist,               // 傳送資料到指定的 url
    		        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    		        success: function (data) {      // request 成功取得回應後執行
    		          console.log(data);
    		          if(data.res == "刪除成功"){
    		          self.list.splice(sindex, 1);
    		          }else if(data.result == "delete_success"){
//    			        	  that.html("加入收藏")
    		          }
    		        }
    		    });
        	} else {
        	 //   alert('你按了取消按鈕');
        	}
        }    
    },
    filters: {
    	timeString(value) {
    		let formatNumber = '';
            formatNumber += value.substr(0, 10);
            return formatNumber;
        },
    }
    
})

</script>
</body>

</html>