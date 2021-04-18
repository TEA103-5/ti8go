<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM card: Home</title>

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
   <tr><td><h3>IBM card: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM card: Home</p>

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
  <li><a href='listAllCard.jsp'>List</a> all card.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="card.do" >
        <b>輸入信用卡編號 (如1):</b>
        <input type="text" name="card_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="cardSvc" scope="page" class="com.card.model.CardService" />
   
  <li>
     <FORM METHOD="post" ACTION="card.do" >
       <b>選擇信用卡編號:</b>
       <select size="1" name="card_id">
         <c:forEach var="cardVO" items="${cardSvc.all}" > 
          <option value="${cardVO.card_id}">${cardVO.card_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="card.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="card_id">
         <c:forEach var="cardVO" items="${cardSvc.all}" > 
          <option value="${cardVO.card_id}">${cardVO.users_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>卡片管理</h3>

<ul>
  <li><a href='addCard.jsp'>Add</a> a new Card.</li>
</ul>

</body>
</html>