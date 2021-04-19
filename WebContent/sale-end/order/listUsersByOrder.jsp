<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <title>Blank Page - Brand</title>
 <%@ include file="/sale-end/pages/link.html" %> 
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
	width: 800px;
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

<body id="page-top">
    <div id="wrapper">
 <%@ include file="/sale-end/pages/nav.jsp" %> 
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
  <%@ include file="/sale-end/pages/fnav.html" %>
                <div class="container-fluid">
                    <h3 class="text-dark mb-1">Blank Page</h3>
                    
                  <table>
	<tr>
		<th>訂單編號</th>
		<th>訂單成立時間</th>
		<th>訂單狀態</th>
		<th>評價分數</th>
		<th>信用卡號</th>
		<th>購買者</th>
		<th>地址</th>
	</tr>
	<tr>
		<td>${ordVO.order_id}</td>
		<td>${ordVO.order_date}</td>
		<td>${ordVO.order_status}</td>
		<td>${ordVO.order_rate}</td>
		<td>${ordVO.card_number}</td>
		<td>${ordVO.users_id}</td>
		<td>${ordVO.order_address}</td>
	</tr> 
</table>  
                    
                </div>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"><span>Copyright © Brand 2021</span></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
    </div>
 <%@ include file="/sale-end/pages/script.html" %> 
</body>

</html>