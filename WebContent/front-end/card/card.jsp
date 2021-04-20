<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.card.model.*"%>
<%@ page import="com.users.model.*"%>
<%@ page import="com.card.controller.*"%>

<%
	CardService cardSvc = new CardService();
    List<CardVO> list = cardSvc.getAll();
    pageContext.setAttribute("list",list);
	
    UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
// 	未登入過，連進此頁，轉去登入頁，避免錯誤	
	if (usersVO == null) {
		session.setAttribute("location", request.getRequestURI());
		response.sendRedirect(request.getContextPath()+"/front-end/login.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
	    return;
	}
%>

<!DOCTYPE html>
<html lang="zh-Hant" dir="ltr">
  <head>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<%@ include file="/sale-end/pages/link.html"%>
  </head>
<body>
<!-- 頭段 -->
	<%@ include file="/front-end/pages/headNav.html" %>  
<!-- 中段     -->
	<main class="page landing-page">
		<div class="mx-auto col-8" style="margin: 100px">
			<div class="card bg-light">
				<div class="card-header">
					<h2 class="card-title pt-3">
						信用卡資料
					</h2>
				</div>
				<div class="card-body">	
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
			
		
				<div class="form-group form-row">
						<label for="users_id" class="col-sm-2">會員帳號</label>
						<div class="col-sm-6">
							<P>${usersVO.users_mail}</P>
						</div>
				</div>
					<table class="table">
					<thead>
						<tr>
							<th>信用卡編號</th>
							<th>信用卡號碼</th>
							<th>信用卡日期</th>
							<th>信用卡後三碼</th>
							<th></th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cardVO" items="${list}" >
							<c:if test="${usersVO.users_id == cardVO.users_id }">
				
							<tr class="card_list">
								<form METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" 
									style="margin-bottom: 0px;">
								<td><p class="para">${cardVO.card_id}</p>
								</td>
								<td><p class="para">${cardVO.card_number}</p>
										<input type="hidden" name="card_number" size="16"
											value="${cardVO.card_number}" class="card_up">
								</td>
								<td><p class="para">${cardVO.card_date}</p>
										<input name="card_date" id="f_date1" type="hidden" size="5"
											value="${cardVO.card_date}" class="card_up" >
								</td>
								<td><p class="para">${cardVO.card_last}</p>
										<input type="hidden" name="card_last" size="3"
											value="${cardVO.card_last}" class="card_up">
								</td>
								<td>
										<input type="hidden" name="card_default" value="1">
										<input type="hidden" name="action" value="update">
										<input type="hidden" name="card_id" value="${cardVO.card_id}">
										<input type="hidden" name="users_id" value="${usersVO.users_id}">
										<input type="hidden" class="card_up_btn" value="送出修改">
								</td>
<!-- 										<input type="submit" class="up_btn" value="送出修改"> -->
								</form>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" style="margin-bottom: 0px;">
								     <input type="submit" value="修改">
								     <input type="hidden" name="requestUrl" value="/front-end/card">
								     <input type="hidden" name="card_id"  value="${cardVO.card_id}">
								     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
								</td>
								<td>
									<form METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" 
										style="margin-bottom: 0px;">
								     <input type="submit" value="刪除">
								     <input type="hidden" name="requestUrl" value="/front-end/card">
								     <input type="hidden" name="card_id"  value="${cardVO.card_id}">
								     <input type="hidden" name="action" value="delete">
									</form>
								</td>
							</tr>
				
							
						</c:if>
					</c:forEach>				
				</tbody>
			</table>	
		</div>
	</div>	
	<div class="card-footer">
		<input type ="button" onclick="javascript:location.href='<%=request.getContextPath()%>/front-end/card/newcard.jsp'" value="新增信用卡"></input>
	</div>			
	
	</div>
	</main>
	<script type="text/javascript">
// 	var btn_up = document.getElementById("btu_update");
// 	btn_up.onclick = function() {
// 		console.log("TEST");
// // 		document.getElementsByClassName("card_td_2").setAttribute("type","text");
// 		document.getElementsByClassName("card_up1").type = "text";
// 	};
	
	$("#btu_update").on("click", function() {
		$(".card_up_btn").attr("type", "submit");
		$("#btu_update").hide();
		$(".card_up").attr("type" , "text");
	})
	</script>
</body>
</html>
					
					