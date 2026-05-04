<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="murach.business.User" %>
<%@ page import="murach.data.UserIO" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlet and JSP</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
	<h2><c:out value="Current Date: ${currentDate}"/></h2>
	<h2><c:out value='Hello, ${user["firstName"]}'/></h2>
	<h1>Available Downloads for you:</h1>
	
	<h2>86 (the band) - True Life Songs and Pictures</h2>
	
	<table>
		<tr>
			<th>Song title</th>
			<th>Audio Format</th>
		</tr>
		<tr>
			<td>You are the Star</td>
			<td><a href="sound/${productCode}/star.mp3">MP3</a></td>
		</tr>
		<tr>
			<td>Don't make No Difference</td>
			<td><a href="sound/${productCode}/no_different.mp3">MP3</a></td>
		</tr>
	</table>
	
	<p><a href="download?action=viewAlbums">View list of albums</a></p>

	<p><a href="download?action=viewCookies">View all cookies</a></p>
	
	<h2>Online users:</h2>
	<c:forEach var="onlineUser" items="${onlineUsers}">
		<c:set var="firstName" value="${onlineUser.firstName}" scope="page" />
		<c:set var="lastName" value="${onlineUser.lastName}" scope="page" />
		<c:set var="email" value="${onlineUser.email}" scope="page" />
		<c:out value="${firstName} ${lastName} - (${email})"/><br>
	</c:forEach>
		
	<p>&nbsp;</p>
	
	<p>
		<h2>Implicit pageContext:</h2>
		HTTP Request Method: ${pageContext.request.method}<br>
		HTTP Response Type: ${pageContext.response.contentType}<br>
		HTTP Session ID: ${pageContext.session.id}<br>
		HTTP ServletContext Path: ${pageContext.servletContext.contextPath}<br>
	</p>	
</body>
</html>