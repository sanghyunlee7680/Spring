<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.springmvc.domain.Book" %>
<%
	System.out.println("book.jsp 출력");
	Book book = new Book();
%>
<!DOCTYPE html>
<html>
<head>
<link href="/chapter2/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="/chapter2/resources/js/controllers.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/springBookMarket/home">Home</a>
			</div>
			<div class="navbar-header">
				<a class="navbar-brand" href="/springBookMarket/books">Books</a>
			</div>
			<div class="navbar-header">
				<a class="navbar-brand" href="/springBookMarket/books/add">AddBook</a>
			</div>
			<div class="navbar-header">
				<a class="navbar-brand" href="/springBookMarket/cart">Cart</a>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">도서 정보</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4">
			<%if(book.getBookImage()==null){ %>
				<img src="/springBookMarket/resources/images/${book.getBookId()}.png" style="width:100%"/>
			<%}else{ %>
				<img src="/springBookMarket/resources/images/${book.getBookImage().getOriginalFilename()}" style="width:100%"/>
			<% } %>
			</div>
			<div class="col-md-8">
				<h3>${book.name}</h3>
				<p>${book.description}</p>
				<br>
				<p><b>도서코드 : </b><span class="badge badge-info">${book.bookId}</span>
				<p><b>저자</b> : ${book.author}
				<p><b>출판사</b> : ${book.publisher}
				<p><b>출판일</b> : ${book.releaseDate}
				<p><b>분류</b> : ${book.category}
				<p><b>재고수</b> : ${book.unitsInStock}
				<h4>${book.unitPrice}원</h4>
				<br>
				<form:form name="addForm" method="put">
					<p><a href="javascript:addToCart('../cart/add/${book.bookId}')" class="btn btn-primary">도서주문 &raquo;</a>
					<a href="/springBookMarket/cart" class="btn btn-warning">장바구니 &raquo;</a>
					<a href="<c:url value="/books"/>" class="btn btn-secondary">도서 목록 &raquo;</a>
				</form:form>
			</div>
		</div>
		<hr>
		<footer>
			<p>&copy; BookMarket</p>
		</footer>
	</div>
</body>
</html>