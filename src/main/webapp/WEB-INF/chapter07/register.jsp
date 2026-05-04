<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlet and JSP</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
	
	<h1>Download Register</h1>
	
	<p>To register for our downloads, enter your name and email  
	   address below. Then click on the Submit button.</p>
	   
	<form action="download" method="post">
		<input type="hidden" name="action" value="registerUser">
		<label class="pad_top">Email:</label>
		<input type="email" name="email" value="${ user.email }"><br>
		<label class="pad_top">First Name:</label>
		<input type="text" name="firstName" value="${ user.firstName }"><br>
		<label class="pad_top">Last Name:</label>
		<input type="text" name="lastName" value="${ user.lastName }"><br>
		<label>&nbsp;</label>
		<input type="submit" name="register" value="Register" class="margin-left"><br>
	</form>
</body>
</html>