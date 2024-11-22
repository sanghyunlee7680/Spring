<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<script src="/chapter2/resources/js/map.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/chapter2/home">Home</a>
			</div>
			<div class="navbar-header">
				<a class="navbar-brand" href="/chapter2/books">Books</a>
			</div>
			<div class="navbar-header">
				<a class="navbar-brand" href="/chapter2/books/add">AddBook</a>
			</div>
			<div class="navbar-header">
				<a class="navbar-brand" href="/chapter2/cart">Cart</a>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">${greeting}</h1>
		</div>
	</div>
	<div class="container">
		<div class="text-center">
			<h3>${strapline}</h3>
		</div>
	</div>
	<div id="map" style="width:500px;height:400px;">
	</div>
		<p>
		   <button onclick="zoomIn()">-</button>
		   <button onclick="zoomOut()">+</button>
		    <span id="maplevel"></span>
		</p>
	<footer class="container">
		<hr>
		<p>&copy; WebMarket</p>
	</footer>
	<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6a14fe95598e35dd37cc0ae4d699b9d2"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
</body>
</html>