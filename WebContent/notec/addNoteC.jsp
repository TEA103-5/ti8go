<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.notec.model.*"%>

<%
  NoteCVO noteCVO = (NoteCVO) request.getAttribute("noteCVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>遊記內編資料新增 - addNoteC.jsp</title>

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
	width: 600px;
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
		 <h3>遊記內編資料新增 - addNoteC.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="notec.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>副標題:</td>
		<td><input type="TEXT" name="note_c_title" size="45" 
			 value="<%= (noteCVO==null)? "吳永志在宜蘭" : noteCVO.getNote_c_title()%>" /></td>
	</tr>
	<tr>
		<td>內容:</td>
		<td><input type="TEXT" name="note_c_content" size="45" 
			 value="<%= (noteCVO==null)? "羅東好開心好好玩好逛好吃,礁溪溫泉讚讚讚" : noteCVO.getNote_c_content()%>" /></td>
	</tr>
	<tr>
		<td>附加圖片:</td>
		<td><input type="FILE" class="upload" name="note_c_img" accept=image/*>
		  <td><img src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}" style="width:120px; height:120px"></td><br>
	</tr>
		

			<tr>
				<td>遊記序號:</td>


				<td>
				<jsp:useBean id="noteCSvc" scope="page"
						class="com.note.model.NoteService" />


					<FORM METHOD="post" ACTION="notec.do">
						<select size="1" name="note_id">
							<c:forEach var="noteCVO" items="${noteCSvc.all}">
								<option value="${noteCVO.note_id}">${noteCVO.note_id}
							</c:forEach>
						</select> <input type="hidden" name="action" value="insert">
						<input type="submit" value="送出">
					</FORM>
					
					</td>




			</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">

<input type="submit" value="送出新增"></FORM>
</body>

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script>
 $(window).on("load", function () {
  
  // 選擇圖片及時預覽
     $(document).on('change', 'input.upload', function (e) {
 
       var input_el = $(this);
       var img_el = input_el.parent().next().children('img');
       
       var file = e.target.files[0];
       var reader = new FileReader(); // 用來讀取檔案的物件
 
       // 點選取消，也是有觸發change事件，但file是undefined
       if (typeof(file) != 'undefined'){
        reader.readAsDataURL(file); // 讀取檔案
       }
 
       // 檔案讀取完成
       reader.addEventListener("load", function () {
       let img_el_change = '<img src="'+ reader.result + '" style="width:120px; height:120px">';
       
    img_el.replaceWith(img_el_change);
 
       })
 
     })

 })
   
</script>



</html>