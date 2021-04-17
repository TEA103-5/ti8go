<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%
    Object account = session.getAttribute("account");                  // 從 session內取出 (key) account的值
    if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
      session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
      response.sendRedirect(request.getContextPath()+"/login.html");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入
      return;
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>select_page2_protected.jsp</title>
</head>
<body>

	<br>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			 <td>   
			       <h3> 保護的頁面 - select_page2_protected.jsp      </h3> 
				     <h3> 歡迎:<font color=red> ${account} </font>您好 </h3>
			 </td>
		</tr>
	</table>
	<b> <br>
	<br>                以下留空....
	</b>
	
</body>
</html>
