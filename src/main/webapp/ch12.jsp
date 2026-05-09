<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlet and JSP - SQL Gateway</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css"/>
	</head>
<body>
	<c:if test="${sqlStatement == null }">
		<c:set var="sqlStatement" value="Select * from User" />
	</c:if>
	
	<h1>The SQL Gateway</h1>
	<p>Enter an SQL Statement and click the Execute button.</p>
	
	<p><b>SQL statement:</b></p>
	<form action="sqlGateway" method="post">
		<textarea rows="8" cols="60" name="sqlStatement">${sqlStatement}</textarea>
		<input type="submit" value="Execute"/>
	</form>
	
	<p><b>SQL result:</b><br>
	${sqlResult}
	
</body>
</html>