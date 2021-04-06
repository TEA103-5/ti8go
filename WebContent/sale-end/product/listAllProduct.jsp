<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
	ProductService empSvc = new ProductService();
	List<ProductVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="deptSvc" scope="page"
	class="com.sale.model.SaleService" />
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/fonts/fontawesome5-overrides.min.css">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/css/Bootstrap-DataTables.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="/TEA103G5/back-end/sale/assets/css/styles.css">

<style>
.img {
	width: 50px;
	height: 50px;
}
</style>
<title>所有商品資料 - listAllProduct.jsp</title>
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="/sale-end/pages/nav.html"%>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="/sale-end/pages/fnav"%>
				<div class="container-fluid">
					<div class="card rounded shadow border-0">
						<div class="card-body p-5 bg-white rounded">
							<div class="table-responsive">






								<table id="example" style="width: 100%"
									class="table table-bordered table-hover ">
									<thead class="thead-dark">
										<tr>

											<th>商品名稱</th>
											<th>商品分類</th>
											<th>商品價格</th>
											<th>商品庫存</th>
											<th>商品創建日期</th>
											<th>商品圖片</th>
											<th>販售者</th>
											<th>修改</th>
											<th>刪除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="empVO" items="${list}">

											<tr>
												<td>${empVO.product_name}</td>
												<td>${empVO.product_categories}</td>
												<td>${empVO.product_price}</td>
												<td>${empVO.product_stock}</td>
												<td><fmt:formatDate
														value="${empVO.product_update_time}" pattern="yyyy-MM-dd" /></td>

												<td><img class="img" id="preimg"
													src="<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p" /></td>
												<td><c:forEach var="deptVO" items="${deptSvc.all}">
														<c:if test="${empVO.sale_id==deptVO.sale_id}">【${deptVO.sale_name}】
                    									</c:if>
													</c:forEach></td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/product/prod.do"
														style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-outline-secondary btn-sm" value="修改"> <input
															type="hidden" name="empno" value="${empVO.product_id}">
														<input type="hidden" name="requestURL"
															value="<%=request.getServletPath()%>">
														<!--送出本網頁的路徑給Controller-->
														<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
														<input type="hidden" name="action"
															value="getOne_For_Update">
													</FORM>
												</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/product/prod.do"
														style="margin-bottom: 0px;">
														<input type="submit" class="btn btn-outline-secondary btn-sm" value="刪除"> <input
															type="hidden" name="empno" value="${empVO.product_id}">
														<input type="hidden" name="requestURL"
															value="<%=request.getServletPath()%>">
														<!--送出本網頁的路徑給Controller-->
														<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
														<input type="hidden" name="action" value="delete">
													</FORM>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>








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
	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>
	</div>



	<script src="/TEA103G5/back-end/sale/assets/js/jquery.min.js"></script>
	<script
		src="/TEA103G5/back-end/sale/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/chart.min.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/bs-init.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/theme.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
	<script src="/TEA103G5/back-end/sale/assets/js/Bootstrap-DataTables.js"></script>

</body>
</html>