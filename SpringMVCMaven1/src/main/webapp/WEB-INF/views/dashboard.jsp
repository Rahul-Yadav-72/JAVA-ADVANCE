<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
<h2>Welcome, ${sessionScope.user }</h2>
<a href="${pageContext.request.contextPath }/logout"></a>
</body>
</html>