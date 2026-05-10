package murach.survey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SurveyListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String url = "/ch04.jsp";
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String dob = request.getParameter("DoB");
	
		String heardFrom = request.getParameter("heardFrom");
		String wantsUpdates = request.getParameter("wantsUpdates");
		if (wantsUpdates.length()>0) {
			wantsUpdates = "<br>Yes, want update";
		}
		String emailOk = request.getParameter("emailOk");
		if (emailOk.length()>0) {
			emailOk = "<br>Yes, please send me email announcements.";
		}
		
		String contactVia = request.getParameter("contactVia");
		if (contactVia.equals("both")) {
			contactVia = "You will contacted by Email and postal mail";
		}
		else if (contactVia.equals("Email")) {
			contactVia = "You will contacted by Email only";
		} else {
			contactVia = "You will contacted by postal mail only";
		}
		
		String thanks = 
				"Hi,  " + firstName + " " + lastName + "<br>" +
				"Email: " + email + "<br>" +
				"Date of Birth: " + dob + "<br><br>" +
				
				"You heard about us from " + heardFrom + "<br><br>" +
				"Would you like to receive announcements about new CDs and special offers? " + wantsUpdates + " " +
				" by " + emailOk + "<br><br>" +
				
				contactVia +"<br>";
		
		request.setAttribute("thanks", thanks);
		url = "/WEB-INF/chapter04/thanks.jsp";
		
		// forward request and response object to specific URL
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
}
