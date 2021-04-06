<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>tea05 Place: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor="white">

	<table id="table-1">
		<tr>
			<td><h3>tea05 Place: Home</h3>
				<h4>( MVC )</h4>
			</td>
		</tr>
	</table>

	<p>This is the Home page for tea05 Place: Home</p>

	<h3>資料查詢:</h3>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>

<!-- 		<li><a href="/Tivago_Git/place_collect/select_page.jsp">進入地點收藏.</a>  <br> <br></li> -->

		<li><a href="listAllPlace.jsp">List</a> all Place. <br> <br></li>
		
		
		<li>
			<FORM METHOD="post" ACTION="place.do">
				<b>輸入地點編號 (如1):</b> <input type="text" name="place_id"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="placeSvc" scope="page" class="com.place.model.PlaceService" />

		<li>
			<FORM METHOD="post" ACTION="place.do">
				<b>選擇地點編號 :</b> 
				<select size="1" name="place_id">
					<c:forEach var="placeVO" items="${placeSvc.all}" >
						<option value="${placeVO.place_id}">${placeVO.place_id}
					</c:forEach>
				</select> 
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="place.do">
				<b>選擇地點名稱:</b> 
				<select size="1" name="place_id">
					<c:forEach var="placeVO" items="${placeSvc.all}">
						<option value="${placeVO.place_id}">${placeVO.place_name}
					</c:forEach>
				</select> 
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		
		<li>
			<FORM METHOD="post" ACTION="place.do">
				<b>輸入地址跟地點名稱進行模糊查詢 :</b> 
				<input type="text" name="county" placeholder="請輸入縣市名稱"> 
				<input type="text" name="district" placeholder="請輸入區域名稱"> 
				<input type="text" name="place_name" placeholder="請輸入地點名稱" > 
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
				<input type="hidden" name="action" value="getCard"> 
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	
	
	<h3>地點管理</h3>
	
	<ul>
		<li><a href="addPlace.jsp">Add</a> a new Place.</li>
	</ul>
	
	



</body>
</html>