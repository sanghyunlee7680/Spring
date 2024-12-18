<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/springBookMarket/resources/css/bootstrap.min.css" rel="stylesheet">
<title>로그인</title>
</head>
<body>
	<div class="container col-md-4">
		<div class="text-center">
			<h1 class="form-signin-heading">Please login</h1>
		</div>
		<%	
			String error = (String)request.getAttribute("error");
			if(error!=null){
		%>
			<div class="alert alert-danger">
				UserName과 Password가 올바르지 않습니다.<br/>
			</div>
		<% 	} %>
		<form class="form-signin" action="login" method="post">
			<div class="form-group row">
				<input type="text" name="username" class="form-control" placeholder="User Name" required autofocus>
			</div>
			<div class="form-group row">
				<input type="password" name="password" class="form-control" placeholder="Password" required>
			</div>
			<div class="form-group row">
				<button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>
	</div>
</body>
</html>