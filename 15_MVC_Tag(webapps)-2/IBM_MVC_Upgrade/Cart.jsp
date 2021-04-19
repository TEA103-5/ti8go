<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.util.* , shopping.BOOK" %>
<html>
<head>
 <title>Mode II 範例程式  - Cart.jsp</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/ShoppingCart.css">
</head>
<body><br>
   <% @SuppressWarnings("unchecked")
   Vector<BOOK> buylist = (Vector<BOOK>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="images/tomcat.gif"> <font size="+3">目前購物車的內容如下：（Cart.jsp）</font>

<table id="table-1">
    <tr> 
      <th width="200">書名</th><th width="100">作者</th><th width="100">出版社</th><th width="100">價格</th>
      <th width="120">數量</th><th width="120"><img src="images/view-cart.png"></th>
    </tr></table><table>

	<%
	 for (int index = 0; index < buylist.size(); index++) {
		BOOK order = buylist.get(index);
	%>
	<tr>
		<td width="200"><%=order.getName()%>     </td>
		<td width="100"><%=order.getAuthor()%>   </td>
		<td width="100"><%=order.getPublisher()%></td>
		<td width="100"><%=order.getPrice()%>    </td>
		<td width="120"><%=order.getQuantity()%> </td>
		
        <td width="120">
          <form name="deleteForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action"  value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪 除" class="button">
          </form></td>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action"  value="CHECKOUT"> 
              <input type="submit" value="付款結帳" class="button">
          </form>
<%}%>
</body>
</html>