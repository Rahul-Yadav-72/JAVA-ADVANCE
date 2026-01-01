<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
    }
    .login-box {
        width: 350px;
        margin: 120px auto;
        padding: 20px;
        background: white;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2 {
        text-align: center;
    }
    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border-radius: 4px;
        border: 1px solid #ccc;
    }
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #45a049;
    }
    .error {
        color: red;
        text-align: center;
    }
</style>
</head>

<body>

<%
    if (session != null && session.getAttribute("loginhistory") != null) {
        response.sendRedirect("WelcomeServlet");
        return;
    }
%>

<div class="login-box">
    <h2>Login</h2>

    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <div class="error">Invalid Username or Password</div>
    <%
        }
    %>

    <form action="LogInServlet" method="post">
        <input type="text" name="uname" placeholder="Username" required autocomplete="off">
        <input type="password" name="pwd" placeholder="Password" required>
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
