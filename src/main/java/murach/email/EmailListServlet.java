package murach.email;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import murach.business.User;

public class EmailListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String url = "/index.html";
		
		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "join";
		}
		
		// perform action and set URL to appropriate page
		if (action.equals("join")) {
			url = "/index.html";
		}
		else if (action.equals("add")) {
			// get parameters from request
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			// store data in User Object and save User object to database
			User user = new User(firstName, lastName, email);
			// UserDB.insert(user);
			
			// set User object in request object and set url
			request.setAttribute("user", user);
			url = "/WEB-INF/chapter02/thanks.jsp";
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
