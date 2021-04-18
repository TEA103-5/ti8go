<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM NoteC內容編輯: Home</title>

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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM NoteC內容編輯: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM NoteC內容編輯: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllNoteC.jsp'>查詢List</a> all NoteCs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="notec.do" >
        <b>輸入內編序號 (如1):</b>
        <input type="text" name="note_c_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="noteCSvc" scope="page" class="com.notec.model.NoteCService" />
   
  <li>
     <FORM METHOD="post" ACTION="notec.do" >
       <b>選擇內編序號:</b>
       <select size="1" name="note_c_id">
         <c:forEach var="noteCVO" items="${noteCSvc.all}" > 
          <option value="${noteCVO.note_c_id}">${noteCVO.note_c_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="notec.do" >
       <b>選擇副標題:</b>
       <select size="1" name="note_c_id">
         <c:forEach var="noteCVO" items="${noteCSvc.all}" > 
          <option value="${noteCVO.note_c_id}">${noteCVO.note_c_title}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>遊記內編管理</h3>

<ul>
  <li><a href='addNoteC.jsp'>新增Add</a> a new NoteC.</li>
</ul>

</body>
</html>