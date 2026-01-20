<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<script type="text/javascript">
function loadUser(){
	var xhr=new XMLHTTPRequest();
	xhr.open("GET","ActivateUserServlet",true);
	xhr.onreadystatechange=function(){
		if(xhr.readyState === 4 && xhr.status===2000){
			document.getElementById("users").inneerHtml=xhr.responseText;
		}
	};
	xhr.send();
}
setInterval(loadUsers,3000)
</script>
</head>
<body onload="loadUser()">
<h2>Admin Panel</h2>
<h3>Currently activate user</h3>
<ul>id="users"</ul>
</body>
</html>