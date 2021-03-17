<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.place.model.*" %>

<%
	PlaceVO placeVO = 	(PlaceVO)request.getAttribute("placeVO");//PlaceServlet.java (Concroller) 存入req的placeVO物件 (包括幫忙取出的placeVO, 也包括輸入資料錯誤時的placeVO物件)
%>
<%= placeVO==null %>--{}


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>