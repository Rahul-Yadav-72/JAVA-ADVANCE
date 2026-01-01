<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Form</title>
</head>
<body>

<h2>Employee Registration</h2>

<form action="insert" method="get">
    <label>Employee Name:</label><br>
    <input type="text" name="empname" required><br><br>

    <label>Mobile Number:</label><br>
    <input type="text" name="mobileno" required><br><br>
    <a href="select">View all data</a>

    <input type="submit" value="Submit">
</form>

</body>
</html>
