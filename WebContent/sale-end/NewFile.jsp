<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>點選文字彈出一個DIV層視窗程式碼</title>
<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 88);
}

.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 55%;
	height: 55%;
	padding: 20px;
	border: 10px solid orange;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
</head>
<body>
	<p>
		示例彈出層：<a href="javascript:void(0)"
			onclick="document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">請點這裡</a>
	</p>
		<p>
		2示例彈出層：<a href="javascript:void(0)"
			onclick="document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'">請點這裡</a>
	</p>
	<div id="light" class="white_content">
		這是一個層視窗示例程式. <a href="javascript:void(0)"
			onclick="document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">點這裡關閉本視窗</a>
	</div>
	<div id="light2" class="white_content">
		2這是一個層視窗示例程式. <a href="javascript:void(0)"
			onclick="document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'">點這裡關閉本視窗</a>
	</div>
	
	<div id="fade" class="black_overlay"></div>
</body>
</html>