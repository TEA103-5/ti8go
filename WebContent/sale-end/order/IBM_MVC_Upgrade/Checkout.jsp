<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , shopping.BOOK"%>
<html>
<head>
 <title>Mode II 範例程式 - Checkout.jsp</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
 </head>
<body>
<img src="images/tomcat.gif"> <font size="+3">網路書店 - 結帳：（Checkout.jsp）</font>
<hr><p>

<table id="table-1" style="margin: auto;">
	<tr>
		<th width="200">書名</th>
		<th width="100">作者</th>
		<th width="100">出版社</th>
		<th width="100">價格</th>
		<th width="100">數量</th>
		<th width="120"><h3>總價</h3></th>
	</tr></table><table style="margin: auto;">

	<%  @SuppressWarnings("unchecked")
		Vector<BOOK> buylist = (Vector<BOOK>) session.getAttribute("shoppingcart");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			BOOK order = buylist.get(i);
			String name = order.getName();
			String author = order.getAuthor();
			String publisher = order.getPublisher();
			Double price = order.getPrice();
			Integer quantity = order.getQuantity();
	%>
	<tr>
		<td width="200"><%=name%>     </td>
		<td width="100"><%=author%>   </td>
		<td width="100"><%=publisher%></td>
		<td width="100"><%=price%>    </td>
		<td width="100"><%=quantity%> </td><td width="120"></td>
	</tr>
	<%
		}
	%>
	 
	
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font>
	    </td>
	</tr>
</table>
       
       
       <p><a href="EShop.jsp"><font size="+1"> 是 否 繼 續 購 物</font></a>

</body>
</html>