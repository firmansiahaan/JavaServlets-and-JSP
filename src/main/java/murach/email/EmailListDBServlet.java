package murach.email;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.mail.MessagingException;
import murach.business.User;
import murach.data.sql.UserDB;
import murach.util.MailUtilGmail;


public class EmailListDBServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String url = "/ch14.jsp";
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
			url = "/ch14.jsp";
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
			if (UserDB.EmailExists(user.getEmail())) {
				message = "This email address already exist.<br>" + 
						"Please enter another email address.";
				url = "/ch14.jsp";
			} else {
				message = "";
				url = "/WEB-INF/chapter14/thanks.jsp";
				UserDB.insert(user);
			}
			
			// set User object in request object and set url
			request.setAttribute("user", user);
			request.setAttribute("message", message);
			
			// send email to user
            String to = email;
            String from = "john.stamos@gmail.com";
            String subject = "Welcome to our email list";
            String body = "Dear " + firstName + ",\n\n" +
                "Thanks for joining our email list. We'll make sure to send " +
                "you announcements about new products and promotions.\n" +
                "Have a great day and thanks again!\n\n" +
                "Kelly Slivkoff\n" +
                "Mike Murach & Associates";
            boolean isBodyHTML = false;

            try
            {
                MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
//                MailUtilGmail.sendMail(to,from,subject,body,isBodyHTML);
            }
            catch (MessagingException e)
            {
                String errorMessage = 
                    "ERROR: Unable to send email. " + 
                        "Check Tomcat logs for details.<br>" +
                    "NOTE: You may need to configure your system " + 
                        "as described in chapter 14.<br>" +
                    "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                this.log(
                    "Unable to send email. \n" +
                    "Here is the email you tried to send: \n" +
                    "=====================================\n" +
                    "TO: " + email + "\n" +
                    "FROM: " + from + "\n" +
                    "SUBJECT: " + subject + "\n" +
                    "\n" +
                    body + "\n\n");
            }
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
