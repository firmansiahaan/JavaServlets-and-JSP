<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:import url="includes/header.jsp"/>

<h1>Join to our email list</h1>
<p>To join our email list, enter your name and email address below.</p>

<c:if test="${ message != null }">
	<p><i>${message}</i></p>
</c:if>	

<form action="emailList" method="post">
	<input type="hidden" name="action" value="add"/>
	<table>
		<tr>
			<td><label class="pad_top" for="email">Email:</label></td>
			<td><input type="email" name="email" value="${user.email}" required/></td>
		</tr>
		<tr>
			<td><label class="pad_top" for="firstName">First Name:</label></td>
			<td><input type="text" name="firstName" value="${user.firstName}" required/></td>
		</tr>
		<tr>
			<td><label class="pad_top" for="lastName">Last Name:</label></td>
			<td><input type="text" name="lastName" value="${user.lastName}" required/></td>
		</tr>
		<tr>
			<td><label>&nbsp;</label></td>
			<td><input type="submit" value="Join Now" class="margin-left"/></td>
		</tr>
	</table>
</form>

<c:import url="includes/footer.jsp"/>