<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.springmvc.domain.Book" %>
<!DOCTYPE html>
<%
	System.out.println("books.jsp 이동");
	Book book = new Book();
	String images = request.getServletContext().getRealPath("resources/images");
%>
<html>
<head>
<link href="/springBookMarket/resources/css/bootstrap.min.css" rel="stylesheet"/>
<meta charset="UTF-8">
<title>도서 목록</title>
</head>
<body>
	<div class="container">
		<div class="row" align="center">
			<c:forEach items="${bookList}" var="book">
				<div class="col-md-4">
				<%
					if(book.getBookImage()==null){
				%>
						<img src="/springBookMarket/resources/images/${book.fileName}" style="width:60%"/>
				<%  }else{%>
						<img src="/springBookMarket/resources/images/${book.fileName}" style="width:60%"/>
				<%  }%>
					<h3>${book.name}</h3>
					<p>${book.author}
						<br>${book.publisher} | ${book.releaseDate}
					<p align="left">${fn:substring(book.description, 0, 100)}...
					<p>${book.unitPrice}원
					<p><a href="<c:url value="/books/book?id=${book.bookId}"/>"
					   class="btn btn-Secondary" role="button">상세정보 &raquo;</a>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>