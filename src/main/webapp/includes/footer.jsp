<%@ taglib prefix="mma" uri="/WEB-INF/murach.tld" %>
<%@ page import="java.util.GregorianCalendar, java.util.Calendar" %>
<%
	GregorianCalendar currentDate = new GregorianCalendar();
	int currentYear = currentDate.get(Calendar.YEAR);
%>
<p>
	&copy; Copyright <%= currentYear %> Mike Murach &amp; Associates<br>
	The current date is <mma:currentDate/>&nbsp;<mma:currentTime/>
	<mma:ifWeekday>
		<p>Live support available at 1-800-555-2222</p>
	</mma:ifWeekday>
</p>
</body>
</html>