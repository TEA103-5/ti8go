<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav
	class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
	<div class="container-fluid d-flex flex-column p-0">
		<a
			class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0"
			href="#">
			<div class="sidebar-brand-icon rotate-n-15">
				<img  style="width:3rem"  src= "<%=request.getContextPath()%>/BLOB_Reader?id=4" >
			</div>
			<div class="sidebar-brand-text mx-3">
				<span>中文</span>
<!-- 				https://www.ip138.com/utf8/ -->
			</div>
		</a>
		<hr class="sidebar-divider my-0">
		<ul class="navbar-nav text-light" id="accordionSidebar">
			<li class="nav-item"><a class="nav-link active"
				href="<%=request.getContextPath()%>/sale-end/saleEndIndex.jsp"><i
					class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/sale-end/sale/login.jsp"><i
					class="far fa-user-circle"></i><span>&#x767B;&#x5165;</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/sale-end/sale/updateSale.jsp"><i
					class="fas fa-user"></i><span>&#x500B;&#x4EBA;&#x8CC7;&#x6599;&#x8A2D;&#x5B9A;</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/sale-end/sale/listAllSale.jsp"><i
					class="fas fa-table"></i><span>&#x6240;&#x6709;&#x8CA9;&#x552E;&#x8005;</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/sale-end/product/listAllProduct.jsp"><i
					class="fas fa-table"></i><span>&#x6240;&#x6709;&#x5546;&#x54C1;</span></a></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sale-end/product/addProduct.jsp"><i
					class="fas fa-window-maximize"></i><span>&#x65B0;&#x589E;&#x5546;&#x54C1;</span></a></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sale-end/product/listProductsBySale.jsp"><i
					class="fas fa-window-maximize"></i><span>&#x6211;&#x7684;&#x5546;&#x54C1;</span></a></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sale-end/order/listAllOrder.jsp"><i
					class="fas fa-window-maximize"></i><span>所有訂單</span></a></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sale-end/order/addOrder.jsp"><i
					class="fas fa-window-maximize"></i><span>新增訂單</span></a></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sale-end/orderDetail/addOrderDetail.jsp"><i
					class="fas fa-window-maximize"></i><span>新增訂單明細</span></a></li>
			<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/sale-end/orderDetail/listAllOrderDetail.jsp"><i
					class="fas fa-window-maximize"></i><span>所有訂單明細</span></a></li>


	


		</ul>
		<div class="text-center d-none d-md-inline">
			<button class="btn rounded-circle border-0" id="sidebarToggle"
				type="button"></button>
		</div>
	</div>
</nav>