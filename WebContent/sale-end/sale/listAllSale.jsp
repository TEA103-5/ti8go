<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sale.model.*"%>

<%
	SaleService empSvc = new SaleService();
	List<SaleVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>

<title>所有販售者資料 - ListAllSale.jsp</title>
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
											<th>姓名</th>
											<th>暱稱</th>
											<th>頭像</th>
											<th>評分</th>
											<th>創辦時間</th>
											<th>修改</th>
											<th>刪除</th>
											<th>販售商品</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="saleVO" items="${list}">

											<tr>


												<td>${saleVO.sale_name}</td>
												<td>${saleVO.sale_nickname}</td>


												<td><img class="img rounded"
													src="<%=request.getContextPath()%>/DBGifReader4_1?id=${saleVO.sale_id}" />
												</td>

												<td>${saleVO.sale_rate}</td>
												<td><fmt:formatDate value="${saleVO.sale_time_create}"
														pattern="yyyy-MM-dd" /></td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/sale/sale.do"
														style="margin-bottom: 0px;">
														<button type="submit"
															class="btn btn-outline-secondary btn-sm">修改</button>
														<input type="hidden" name="empno"
															value="${saleVO.sale_id}"> <input type="hidden"
															name="action" value="getOne_For_Updatejsp">
													</FORM>
												</td>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/sale/sale.do"
														style="margin-bottom: 0px;">
														<button type="submit"
															class="btn btn-outline-secondary btn-sm">刪除</button>
														<input type="hidden" name="empno"
															value="${saleVO.sale_id}"> <input type="hidden"
															name="action" value="delete">
													</FORM>
												</td>
												<td>

													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/sale/sale.do"
														style="margin-bottom: 0px;">
														<button type="submit"
															class="btn btn-outline-secondary btn-sm">送出查詢</button>

														<input type="hidden" name="deptno"
															value="${saleVO.sale_id}"> <input type="hidden"
															name="action" value="listEmps_ByDeptno_B">
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