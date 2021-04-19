<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	OrderService ordSvc = new OrderService(); //不需要錯誤驗證,直接呼叫Service
	List<OrderVO> list = ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>

<head>
<title>Blank Page - Brand</title>
<%@ include file="/sale-end/pages/link.html"%>
<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 88);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 55%;
	height: 55%;
	padding: 20px;
	border: 10px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
</head>

<body id="page-top">
	<div id="wrapper">
		<%@ include file="/sale-end/pages/nav.jsp"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="/sale-end/pages/fnav.html"%>
				<div class="container-fluid">




					<h3 class="text-dark mb-1">Blank Page</h3>
					<div id="app">



						<button v-on:click="selectadd()">新增</button>
						<br /> <br />


						<table>
							<tr>
								<th>訂單編號</th>
								<th>訂單成立時間</th>
								<th>訂單狀態</th>
								<th>信用卡號</th>
								<th>購買者編號</th>
								<th>地址</th>
								<th>刪除</th>
							</tr>

							<tr v-for="(item, index) in list">
								<td>{{item.order_id}}</td>
								<td>{{item.order_date}}</td>
								<td>{{item.order_status}}</td>
								<td>{{item.card_number}}</td>
								<td>{{item.users_id}}</td>
								<td>{{item.order_address}}</td>

								<td>
									<button v-on:click="remove(index)">刪除</button>
								</td>
								<td>
									<button href="javascript:void(0)" v-on:click="select(index)">
										修改</button>

								</td>

							</tr>

						</table>

						<div id="light" class="white_content">
							編號：<input type="text" v-model="updatename"><br /> 
							地址：<input type="text" v-model="updateage"><br />
							日期：<input type="text" v-model="xxx"><br />
							狀態：<input type="text" v-model="ooo"><br />
							卡號：<input type="text" v-model="ppp"><br />
							<button v-on:click="update()">修改</button>
							<button
								onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">取消</button>
						</div>


						<div id="lightadd" class="white_content">
							編號：<input type="text" v-model="updatename"><br /> 
							地址：<input type="text" v-model="updateage"><br />
							日期：<input type="text" v-model="xxx"><br />
							狀態：<input type="text" v-model="ooo"><br />
							卡號：<input type="text" v-model="ppp"><br />
							<button v-on:click="add()">新增</button>
							<button
								onclick="document.getElementById('lightadd').style.display='none';document.getElementById('fade').style.display='none'">取消</button>
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

	<div id="fade" class="black_overlay"></div>
	<%@ include file="/sale-end/pages/script.html"%>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>

var vm = new Vue({
    el: '#app',
    data: {
        name: '',
        xxx:"",
        ooo:"",
        ppp:"",
        age:'',
        updatename: '',
        updateage: '',
        updateindex:0,
        list: [
        	<c:forEach var="orderVO" items="${list}" >
        	{
        		order_id:'${orderVO.order_id}',
        		order_address:'${orderVO.order_address}',
        			order_date:'${orderVO.order_date}',
        			order_status:'${orderVO.order_status}',
        			card_number:	'${orderVO.card_number}',
        	},
        	</c:forEach>
       
        ]
    },
    methods: {
        add() {    	
            this.list.push({
            	order_id: this.name,
            	order_address: this.age,
                order_date:this.xxx,
                order_status:this.ooo,
                card_number:this.ppp	
            });
            document.getElementById('lightadd').style.display='none';
            document.getElementById('fade').style.display='none';
        },
        remove(index) {
            this.list.splice(index, 1);
        },
        select(index){
        	document.getElementById('light').style.display='block';
        	document.getElementById('fade').style.display='block';
        	this.updatename=this.list[index].order_id;
        	this.updateage=this.list[index].order_address;
        	this.xxx=this.list[index].order_date;
        	this.ooo=this.list[index].order_status;
        	this.ppp=this.list[index].card_number;
        	
        	this.updateindex=index;
        },
        update(){
        	this.list[this.updateindex].order_id=this.updatename;
        	this.list[this.updateindex].order_address=this.updateage;
        	this.list[this.updateindex].order_date=this.xxx;
        	this.list[this.updateindex].order_status=this.ooo;
        	this.list[this.updateindex].card_number=this.ppp;
        	document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none';
        },
        selectadd(){
        	document.getElementById('lightadd').style.display='block';document.getElementById('fade').style.display='block';
        }
    }
})

</script>
</body>

</html>