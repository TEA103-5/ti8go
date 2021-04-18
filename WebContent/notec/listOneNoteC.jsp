<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.notec.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  NoteCVO noteCVO = (NoteCVO) request.getAttribute("noteCVO");
%>

<html>
<head>
<title>遊記資料 - listOneNote.jsp</title>

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
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>遊記內編資料 - ListOneNoteC.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>內編序號</th>
		<th>副標題</th>
		<th>內容</th>
		<th>附加圖片</th>
		<th>遊記序號</th>
	</tr>
	<tr>
		<td><%=noteCVO.getNote_c_id()%></td>
		<td><%=noteCVO.getNote_c_title()%></td>
		<td><%=noteCVO.getNote_c_content()%></td>
		<td><img src="<%=request.getContextPath()%>/DBGifReaderNoteC?note_c_id=${noteCVO.note_c_id}" style="width:120px; height:120px"></td>
		<td><%=noteCVO.getNote_id()%></td>

	</tr>
</table>

</body>
</html>