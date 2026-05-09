package murach.email.jsf;


import jakarta.annotation.PostConstruct;
import murach.business.jpa.User;
import murach.data.jpa.UserDB;

public class EmailList {
	private User user;
	private String message;
	
	public EmailList() { }
	
	@PostConstruct
	public void init() {
		user = new User();
		message ="Hello!";
	}

	public String addToEmailList() {
		if (UserDB.emailExists(user.getEmail())) {
			message = "This email address already exist. Please enter another email address.";
			return "index";
		}
		else {
			UserDB.insert(user);
			return "thanks";
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}
	
}
