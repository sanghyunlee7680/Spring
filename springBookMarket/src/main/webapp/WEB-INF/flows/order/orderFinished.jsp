<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<link href="/springBookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<head>
<meta charset="UTF-8">
<title>Thank you</title>
</head>
<body>
	<div class="container">
		<h2 class="alert alert-danger">주문해 주셔서 감사합니다.</h2>
		<p> 주문은
			<fmt:formatDate type="date" value="${order.shipping.date}" />
			에 배송될 예정입니다!
		</p>
		<p> 주문번호 : ${order.orderId} </p>
	</div>
	<div class="container">
		<p>
			<a href="/springBookMarket/books" class="btn btn-primary">&laquo; 도서목록</a>
		</p>
	</div>
</body>
</html>