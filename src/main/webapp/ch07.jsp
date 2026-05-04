<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlet and JSP</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

	<jsp:useBean  id="user" class="murach.business.User" scope="session"/>
	<c:choose>
		<c:when test='${user.firstName == ""}'>
			<p>User: <strong><i>You must <a href="download?action=checkUser&amp;productCode=">register</a> download first</i></strong></p>
		</c:when>
		<c:otherwise>
			<h2>User: ${user.firstName}&nbsp;${user.lastName}</h2>
		</c:otherwise>
	</c:choose>
	
	<h1>List of Albums</h1>
	
	<p>
	<a href="download?action=checkUser&amp;productCode=8601">
		86 (the band) - True Life Songs and Pictures
	</a><br>
	
	<a href="download?action=checkUser&amp;productCode=pf01">
		Paddlefoot - The First CD
	</a><br>
		
	<a href="download?action=checkUser&amp;productCode=pf02">
		Paddlefoot - The Second CD
	</a><br>
			
	<a href="download?action=checkUser&amp;productCode=jr01">
		Joe Rut - Genuine Wood Grained Finish
	</a><br>
	
	</p>
</body>
</html>