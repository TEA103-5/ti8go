<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<TITLE> 查詢資料 </TITLE>
</HEAD>
<BODY>
<%
            Context ctx = new javax.naming.InitialContext();
           DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tea05");
          	Connection con =  ds.getConnection();
          //	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcsample?serverTimezone=Asia/Taipei", "David", "123456");
            Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT * from place");
            
           ResultSetMetaData rsmd = rs.getMetaData();
	        int numberOfColumns = rsmd.getColumnCount();
%>

 <table border="1" bordercolor="blue">

   <tr> 
    <%for (int i = 1; i <= numberOfColumns; i++){%>
	         <th><%=rsmd.getColumnName(i)%></th>
	<%}%>      
   </tr>
   
   <% while (rs.next()) { %>
     <tr>
        <% for (int i = 1; i <= numberOfColumns; i++) { %> 
           <td><%=rs.getString(i)%></td>
        <% } %>
     </tr>
   <% } %>

 </table>

<%con.close(); %>

</BODY>
</HTML>
