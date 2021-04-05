<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.place.model.*" %>

<%
	PlaceVO placeVO = 	(PlaceVO)request.getAttribute("placeVO");//PlaceServlet.java (Concroller) 存入req的placeVO物件 (包括幫忙取出的placeVO, 也包括輸入資料錯誤時的placeVO物件)
%>

<html>
<head>
<meta charset="UTF-8">
<title>地點資料修改 - update_place_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>地點資料修改 - update_place_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
${not empty placeVO.place_id}

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="place.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>地點編號:<font color=red><b>*</b></font></td>
		<td><%=placeVO.getPlace_id()%></td>
	</tr>
	<tr>
		<td>地點名稱:</td>
		<td><input type="TEXT" name="place_name" size="45" 
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_name()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="place_address" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_address()%>" /></td>
	</tr>
	<tr>
		<td>經度:</td>
		<td><input type="TEXT" name="place_longitude" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_longitude()%>" /></td>
	</tr>
	<tr>
		<td>緯度:</td>
		<td><input type="TEXT" name="place_latitude" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_latitude()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="place_tel" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_tel()%>" /></td>
	</tr>
	<tr>
		<td>縣市:</td>
		<td><input type="TEXT" name="place_region" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_region()%>" /></td>
	</tr>
	<tr>
		<td>地點類型:</td>
		<td><input type="TEXT" name="place_type" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_type()%>" /></td>
	</tr>
	<tr>
		<td>地點簡介:</td>
		<td><input type="TEXT" name="place_index" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getPlace_index()%>" /></td>
	</tr>
	<tr>
		<td>圖片1:</td>
		<td><input id="p_file1" type="file" name="place_pic1" size="45"/>
			 <img id="pic1" width="300" height="225" src="DBGifReader4.do?place_pic=place_pic1&place_id=${placeVO.place_id}"></td>
	</tr>
	<tr>
		<td>圖片2:</td>
		<td><input id="p_file2" type="file" name="place_pic2" size="45" />
			 <img id="pic2" width="300" height="225" src="DBGifReader4.do?place_pic=place_pic2&place_id=${placeVO.place_id}"></td>
	</tr>
	<tr>
		<td>圖片3:</td>
		<td><input id="p_file3" type="file" name="place_pic3" size="45" />
			 <img id="pic3" width="300" height="225" src="DBGifReader4.do?place_pic=place_pic3&place_id=${placeVO.place_id}"></td>
	</tr>
	<tr>
		<td>地點狀態:</td>
		<td>
			<select size="1" name="place_state">
				<c:choose>
	            	<c:when test="${placeVO.place_state == 1}">
	                	<option selected="selected" value="1">上架中
						<option value="0">審核中
	           		</c:when>
	          		<c:otherwise>
	                 	<option value="1">上架中
						<option selected="selected" value="0">審核中
	            	</c:otherwise>
	        	</c:choose>
			</select>	
		</td>
	</tr>
	<tr>
		<td>建立者:</td>
		<td><input type="TEXT" name="users_id" size="45"
			 value="<%= (placeVO==null)? "" : placeVO.getUsers_id()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>營業時間:</td> -->
<!-- 		<td><input type="TEXT" name="business_time" size="45" -->
<%-- 			 value="<%= (placeVO==null)? "" : placeVO.getBusiness_time()%>" /></td> --%>
<!-- 	</tr> -->

<!-- 	未更改,這邊之後應該是用來放建立者的位置 -->
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
<input type="hidden" name="place_id" value="<%=placeVO.getPlace_id()%>">
<input type="submit" value="送出修改"></FORM>

<script>
	
// 	為何變數跟id同名時, 沒有getElementById , 監聽事件就可以直接使用元素id ???

	let pic1_el = document.getElementById("pic1");
	let p_file1_el = document.getElementById("p_file1");
	
	let pic2_el = document.getElementById("pic2");
	let p_file2_el = document.getElementById("p_file2");
	
	let pic3_el = document.getElementById("pic3");
	let p_file3_el = document.getElementById("p_file3");
	
	// 先將圖片修改前內容存起來
	let pic1_origin = pic1_el.getAttribute("src");
	let pic2_origin = pic2_el.getAttribute("src");
	let pic3_origin = pic3_el.getAttribute("src");
	

// target是代表對應的圖片標籤節點
	var preview_img = function(file , target) {
		var reader = new FileReader(); // 用來讀取檔案
		reader.addEventListener("load", function() {
			target.setAttribute("src", reader.result);
		});
		reader.readAsDataURL(file); // 讀取檔案
	};

	
	p_file1_el.addEventListener("change", function(e) {

		// 可接受的附檔名 , 如上傳例外檔案類型 , 則不讀取 , 預覽圖仍保持原樣
		var validExts = new Array(".jpg", ".png");

		var fileExt = this.value;
		fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
		if (validExts.indexOf(fileExt) < 0) {
			alert("檔案類型錯誤，可接受的副檔名有：" + validExts.toString());
			this.value = null;
			// 將修改前的圖片內容讀回去
			pic1_el.setAttribute("src" , pic1_origin);
			return;
		}

		if (this.files.length > 0) {
			preview_img(this.files[0] , pic1_el);
		} else {
		}
	});
	
	p_file2_el.addEventListener("change", function(e) {

		// 可接受的附檔名 , 如上傳例外檔案類型 , 則不讀取 , 預覽圖仍保持原樣
		var validExts = new Array(".jpg", ".png");

		var fileExt = this.value;
		fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
		if (validExts.indexOf(fileExt) < 0) {
			alert("檔案類型錯誤，可接受的副檔名有：" + validExts.toString());
			this.value = null;
			// 將修改前的圖片內容讀回去
			pic2_el.setAttribute("src" , pic2_origin);
			return;
		}

		if (this.files.length > 0) {
			preview_img(this.files[0] , pic2_el);
		} else {
		}
	});
	
	p_file3_el.addEventListener("change", function(e) {

		// 可接受的附檔名 , 如上傳例外檔案類型 , 則不讀取 , 預覽圖仍保持原樣
		var validExts = new Array(".jpg", ".png");

		var fileExt = this.value;
		fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
		if (validExts.indexOf(fileExt) < 0) {
			alert("檔案類型錯誤，可接受的副檔名有：" + validExts.toString());
			this.value = null;
			// 將修改前的圖片內容讀回去
			pic3_el.setAttribute("src" , pic3_origin);
			return;
		}

		if (this.files.length > 0) {
			preview_img(this.files[0] , pic3_el);
		} else {
		}
	});
	
</script>


</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>