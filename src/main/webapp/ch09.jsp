<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Murach's Java Servlet and JSP</title>
	<link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
	<h1>CD list</h1>
	<table>
		<tr>
			<th>Desccription</th>
			<th class="right">Price</th>
			<th>&nbsp;</th>
		</tr>
		<tr>
			<td>86 (the band) - True Life Songs and Pictures</td>
			<td class="right">$14.95</td>
			<td>
				<form action="cart" method="post">
					<input type="hidden" name="productCode" value="8601"/>
					<input type="hidden" name="quantity" value="1"/>
					<input type="submit" value="Add to Cart"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>Paddlefoot - The First CD</td>
			<td class="right">$12.95</td>
			<td>
				<form action="cart" method="post">
					<input type="hidden" name="productCode" value="pf01"/>
					<input type="hidden" name="quantity" value="1"/>
					<input type="submit" value="Add to Cart"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>Paddlefoot - The Second CD</td>
			<td class="right">$14.95</td>
			<td>
				<form action="cart" method="post">
					<input type="hidden" name="productCode" value="pf02"/>
					<input type="hidden" name="quantity" value="1"/>
					<input type="submit" value="Add to Cart"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>Joe Rut - Genuine Wood Grained Finish</td>
			<td class="right">$14.95</td>
			<td>
				<form action="cart" method="post">
					<input type="hidden" name="productCode" value="jr01"/>
					<input type="hidden" name="quantity" value="1"/>
					<input type="submit" value="Add to Cart"/>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>