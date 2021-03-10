<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<jsp:useBean id="customer" scope="session" class="com.sale.model.SaleVO" />
<% //小心session在你對jsp做更新之後 瀏覽器沒關舊的session還在 這裡可能會讓你吃500 %>
<jsp:setProperty name="customer" property="sale_nickname" value="peter1"/>
<jsp:setProperty name="customer" property="sale_id" value="1"/>
<html>
<head><title>Test_useBean.jsp</title></head>
<body>

<h3>&lt;jsp:getProperty&gt; 取值</h3>
姓名：<jsp:getProperty name="customer" property="sale_nickname"/><br>
地址：<jsp:getProperty name="customer" property="sale_id"/>
<br>
<br>
<br>
<h3>&lt;%=bean.getXxxx()%&gt; 取值</h3>
姓名：<%=customer.getSale_nickname()%><br>
地址：<%=customer.getSale_id()%>

<br>
<br>
<h3>Tomcat5 開始新增(EL)的寫法-1</h3>
姓名：${customer.sale_nickname}<br>
地址：${customer.sale_id}
<br>
<br>
<br>
<h3>Tomcat5 開始新增(EL)的寫法-2</h3>
姓名：${customer["sale_nickname"]}<br>
地址：${customer["sale_id"]}
</body>
</html>