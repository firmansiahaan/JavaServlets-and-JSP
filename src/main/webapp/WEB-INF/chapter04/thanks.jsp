<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlets and JSP</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
	<div><img src="images/murachlogo.jpg" alt="Murach Logo" width="100" height="100"></div>
	<h1>Thanks for surveys.jsp</h1>
	
	<p>${thanks}</p>
	
	<p>To enter another survey, click on the Back
	button in your browser or the Return button shown
	below.</p>
	
	<form action="index_ch04.html" method="get">
		<input type="hidden" name="action" value="join">
		<input type="submit" value="Return">
	</form>
</body>
</html>