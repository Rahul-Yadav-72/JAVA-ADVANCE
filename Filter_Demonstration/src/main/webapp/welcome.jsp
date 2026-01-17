<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page session="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome page</title>
</head>
<body>
<h2>Welcome page
${sessionScope.user }
</h2>
<a href="LogoutServlet">Logout</a>
</body>
</html>