<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*"%>
<%@page import="com.place.model.*"%>
<%@ page import="com.trip.model.*"%>
<%@ page import="java.net.*" %>
<jsp:useBean id="pSvc" scope="page" class="com.place.model.PlaceService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
<%TripVO tripVO = (TripVO) request.getAttribute("tripVO");%>
<!DOCTYPE html>
<html>

<head>
    <title>selectOneTrip</title>
     <%@ include file="/front-end/pages/links.html" %> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/assets/css/tripstyles.css">
</head>

<body>
    <%@ include file="/front-end/pages/headNav.html" %>
<!--  <button @click="setCookie('ABA','10',1)">set</button> -->
<!--  <button @click="getCookie('peter')">get</button> -->
    <main id="app" class="page glass">
<!--     <div id="fade" class="black_overlay" style="display:none; min-height: 100%;"> -->
    
<!-- 						<div id="tripadd" class="white_content glass" style="display:none;"> -->
<!-- 				行程名稱:<br/> -->
<!-- 				<input -->
<!-- 				class="" type="text" name="trip_name" -->
<!-- 				placeholder="為你的行程取個名子" v-model="addtrip.trip_name"> -->
<!-- 				 <br/> -->
<!-- 				 		開始時間: -->
<!-- 				<input -->
<!-- 							class="" type="text" name="trip_start" @click="clickdate" @blur="blurdate" -->
<!-- 							id="f_date1"> -->
							
<!-- 				結束時間: -->
<!-- 				<input -->
<!-- 							class="" type="text" name="trip_end" @click="clickdate" @blur="blurdate" -->
<!-- 							id="f_date2"> -->
<!-- 				 <br/> -->
<!-- 				 出遊類型: -->
<!-- 				<input -->
<!-- 				class="" type="text" name="trip_name" -->
<!-- 				v-model="addtrip.trip_type"> -->
<!-- 				 <br/> -->
<!-- 				<textarea class="form-control" id="signature" rows="4" -->
<!-- 				placeholder="描述此次行程您最期待的部分" -->
<!-- 												name="signature" v-model="addtrip.trip_description"></textarea> -->

<!-- 					<br/> -->
<!-- 	<button class="btnl btn-cancel"   -->
<!-- 	@click="submitTrip()">確定</button> -->
						
<!-- 						</div> -->
						
<!-- 			<div id="choicePlace" class="white_content glass"> -->
<!-- 					<input placeholder="搜尋" class=""  v-model="searchName" type="text"> -->
<!-- 					<table class=" table   table-place" style="height:470px;" > -->
<!-- 					<tr> -->
<!-- 							<th> -->
							
<!-- 							</th> -->
<!-- 					</tr> -->
<!-- 					<tr v-for="(item, index) in filterlist" > -->
<!-- 					<td> -->
<!-- 					<img class="img" id="preimg" -->
<!-- 									v-bind:src="item.place_pic" /> -->
<!-- 					</td> -->
<!-- 						<td> -->
<!-- 						{{item.place_name}} -->
<!-- 						</td> -->
<!-- 						<td> -->
<!-- 						<button @click="tripDetailAdd(item)">add</button> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 					</table> -->
<!-- 					<button onclick="document.getElementById('choicePlace').style.display='none';document.getElementById('fade').style.display='none';">取消</button> -->
<!-- 			</div>			 -->
			
<!-- 			<div id="setTripDetail" class="white_content glass"> -->
<!-- 					<img class="img" id="preimg" -->
<!-- 									v-bind:src="tripDetail.place_pic" />			 			 -->
<!-- 							 				<input -->
<!-- 				class="" type="text" name="trip_name" -->
<!-- 				placeholder="行程內容" -->
<!-- 				v-model="tripDetail.trip_content"> -->
<!-- 							 <br/> -->
		
<!-- 				開始時間:<input type="time"  id="time1" /> -->
<!-- 				結束時間:<input type="time" id="time2"  /> -->
<!-- 				花費:<input type="text" v-model="tripDetail.trip_cost"/>臺幣 -->
				
<!-- 					<br/> -->
<!-- 				 <br/>	 -->
<!-- 				<textarea class="form-control" id="signature" rows="4" -->
<!-- 				placeholder="備註" -->
<!-- 												name="signature" v-model="tripDetail.trip_remarks"></textarea> -->
				
				
<!-- 				 <br/> -->
<!-- 					<button v-if="!editshow&&!DetailSplice" @click="sendDetailEditToList()">確認</button> -->
<!-- 					<button v-if="editshow" @click="sendDetailEditToList2()">編輯</button> -->
<!-- 					<button v-if="DetailSplice" @click="sendDetailSpliceToList()">插入</button> -->

<!-- 					<button @click="fadeCancel">取消</button> -->
<!-- 			</div>			 -->

<!--     </div> -->

            <div class="container-fluid" style="margin:0px;">
                <div class="block-heading">
                    <h1 class="text-info">{{addtrip.trip_name}}</h1>
                    <h10>{{addtrip.trip_username}}</h10><br/>
                    {{addtrip.last_edit_time}}更新<br/>
                   
<!--                     		<input -->
<!-- 							class="" type="text" name="trip_start" @click="clickdate" @blur="blurdate" -->
<!-- 							id="f_date3"> -->
					</div>
					<input type="hidden" id="f_date3" value="addtrip.trip_start"/>
            <div class="row" style="margin-right:0px; margin-left:0px; flex-wrap:wrap;">
            	 

<!-- 				 <div class="col-md-2 col-xl-2 mb-2 conn" style="height:500px;overflow-y: scroll;"> -->
		
<!-- 				<ul v-for="(item,index) in daylist" class="list-group list-group-flush" style="border-radius: 2rem;"> -->
<!-- 					<li class="list-group-item"> -->
<!-- 							Day{{index+1}} -->
<!-- 							<button class="btnl"   -->
<!-- 	@click="delOneDay(index)">刪除</button> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- <button class="btnl btn-cancel"   -->
<!-- 	@click="addOneDay">新增一天</button> -->
<!-- 				 </div> -->
				 
				 
					<div class="col-md-7 col-xl-7 mb-7 conn" style="height:100%;">
					<h3>行程描述</h3>
					{{addtrip.trip_description}}
					<br/>共{{addtrip.place_weather}}天<br/>總花費:{{total}}<br/>
					<h3>行程內容</h3>
					<ul v-for="(item,index) in daylist" class="list-group list-group-flush footers" style="border-radius: 2rem;">
						 <li class="list-group-item footers">
							<table class="table-users">
												<tr>				
										<th>
										 D{{index+1}}
										</th>
										<th>
										       {{dayDate(index)}} 
										</th>
										<th>
										      
										</th>
								</tr>
								<tr v-for="(item, inde) in daylist[index].tripDetail" v-bind:key="item" draggable="true"
								 @dragstart="dragStart($event,index,inde)" @dragover="allowDrop"  @drop="drop2($event,index,inde)">
							
									<td>
								<img class="img" id="preimg"
									v-bind:src="item.place_pic" />
									</td>
									<td>	
								
									{{item.trip_content}}<br/>
									{{item.trip_start_time.slice(0,5)}}~{{item.trip_end_time.slice(0,5)}}<br/>
									{{item.trip_remarks}}
						
									</td>
									<td>
								  	花費:{{item.trip_cost}}
									</td>
									
<!-- 									<td> -->
<!-- 									插入: -->
<!-- 									<button @click="choicePlaceToDetailSplice(index,inde)">地點</button>/ -->
<!-- 									<button @click="choiceOtherToDetailSplice(index,inde)">其他</button> -->
<!-- 									<button @click="tripDetailEdit(item,index,inde)">edit</button>	 -->
<!-- 									<button @click="tripDetailDel(index,inde)">del</button> -->
		
<!-- 									</td> -->
									
								</tr>
					</table>
					</li>
<!-- 						<li class="list-group-item" -->
<!-- 								 @dragover="allowDrop"  @drop="drop2($event,index,daylist[index].tripDetail.length)"> -->
							
<!-- 						新增: -->
<!-- 						<button @click="choicePlaceToDetail(index)">地點</button>/ -->
<!-- 						<button @click="choiceOtherToDetail(index)">其它</button> -->
<!-- 						</li> -->
								  
					</ul>
					

					</div>
            </div>
        
    </main>

<%--  <%@ include file="/front-end/pages/footer.html" %> --%>
 <%@ include file="/front-end/pages/script.html" %>
<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>

	var vm = new Vue({
	    el: '#app',
	    data: {
	    	tripDetailList:[
	    		<c:forEach var="trip_detailVO" items="${listDetail_ByTrip}" >
					{
						trip_detail_id:'${trip_detailVO.trip_detail_id}',
						trip_day:'${trip_detailVO.trip_day}',
						trip_sort:'${trip_detailVO.trip_sort}',
						trip_detail_type:'${trip_detailVO.trip_detail_type}',
						trip_content:'${trip_detailVO.trip_content}',
						place_id:'${trip_detailVO.place_id}',
						trip_start_time:'${trip_detailVO.trip_start_time}',
						trip_end_time:'${trip_detailVO.trip_end_time}',
						trip_remarks:'${trip_detailVO.trip_remarks}',
						trip_cost:'${trip_detailVO.trip_cost}',
						place_pic:'<%=request.getContextPath()%>/place/DBGifReader4.do?place_id=${trip_detailVO.place_id}&place_pic=place_pic1',
					},
					</c:forEach>
	    	],
	    	show:0,
	    	searchName:'',
	    	startTime:'',
	    	from:{
	    		index:0,
	    		inde:0,
	    	},
	    	DetailSplice:false,
	    	editshow:false,
	    	dayCount:1,
	    	daylist:[
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
	    		trip_id:'${tripVO.trip_id}',
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
	    		place_pic:'',
	    	},
	    	addtrip:{
	    		users_id:'${tripVO.users_id}',
	    		last_editor:'${usersVO.users_id}',
	    		trip_start:'${tripVO.trip_start}',
	    		trip_end:'${tripVO.trip_end}',
	    		trip_name:'${tripVO.trip_name}',
	    		trip_state:'${tripVO.trip_state}',
	    		read_authority:'${tripVO.read_authority}',
	    		edit_authority:'${tripVO.edit_authority}',
	    		trip_area:'${tripVO.trip_area}',
	    		trip_description:'${tripVO.trip_description}',
	    		trip_type:'${tripVO.trip_type}',
	    		trip_tot_cost:'${tripVO.trip_tot_cost}',
	    		place_weather:'${tripVO.place_weather}',
	    		action:'updateAjax',
	    		trip_id:'${tripVO.trip_id}',
	    		last_edit_time:'<fmt:formatDate value="${tripVO.last_edit_time}"
				pattern="yyyy-MM-dd" />',
				trip_username:'${usersSvc.getOneusers(tripVO.users_id).users_name}',
	    	},
	    	placelist:[
	    		<c:forEach var="placeVO" items="${pSvc.all}">
	    		{
	    			place_id:'${placeVO.place_id}',
	    			place_name:'${placeVO.place_name}',
	    			place_pic:'<%=request.getContextPath()%>/place/DBGifReader4.do?place_id=${placeVO.place_id}&place_pic=place_pic1',
	    		},
	    		</c:forEach>
	    	],
	    },
	    methods: {
	    		blurdate(){//雖然可以做到更改日期,但失焦當作觸發條件還是有點不穩定
				
					this.addtrip.trip_end=$('#f_date2').val();
					//console.log("date1:"+this.addtrip.trip_start+"date2:"+this.addtrip.trip_end);
	    		},
	    		clickdate(){//按下去的當下也不能穩定的更改日期
	    		let somedate2=new Date($('#f_date1').val());
	    		let somedate1=new Date($('#f_date2').val());
	    	             $('#f_date2').datetimepicker({
	    	                 beforeShowDay: function(date) {
	    	               	  if (  date.getYear() <  somedate2.getYear() || 
	    	        		           (date.getYear() == somedate2.getYear() && date.getMonth() <  somedate2.getMonth()) || 
	    	        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() < somedate2.getDate())
	    	                     ) {
	    	                          return [false, ""]
	    	                     }
	    	                     return [true, ""];
	    	             }});

	    		},
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

				if(index> this.from.index){//往下一層
					this.daylist[index].tripDetail.splice(inde,0,this.daylist[this.from.index].tripDetail[this.from.inde]);
					this.daylist[this.from.index].tripDetail.splice(this.from.inde, 1);
				}else if( this.from.index>index){//往上一層
	 				this.daylist[index].tripDetail.splice(inde,0,this.daylist[this.from.index].tripDetail[this.from.inde]);
	 				this.daylist[this.from.index].tripDetail.splice(this.from.inde, 1);
				}else{
					if(inde>this.from.inde){//往下
						this.daylist[index].tripDetail.splice(inde+1,0,this.daylist[this.from.index].tripDetail[this.from.inde]);
						this.daylist[this.from.index].tripDetail.splice(this.from.inde, 1);					
					}else if(this.from.inde>inde){//往上
		 				this.daylist[index].tripDetail.splice(inde,0,this.daylist[this.from.index].tripDetail[this.from.inde]);
		 				this.daylist[this.from.index].tripDetail.splice(this.from.inde+1, 1);
					}else{
						
					}
					
				}

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
				let month = new Array(12);
				month[0] = "01";
				month[1] = "02";
				month[2] = "03";
				month[3] = "04";
				month[4] = "05";
				month[5] = "06";
				month[6] = "07";
				month[7] = "08";
				month[8] = "09";
				month[9] = "10";
				month[10] = "11";
				month[11] = "12";
				let btime=new Date($('#f_date3').val());
				let d=btime.getTime()+1000 * 60 * 60 * 24*this.daylist.length;
				let ctime=new Date(d);			
	            this.daylist.push({
	            	day:ctime.getFullYear()+'-'+month[ctime.getMonth()]+'-'+ctime.getDate(),
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
		    		place_pic:this.tripDetail.place_pic,
				});

		    			this.tripDetail.trip_day='1',
		    			this.tripDetail.place_id='1',
		    			this.tripDetail.trip_id='1',
		    			this.tripDetail.trip_sort=1,
		    			this.tripDetail.trip_detail_type='其他',
		    			this.tripDetail.trip_content='',
		    			this.tripDetail.trip_start_time='',
		    			this.tripDetail.trip_end_time='',
		    			this.tripDetail.place_pic='',
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
		    		place_pic:this.tripDetail.place_pic,
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
		    			this.tripDetail.place_pic='',
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
			sendDetailEditToList2(){//-------有遇到一個類似潛層複製的問題 但我也不確定...但這樣似乎沒問題就是了
				
				this.tripDetail.trip_start_time=$('#time1').val()+':00';//這個放到後端會比較好看
				this.tripDetail.trip_end_time=$('#time2').val()+':00';			
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_content=this.tripDetail.trip_content;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].place_name=this.tripDetail.place_name;
					
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_day=this.tripDetail.trip_day;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].place_id=this.tripDetail.place_id;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_id=this.tripDetail.trip_id;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_sort=this.tripDetail.trip_sort;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].trip_detail_type=this.tripDetail.trip_detail_type;
					this.daylist[this.dayCount].tripDetail[this.tripDetail.indexOfList].place_pic=this.tripDetail.place_pic;	    
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
	    			this.tripDetail.place_pic='',
	    			this.tripDetail.trip_remarks='',
	    			this.tripDetail.trip_cost='0',
	    			this.tripDetail.action='insertajax',
	    		this.tripDetail.place_name='',
	    		this.tripDetail.indexOfList=0;

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
    			this.tripDetail.place_pic='',
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
				this.tripDetail.place_pic=item.place_pic;	
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
				this.daylist[inde].tripDetail.splice(index, 1);
			},
			tripDetailAdd(e){ 
				this.act=1;
	            document.getElementById('choicePlace').style.display='none';
	            document.getElementById('setTripDetail').style.display='block';
	            document.getElementById('fade').style.display='block';
	            this.tripDetail.place_name=e.place_name;
	            this.tripDetail.trip_content=e.place_name;
	            this.tripDetail.place_id=e.place_id;
	            this.tripDetail.place_pic=e.place_pic;
			},
			
			submitTripDetailini(){//---------------------------------------------在這邊更新天數
				let self=this;
			
				let data={
						action:'updateDay',
						day:self.daylist.length+1,
						trip_id:self.theTrip_id,
				}
			
 				$.ajax({
 			        url: "<%=request.getContextPath()%>/trip/trip.do",           // 資料請求的網址
 			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
 			        async: false,
 			        data:data,               // 傳送資料到指定的 url
 			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
 			        success: function (data) {      //這裡依照陣列長度(有幾個行程明細)呼叫自己      
 			        }
 			    });
	
			
			
				this.daylist.forEach(function(item, i) {
					  item.tripDetail.forEach(function(jtem, j) {
						  jtem.trip_day=i;//要是在這裡送.大概會因為呼叫過快而失敗
						  jtem.trip_sort=j;
						  jtem.trip_id=self.addtrip.trip_id;
						  jtem.action='insertajax';
					
						 // console.log(i+'....'+j);
					  });
					});
				//console.log(this.daylist);
				this.dayCount=this.daylist.length;
				
				
				let data2={
						action:'delTDbyTid',
						trip_id:self.addtrip.trip_id,
				}
			
 				$.ajax({
 			        url: "<%=request.getContextPath()%>/trip/trip.do",           // 資料請求的網址
 			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
 			        async: false,
 			        data:data2,               // 傳送資料到指定的 url
 			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
 			        success: function (data) {      //這裡依照陣列長度(有幾個行程明細)呼叫自己 
 			        	console.log(data);
 			        	self.submitTripDetailj();
 			        }
 			    });	
				
				
				
				
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
				let month = new Array(12);
				month[0] = "01";
				month[1] = "02";
				month[2] = "03";
				month[3] = "04";
				month[4] = "05";
				month[5] = "06";
				month[6] = "07";
				month[7] = "08";
				month[8] = "09";
				month[9] = "10";
				month[10] = "11";
				month[11] = "12";
				let self=this;
				$('#f_date3').val($('#f_date1').val())
				this.addtrip.trip_start=$('#f_date1').val();
			if($('#f_date2').val()==''){
				let lctime=new Date();
				this.addtrip.trip_end=lctime.getFullYear()+'-'+month[lctime.getMonth()]+'-'+lctime.getDate()
			}else{
				this.addtrip.trip_end=$('#f_date2').val();
				
			}
				
				let endtime=new Date(this.addtrip.trip_end);

				let btime=new Date(this.addtrip.trip_start);
				let tripday=new Date(endtime-btime);
				let hour=Math.floor(tripday.getTime() / 3600000);

				let tday=0;
				if(hour!=0){
					tday=hour/24;
				}

				
	
				
			//	console.log(ctime);
				for(let n=0;n<=tday;n++){
					let d=btime.getTime()+1000 * 60 * 60 * 24*n;
					let ctime=new Date(d);
				self.daylist.push({
					day:ctime.getFullYear()+'-'+month[ctime.getMonth()]+'-'+ctime.getDate(),
					tripDetail:[],
				});

				}
				//console.log(self.daylist);
				if(self.daylist.length==0){
					self.daylist.push({
						day:$('#f_date1').val(),
						tripDetail:[],
					});
				}
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
			dayDate(index){
				let month = new Array(12);
				month[0] = "01";
				month[1] = "02";
				month[2] = "03";
				month[3] = "04";
				month[4] = "05";
				month[5] = "06";
				month[6] = "07";
				month[7] = "08";
				month[8] = "09";
				month[9] = "10";
				month[10] = "11";
				month[11] = "12";
				let btime=new Date($('#f_date3').val());
				let d=btime.getTime()+1000 * 60 * 60 * 24*index;
				let ctime=new Date(d);
				
				return ctime.getFullYear()+'-'+month[ctime.getMonth()]+'-'+ctime.getDate();
			},
	    },
	    computed:{
	    	filterlist () {
	    		const {searchName,placelist} = this;
// 	    		,orderType,x,n,pricemax,pricemin
	    		let self=this;
	    		let flist;
// 	    		let begin=x*n-(n);
// 	    		let end=x*n;
//	     		console.log(this.list.slice(1,this.list.length));
//	     		flist = list.filter(p => true)
	    		flist = placelist.filter(p => (p.place_name.indexOf(searchName)!==-1
// 	    				||p.productCategories.indexOf(searchName)!==-1
	    				)
	    				);
// 	    		self.productcount=flist.length;
	    		
//	     		flis.forEach(max => (parseInt(max.age)>parseInt(this.priceupbound)){
//	     			this.priceupbound=parseInt(max.age);
//	     		});
// 				if(flist.length==0){
// 					this.priceupbound=5000;
// 					this.pricelowbound=0;
// 				}else{
					
// 				this.priceupbound=Math.max(...flist.map(p => p.age));
// 				this.pricelowbound=Math.min(...flist.map(p => p.age));
// 				}
	    		
// 	    		flist = flist.filter(p =>
// 	    		parseInt(p.age)<=parseInt(pricemax) &&
// 				parseInt(p.age)>=parseInt(pricemin)
// 	    		);
	    		
// 	    		self.pagemax=flist.length;

	    		//排序
// 	    		if(orderType!==0){
// 	    			flist.sort(function(p1,p2){//如果sort(p1,p2)返回負數,p1在前
// 	    				//1升2降
// 	    				if(orderType===2){
// 	    				return p2.age-p1.age //正數為p2大	
// 	    				}else{
// 	    				return p1.age-p2.age //正數為p2大	    					
// 	    				}
	    				
// 	    			})
// 	    		}
// 	    		flist =flist.slice(begin,end);
// 	    		self.fflist=flist.slice();
//	     		console.log(self.fflist);
	    		return flist
	    	},
	    	total: function(){
	    		let count=0;
	    		
				this.daylist.forEach(function(item, i) {
					  item.tripDetail.forEach(function(jtem, j) {
						  count+=parseInt(jtem.trip_cost,10);
// 						  jtem.trip_day=i+1;//要是在這裡送.大概會因為呼叫過快而失敗
// 						  jtem.trip_id=self.theTrip_id;
// 						  jtem.action='insertajax';
// 						  jtem.trip_id=self.theTrip_id;
						 // console.log(i+'....'+j);
					  });
					});
	    		
	    		
	    		//this.orderdeatillist.forEach(o =>count= o.age*o.count +count);
	    		return count
	    	}
	    },
	    mounted: function(){//類似ini或onload	
	    	let self=this;
	    	$('#f_date3').val(this.addtrip.trip_start);
	    for(let n=0;n<this.addtrip.place_weather;n++){
	    	self.daylist.push(
	    	{
	    		day:n,
	    		tripDetail:[],
	    	},		
	    	);
	    let List=this.tripDetailList.filter(p =>
	    		p.trip_day==n
	    		);
	    if(List.length!=0){
	    	//console.log(List);
	    	List.forEach(p=> self.daylist[n].tripDetail.push(p)
	    			);
	    }
//	    	flist = flist.filter(p =>
// 	    		parseInt(p.age)<=parseInt(pricemax) &&
// 				parseInt(p.age)>=parseInt(pricemin)
// 	    		);
	    }
	    
	    
	    self.placelist=self.placelist.slice(10);
	    	$(function(){
	    		$("#f_date1").bind('change',function(){
	    			$('#f_date3').val($('#f_date1').val());
	    			self.addtrip.trip_start=$('#f_date1').val();
	    			
	    			let endtime=new Date($("#f_date2").val());
					let btime=new Date($("#f_date1").val());
					let tripday=new Date(endtime-btime);
					let hour=Math.floor(tripday.getTime() / 3600000);
	
					if(hour<0){
						$("#f_date2").val($("#f_date1").val());
					}
	    		//console.log("e");
	    		});
	    		$("#f_date2").bind('change',function(){
	    			self.addtrip.trip_end=$('#f_date2').val();
	    		//console.log("e");
	    		});
	    		$("#f_date3").bind('change',function(){
	    			$('#f_date1').val($('#f_date3').val());
	    			self.addtrip.trip_start=$('#f_date3').val();
	    			self.daylist.push('1');
	    			self.daylist.pop();
	    			
	    		
	    			//self.addtrip.trip_end=$('#f_date2').val();
	    		//console.log("e");
	    		});
	    		});
	    }
	})
	
	
//	console.log(vm.addtrip.place_weather);
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
	       value:'',
<%-- 		   value: '<%=end%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value:'',
<%-- 		   value: '<%=end%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
             var somedate1 = new Date();
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
             $('#f_date3').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});
//              var somedate2 = new Date();
//              $('#f_date2').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() <  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() <  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() < somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});

        
        //      2.以下為某一天之後的日期無法選擇
//              var somedate2 = new Date('2017-06-15');
//              $('#f_date1').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() >  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});


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