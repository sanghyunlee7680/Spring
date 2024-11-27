<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Book Market</a>
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/springBookMarket/home">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="/springBookMarket/books">Books</a></li>
				<li class="nav-item"><a class="nav-link" href="/springBookMarket/books/add">AddBooks</a></li>
				<li class="nav-item"><a class="nav-link" href="/springBookMarket/cart/">Cart</a></li>
			
				<li class="nav-item">
					<sec:authorize access="isAuthenticated()">
						<form:form action="${pageContext.request.contextPath}/logout" method="POST">
							<input type="submit" class="btn btn-success" value="Logout"/>
						</form:form>
					</sec:authorize>
				</li>
				<li class="nav-item">
					<sec:authorize access="!isAuthenticated()">
						<a class="nav-link" href="/springBookMarket/login">Login</a>
					</sec:authorize>
				</li>
			</ul>
		</div>
	</div>
</nav>