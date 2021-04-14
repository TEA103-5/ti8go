<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:useBean id="pSvc" scope="page" class="com.product.model.ProductService" />
<head>
    <title>addOrder - font-end</title>
     <%@ include file="/front-end/pages/links.html" %> 
     <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/productstyles.css">
     <style>

</style>
</head>

<body>

    <%@ include file="/front-end/pages/headNav.html" %>
  
    <main class="page glass">
						<div id="fade" class="black_overlay"></div>
						<div id="fade1" class="black_overlay"></div>
    	  <div class="circle1"></div>
    	  <div class="circle2"></div>
				<div class="container-fluid glass">
		
					<div id="app" class="row" style="margin-right:0px; margin-left:0px">
<!-- 						 <div class="col-md-1 col-xl-1 mb-1 conn"> -->
<!-- 						 						 一次顯示商品數量:{{n}} -->
<!-- 						 <input type="range" :min="1" :max="pagemax" step="1" v-model="n"> -->
<!-- 						 	<a href="javascript:scroll(0,0)">回頂部</a> -->
<!-- 						 </div> -->
						 <div class="col-md-7 col-xl-7 mb-7 conn">
						 <div class="user">
						 <input type="range" :min="pricelowbound" :max="pricemax" step="1" v-model="pricemin">
						價格下限:{{pricemin}}~價格上限:{{pricemax}}
						<input type="range" :min="pricemin" :max="priceupbound" step="1" v-model="pricemax"><br/>

						
						 <button class="btnl btn-cancel"  @click="setOrderType(1)">價格升序</button>
						<button class="btnl btn-cancel"  @click="setOrderType(2)">價格降序</button>
						<button class="btnl btn-cancel"  @click="setOrderType(0)">原本順序</button>
						 <br/> <input type="text"  placeholder="搜尋" @blur="scheck()" v-model="searchName">符合條件有{{pagemax}}件商品		
												  <button class="btnl btn-edit" v-on:click="pagepre()">pre</button>
						 第{{px}}/{{Math.ceil(pagemax/n)}}頁
						 <button class="btnl btn-edit" v-on:click="pageadd()">next</button>
						 </div>
						 
						<table class="table-users">
							<tr>
								<th>圖片</th>
								<th>商品名稱</th>
								<th>價格</th>
								<th>數量</th>
								<th>分類</th>
								<th>添加至定單</th>
							</tr>
							<tr v-for="(item, index) in filterlist" >
								<td><img class="img" id="preimg"
									v-bind:src="item.productSrc" /></td>
								<td>{{ item.productName }}</td>
								<td>{{ item.age }}</td>
								<td>{{ item.productStock }}</td>
								<td>{{ item.productCategories }}</td>								
								<td>
									<button class="btnl btn-modal" v-on:click="addtoorder(index)">add</button>									
								</td>
							</tr>
						</table>
					
						 </div>
						 
						 
						 
						  <div class="col-md-4 col-xl-4 mb-4 conn">
				

						  <div class="user">
						  	 <header>
						 <h3>總價:{{total}}
						 </h3>
						 <button class="btnl btn-add" v-on:click="selectadd()">登入</button>
						<button class="btnl btn-add" v-on:click="location.href='<%=request.getContextPath()%>/login.do?requestURL=<%=request.getServletPath()%>'">登出</button>
						Usersid:{{updatelist.users_id}}
						 <button class="btnl btn-cancel" v-on:click="orderadd()">建立訂單</button>
						 </header>
						  </div>
						  
					
						 <table class="table-users table-hover ">
							<thead>
							<tr>
								<th>圖片</th>
								<th>商品名稱</th>
								<th>價格</th>
								<th>刪除</th>
								<th>數量</th>
							</tr>
							</thead>
							<tr v-for="(item, index) in orderdeatillist" >
							<td><img class="img" id="preimg"
									v-bind:src="item.psrc" /></td>
								<td>{{ item.productName }}</td>
								<td>{{ item.age }}</td>
								<td>
									<button class="btn-delete btnl" v-on:click="orderremove(index)">del</button>
								</td>
								<td>
									<button class="btnl btn-add" v-on:click="countup(index)">
										+</button>

								<h7>{{ item.count }}</h7>

									<button class="btnl btn-add" v-on:click="countdown(index)">
										-</button>
								</td>
							</tr>
						</table>
			
						
						
						  </div>
						  						<div id="light" class="white_content">
						
		
							</br>輸入物品配送地址
						  <input class="form-control"  name="address" placeholder="address" v-model="updatelist.address">
						  </br>選擇付款方式
						  <input class="form-control"  name="card_number" placeholder="card_number" v-model="updatelist.card_number">
						  </br>						  
						  <button class="btnl btn-cancel"  v-on:click="submitorder()">建立訂單</button>				
						

							<button class="btnl btn-cancel" 
								onclick="document.getElementById('light').style.display='none';document.getElementById('fade1').style.display='none'">取消</button>

						</div>
							<section class="">
							
						<div id="lightadd" class="white_content glass">
<!-- 							帳號：<input type="text" v-model="name"> <br />  -->
<!-- 							密碼：<input type="text" v-model="age"><br /> -->
<!-- 							<button v-on:click="login()">登入</button> -->
<!-- 							<button -->
<!-- 								onclick="document.getElementById('lightadd').style.display='none';document.getElementById('fade').style.display='none'">取消</button> -->
						
						
										<form METHOD="post"
										ACTION="<%=request.getContextPath()%>/login.do" id="form1">
										<div class="form-group">
							
														<h10 id="errormessage" style="color:red">${message}</h10>
								
											<input class="form-control form-control-user" type="email" name="u_id"
												id="email" placeholder="Email Address" v-model="account">
										</div>
										<div class="form-group">
										<input
												class="form-control" type="password" name="pwd"
												id="password" placeholder="Password" v-model="password">
										</div>
										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" type="checkbox"
													id="checkbox"><label class="form-check-label"
													for="checkbox">Remember me</label>
											</div>
										</div>
										<input class="btnl btn-cancel"  
										@click="send"
											type="button" value="Log In"> 
											<input type="hidden"
											name="action" value="userlogin">
<!-- 											<input type="hidden" -->
<!-- 											name="arraysize" v-model="updatelist.arraysize"> -->
											
<!-- 										<input type="hidden" name="array" value="1" id="checkedIdArray"> -->


											
											 <input type="hidden"
											name="requestURL" value="<%=request.getServletPath()%>">
									</form>
													<button class="btnl btn-cancel" 
								onclick="document.getElementById('lightadd').style.display='none';document.getElementById('fade').style.display='none'">取消</button>
						
						</div>
							</section>	
					</div>
				</div>

    </main>
 <%@ include file="/front-end/pages/footer.html" %>
 <%@ include file="/front-end/pages/script.html" %>

	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>

var vm = new Vue({
    el: '#app',
    data: {
    	prescrolly:0,
    	scrolly:1,
    	productcount:10,
    	pricelowbound:0,
    	priceupbound:10000,
    	pricemax:1000,
    	pricemin:100,
    	pagemax:9,
    	x:1,
    	n:7,
    	oderset: new Set([]),//購物車
    	updatelist:{
    		address:'',
    		arraysize:0,
    		array:[
                { 
                	name: '1',
                	age: 25,
                	count:1,	
                },
    		],
    		action:"insertajax",
    		users_id:${(usersVO==null)?0:usersVO.users_id },
    		card_number:'',
    		order_status:0,
    		pwd:'',
	        u_id:'' 
    	},
    	account:'',
    	password:'',
    	searchName:'',
    	orderType:0,//0原本1升2降
        name: '',
        age: '',
        updatename: '',
        updateage: '',
        updateindex:-1,
        orderdeatillist:[//購物車2-2 
        ],
        fflist:[
        	{ name: 'aaa', age: 25 },
        	],
        list: [
        	<c:forEach var="empVO" items="${pSvc.all}">
           	{
        		productName:'${empVO.product_name}',
        		name:'${empVO.product_id}',
        		productSrc:'<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p',
        		productStatus:'${empVO.product_status}',
        		productContent:'${empVO.product_content}',
				productDescription:'${empVO.product_description}',
				productCategories:'${empVO.product_categories}',
				age:'${empVO.product_price}',
				productStock:'${empVO.product_stock}',
				productCreateTime:'<fmt:formatDate value="${empVO.product_update_time}"
					pattern="yyyy-MM-dd" />',
				productSale:'<c:forEach var="deptVO" items="${deptSvc.all}"><c:if test="${empVO.sale_id==deptVO.sale_id}">${deptVO.sale_id}【${deptVO.sale_name} - ${deptVO.sale_phone}】</c:if></c:forEach>',
        		productSaleId:'${empVO.sale_id}'
        	},
		</c:forEach>
        ]
    },
    mounted: function(){
    //	window.addEventListener('scroll', this.handleScroll, true)
    }
    ,
    methods: {
//     	handleScroll () {
//     		this.scrolly=window.scrollY;
//     		if((this.scrolly-this.prescrolly)>116){
//     			this.n=this.n+2;
//     			this.prescrolly=this.scrolly;
//     		}
//     		if(this.scrolly<100){
//     			this.prescrolly=0;
//     			this.n=6;
//     		}
//     		},
    	orderadd(){
    		if(this.oderset.size>0){
    			
    		document.getElementById('light').style.display='block';
    		document.getElementById('fade1').style.display='block';
    		}else{
    			alert("購物車內空無一物");
    		}
    	},
    	scheck(){
    		if(this.searchName==''){
    			this.pricemax=this.priceupbound;
    			this.pricemin=this.pricelowbound;
    		}
    	},
    	pageadd(){
    		if(this.x<Math.ceil(this.pagemax/this.n)){
    		this.x++;
    		}
    	},
    	pagepre(){
    		if(this.x>1){
    		this.x--;    			
    		}
    	},
    	send(){
    		let self=this;
			if (this.account !== '' && this.password !== '') {
				$("#errormessage").show();
				$("#errormessage").text("");
				let uid=this.account;
				let pwd=this.password;
					console.log(uid);
		    		this.updatelist.action="userlogin";
		    		this.updatelist.pwd=pwd;
		    		this.updatelist.u_id=uid;
		       		this.updatelist.array=this.orderdeatillist;
		    		this.updatelist.arraysize=this.updatelist.array.length;
// 		    		$("#checkedIdArray").val(this.orderdeatillist);
// 			  	let data = {
// 		  			"action": "userlogin",
// 		  			"pwd": pwd,
// 			           "u_id":uid 
// //			            "users_id": login_users
// 		  				}		
// console.log($(this).parent(".card-body").find(".place_id_value").attr("value"));
// console.log($(this).parent(".card-body").find(".users_id_value").attr("value"));
			
			$.ajax({
		        url: "<%=request.getContextPath()%>/login.do",           // 資料請求的網址
		        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		        async: false,
		        data: this.updatelist,               // 傳送資料到指定的 url
		        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		        success: function (data) {      // request 成功取得回應後執行
		          console.log(data);
		          
		        	$("#errormessage").text(data.errormsg);
		        	$("#errormessage").fadeOut(1300);
		          if(data.hasVO ==false){
								if(self.oderset.size>0&&data.uVO==true){
									   self.updatelist.users_id =data.uid;
									document.getElementById('lightadd').style.display='none';document.getElementById('fade').style.display='none';
								}else{
									
												$("#form1").submit();
								}
		          }
//			          else if(data.result == "delete_success"){
//			        	  that.html("加入收藏")
//			          }
		          
		        }
		    });
			} else {
				$("#errormessage").show();
				$("#errormessage").text('資料尚未輸入完全');
				$("#errormessage").fadeOut(1300);
				
			}
    	},
    	submitorder(){
    		let self=this;
    		if(this.oderset.size>0){
    			if(this.updatelist.users_id!=0){
    				if(this.updatelist.address!=''&&this.updatelist.card_number!=''){
    					
    	    		this.updatelist.action="insertajax";
    		this.updatelist.array=this.orderdeatillist;
    		this.updatelist.arraysize=this.updatelist.array.length;
     		console.log(this.updatelist.array);//假如是從這觸發的array中已有資料
			$.ajax({
		        url: "<%=request.getContextPath()%>/Order/Order.do",           // 資料請求的網址
		        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		        data:this.updatelist,               // 傳送資料到指定的 url
		        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		        success: function (data) {      // request 成功取得回應後執行
		          console.log(data);
		        	  $("#form1").submit();
		          self.orderdeatillist.splice(0);
		          self.oderset.clear();
		          if(data.result == "insert_succss"){
		        	  // 新增成功則按鈕改成顯示取消收藏
//			        	  that.html("取消收藏")
		          }else if(data.result == "delete_success"){
//			        	  that.html("加入收藏")
		          }
		          
		        }
		    });
    				}else{
    					alert('資料不完備');
    				}
     		
     		
    			}else{
    				
    				//登入
    				document.getElementById('lightadd').style.display='block';document.getElementById('fade').style.display='block';
    			}
			
    		}else{
    			alert("購物車內空無一物");
    		}
    	},
    	setOrderType(t){
    		this.orderType=t;
    	},
    	countup(index){
    		if(this.orderdeatillist[index].count<this.orderdeatillist[index].max){
    		this.orderdeatillist[index].count++;
    			
    		}
    	},
    	countdown(index){
    		if(this.orderdeatillist[index].count>1){
    		this.orderdeatillist[index].count--;
    			
    		}
    	},
        add() {
            this.list.push({
                name: this.name,
                age: this.age
            });
        	
            document.getElementById('lightadd').style.display='none';document.getElementById('fade').style.display='none';
        },
        remove(index) {
            this.list.splice(index, 1);
        },
        orderremove(index){
        	this.oderset.delete(this.orderdeatillist[index].name);
        	this.orderdeatillist.splice(index, 1);
       		this.updatelist.array=this.orderdeatillist;
    		this.updatelist.arraysize=this.updatelist.array.length;
        },
        addtoorder(index){
        	if(!this.oderset.has(this.fflist[index].name)){
        		this.oderset.add(this.fflist[index].name);
 console.log(this.fflist[index].name);//p_id
            this.orderdeatillist.push({
            	productName: this.fflist[index].productName,
                name: this.fflist[index].name,
                age: this.fflist[index].age,
                max:this.fflist[index].productStock,
                psrc:this.fflist[index].productSrc,
                count:1
            });
       		this.updatelist.array=this.orderdeatillist;
    		this.updatelist.arraysize=this.updatelist.array.length;
        	}
        },
        select(index){
        	document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block';
        	this.updatename=this.fflist[index].name;
        	this.updateage=this.fflist[index].age;
        	this.updateindex=index;
        },
        update(){
        	this.fflist[this.updateindex].name=this.updatename;
        	this.fflist[this.updateindex].age=this.updateage;
        	document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none';
        },
        selectadd(){
        	document.getElementById('lightadd').style.display='block';document.getElementById('fade').style.display='block';
        }
    },
    computed:{
   
     	px(){
     		const {n,pagemax}=this;
     		let pxx=this.x;
     		let y=Math.ceil(pagemax/n);
     		if(y==0){
     			pxx=y+1;
     		  	this.x=pxx;
     		}else if(this.x>y){
            	pxx=1;
            	this.x=pxx;
            }
     		if(this.pricelowbound>this.pricemax ||this.pricemax>this.priceupbound){
     			this.pricemax=this.priceupbound;
     		}
     		if(this.pricemin>this.priceupbound ||this.pricemin<this.pricelowbound){
     			this.pricemin=this.pricelowbound;
     		}
     		if(this.n>this.pagemax && this.pagemax>3){
     			this.n=this.pagemax;	
     		}
     		return pxx;
     	},
    	filterlist () {
    		const {searchName,list,orderType,x,n,pricemax,pricemin} = this;
    		let self=this;
    		let flist;
    		let begin=x*n-(n);
    		let end=x*n;
//     		console.log(this.list.slice(1,this.list.length));
//     		flist = list.filter(p => true)
    		flist = list.filter(p => (p.productName.indexOf(searchName)!==-1||
    				p.productCategories.indexOf(searchName)!==-1)
    				);
    		self.productcount=flist.length;
    		
//     		flis.forEach(max => (parseInt(max.age)>parseInt(this.priceupbound)){
//     			this.priceupbound=parseInt(max.age);
//     		});
			if(flist.length==0){
				this.priceupbound=5000;
				this.pricelowbound=0;
			}else{
				
			this.priceupbound=Math.max(...flist.map(p => p.age));
			this.pricelowbound=Math.min(...flist.map(p => p.age));
			}
    		
    		flist = flist.filter(p =>
    		parseInt(p.age)<=parseInt(pricemax) &&
			parseInt(p.age)>=parseInt(pricemin)
    		);
    		
    		self.pagemax=flist.length;

    		//排序
    		if(orderType!==0){
    			flist.sort(function(p1,p2){//如果sort(p1,p2)返回負數,p1在前
    				//1升2降
    				if(orderType===2){
    				return p2.age-p1.age //正數為p2大	
    				}else{
    				return p1.age-p2.age //正數為p2大	    					
    				}
    				
    			})
    		}
    		flist =flist.slice(begin,end);
    		self.fflist=flist.slice();
//     		console.log(self.fflist);
    		return flist
    	},
    	total: function(){
    		let count=0;
    		this.orderdeatillist.forEach(o =>count= o.age*o.count +count);
    		return count
    	}
    }
})

</script>
</body>

</html>