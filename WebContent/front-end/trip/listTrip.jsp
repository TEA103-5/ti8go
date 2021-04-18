<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="com.trip_detail.model.*"%>
    <%@ page import="java.util.*"%>
<%@ page import="com.trip.model.*"%>

    <jsp:useBean id="tripSvc" scope="page" class="com.trip.model.TripService" />
<jsp:useBean id="usersSvc" scope="page" class="com.users.model.UsersService" />
    
    <%
    List<TripVO> list = tripSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>

<head>
    <title>ListTripByUsersId - font-end</title>
     <%@ include file="/front-end/pages/links.html" %> 
</head>

<body>

    <%@ include file="/front-end/pages/headNav.html" %>
    
    <main id="app" class="page catalog-page">
     
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">行程資料庫</h2>
                    <p></p>
                </div>
				<div class="content">
					 <div class="row">
					 <div class="col-md-12">
                            <div class="products">
                                <div  class="row no-gutters">
                                
                                
                                    <div v-for="(item,index) in tripList" class="col-12 col-md-6 col-lg-4">
                                        <div class="clean-product-item">
                                            <div class="image"><img class="img-fluid d-block mx-auto" 
                                           v-bind:src="item.trip_pic"></div>
                                            
                                            <div class="product-name"><h5>{{item.trip_name}}</h5>
                                          <FORM id="from2" METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
                                          
                                  
			  								<input type="hidden" name="trip_id" v-model="item.trip_id">
			    							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     							<input type="hidden" name="action" value="listDetail_ByTrip_A">
			     							<button @click="chose(item)">查看</button>
			     							</FORM>
                                            </div>
                                            
                                            
                                            <div class="product-name"><h10>出發日期:{{item.trip_start}}/天數:{{item.trip_day}}</h10></div>
                                            <div class="product-name">
                                            			  <FORM id="from1" METHOD="post" ACTION="<%=request.getContextPath()%>/trip/trip.do" style="margin-bottom: 0px;">
<!--                                             <button onclick="document.getElementById('from1').submit()">編輯</button> -->
			  								<input type="hidden" name="trip_id" v-model="item.trip_id">
			    							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     							<input type="hidden" name="action" value="listDetail_ByTrip_B"></FORM>
                                            
<!--                                             <button @click="delTrip(index,item.trip_id)">刪除</button>   -->
											</div>                                          
                                            <div class="about">
                                                <div class="price">
                                                    <h10>瀏覽次數:{{item.trip_look}}</h10>
                                                </div>
                                                <div class="price">
                                                    <h10>{{item.trip_username}}</h10>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                
                                
                                
                                </div>
                                </div>
                                </div>
					 </div>
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
	    	userid:'${usersVO.users_id}',
	    	tripList:[
	    		<c:forEach var="tripVO" items="${list}" >
					{
						trip_id:'${tripVO.trip_id}',
						trip_name:'${tripVO.trip_name}',
						trip_start:'${tripVO.trip_start}',
						trip_day:'${tripVO.place_weather}',						
						trip_look:${tripVO.trip_look},						
						trip_username:'${usersSvc.getOneusers(tripVO.users_id).users_name}',
						trip_state:'${tripVO.trip_state}',
						trip_pic:'<%=request.getContextPath()%>/place/DBGifReader4.do?place_id=${tripVO.read_authority}&place_pic=place_pic2',
						users_id:'${tripVO.users_id}',
						last_editor:'${tripVO.last_editor}',
						action:'updateLook',
					},
					</c:forEach>
	    	],
	    },
	    methods: {
	    	chose(item){
	    		if(this.userid!=item.users_id){
	    			console.log(item.trip_id);
	    		
	    			$.ajax({
	  			        url: "<%=request.getContextPath()%>/trip/trip.do",           // 資料請求的網址 --%>
	 			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
	 			        async: false,
	 			        data:item,               // 傳送資料到指定的 url
	 			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
	 			        success: function (data) {      //這裡依照陣列長度(有幾個行程明細)呼叫自己      
	 			        }
	 			    });
	    		}
	    		document.getElementById('from2').submit();
	    	},
	    	delTrip(index,trip_id){
	    	
	    		this.tripList.splice(index, 1);	
				let data={
						action:'delAjax',
						trip_id:trip_id,
				}
				$.ajax({
  			        url: "<%=request.getContextPath()%>/trip/trip.do",           // 資料請求的網址 --%>
 			        type: "POST",                  // GET | POST | PUT | DELETE | PATCH
 			        async: false,
 			        data:data,               // 傳送資料到指定的 url
 			        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
 			        success: function (data) {      //這裡依照陣列長度(有幾個行程明細)呼叫自己      
 			        }
 			    });
	    		
	    	},
	    },
	    computed:{
	    	
	    },
	    mounted: function(){//類似ini或onload
	    	},
	})
</script>
</body>

</html>