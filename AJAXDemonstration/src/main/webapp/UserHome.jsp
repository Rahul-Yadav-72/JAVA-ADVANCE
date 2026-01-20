<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Home</title>
</head>
<body>
<h2>Welcome Page </h2>
<p>Welcome,$(sessionScope.user)</p>
<a href="LogoutServlet" >Logout</a>
</body>
</html>