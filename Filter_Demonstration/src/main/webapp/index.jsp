<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<form action="LoginServlet" method="post">
<label for="name">Enter username:</label> 
<input type="text" name="username" id="name">
<br>
<label for="pws">Enter password:</label>
<input type="text" name="password" id="pws">
<br>
<input type="submit" value="login">

</form>

</body>
</html>