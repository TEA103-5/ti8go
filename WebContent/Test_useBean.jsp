<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<jsp:useBean id="customer" scope="session" class="com.sale.model.SaleVO" />
<% //�p��session�b�A��jsp����s���� �s�����S���ª�session�٦b �o�̥i��|���A�Y500 %>
<jsp:setProperty name="customer" property="sale_nickname" value="peter1"/>
<jsp:setProperty name="customer" property="sale_id" value="1"/>
<html>
<head><title>Test_useBean.jsp</title></head>
<body>

<h3>&lt;jsp:getProperty&gt; ����</h3>
�m�W�G<jsp:getProperty name="customer" property="sale_nickname"/><br>
�a�}�G<jsp:getProperty name="customer" property="sale_id"/>
<br>
<br>
<br>
<h3>&lt;%=bean.getXxxx()%&gt; ����</h3>
�m�W�G<%=customer.getSale_nickname()%><br>
�a�}�G<%=customer.getSale_id()%>

<br>
<br>
<h3>Tomcat5 �}�l�s�W(EL)���g�k-1</h3>
�m�W�G${customer.sale_nickname}<br>
�a�}�G${customer.sale_id}
<br>
<br>
<br>
<h3>Tomcat5 �}�l�s�W(EL)���g�k-2</h3>
�m�W�G${customer["sale_nickname"]}<br>
�a�}�G${customer["sale_id"]}
</body>
</html>