<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:import url="includes/header.jsp"/>

<form action="surveyList" method="post">
	<h1>Survey</h1>
    <p>If you have a moment, we'd appreciate it if you would fill out this survey.</p>
            
    <h2>Your information:</h2>
            
	<table>
		<tr>
			<td><label class="pad_top" for="firstName">First Name:</label></td>
			<td><input type="text" name="firstName" value="${user.firstName}" required/></td>
		</tr>
		<tr>
			<td><label class="pad_top" for="lastName">Last Name:</label></td>
			<td><input type="text" name="lastName" value="${user.lastName}" required/></td>
		</tr>
		<tr>
			<td><label class="pad_top" for="email">Email:</label></td>
			<td><input type="email" name="email" value="${user.email}" required/></td>
		</tr>
		<tr>
			<td><label>Date of Birth</label></td>
			<td><input type="text" name="DoB"></td>
		</tr>
	</table>
			
    <h2>How did you hear about us?</h2>
    <p><input type=radio name="heardFrom" value="Social Media">Social Media
    <input type=radio name="heardFrom" value="Search Engine" checked>Search engine
    <input type=radio name="heardFrom" value="Friend">Word of mouth
    <input type=radio name="heardFrom" value="Other">Other</p>

    <h2>Would you like to receive announcements about new CDs and special offers?</h2>
    	<p><input type="checkbox" name="wantsUpdates" >YES, I'd like that.</p>
        <p><input type="checkbox" name="emailOk" >YES, please send me email announcements.</p>
            
        <p>Please contact me by:
	        <select name="contactVia">
	        	<option value="Both" selected>Email or postal mail</option>
	            <option value="Email">Email only</option>
	            <option value="Postal Mail">Postal mail only</option>
	        </select>
        </p>

    <input type=submit value="Submit">
</form>

<c:import url="includes/footer.jsp"/>