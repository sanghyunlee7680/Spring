<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/chapter2/resources/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>예외 처리</title>
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
			<h2 class="alert alert-danger">
				해당 도서가 존재하지 않습니다.<br>
				도서 ID : ${invalidBookId}
			</h2>
		</div>
	</div>
	<div class="container">
		<p>${url}</p>
		<p>${exception}</p>
	</div>
	<div class="container">
		<p>
			<a href="/books" class="btn btn-secondary">도서목록 &raquo;</a>
		</p>
	</div>
</body>
</html>