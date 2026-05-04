package murach.email;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import murach.business.User;
import murach.data.UserDB;


public class EmailListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String url = "/ch05.jsp";
		User user;
		String message;
		
		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "join";
		}
		
		// perform action and set URL to appropriate page
		if (action.equals("join")) {
			user = new User();
			message = "";
			url = "/ch05.jsp";
			request.setAttribute("user", user);
			request.setAttribute("message", message);
		}
		else if (action.equals("add")) {
			// get parameters from request
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			// store data in User Object and save User object to database
			user = new User(firstName, lastName, email);
			
			// validate the parameters
			if (firstName == null || lastName == null || email == null ||
					firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
				message = "Please fill all three text boxes.";
				url = "/ch05.jsp";
			} else {
				message = "";
				url = "/WEB-INF/chapter05/thanks.jsp";
				UserDB.insert(user);
			}
			
			// set User object in request object and set url
			request.setAttribute("user", user);
			request.setAttribute("message", message);
		}
		
		// forward request and response object to specific URL
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doPost(request, response);
	}
}
