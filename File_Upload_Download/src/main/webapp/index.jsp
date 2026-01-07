<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>File Upload & Download</title>
</head>
<body>

<h2>Upload File</h2>

<form action="Upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" required>
    <input type="submit" value="Upload">
</form>

<hr>

<h2>Download File</h2>
<a href="Download">View & Download File</a>

</body>
</html>
