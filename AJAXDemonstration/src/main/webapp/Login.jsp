<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page </title>
</head>
<body>
<form action="UserHome.jsp" method="post">

  <label for="user">User Name :</label>
  <input type="text" id="user" name="username">
  <br><br>

  <label for="psw">Password :</label>
  <input type="password" id="psw" name="password">
  <br><br>

  <button type="submit">Login</button>

</form>

</body>
</html>