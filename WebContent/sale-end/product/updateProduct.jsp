<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO empVO = (ProductVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html>

<head>
<title>商品資料修改 - update_prod_input.jsp</title>
<%@ include file="/sale-end/pages/link.html"%>
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
				<%@ include file="/sale-end/pages/fnav.html"%>
				<div class="container-fluid">




					<h3 class="text-dark mb-1">商品資料修改</h3>

					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/product/prod.do"
						name="form1" enctype="multipart/form-data">
						<table>
							<tr>
								<td><img class="img" id="preimg"
									src="<%=request.getContextPath()%>/DBGifReader4_1?id=${empVO.product_id}&id2=p" /></td>
								<td><input type="file" id="file1" accept="image/*"
									name="upfile1"></td>
							</tr>
							<tr>
								<td>商品編號:<font color=red><b>*</b></font></td>
								<td><%=empVO.getProduct_id()%></td>
							</tr>
							<tr>
								<td>商品名稱:</td>
								<td><input type="TEXT" name="name" size="45"
									value="<%=empVO.getProduct_name()%>" /></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="form-group">
										<div class="custom-control custom-switch">
											<input class="custom-control-input" type="checkbox"
												<c:if test="${empVO.product_status!=0}">checked</c:if>
												id="formCheck-1" name="stat1"> <label
												class="custom-control-label" for="formCheck-1"><strong>上架狀態</strong></label>
										</div>
									</div>
								</td>
							</tr>

							<tr>
								<td>商品內容:</td>
								<td><input type="TEXT" name="cont" size="45"
									value="<%=empVO.getProduct_content()%>" /></td>
							</tr>
							<tr>
								<td>商品簡短描述:</td>
								<td><input type="TEXT" name="desc" size="45"
									value="<%=empVO.getProduct_description()%>" /></td>
							</tr>
							<tr>
								<td>商品分類:</td>
								<td><input type="TEXT" name="cate" size="45"
									value="<%=empVO.getProduct_categories()%>" /></td>
							</tr>
							<tr>
								<td>商品價格:</td>
								<td><input type="TEXT" name="pric" size="45"
									value="<%=empVO.getProduct_price()%>" /></td>
							</tr>
							<tr>
								<td>商品庫存:</td>
								<td><input type="TEXT" name="stoc" size="45"
									value="<%=empVO.getProduct_stock()%>" /></td>
							</tr>



							<jsp:useBean id="deptSvc" scope="page"
								class="com.sale.model.SaleService" />
							<tr>
								<td>販售者:<font color=red><b>*</b></font></td>
								<td><select size="1" name="saleid">
										<c:forEach var="deptVO" items="${deptSvc.all}">
											<option value="${deptVO.sale_id}"
												${(empVO.sale_id==deptVO.sale_id)?'selected':'' }>${deptVO.sale_name}
										</c:forEach>
								</select></td>
							</tr>

						</table>
						<br> <input type="hidden" id="pic" name="picc" value="T">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="empno" value="<%=empVO.getProduct_id()%>">
						<input type="hidden" name="requestURL"
							value="<%=request.getParameter("requestURL")%>"> <input
							type="hidden" name="whichPage"
							value="<%=request.getParameter("whichPage")%>">
						<!--只用於:istAllEmp.jsp-->
						<input type="submit" value="送出修改">
					</FORM>



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
	<%@ include file="/sale-end/pages/script.html"%>

	<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
	<script>
		$("#file1").on('change', function(e) {
			const file = this.files[0];

			const fr = new FileReader();
			fr.onload = function(e) {
				$("#preimg").attr('src', e.target.result);
				$("#pic").val("F");
			};

			// 使用 readAsDataURL 將圖片轉成 Base64
			fr.readAsDataURL(file);
		});
	</script>
</body>

</html>