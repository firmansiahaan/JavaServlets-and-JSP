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
	
	<h1>Your cart</h1>
	
	<table>
		<tr>
			<th>Quantity</th>
			<th>Description</th>
			<th>Price</th>
			<th>Amount</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${cart.items}">
			<tr>
			<td>
				<form action="" method="post">
					<input type="hidden" name="productCode" value="<c:out value='${item.product.code}'/>"/>
					<input type="text" name="quantity" value="<c:out value='${item.quantity}'/>" class="quantity" id="quantity" />
					<input type="submit" value="Update" />
				</form>
			</td>
			<td><c:out value='${item.product.description}'/></td>
			<td><c:out value='${item.product.priceCurrencyFormat}'/></td>
			<td><c:out value='${item.totalCurrencyFormat}'/></td>
			<td>
				<form action="" method="post">
					<input type="hidden" name="productCode" value="<c:out value='${item.product.code}'/>"/>
					<input type="hidden" name="quantity" value="0"/>
					<input type="submit" value="Remove Item" />
				</form>
			</td>
			</tr>	
		</c:forEach>
	</table>
	
	<p>
		<b>To change the quantity</b>, enter the quantity and click on Update button.
	</p>
	
	<form action="" method="post">
		<input type="hidden" name="action" value='shop'/>
		<input type="submit" value='Continue Shopping'/>
	</form>
	
	<form action="" method="post">
		<input type="hidden" name="action" value='checkout'/>
		<input type="submit" value='Checkout'/>
	</form>
</body>
</html>