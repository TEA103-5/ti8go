<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Blank Page - Brand</title>
<%@ include file="pages/link.html"%>
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
		<%@ include file="pages/nav.html"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="pages/fnav.html"%>
				<div class="container-fluid">




					<h3 class="text-dark mb-1">Blank Page</h3>
					<div id="app">



						<button v-on:click="selectadd()">新增</button>
						<br />
						<input type="text" v-model="searchName">
						 <br />


						<table>
							<tr>
								<th>姓名</th>
								<th>年齡</th>
								<th>刪除</th>
							</tr>

							<tr v-for="(item, index) in filterlist" >
								<td>{{index}}</td>
								<td>{{ item.name }}</td>
								<td>{{ item.age }}</td>
								<td>
									<button v-on:click="remove(index)">刪除</button>
								</td>
								<td>
									<button href="javascript:void(0)" v-on:click="select(index)">
										修改</button>

								</td>

							</tr>

						</table>
						<button @click="setOrderType(1)">年齡升序</button>
						<button @click="setOrderType(2)">年齡降序</button>
						<button @click="setOrderType(0)">原本順序</button>
						<div id="light" class="white_content">
							姓名：<input type="text" v-model="updatename"> <br /> 年齡：<input
								type="text" v-model="updateage"><br />
							<button v-on:click="update()">修改</button>
							<button
								onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">取消</button>
						</div>
						<div id="lightadd" class="white_content">
							姓名：<input type="text" v-model="name"> <br /> 年齡：<input
								type="text" v-model="age"><br />
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
	<%@ include file="pages/script.html"%>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
	<script>

var vm = new Vue({
    el: '#app',
    data: {
    	searchName:'',
    	orderType:0,//0原本1升2降
        name: '',
        age: '',
        updatename: '',
        updateage: '',
        updateindex:-1,
        fflist:[
        	{ name: 'aaa', age: 25 },
        	],
        list: [
            { name: 'aaa', age: 25 },
            { name: 'abbb', age: 26 },
            { name: 'accc', age: 27 },
            { name: 'bd', age: 28 },
            { name: 'be', age: 29 },
            { name: 'bf', age: 30 },
            { name: 'ccg', age: 31 },
            { name: 'ccch', age: 32 },
            { name: 'cci', age: 33 },
            { name: 'cccj', age: 34 },
        ]
    },
    methods: {
    	setOrderType(t){
    		this.orderType=t;
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
    	filterlist () {
    		const {searchName,list,orderType} = this;
    		let self=this;
    		let flist;
//     		console.log(this.list.slice(1,this.list.length));
//     		flist = list.filter(p => true)
    		flist = list.filter(p => p.name.indexOf(searchName)!==-1)
    		
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
    		self.fflist=flist.slice();
    		console.log(self.fflist);
    		return flist
    	}
    }
})

</script>
</body>

</html>