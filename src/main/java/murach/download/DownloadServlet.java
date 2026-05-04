package murach.download;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import murach.business.User;
import murach.data.UserIO;
import murach.util.CookieUtil;

public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		Date currentDate = new Date();
		request.setAttribute("currentDate", currentDate);
		
		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "viewAlbums";		// default action
		}
		
		// perform action and set URL to appropriate page
		String url = "/ch07.jsp";
		if (action.equals("viewAlbums")) {
			url = "/ch07.jsp";
		} 
		else if (action.equals("viewCookies")) {
			url = "/WEB-INF/chapter07/view_cookies.jsp";
		}
		else if (action.equals("deleteCookies")) {
			deleteCookies(request, response);
			url = "/WEB-INF/chapter07/register.jsp";
		}
		else {
			url = checkUser(request, response);
		}
		
		getOnlineUsers(request, response);
		
		// forward to the view
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		// perform action and set URL to appropriate page
		String url = "/ch07.jsp";
		if (action.equals("registerUser")) {
			url = registerUser(request, response);
		}
		
		getOnlineUsers(request, response);
		
		// forward to the view
		getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}

	private String checkUser(HttpServletRequest request, HttpServletResponse response) {
		
		String url = "";
		String productCode = request.getParameter("productCode");
		String emailAddress;
		HttpSession session = request.getSession();
		session.setAttribute("productCode", productCode);
		
		User user = null; 
		user = (User) session.getAttribute("user");
		
		if (user == null) {
			Cookie[] cookies = request.getCookies();
			emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
			
			// if cookie does not exist, go to register page
			if (emailAddress.equals("") || emailAddress == "" || emailAddress == null) {
				url = "/WEB-INF/chapter07/register.jsp";				
			}
			// if cookie exist, create User object and go to the download page 
			else {
				ServletContext sc = getServletContext();
				String path = sc.getRealPath("/WEB-INF/chapter07/EmailList.txt");
				user = UserIO.getUser(emailAddress, path);
				session.setAttribute("user", user);
				url = "/WEB-INF/chapter07/" + productCode + "_download.jsp";
			}
		}
		else {
			try {
				url = "/WEB-INF/chapter07/register.jsp";
				emailAddress = user.getEmail();
				
				ServletContext sc = getServletContext();
				String path = sc.getRealPath("/WEB-INF/chapter07/EmailList.txt");
				user = UserIO.getUser(emailAddress, path);
				
				// store the User object as a sesion attribute
				session = request.getSession();
				session.setAttribute("user", user);
				
				// add a cookie that stores the user's email to browser
				if (!emailAddress.equals("")) {
					Cookie c = new Cookie("emailCookie", emailAddress);
					c.setMaxAge(60 * 60 * 24 * 365 * 2);	// set age to 2 years
					c.setPath("/");
					response.addCookie(c);
					url = "/WEB-INF/chapter07/" + productCode + "_download.jsp";
				}
				else {
					deleteCookies(request, response);
				}
			}
			catch (Exception e) {
				// remove cookie
				deleteCookies(request, response);
				
				url = "/WEB-INF/chapter07/register.jsp";
			}
		}
		
		return url;
	}
	
	private String registerUser(HttpServletRequest request, HttpServletResponse response) {
		
		// get user data
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		// store the data in User object
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		// write the User Object to a file
		ServletContext sc = request.getServletContext();
		String path = sc.getRealPath("/WEB-INF/chapter07/EmailList.txt");
		if (!isExistingUser(request, response, email)) {
			UserIO.add(user, path);
		}
		
		// store the User object as a sesion attribute
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		// add a cookie that stores the user's email to browser
		if (!email.equals("")) {
			Cookie c = new Cookie("emailCookie", email);
			c.setMaxAge(60 * 60 * 24 * 365 * 2);	// set age to 2 years
			c.setPath("/");
			response.addCookie(c);
		}
		else {
			deleteCookies(request, response);
		}
		
		// create an return a URL for the appropriate Download page
		String productCode = (String) session.getAttribute("productCode");
		String url = "/WEB-INF/chapter07/" + productCode + "_download.jsp";
		
		if (productCode == null || productCode.equals("")) {
			return url = "/download?action=viewAlbums";
		}

		return url;
		
	}
	
	private void deleteCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("emailCookie")) {
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				
				HttpSession session = request.getSession();
				session.removeAttribute("user");
			}
		}
	}
	
	protected void getOnlineUsers(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServletContext sc = request.getServletContext();
		String path = sc.getRealPath("/WEB-INF/chapter07/EmailList.txt");
		
		ArrayList<User> users = null;
		users = UserIO.getUsers(path);
		session.setAttribute("onlineUsers", users);
	}
	
	@SuppressWarnings("unchecked")
	protected boolean isExistingUser(HttpServletRequest request, HttpServletResponse response, String email) {
		try {
			HttpSession session = request.getSession();
			ArrayList<User> userArrayList = (ArrayList<User>) session.getAttribute("onlineUsers");
			User[] users = (User[]) userArrayList.toArray();
			for (int i=0; i<users.length; i++) {
				if (users[i].getEmail().equals(email)) {
					return true;
				}
			}
			return false;
		}
		catch (Exception e) {
			return false;
		}
	}
}
