<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlets and JSP</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
	<h1>Java Error</h1>
	<p>Sorry, Java has thrown an exception.</p>
	<p>To continue, click the back button</p>
	
	<h2>Details</h2>
	<p>Type: {pageContext.exception["class"]}</p>
	<p>Message: {pageContext.exception.message}</p>
</body>
</html>