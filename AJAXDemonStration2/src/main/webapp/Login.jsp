<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<form action="LoginServlet" method="post">
<label for="user">username: </label>
<input type="text" id="user" name="username">
<br><br>
<label for="pwd">password: </label>
<input type="text" id="pwd" name="password">
<br><br>
<button>Login</button>
</form>
</body>
</html>