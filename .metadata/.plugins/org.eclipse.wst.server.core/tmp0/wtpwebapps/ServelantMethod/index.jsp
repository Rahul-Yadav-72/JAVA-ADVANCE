<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Refresh File .</h1>

<servlet>
<servlet-name>Method</servlet-name>
    <servlet-class>ServelantMethod.Method</servlet-class>

    <init-param>
        <param-name>configname</param-name>
        <param-value>MyServletConfig</param-value>
    </init-param>


</servlet>

<context-param>
    <param-name>contextname</param-name>
    <param-value>MyContextValue</param-value>
</context-param>

</body>
</html>