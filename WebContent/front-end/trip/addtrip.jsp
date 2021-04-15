<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.net.*" %>
<jsp:useBean id="pSvc" scope="page" class="com.place.model.PlaceService" />
<%TripVO tripVO = (TripVO) request.getAttribute("tripVO");%>
<!DOCTYPE html>
<html>

<head>
    <title>addtrip - font-end</title>
     <%@ include file="/front-end/pages/links.html" %> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/tripstyles.css">
</head>

<body>
    <%@ include file="/front-end/pages/headNav.html" %>
<!--  <button @click="setCookie('ABA','10',1)">set</button> -->
<!--  <button @click="getCookie('peter')">get</button> -->
    
    <main id="app" class="page  glass">
    <div id="fade" class="black_overlay" style="display:block; min-height: 100%;">
    
						<div id="tripadd" class="white_content glass" style="display:block;">
				行程名稱:<br/>
				<input
				class="" type="text" name="trip_name"
				placeholder="為你的行程取個名子" v-model="addtrip.trip_name">
				 <br/>
				 		開始時間:
				<input
							class="" type="text" name="trip_start"
							id="f_date1">
							
				結束時間:
				<input
							class="" type="text" name="trip_end"
							id="f_date2">
				 <br/>
				 出遊類型:
				<input
				class="" type="text" name="trip_name"
				v-model="addtrip.trip_type">
				 <br/>
				<textarea class="form-control" id="signature" rows="4"
				placeholder="描述此次行程您最期待的部分"
												name="signature" v-model="addtrip.trip_description"></textarea>

					<br/>
	<button class="btnl btn-cancel"  
	@click="submitTrip()">確定</button>
						
						</div>
						
			<div id="choicePlace" class="white_content glass">
					<input placeholder="搜尋" class="" type="text">
					<table class=" table   table-place" style="height:470px;" >
					<tr>
							<th>
							
							</th>
					</tr>
					<tr v-for="(item, index) in placelist" >
						<td>
						{{item.place_name}}
						</td>
						<td>
						<button @click="tripDetailAdd(item)">add</button>
						</td>
					</tr>
					</table>
					<button onclick="document.getElementById('choicePlace').style.display='none';document.getElementById('fade').style.display='none';">取消</button>
			</div>			
			
			<div id="setTripDetail" class="white_content glass">
							 				<input
				class="" type="text" name="trip_name"
				placeholder="行程內容"
				v-model="tripDetail.trip_content">
							 <br/>
		
				開始時間:<input type="time"  id="time1" />
				結束時間:<input type="time" id="time2"  />
				花費:<input type="text" v-model="tripDetail.trip_cost"/>臺幣
				
					<br/>
				 <br/>	
				<textarea class="form-control" id="signature" rows="4"
				placeholder="備註"
												name="signature" v-model="tripDetail.trip_remarks"></textarea>
				
				
				 <br/>
					<button v-if="!editshow&&!DetailSplice" @click="sendDetailEditToList()">確認</button>
					<button v-if="editshow" @click="sendDetailEditToList2()">編輯</button>
					<button v-if="DetailSplice" @click="sendDetailSpliceToList()">插入</button>

					<button @click="fadeCancel">取消</button>
			</div>			

    </div>

            <div class="container-fluid" style="margin:0px;">
                <div class="block-heading">
                    <h2 class="text-info">addtrip</h2>
					</div>

            <div class="row" style="margin-right:0px; margin-left:0px; flex-wrap:wrap;">
            	

				 <div class="col-md-2 col-xl-2 mb-2 conn" style="height:500px;">
				若4/15-4/15為一天
				<ul v-for="(item,index) in daylist" class="list-group list-group-flush" style="border-radius: 2rem;">
					<li class="list-group-item">
							Day{{index+1}}
							<button class="btnl"  
	@click="delOneDay(index)">刪除</button>
					</li>
				</ul>
<button class="btnl btn-cancel"  
	@click="addOneDay">新增一天</button>
				 </div>
				 
				 
					<div class="col-md-7 col-xl-7 mb-7 conn" style="height:500px;overflow-y: scroll;">
					<button class="btnl btn-cancel"  @click="submitTripDetailini">建立行程</button>
					<br/>總花費:
					<ul v-for="(item,index) in daylist" class="list-group list-group-flush footers" style="border-radius: 2rem;">
						 <li class="list-group-item footers">
							<table class="table-users">
												<tr>				
										<th>
										 D{{index+1}}
										</th>
										<th>
										      
										</th>
										<th>
										        
										</th>
								</tr>
								<tr v-for="(item, inde) in daylist[index].tripDetail" v-bind:key="item" draggable="true"
								 @dragstart="dragStart($event,index,inde)" @dragover="allowDrop"  @drop="drop2($event,index,inde)">
							
									<td>
									</td>
									<td>	
								
									{{item.trip_content}}<br/>
									{{item.trip_start_time}}~{{item.trip_end_time}}<br/>
									{{item.trip_remarks}}
						
									</td>
									<td>
								  	花費:{{item.trip_cost}}
									</td>
									
									<td>
									插入:
									<button @click="choicePlaceToDetailSplice(index,inde)">地點</button>/
									<button @click="choiceOtherToDetailSplice(index,inde)">其他</button>
									<button @click="tripDetailEdit(item,index,inde)">edit</button>	
									<button @click="tripDetailDel(index,inde)">del</button>
		
									</td>
									
								</tr>
					</table>
					</li>
						<li class="list-group-item"
								 @dragover="allowDrop"  @drop="drop2($event,index,daylist[index].tripDetail.length)">
							
						新增:
						<button @click="choicePlaceToDetail(index)">地點</button>/
						<button @click="choiceOtherToDetail(index)">其它</button>
						</li>
								  
					</ul>
					

					</div>
            </div>
        
    </main>

<%--  <%@ include file="/front-end/pages/footer.html" %> --%>
 <%@ include file="/front-end/pages/script.html" %>
<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>
	function dragstart_handler(ev) {
		 console.log("dragStart");
		 // Add the target element's id to the data transfer object
		 ev.dataTransfer.setData("text/plain", ev.target.id);
		}
	
	var vm = new Vue({
	    el: '#app',
	    data: {
	    	from:{
	    		index:0,
	    		inde:0,
	    	},
	    	DetailSplice:false,
	    	editshow:false,
	    	dayCount:1,
	    	daylist:[
 	    		{
 	    			day:1,
 	    			tripDetail:[
// 	    				{
// 	    		    		trip_day:'1',
// 	    		    		place_id:'1',
// 	    		    		trip_id:'1',
// 	    		    		trip_sort:1,
// 	    		    		trip_detail_type:'其他',
// 	    		    		trip_content:'',
// 	    		    		trip_start_time:'00:00',
// 	    		    		trip_end_time:'00:00',
// 	    		    		trip_remarks:'行程描述',
// 	    		    		trip_cost:'0',
// 	    		    		action:'insertajax',
// 	    		    		place_name:'',
// 	    		    		indexOfList:0,
// 	    				},
	    			],
 	    		},
	    	],
	    	theTrip_id:0,
	    	detailUpdateCount:0,
	    	tripDetaillist:[
	    		{
	    			day:1,

	    		},
	    		
	    	],
	    	tripDetail:{
	    		trip_day:'1',
	    		place_id:'1',
	    		trip_id:'1',
	    		trip_sort:1,
	    		trip_detail_type:'其他',
	    		trip_content:'',
	    		trip_start_time:'',
	    		trip_end_time:'',
	    		trip_remarks:'',
	    		trip_cost:'0',
	    		action:'insertajax',
	    		place_name:'',
	    		indexOfList:0,
	    	},
	    	addtrip:{
	    		users_id:${(usersVO==null)?1:usersVO.users_id },
	    		last_editor:${(usersVO==null)?1:usersVO.users_id },
	    		trip_start:'',
	    		trip_end:'',
	    		trip_name:'',
	    		trip_state:'1',
	    		read_authority:'1',
	    		edit_authority:'1',
	    		trip_area:'台北',
	    		trip_description:'行程描述',
	    		trip_type:'未設定',
	    		trip_tot_cost:'0',
	    		place_weather:'正常',
	    		action:'insertajax',
	    	},
	    	placelist:[
	    		<c:forEach var="placeVO" items="${pSvc.all}">
	    		{
	    			place_id:'${placeVO.place_id}',
	    			place_name:'${placeVO.place_name}',
	    			
	    		},
	    		</c:forEach>
	    	],
	    },
	    methods: {
	    	   allowDrop(e){
	               e.preventDefault();
	              // console.log(e);
	           },
	    	//开始拖动
	    	dragStart(e, index,inde){
	    	    e.dataTransfer.setData('Text', index);
	    	    e.dataTransfer.setData('Text', inde);
	    	    this.from.index=index;
	    	    this.from.inde=inde;
	  
	    	},
	    	//放置
	    	drop2(e, index,inde){
	    		let self=this;
	    	    //取消默认行为
 	    	    this.allowDrop(e);
 	    	   e.dataTransfer.setData('Text', index);
 	    	   e.dataTransfer.setData('Text', inde);

 	 

 	   		if(parseInt(this.from.index,10)==parseInt(index,10)&&parseInt(inde,10)==0){
 	   			let VO=this.daylist[index].tripDetail[inde];
 				this.daylist[index].tripDetail.splice(inde,0,this.daylist[this.from.index].tripDetail[this.from.inde]);
 				this.daylist[this.from.index].tripDetail.splice(this.from.inde+1, 1);
 	   		}else{
			this.daylist[index].tripDetail.splice(inde,0,this.daylist[this.from.index].tripDetail[this.from.inde]);
			this.daylist[this.from.index].tripDetail.splice(this.from.inde, 1);
 	   		}

// 	    		trip_day:'1',
// 	    		trip_id:'1',
// 	    		trip_sort:1,
// 	    		trip_detail_type:'景點',
// 	    		place_id:this.tripDetail.place_id,
// 	    		trip_content:this.tripDetail.trip_content,
// 	    		trip_start_time:this.tripDetail.trip_start_time,
// 	    		trip_end_time:this.tripDetail.trip_end_time,
// 	    		trip_remarks:this.tripDetail.trip_remarks,
// 	    		trip_cost:this.tripDetail.trip_cost,
// 	    		action:'insertajax',
// 	    		place_name:this.tripDetail.place_name,
// 	    		indexOfList:0,
		
 	   		
	    	},
	    	//放置
	    	drop(e, index){
	    
	    	    //取消默认行为
 	    	    this.allowDrop(e);
 	    	   e.dataTransfer.setData('Text', index);
 	   		console.log(index);
// 	    	    //使用一个新数组重新排序后赋给原变量
// 	    	    let arr = this.lists.concat([]),
// 	    	        dragIndex = e.dataTransfer.getData('Text');
// 	    	        temp = arr.splice(dragIndex, 1);
	    	    
// 	    	    arr.splice(index, 0, temp[0]);
// 	    	    this.lists = arr;
	    	},
// 	    	setCookie(cname,cvalue,exdays){
// 	    	    var d = new Date();
// 	    	    d.setTime(d.getTime()+(exdays*24*60*60*1000));
// 	    	    var expires = "expires="+d.toGMTString();
// 	    	    document.cookie = cname+"="+cvalue+"; "+expires;
// 	    	},
// 	    	getCookie(cname){
// 	      		console.log("123")
// 	    		console.log(document.cookie)
// 	    	    var name = cname + "=";
// 	    	    var ca = document.cookie.split(';');
// 	    	    for(var i=0; i<ca.length; i++) {
// 	    	        var c = ca[i].trim();
// 	    	        if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
// 	    	    }
// 	    	    return "";
// 	    	},
// 	    	pageadd(){
// 	    		if(this.x<Math.ceil(this.pagemax/this.n)){
// 	    		this.x++;
// 	    		}
// 	    	},
// 	    	pagepre(){
// 	    		if(this.x>1){
// 	    		this.x--;    			
// 	    		}
// 	    	},
// 			checkvalue(){
// 				this.addtrip.trip_start=$('#f_date1').val();
// 				this.addtrip.trip_end=$('#f_date2').val();
// 				console.log(this.addtrip.trip_name);
// 			},

				choiceOtherToDetailSplice(index,inde){
					document.getElementById('setTripDetail').style.display='block';
					document.getElementById('fade').style.display='block';
					this.dayCount=index;
					this.tripDetail.place_id=1;
					this.tripDetail.indexOfList=inde;
					this.DetailSplice=true;
				},
			choiceOtherToDetail(index){//-------------------------------------------------------------------3
				document.getElementById('setTripDetail').style.display='block';
				document.getElementById('fade').style.display='block';
				this.dayCount=index;
				this.tripDetail.place_id=1;
			},
			choicePlaceToDetailSplice(index,inde){
				document.getElementById('choicePlace').style.display='block';
				document.getElementById('fade').style.display='block';
				this.dayCount=index;//為了知道是第幾天的行程(天實為daylist.index+1)
				this.tripDetail.indexOfList=inde;//其實這幾個可以合在一起,但分開比較直覺
				this.DetailSplice=true;
			},
			choicePlaceToDetail(index){
				document.getElementById('choicePlace').style.display='block';
				document.getElementById('fade').style.display='block';
				this.dayCount=index;//為了知道是第幾天的行程(天實為daylist.index+1)
			},
			delOneDay(index){
				 this.daylist.splice(index, 1);	
			},
			addOneDay(){
	            this.daylist.push({
	            	day:this.daylist.length+1,
	            	tripDetail:[],
	            });
	           
			},
			sendDetailSpliceToList(){
				this.tripDetail.trip_start_time=$('#time1').val()+':00';
				this.tripDetail.trip_end_time=$('#time2').val()+':00';
				
				this.daylist[this.dayCount].tripDetail.splice(this.tripDetail.indexOfList,0,{
		    		trip_day:'1',
		    		trip_id:'1',
		    		trip_sort:1,
		    		trip_detail_type:'景點',
		    		place_id:this.tripDetail.place_id,
		    		trip_content:this.tripDetail.trip_content,
		    		trip_start_time:this.tripDetail.trip_start_time,
		    		trip_end_time:this.tripDetail.trip_end_time,
		    		trip_remarks:this.tripDetail.trip_remarks,
		    		trip_cost:this.tripDetail.trip_cost,
		    		action:'insertajax',
		    		place_name:this.tripDetail.place_name,
		    		indexOfList:0,
				});

				
				
		    			this.tripDetail.trip_day='1',
		    			this.tripDetail.place_id='1',
		    			this.tripDetail.trip_id='1',
		    			this.tripDetail.trip_sort=1,
		    			this.tripDetail.trip_detail_type='其他',
		    			this.tripDetail.trip_content='',
		    			this.tripDetail.trip_start_time='',
		    			this.tripDetail.trip_end_time='',
		    			$('#time1').val("");
						$('#time2').val("");
		    			this.tripDetail.trip_remarks='',
		    			this.tripDetail.trip_cost='0',
		    			this.tripDetail.action='insertajax',
		    		this.tripDetail.place_name='',
		    		this.tripDetail.indexOfList=0;
	
		    			this.DetailSplice=false;
				document.getElementById('setTripDetail').style.display='none';
				document.getElementById('fade').style.display='none';
			},
			sendDetailEditToList(){//-------4------小心潛層複製的問題
				this.tripDetail.trip_start_time=$('#time1').val()+':00';
				this.tripDetail.trip_end_time=$('#time2').val()+':00';

				this.daylist[this.dayCount].tripDetail.push({
		    		trip_day:'1',
		    		trip_id:'1',
		    		trip_sort:1,
		    		trip_detail_type:'景點',
		    		place_id:this.tripDetail.place_id,
		    		trip_content:this.tripDetail.trip_content,
		    		trip_start_time:this.tripDetail.trip_start_time,
		    		trip_end_time:this.tripDetail.trip_end_time,
		    		trip_remarks:this.tripDetail.trip_remarks,
		    		trip_cost:this.tripDetail.trip_cost,
		    		action:'insertajax',
		    		place_name:this.tripDetail.place_name,
		    		indexOfList:0,
				});
				
				
		    			this.tripDetail.trip_day='1',
		    			this.tripDetail.place_id='1',
		    			this.tripDetail.trip_id='1',
		    			this.tripDetail.trip_sort=1,
		    			this.tripDetail.trip_detail_type='其他',
		    			this.tripDetail.trip_content='',
		    			this.tripDetail.trip_start_time='',
		    			this.tripDetail.trip_end_time='',
		    			$('#time1').val("");
						$('#time2').val("");
		    			this.tripDetail.trip_remarks='',
		    			this.tripDetail.trip_cost='0',
		    			this.tripDetail.action='insertajax',
		    		this.tripDetail.place_name='',
		    		this.tripDetail.indexOfList=0;

				document.getElementById('setTripDetail').style.display='none';
				document.getElementById('fade').style.display='none';
			},
			sendDetailEditToList2(){//-------5
				
				this.tripDetail.trip_start_time=$('#time1').val()+':00';
				this.tripDetail.trip_end_time=$('#time2').val()+':00';			
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_content=this.tripDetail.trip_content;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].place_name=this.tripDetail.place_name;
					
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_day=this.tripDetail.trip_day;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].place_id=this.tripDetail.place_id;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_id=this.tripDetail.trip_id;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_sort=this.tripDetail.trip_sort;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_detail_type=this.tripDetail.trip_detail_type;
	    
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_start_time=this.tripDetail.trip_start_time;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_end_time=this.tripDetail.trip_end_time;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_remarks=this.tripDetail.trip_remarks;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_cost=this.tripDetail.trip_cost;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].action=this.tripDetail.action;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].indexOfList=this.tripDetail.indexOfList;
					
	    			$('#time1').val("");
					$('#time2').val("");
		
	    			this.tripDetail.trip_day='1',
	    			this.tripDetail.place_id='1',
	    			this.tripDetail.trip_id='1',
	    			this.tripDetail.trip_sort=1,
	    			this.tripDetail.trip_detail_type='其他',
	    			this.tripDetail.trip_content='',
	    			this.tripDetail.trip_start_time='',
	    			this.tripDetail.trip_end_time='',

	    			this.tripDetail.trip_remarks='',
	    			this.tripDetail.trip_cost='0',
	    			this.tripDetail.action='insertajax',
	    		this.tripDetail.place_name='',
	    		this.tripDetail.indexOfList=0;

					console.log(this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_content)


				document.getElementById('setTripDetail').style.display='none';
				document.getElementById('fade').style.display='none';
				this.editshow=false;
			},
			fadeCancel(){
				$('#time1').val("");
				$('#time2').val("");
    			this.tripDetail.trip_day='1',
    			this.tripDetail.place_id='1',
    			this.tripDetail.trip_id='1',
    			this.tripDetail.trip_sort=1,
    			this.tripDetail.trip_detail_type='其他',
    			this.tripDetail.trip_content='',
    			this.tripDetail.trip_start_time='',
    			this.tripDetail.trip_end_time='',
		
    			this.tripDetail.trip_remarks='',
    			this.tripDetail.trip_cost='0',
    			this.tripDetail.action='insertajax',
    		this.tripDetail.place_name='',
    		this.tripDetail.indexOfList=0;
				document.getElementById('setTripDetail').style.display='none';
				document.getElementById('fade').style.display='none';
				this.editshow=false;
				this.DetailSplice=false;
			},
			tripDetailEdit(item,index,inde){//--------------------------------------------2...
				this.editshow=true;
				this.act=0;//--edit
					
				this.tripDetail.trip_content=item.trip_content;		
				this.tripDetail.place_name=item.place_name;	
				this.tripDetail.trip_day=item.trip_day;
				this.tripDetail.trip_id=item.trip_id;
				this.tripDetail.trip_sort=item.trip_sort;
				this.tripDetail.trip_detail_type=item.trip_detail_type;
				this.tripDetail.place_id=item.place_id;
	    		this.tripDetail.trip_start_time=item.trip_start_time;
	    		this.tripDetail.trip_end_time=item.trip_end_time;
	    		this.tripDetail.trip_remarks=item.trip_remarks;
	    		this.tripDetail.trip_cost=item.trip_cost;
	    		this.tripDetail.action=item.action;
	    		this.tripDetail.indexOfList=item.indexOfList;

				this.dayCount=index;
				this.tripDetail.indexOfList=inde;
				$('#time1').val(item.trip_start_time);
				$('#time2').val(item.trip_end_time);
				document.getElementById('setTripDetail').style.display='block';
				document.getElementById('fade').style.display='block';
			},
			tripDetailDel(inde,index){
				console.log(inde+" "+index);
				this.daylist[inde].tripDetail.splice(index, 1);
			},
			tripDetailAdd(e){ //這裡輸入時間的item1/2 是id 不一起用會有麻煩----------------------1
				this.act=1;
	            document.getElementById('choicePlace').style.display='none';
	            document.getElementById('setTripDetail').style.display='block';
	            document.getElementById('fade').style.display='block';
	            this.tripDetail.place_name=e.place_name;
	            this.tripDetail.trip_content=e.place_name;
	            this.tripDetail.place_id=e.place_id;
			},
			
			submitTripDetailini(){
				let self=this;
				this.daylist.forEach(function(item, i) {
					  item.tripDetail.forEach(function(jtem, j) {
						  jtem.trip_day=i+1;//要是這樣送.大概會因為呼叫過快而失敗
						  jtem.trip_id=self.theTrip_id;
						  jtem.action='insertajax';
						  jtem.trip_id=self.theTrip_id;
						 // console.log(i+'....'+j);
					  });
					});
				//console.log(this.daylist);
				this.dayCount=this.daylist.length;
				this.submitTripDetailj();
			},
			submitTripDetailj(){
				if(this.dayCount>=1){
				this.detailUpdateCount=this.daylist[this.dayCount-1].tripDetail.length;
				this.submitTripDetail();
				}
			},
			submitTripDetail(){  //行程細節送出 目前以submitTrip()觸發
				let self=this;
					

 
 				if(self.detailUpdateCount>=1){
					
 					self.detailUpdateCount--;
 					console.log(self.detailUpdateCount);
// 					self.tripDetaillist[self.detailUpdateCount].trip_id=self.theTrip_id;
					let updata=self.daylist[self.dayCount-1].tripDetail[self.detailUpdateCount];
					console.log(updata);
 				$.ajax({
 			        url: "<%=request.getContextPath()%>/trip_detail/trip_detail.do",           // 資料請求的網址
 			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
 			        async: false,
 			        data:updata,               // 傳送資料到指定的 url
 			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
 			        success: function (data) {      //這裡依照陣列長度(有幾個行程明細)呼叫自己
 			          console.log(data);
 			      	self.submitTripDetail();        
 			        }
 			    });
				
				
				}else{
					this.dayCount--;
					self.submitTripDetailj();       
				}
				
			},
			submitTrip(){
				let self=this;
				this.addtrip.trip_start=$('#f_date1').val();
				this.addtrip.trip_end=$('#f_date2').val();
				
				$.ajax({
			        url: "<%=request.getContextPath()%>/trip/trip.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        async: false,
			        data: this.addtrip,               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      // 這裡必須得到trip_id
			          
			        self.detailUpdateCount=self.tripDetaillist.length;
			        self.theTrip_id=data.trip_id;
			        console.log('trip_id='+self.theTrip_id);
			        //self.submitTripDetail();        
			        }
			    });
				document.getElementById('tripadd').style.display='none';
				document.getElementById('fade').style.display='none';
			},
	    },
	    computed:{
	    	
	    },
	    mounted: function(){
	    	
	    }
	})
	</script>

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date start = null;
  try {
	  start = tripVO.getTrip_start();
   } catch (Exception e) {
	   start = new java.sql.Date(System.currentTimeMillis());
   }
%>
<% 
  java.sql.Date end = null;
  try {
	  end = tripVO.getTrip_end();
   } catch (Exception e) {
	   end = new java.sql.Date(System.currentTimeMillis());
   }
%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=start%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=end%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>