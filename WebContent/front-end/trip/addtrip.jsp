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
					<button @click="sendDetailEditToList">確認</button>
			

					<button onclick="document.getElementById('setTripDetail').style.display='none';document.getElementById('fade').style.display='none';">取消</button>
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
				 
				 
					<div class="col-md-5 col-xl-5 mb-5 conn" style="height:500px;overflow-y: scroll;">
					<ul v-for="(item,index) in daylist" class="list-group list-group-flush footers" style="border-radius: 2rem;">
						 <li class="list-group-item footers">
							<table class="table-users">
												<tr>				
										<th>
										 D{{index+1}}
										</th>
								</tr>
								<tr v-for="(item, inde) in daylist[index].tripDetail" >
			
									<td>
									
									</td>
									<td>	
									{{item.trip_content}}<br/>
									{{item.trip_start_time}}~{{item.trip_end_time}}<br/>
									   {{item.trip_remarks}}
									</td>
									<td>
								  
									</td>
									
									<td>
									<button @click="">插入</button>
									</td>
									<td>
									<button @click="tripDetailEdit(item,inde)">edit</button>
									</td>
									<td>
									<button @click="tripDetailDel(inde)">del</button>
									</td>
								</tr>
					</table>
					</li>
						<li class="list-group-item">
						新增:
<!-- 						choicePlaceToDetail -->
						<button @click="choicePlaceToDetail(index)">地點</button>
						<button onclick="document.getElementById('choicePlace').style.display='block';document.getElementById('fade').style.display='block';">其它</button>
						</li>
								  
					</ul>
					

					</div>
					

						 
					 <div class="col-md-2 col-xl-2 mb-2 conn">

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
			sendDetailEditToList(){
				
				
				
				
				this.tripDetail.trip_start_time=$('#time1').val()+':00';
				this.tripDetail.trip_end_time=$('#time2').val()+':00';
			//	this.tripDetaillist[this.tripDetail.indexOfList]=this.tripDetail;
				
				
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
		    		this.tripDetail.indexOfList=0,
		
				
				document.getElementById('setTripDetail').style.display='none';
				document.getElementById('fade').style.display='none';
				
				
			},
			tripDetailEdit(item,index){
				this.tripDetail=item;
				this.tripDetail.indexOfList=index;
				$('#time1').val(item.trip_start_time);
				$('#time2').val(item.trip_end_time);
			},
			tripDetailDel(index){
				this.tripDetaillist.splice(index, 1);
			},
			tripDetailAdd(e){
	            document.getElementById('choicePlace').style.display='none';
	            document.getElementById('setTripDetail').style.display='block';
	            document.getElementById('fade').style.display='block';
	            this.tripDetail.place_name=e.place_name;
	            this.tripDetail.trip_content=e.place_name;
	            this.tripDetail.place_id=e.place_id;
// 	            this.tripDetaillist.push({
// 		    		trip_day:'1',
// 		    		place_id:e.place_id,
// 		    		trip_id:'1',
// 		    		trip_sort:1,
// 		    		trip_detail_type:'其他',
// 		    		trip_content:'',
// 		    		trip_start_time:'',
// 		    		trip_end_time:'',
// 		    		trip_remarks:'無',
// 		    		trip_cost:'0',
// 		    		action:'insertajax',
// 		    		place_name:e.place_name,
// 		    		indexOfList:0,
// 	            });
// 	            console.log(this.tripDetaillist.length);
			},
			submitTripDetail(){//行程細節送出 目前以submitTrip()觸發
				let self=this;
					
				console.log(self.detailUpdateCount);
				if(self.detailUpdateCount>=1){
					self.detailUpdateCount--;
					self.tripDetaillist[self.detailUpdateCount].trip_id=self.theTrip_id;
				$.ajax({
			        url: "<%=request.getContextPath()%>/trip_detail/trip_detail.do",           // 資料請求的網址
			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
			        async: false,
			        data: self.tripDetaillist[self.detailUpdateCount],               // 傳送資料到指定的 url
			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
			        success: function (data) {      //這裡依照陣列長度(有幾個行程明細)呼叫自己
			          console.log(data);
			      	self.submitTripDetail();        
			        }
			    });
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