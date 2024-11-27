<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="/springBookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6a14fe95598e35dd37cc0ae4d699b9d2"></script>
	<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
	<script src="/springBookMarket/resources/js/map.js"></script>
</body>
</html>